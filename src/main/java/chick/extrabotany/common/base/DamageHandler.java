package chick.extrabotany.common.base;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.handler.EquipmentHandler;

import java.util.List;
import java.util.stream.Collectors;

public final class DamageHandler
{
    public static final DamageHandler INSTANCE = new DamageHandler();

    public final int LIFE_LOSING = 4;

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
     * 造成伤害（考虑和平友好之证）
     */
    public boolean dmg(Entity target, Entity source, float amount, int type)
    {
        switch (type)
        {
            case LIFE_LOSING ->
            {
                if (!(target instanceof LivingEntity living))
                    return false;
                float trueHealth = Math.max(0F, living.getHealth() - amount);
                if (trueHealth <= 0)
                {
                    if (source instanceof Player player)
                        living.die(DamageSource.playerAttack(player));
                    else if (source instanceof LivingEntity livingEntity)
                        living.die(DamageSource.mobAttack(livingEntity));
                    if (living.getHealth() > 0)
                        living.setHealth(-1F);
                } else
                {
                    living.setHealth(trueHealth);
                }
                return true;
            }
        }
        return false;
    }

    public boolean doDamage(Entity target, DamageSource s, float amount, boolean bypassInvulnerable, int type)
    {
        if (target == null || target.isRemoved())
            return false;
        if (s.getEntity() != null && !checkPassable(target, s.getEntity()))
            return false;
        if (bypassInvulnerable)
        {
            target.invulnerableTime = 0;
        }
        return hurt(target, s, amount, type);
    }

    public boolean doDamage(Entity target, DamageSource s, float amount, int type)
    {
        if (target == null || target.isRemoved())
            return false;
        if (s.getEntity() != null && !checkPassable(target, s.getEntity()))
            return false;
        return hurt(target, s, amount, type);
    }

    public boolean doDamage(Entity target, @Nullable Entity item, Entity source, float amount, boolean bypassInvulnerable, int type)
    {
        DamageSource s;
        if (source instanceof Player player)
        {
            s = DamageSource.playerAttack(player);
        } else
        {
            var mob = (LivingEntity) source;
            s = (item == null) ? DamageSource.mobAttack(mob) : DamageSource.indirectMobAttack(item, mob);
        }
        return doDamage(target, s, amount, bypassInvulnerable, type);
    }

    private boolean hurt(Entity target, DamageSource s, float amount, int type)
    {
        if ((type & BYPASS_ARMOR) > 0)
            s.bypassArmor();
        if ((type & BYPASS_MAGIC) > 0)
            s.bypassMagic();
        float absorbNum = 0;
        if ((type & BYPASS_ABSORB) > 0 && target instanceof Player player)
        {
            absorbNum = player.getAbsorptionAmount();
            player.setAbsorptionAmount(0);

        }
        if ((type & BYPASS_INVUL) > 0)
            target.invulnerableTime = 0;
        if ((type & SCALE_WITH_DIFFICULTY) > 0)
            s.setScalesWithDifficulty();
        if ((type & FIRE) > 0)
            s.setIsFire();
        if ((type & MAGIC) > 0)
            s.setMagic();
        if ((type & PROJECTILE) > 0)
            s.setProjectile();
        if ((type & EXPLOSION) > 0)
            s.setExplosion();
        if ((type & NO_AGGRO) > 0)
            s.setNoAggro();
        if ((type & FALL) > 0)
            s.setIsFall();
        //TODO: THIS MAY CAUSE WRONG IN PVP MODE
        if ((type & CREATIVE) > 0)
            s.bypassInvul();

        //do damage here
        boolean result = target.hurt(s, amount);
        if ((type & BYPASS_ABSORB) > 0 && target instanceof Player player && !target.isRemoved())
        {
            player.setAbsorptionAmount(absorbNum);
        }
        return result;
    }
}
