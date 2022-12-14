package chick.extrabotany.common.entities.projectile.relic_projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;

import java.util.List;

public class EntityInfluxWaverProjectile extends RelicProjectileBase
{
    private int removeFlag = -1;

    private static final String TAG_STRIKE_TIMES = "strike_times";
    private static final String TAG_NEXT = "next";
    private static final EntityDataAccessor<Integer> STRIKE_TIMES = SynchedEntityData.defineId(EntityInfluxWaverProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> NEXT = SynchedEntityData.defineId(EntityInfluxWaverProjectile.class, EntityDataSerializers.BLOCK_POS);

    public EntityInfluxWaverProjectile(EntityType<? extends ThrowableProjectile> type, Level level)
    {
        super(type, level);
    }

    public EntityInfluxWaverProjectile(Level level, LivingEntity thrower, float damageTime)
    {
        super(ModEntities.INFLUX_WAVER, level, thrower, damageTime);
    }

    @Override
    public void tick()
    {
        if (this.removeFlag != -1 && this.tickCount >= this.removeFlag + 4)
        {
            if (!level.isClientSide && !getNext().equals(BlockPos.ZERO))
            {
                EntityInfluxWaverProjectile proj = make(getNext());
                level.addFreshEntity(proj);
                discard();
            }
        }

        super.tick();

        if (getDeltaMovement().equals(Vec3.ZERO) || this.removeFlag != -1)
            return;

        if (level.isClientSide && tickCount % 2 == 0)
        {
            WispParticleData data = WispParticleData.wisp(0.3F, 0.1F, 0.1F, 0.85F, 1F);

            ClientProxy.INSTANCE.addParticleForceNear(level, data, getX(), getY(), getZ(), 0, 0, 0);
        }

        if (!level.isClientSide)
        {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(2);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, axis);
            boolean flag = false;
            List<LivingEntity> list = DamageHandler.INSTANCE.getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list)
            {
                if (!living.isRemoved())
                {
                    //TODO WIP
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                    DamageHandler.INSTANCE.doDamage(living, DamageSource.indirectMagic(this, getOwner()), 7F * damageTime, DamageHandler.INSTANCE.SCALE_WITH_DIFFICULTY + DamageHandler.INSTANCE.PROJECTILE);
                    DamageHandler.INSTANCE.doDamage(living, DamageSource.indirectMagic(this, getOwner()), 3F * damageTime, DamageHandler.INSTANCE.BYPASS_INVUL + DamageHandler.INSTANCE.SCALE_WITH_DIFFICULTY + DamageHandler.INSTANCE.PROJECTILE);
                    flag = living.isRemoved();
                    if (getStrikeTimes() > 0 && !flag)
                    {
                        setNext(living.blockPosition().offset(0, 1, 0));
                        removeFlag = this.tickCount;
                    }
                    break;
                }
            }

            if (getStrikeTimes() > 0 && flag)
            {
                axis = axis.inflate(5D);
                List<LivingEntity> others = level.getEntitiesOfClass(LivingEntity.class, axis);
                List<LivingEntity> olist = DamageHandler.INSTANCE.getFilteredEntities(others, getOwner());
                for (LivingEntity living : olist)
                {
                    setNext(living.blockPosition().offset(0, 1, 0));
                    removeFlag = this.tickCount;
                    break;
                }
            }
        }

    }

    public EntityInfluxWaverProjectile make(BlockPos targetpos)
    {
        EntityInfluxWaverProjectile proj = new EntityInfluxWaverProjectile(level, (LivingEntity) getOwner(), damageTime);
        float range = 6F;
        double j = -Math.PI + 2 * Math.PI * Math.random();
        double k;
        double x, y, z;
        k = 0.12F * Math.PI * Math.random() + 0.28F * Math.PI;
        x = targetpos.getX() + range * Math.sin(k) * Math.cos(j);
        y = targetpos.getY() + range * Math.cos(k);
        z = targetpos.getZ() + range * Math.sin(k) * Math.sin(j);
        proj.setPos(x, y, z);
        proj.setTargetPos(targetpos);
        proj.faceTarget(0.8F);
        proj.setStrikeTimes(getStrikeTimes() - 1);
        return proj;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp)
    {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_STRIKE_TIMES, getStrikeTimes());
        cmp.putLong(TAG_NEXT, getNext().asLong());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp)
    {
        super.readAdditionalSaveData(cmp);
        setStrikeTimes(cmp.getInt(TAG_STRIKE_TIMES));
        setNext(BlockPos.of(cmp.getLong(TAG_NEXT)));
    }

    public int getStrikeTimes()
    {
        return entityData.get(STRIKE_TIMES);
    }

    public void setStrikeTimes(int i)
    {
        entityData.set(STRIKE_TIMES, i);
    }

    public BlockPos getNext()
    {
        return entityData.get(NEXT);
    }

    public void setNext(BlockPos pos)
    {
        entityData.set(NEXT, pos);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(STRIKE_TIMES, 0);
        entityData.define(NEXT, BlockPos.ZERO);
    }

    @Override
    public int getLifeTicks()
    {
        return 60;
    }

    @Override
    public int getTickPerBlock()
    {
        return 10;
    }

    @Override
    public int getTickBreakBlockCap()
    {
        return 30;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BakedModel getIcon()
    {
        return MiscellaneousIcons.INSTANCE.influxwaverprojectileModel[0];
    }
}
