package chick.extrabotany.common.entities.projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import chick.extrabotany.common.entities.projectile.relic_projectile.RelicProjectileBase;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class EntityAuraFire extends RelicProjectileBase
{

    public EntityAuraFire(EntityType<? extends RelicProjectileBase> type, Level level)
    {
        super(type, level);
    }

    public EntityAuraFire(Level level, LivingEntity owner)
    {
        super(ModEntities.AURAFIRE, level, owner,1F);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level.isClientSide)
            for (int i = 0; i < 5; i++)
                this.level.addParticle(ParticleTypes.FLAME, this.getX() + Math.random() * 0.4F - 0.2F,
                        this.getY() + Math.random() * 0.4F - 0.2F, this.getZ() + Math.random() * 0.4F - 0.2F, 0, 0, 0);

    }

    @Override
    public void onHitEntity(@NotNull EntityHitResult result)
    {
        if (getOwner() instanceof Player player)
        {
            if (result.getEntity() != player)
            {
                float dmg = DamageHandler.calcDamage(5F, player);

                DamageHandler.INSTANCE.doDamage(result.getEntity(), DamageSource.indirectMagic(this,getOwner()), dmg, DamageHandler.INSTANCE.FIRE);
                player.setAbsorptionAmount(Math.min(10, player.getAbsorptionAmount() + 1F));
                discard();
            }
        }
    }

    @Override
    public int getLifeTicks()
    {
        return 80;
    }

    @Override
    public int getTickPerBlock()
    {
        return 0;
    }

    @Override
    public int getTickBreakBlockCap()
    {
        return 81;
    }
}
