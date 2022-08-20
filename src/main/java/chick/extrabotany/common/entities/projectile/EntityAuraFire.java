package chick.extrabotany.common.entities.projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.network.DamageHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class EntityAuraFire extends EntityProjectileBase
{

    public EntityAuraFire(EntityType<? extends EntityProjectileBase> type, Level level)
    {
        super(type, level);
    }

    public EntityAuraFire(Level level, LivingEntity owner)
    {
        super(ModEntities.AURAFIRE, level, owner);
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
                DamageHandler.INSTANCE.dmg(result.getEntity(), player, dmg, DamageHandler.INSTANCE.NETURAL_PIERCING);
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
}
