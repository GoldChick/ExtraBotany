package chick.extrabotany.network;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
    public final int LIFE_LOSINT_ABSORB = 5;

    /**
     * 判断是否可行,source为player并且佩戴和平友好之证的时候无法对友好生物（不extend Mob的）造成伤害
     * 两个玩家都佩戴和平友好之证的时候无法互相造成伤害
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
        if (target == null || !checkPassable(target, source))
            return false;
        switch (type)
        {
            case NETURAL ->
            {
                if (source instanceof Player player)
                {
                    return target.hurt(DamageSource.playerAttack(player), amount);
                } else if (source instanceof LivingEntity living)
                {
                    return target.hurt(DamageSource.mobAttack(living), amount);
                } else
                {
                    return target.hurt(DamageSource.GENERIC, amount);
                }
            }
            case MAGIC ->
            {
                DamageSource s = source == null ? DamageSource.MAGIC : DamageSource.indirectMagic(source, source);
                return target.hurt(s, amount);
            }
            case NETURAL_PIERCING ->
            {
                target.setInvulnerable(false);
                if (source instanceof Player player)
                {
                    DamageSource s = DamageSource.playerAttack(player).bypassArmor().bypassMagic();
                    return target.hurt(s, amount);
                } else if (source instanceof LivingEntity living)
                {
                    DamageSource s = DamageSource.mobAttack(living).bypassArmor().bypassMagic();
                    return target.hurt(s, amount);
                } else
                {
                    return target.hurt(DamageSource.GENERIC, amount);
                }
            }
            case MAGIC_PIERCING ->
            {
                target.setInvulnerable(false);
                DamageSource s = source == null ? DamageSource.MAGIC.bypassArmor().bypassMagic() : DamageSource.indirectMagic(source, source).bypassMagic().bypassArmor();
                return target.hurt(s, amount);
            }
            case LIFE_LOSING ->
            {
                if (!(target instanceof LivingEntity living))
                    return false;
                float trueHealth = Math.max(0F, living.getHealth() - amount);
                if (trueHealth <= 0)
                {
                    dmg(target, source, 0.01F, NETURAL);
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
                return dmg(target, source, 0.01F, NETURAL);
            }
            case LIFE_LOSINT_ABSORB ->
            {
                if (!(target instanceof LivingEntity living))
                    return false;
                float currentYellowHealth = living.getAbsorptionAmount();
                if (currentYellowHealth >= amount)
                {
                    living.setAbsorptionAmount(living.getAbsorptionAmount() - amount);
                } else
                {
                    float trueHealth = Math.max(0F, living.getHealth() - amount + living.getAbsorptionAmount());
                    living.setAbsorptionAmount(0);
                    if (trueHealth <= 0)
                    {
                        dmg(target, source, 0.01F, NETURAL);
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
                }
                return dmg(target, source, 0.01F, NETURAL);
            }
        }
        return false;
    }
}
