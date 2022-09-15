package chick.extrabotany.common.base;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.entities.projectile.relic_projectile.RelicProjectileBase;
import chick.extrabotany.common.tools.others.ElementSteelShield;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.handler.EquipmentHandler;

import java.util.List;
import java.util.stream.Collectors;

public final class DamageHandler
{
    public static final DamageHandler INSTANCE = new DamageHandler();

    public final int BYPASS_ARMOR = 1,
            BYPASS_MAGIC = 1 << 1,
            BYPASS_ABSORB = 1 << 2,
            BYPASS_INVUL = 1 << 3,
            SCALE_WITH_DIFFICULTY = 1 << 4,
            FIRE = 1 << 5,
            MAGIC = 1 << 6,
            PROJECTILE = 1 << 7,
            EXPLOSION = 1 << 8,
            NO_AGGRO = 1 << 9,
            FALL = 1 << 10,
            CREATIVE = 1 << 12;

    /**
     * 判断是否可行,source为player并且佩戴和平友好之证的时候无法对友好生物（不extend Mob的）造成伤害
     * 两个玩家都佩戴和平友好之证的时候无法互相造成伤害
     */
    public boolean checkPassable(Entity target, Entity source)
    {
        if (target == source || target.isRemoved())
            return false;
        if (source instanceof Player sourcePlayer)
        {
            boolean sourceEquipped = !EquipmentHandler.findOrEmpty(ModItems.PEACE_AMULET.get(), sourcePlayer).isEmpty();
            if (target instanceof Player targetPlayer)
            {
                return !sourceEquipped && EquipmentHandler.findOrEmpty(ModItems.PEACE_AMULET.get(), targetPlayer).isEmpty();
            }
            return !sourceEquipped || target instanceof Mob;
        }
        return true;
    }

    /**
     * 使用和平友好之证过滤
     */
    public List<LivingEntity> getFilteredEntities(List<LivingEntity> entities, Entity source)
    {
        return entities.stream().filter((living) -> checkPassable(living, source) && !living.isRemoved()).collect(Collectors.toList());
    }

    /**
     * 将计算的伤害加上读取的Attributes.ATTACK_DAMAGE（
     */
    public static float calcDamage(float orig, Player player)
    {
        if (player == null)
            return orig;
        double value = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return (float) (orig + value);
    }

    /**
     * BYPASS_ARMOR = 1<P>
     * BYPASS_MAGIC = 1 << 1<P>
     * BYPASS_ABSORB = 1 << 2<P>
     * BYPASS_INVUL = 1 << 3<P>
     * SCALE_WITH_DIFFICULTY = 1 << 4<P>
     * FIRE = 1 << 5<P>
     * MAGIC = 1 << 6<P>
     * PROJECTILE = 1 << 7<P>
     * EXPLOSION = 1 << 8<P>
     * NO_AGGRO = 1 << 9<P>
     * FALL = 1 << 10<P>
     * CREATIVE = 1 << 12
     */
    public boolean doDamage(Entity target, DamageSource s, float amount, int type)
    {
        if (target == null || target.isRemoved())
            return false;
        if (s.getEntity() != null && !checkPassable(target, s.getEntity()))
            return false;
        return hurt(target, s, amount, type);
    }

    /**
     * @param item   Nullable, need source non-null
     * @param source Nullable, too
     *               <ul>
     *               <li>BYPASS_ARMOR = 1
     *               <li>BYPASS_MAGIC = 1 << 1
     *               <li>BYPASS_ABSORB = 1 << 2
     *               <li>BYPASS_INVUL = 1 << 3
     *               <li>SCALE_WITH_DIFFICULTY = 1 << 4
     *               <li>FIRE = 1 << 5
     *               <li>MAGIC = 1 << 6
     *               <li>PROJECTILE = 1 << 7
     *               <li>EXPLOSION = 1 << 8
     *               <li>NO_AGGRO = 1 << 9
     *               <li>FALL = 1 << 10
     *               <li>CREATIVE = 1 << 12</ul>
     */
    public boolean doDamage(Entity target, @Nullable Entity item, @Nullable Entity source, float amount, int type)
    {
        DamageSource s;
        if (source instanceof Player player)
        {
            s = DamageSource.playerAttack(player);
        } else
        {
            if (source instanceof LivingEntity mob)
            {
                s = (item == null) ? DamageSource.mobAttack(mob) : DamageSource.indirectMobAttack(item, mob);
            } else
            {
                s = new DamageSource("generic");
            }
        }
        return doDamage(target, s, amount, type);
    }

