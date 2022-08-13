package chick.extrabotany.common.entities.projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import chick.extrabotany.network.DamageHandler;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import vazkii.botania.client.fx.WispParticleData;

import java.util.List;

public class EntityTrueShadowKatanaProjectile extends EntityProjectileBase
{
    public static final int LIVE_TICKS = 40;

    public EntityTrueShadowKatanaProjectile(EntityType<? extends ThrowableProjectile> type, Level level)
    {
        super(type, level);
    }

    public EntityTrueShadowKatanaProjectile(Level level, LivingEntity thrower)
    {
        super(ModEntities.TRUE_SHADOW_KATANA, level, thrower);
    }

    @Override
    public void tick()
    {

        if (this.tickCount >= LIVE_TICKS)
            discard();

        if (!level.isClientSide && (getOwner() == null || getOwner().isRemoved()))
        {
            discard();
            return;
        }

        if (this.tickCount <= 3)
            return;

        if (level.isClientSide && tickCount % 2 == 0)
        {
            WispParticleData data = WispParticleData.wisp(0.15F, 0F, 0F, 0F, 1F);
            //Botania.proxy.addParticleForce(world, data, getPosX(), getPosY(), getPosZ(), 0, 0, 0);
        }
        super.tick();

        if (!level.isClientSide)
        {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(2);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, axis);
            List<LivingEntity> list = DamageHandler.INSTANCE.getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list)
            {
                living.setInvulnerable(false);
                if (getOwner() instanceof Player)
                {
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 5F, DamageHandler.INSTANCE.NETURAL);
                } else
                {
                    if (!living.isInvulnerable())
                        DamageHandler.INSTANCE.dmg(living, getOwner(), 2F, DamageHandler.INSTANCE.LIFE_LOSING);
                    ;
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 5.5F, DamageHandler.INSTANCE.MAGIC);
                }
                discard();
                break;
            }
        }
    }

    @Override
    public BakedModel getIcon()
    {
        return MiscellaneousIcons.INSTANCE.trueshadowkatanaprojectileModel[0];
    }
}
