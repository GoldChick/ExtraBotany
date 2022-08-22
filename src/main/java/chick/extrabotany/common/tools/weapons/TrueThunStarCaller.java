package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.entities.EntityFalseLightning;
import chick.extrabotany.network.DamageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.RelicImpl;

import java.util.List;

public class TrueThunStarCaller extends SwordRelicBase
{
    public TrueThunStarCaller(Properties prop)
    {
        super(Tiers.DIAMOND, -1, -2, prop);
    }

    @Override
    public int getManaPerDamage()
    {
        return 800;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime)
    {
        if (target == null)
            return;
        if (!player.level.isClientSide)
        {
            AABB axis = new AABB(target.getX(), target.getY(), target.getZ(), target.xOld, target.yOld, target.zOld).inflate(5);
            List<LivingEntity> entities = player.level.getEntitiesOfClass(LivingEntity.class, axis);
            List<LivingEntity> list = DamageHandler.INSTANCE.getFilteredEntities(entities, player);
            var newList = DamageHandler.INSTANCE.getFilteredEntities(list, player);
            for (LivingEntity livingEntity : newList)
            {
                float damage = 1F;
                if (newList.size() <= 5)
                    damage = 7F - (float) newList.size() * 0.2F;
                else if (newList.size() <= 10)
                    damage = 11F - (float) newList.size();

                DamageHandler.INSTANCE.dmg(livingEntity, player, damage * damageTime, DamageHandler.INSTANCE.LIFE_LOSING);
                EntityFalseLightning falseLightning = new EntityFalseLightning(ModEntities.FALSE_LIGHTNING, player.level);
                falseLightning.setPos(livingEntity.position());
                player.level.addFreshEntity(falseLightning);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 3));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
                if (livingEntity.isOnFire())
                {
                    DamageHandler.INSTANCE.dmg(livingEntity, player, 5F * damageTime, DamageHandler.INSTANCE.LIFE_LOSING);
                    livingEntity.setRemainingFireTicks(0);
                    Minecraft.getInstance().level.addParticle(ParticleTypes.EXPLOSION_EMITTER, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1D, 0D, 0D);
                }
            }
        }
    }
    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID,"challenge/true_thunstar_caller"));
    }
}
