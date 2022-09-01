package chick.extrabotany.common.entities.projectile.relic_projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class EntityTrueShadowKatanaProjectile extends RelicProjectileBase
{

    public EntityTrueShadowKatanaProjectile(EntityType<? extends ThrowableProjectile> type, Level level)
    {
        super(type, level);
    }

    @Override
    public int getLifeTicks()
    {
        return 40;
    }

    @Override
    public int getTickPerBlock()
    {
        return 20;
    }

    @Override
    public int getTickBreakBlockCap()
    {
        return 35;
    }

    public EntityTrueShadowKatanaProjectile(Level level, LivingEntity thrower, float damageTime)
    {
        super(ModEntities.TRUE_SHADOW_KATANA, level, thrower,damageTime);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.tickCount <= 3)
            return;
        if (level.isClientSide && tickCount % 2 == 0)
        {
            level.addParticle(ParticleTypes.END_ROD, getX(), getY(), getZ(), 0D, 0D, 0D);
        }
        if (!level.isClientSide)
        {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(1);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, axis);
            List<LivingEntity> list = DamageHandler.INSTANCE.getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list)
            {
                living.setInvulnerable(false);
                DamageHandler.INSTANCE.dmg(living, getOwner(), 5.5F * damageTime, DamageHandler.INSTANCE.MAGIC);
                if (attackedEntities != null && !attackedEntities.contains(living))
                {
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 2F * damageTime, DamageHandler.INSTANCE.LIFE_LOSINT_ABSORB);
                    attackedEntities.add(living);
                }
                discard();
                break;
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public BakedModel getIcon()
    {
        return MiscellaneousIcons.INSTANCE.trueshadowkatanaprojectileModel[0];
    }
}
