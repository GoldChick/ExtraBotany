package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.common.ModItems;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.sound.SoundEvent;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.handler.ModSounds;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.List;
import java.util.function.Consumer;


public class ShadowKatana extends SwordItem implements ILensEffect, ICustomDamageItem
{
    public static final int MANA_PER_DAMAGE = 2;

    public ShadowKatana(Properties prop)
    {
        super(Tiers.WOOD, -1, 0, prop);
    }

    public static InteractionResult attackEntity(Player player, InteractionHand hand, ItemStack stack)
    {
        if (hand == InteractionHand.MAIN_HAND
                && stack.getItem() == ModItems.SHADOW_KATANA.get()
                && stack.getDamageValue() == 0)
        {
            EntityManaBurst burst = getBurst(player, player.getMainHandItem());
            player.level.addFreshEntity(burst);
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            stack.setDamageValue(stack.getDamageValue() + 50);
            //player.getMainHandItem().hurtAndBreak(100, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        }
        return InteractionResult.PASS;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        int manaPerDamage = ((ShadowKatana) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    public int getManaPerDamage()
    {
        return MANA_PER_DAMAGE;
    }

    public static void trySpawnBurst(Player player, float attackStrength)
    {
        EntityManaBurst burst = getBurst(player, player.getMainHandItem());
        player.level.addFreshEntity(burst);
        player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
    }

    public static EntityManaBurst getBurst(Player player, ItemStack stack)
    {
        EntityManaBurst burst = new EntityManaBurst(player);

        float motionModifier = 20F;
        burst.setColor(0x7B68EE);
        burst.setMana(400);
        burst.setStartingMana(400);
        burst.setMinManaLoss(32);
        burst.setManaLossPerTick(400F);
        burst.setGravity(0F);
        burst.setDeltaMovement(burst.getDeltaMovement().scale(motionModifier));

        burst.setSourceLens(stack.copy());
        return burst;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props, Level level)
    {
    }

    @Override
    public boolean collideBurst(IManaBurst burst, HitResult pos, boolean isManaBlock, boolean shouldKill, ItemStack stack)
    {
        return shouldKill;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack)
    {
        ThrowableProjectile entity = burst.entity();
        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        Entity thrower = entity.getOwner();

        for (LivingEntity living : entities)
        {
            if (living == thrower
                    || living instanceof Player livingPlayer
                    && thrower instanceof Player throwingPlayer
                    && !throwingPlayer.canHarmPlayer(livingPlayer))
            {
                continue;
            }
            int mana = burst.getMana();
            if (mana > 0)
            {
                burst.setMana(0);
                if (!burst.isFake() && entity.level.isClientSide)
                {
                    Vec3 delta = new Vec3(living.getX() - thrower.getX(), 0, living.getZ() - thrower.getZ()).normalize();
                    thrower.setPos(new Vec3(living.getX() + 3 * delta.x, living.getY(), living.getZ() + 3 * delta.z));
                    // i dont know 180 or -180 is limit, which may cause crash
                    if (thrower.getYRot() > 0)
                    {
                        thrower.setYRot(thrower.getYRot() - 180);
                    } else
                    {
                        thrower.setYRot(thrower.getYRot() + 180);
                    }
                    entity.discard();
                    break;
                }
                if (!entity.level.isClientSide)
                {
                    //thrower.level.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 1F);
                    entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 1F);
                    //entity.level.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 1F);
                }
            }
        }
    }
    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack)
    {
        return true;
    }
}
