package chick.extrabotany.network;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import vazkii.botania.common.handler.EquipmentHandler;

import java.util.List;
import java.util.stream.Collectors;

public final class DamageHandler
{
    public static final DamageHandler INSTANCE = new DamageHandler();
    public final int NETURAL = 0;
    public final int MAGIC = 1;
    public final int NETURAL_PIERCING = 2;
    public final int MAGIC_PIERCING = 3;
    public final int LIFE_LOSING = 4;

    /**
     * 判断是否可行
     */
    public boolean checkPassable(Entity target, Entity source)
    {
        if (target == source)
            return false;
        if (source instanceof Player sourcePlayer)
        {
            boolean sourceEquipped = !EquipmentHandler.findOrEmpty(ModItems.PEACE_AMULET.get(), sourcePlayer).isEmpty();
            if (target instanceof Player targetPlayer)
            {
                return !sourceEquipped && EquipmentHandler.findOrEmpty(ModItems.PEACE_AMULET.get(), targetPlayer).isEmpty();
            }
            if (sourceEquipped && !(target instanceof Mob))
                return false;
            //&& target.isNonBoss()
        }
        return true;
    }

    /**
     * 使用和平友好之证过滤
     */
    public List<LivingEntity> getFilteredEntities(List<LivingEntity> entities, Entity source)
    {
        List<LivingEntity> list = entities.stream().filter((living) -> checkPassable(living, source) && !living.isRemoved()).collect(Collectors.toList());
        return list;
    }

    /**
     * 错乱（？
     */
    public static float calcDamage(float orig, Player player)
    {
        if (player == null)
            return orig;
        double value = 0F;
        return (float) (orig + value);
    }

    /**
     * 造成伤害（考虑和平友好之证）
     */
    public boolean dmg(Entity target, Entity source, float amount, int type)
    {
        if (target == null || !checkPassable(target, source))
            return false;
        switch (type)
        {
            case NETURAL:
            {
                if (source instanceof Player)
                {
                    Player player = (Player) source;
                    DamageSource s = DamageSource.playerAttack(player);
                    return target.hurt(s, amount);
                } else if (source instanceof LivingEntity)
                {
                    LivingEntity living = (LivingEntity) source;
                    DamageSource s = DamageSource.mobAttack(living);
                    return target.hurt(s, amount);
                } else
                {
                    return target.hurt(DamageSource.GENERIC, amount);
                }
            }
            case MAGIC:
            {
                if (source == null)
                {
                    DamageSource s = DamageSource.MAGIC;
                    return target.hurt(s, amount);
                } else
                {
                    DamageSource s = DamageSource.indirectMagic(source, source);
                    return target.hurt(s, amount);
                }
            }
            case NETURAL_PIERCING:
            {
                target.setInvulnerable(false);
                if (source instanceof Player)
                {
                    Player player = (Player) source;
                    DamageSource s = DamageSource.playerAttack(player).bypassArmor().bypassMagic();
                    return target.hurt(s, amount);
                } else if (source instanceof LivingEntity)
                {
                    LivingEntity living = (LivingEntity) source;
                    DamageSource s = DamageSource.mobAttack(living).bypassArmor().bypassMagic();
                    return target.hurt(s, amount);
                } else
                {
                    return target.hurt(DamageSource.GENERIC, amount);
                }
            }
            case MAGIC_PIERCING:
            {
                target.setInvulnerable(false);
                if (source == null)
                {
                    DamageSource s = DamageSource.MAGIC.bypassArmor().bypassMagic();
                    return target.hurt(s, amount);
                } else
                {
                    DamageSource s = DamageSource.indirectMagic(source, source).bypassMagic().bypassArmor();
                    return target.hurt(s, amount);
                }
            }
            case LIFE_LOSING:
            {
                if (!(target instanceof LivingEntity))
                    return false;
                LivingEntity living = (LivingEntity) target;
                float currentHealth = living.getHealth();
                float trueHealth = Math.max(1F, currentHealth - amount);
                living.setHealth(trueHealth);
                return dmg(target, source, 0.01F, NETURAL);
            }
        }
        return false;
    }

}