    private boolean hurt(Entity target, DamageSource source, float amount, int type)
    {
        if ((type & BYPASS_ARMOR) > 0)
            source.bypassArmor();
        if ((type & BYPASS_MAGIC) > 0)
            source.bypassMagic();
        float absorbNum = 0;
        if ((type & BYPASS_ABSORB) > 0 && target instanceof Player player)
        {
            absorbNum = player.getAbsorptionAmount();
            player.setAbsorptionAmount(0);
        }
        if ((type & BYPASS_INVUL) > 0)
        {
            target.invulnerableTime = 0;
            source.bypassInvul();
        }
        if ((type & SCALE_WITH_DIFFICULTY) > 0)
            source.setScalesWithDifficulty();
        //TODO: THIS MAY CAUSE WRONG IN PVP MODE
        if ((type & FIRE) > 0)
            source.setIsFire();
        if ((type & MAGIC) > 0)
            source.setMagic();
        if ((type & PROJECTILE) > 0)
            source.setProjectile();
        if ((type & EXPLOSION) > 0)
            source.setExplosion();
        if ((type & NO_AGGRO) > 0)
            source.setNoAggro();
        if ((type & FALL) > 0)
            source.setIsFall();
        if ((type & CREATIVE) > 0)
            source.bypassInvul();

        //Manasteel Shield
        //This only block Projectiles in Extrabotany!
        if (target instanceof LivingEntity living)
        {
            float blockedDmg;
            if (!living.level.isClientSide && amount > 0.0F && isDamageSourceBlocked(living, source))
            {
                final float originalAmount = amount;
                net.minecraftforge.event.entity.living.ShieldBlockEvent ev = net.minecraftforge.common.ForgeHooks.onShieldBlock(living, source, amount);
                if (!ev.isCanceled())
                {
                    if (ev.shieldTakesDamage() && living instanceof Player player)
                        hurtCurrentlyUsedShield(player, amount);
                    blockedDmg = ev.getBlockedDamage();
                    amount -= ev.getBlockedDamage();

                    living.level.broadcastEntityEvent(living, (byte) 29);
                    if (target instanceof ServerPlayer spTarget)
                    {
                        CriteriaTriggers.ENTITY_HURT_PLAYER.trigger(spTarget, source, originalAmount, amount, true);
                        if (blockedDmg > 0.0F)
                        {
                            (spTarget).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_BLOCKED_BY_SHIELD), Math.round(blockedDmg * 10.0F));
                        }
                    }

                    if (source.getEntity() instanceof ServerPlayer spSource)
                    {
                        CriteriaTriggers.PLAYER_HURT_ENTITY.trigger(spSource, target, source, originalAmount, amount, true);
                    }
                }
            }
        }
        //do damage here
        boolean result = amount > 0 && target.hurt(source, amount);
        if ((type & BYPASS_ABSORB) > 0 && target instanceof Player player && !target.isRemoved())
        {
            player.setAbsorptionAmount(absorbNum);
        }
        return result;
    }

    public boolean isDamageSourceBlocked(LivingEntity target, DamageSource source)
    {
        Entity entity = source.getDirectEntity();
        boolean flag = !(entity instanceof RelicProjectileBase);

//TODO:如何block更好
        if (target.isBlocking() && target.getUseItem().getItem() instanceof ElementSteelShield && !flag)
        {
            Vec3 vec32 = source.getSourcePosition();
            if (vec32 != null)
            {
                Vec3 vec3 = target.getViewVector(1.0F);
                Vec3 vec31 = vec32.vectorTo(target.position()).normalize();
                vec31 = new Vec3(vec31.x, 0.0D, vec31.z);
                return vec31.dot(vec3) < 0.0D;
            }
        }
        return false;
    }

    private void hurtCurrentlyUsedShield(Player target, float dmg)
    {

        if (target.getUseItem().canPerformAction(net.minecraftforge.common.ToolActions.SHIELD_BLOCK))
        {
            if (!target.level.isClientSide && target instanceof ServerPlayer sp)
            {
                sp.awardStat(Stats.ITEM_USED.get(target.getUseItem().getItem()));
            }

            int i = 1 + Mth.floor(dmg);
            InteractionHand interactionhand = target.getUsedItemHand();
            target.getUseItem().hurtAndBreak(i, target, (player) ->
            {
                player.broadcastBreakEvent(interactionhand);
                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(target, target.getUseItem(), interactionhand);
            });
            if (target.getUseItem().isEmpty())
            {
                target.setItemSlot(interactionhand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                target.stopUsingItem();
                target.playSound(SoundEvents.SHIELD_BREAK, 0.8F, 0.8F + target.level.random.nextFloat() * 0.4F);
            }
        }
    }
}
