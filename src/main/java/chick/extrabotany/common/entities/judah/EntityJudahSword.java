package chick.extrabotany.common.entities.judah;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.SparkleParticleData;
import vazkii.botania.client.fx.WispParticleData;

import java.util.List;

public class EntityJudahSword extends Entity
{

    private static final String TAG_DAMAGE = "damage";
    private static final String TAG_START_POS = "start_pos";
    private static final String TAG_END_POS = "end_pos";

    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(EntityJudahSword.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.defineId(EntityJudahSword.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> END_POS = SynchedEntityData.defineId(EntityJudahSword.class, EntityDataSerializers.BLOCK_POS);

    public EntityJudahSword(EntityType<? extends EntityJudahSword> type, Level level)
    {
        super(type, level);
    }

    public EntityJudahSword(Level level, BlockPos start, BlockPos end)
    {
        super(ModEntities.JUDAH_SWORD, level);
        setStartPos(start);
        setEndPos(end);
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(DAMAGE, 0F);
        entityData.define(START_POS, BlockPos.ZERO);
        entityData.define(END_POS, BlockPos.ZERO);
    }

    @Override
    public void tick()
    {
        if (level.isClientSide)
        {
            SparkleParticleData data = SparkleParticleData.sparkle(1.8F, 1.00F, 0.7F, 0.7F, 25);
            ClientProxy.INSTANCE.addParticleForceNear(level, data, getX(), getY(), getZ(), 0, 0, 0);
        }
        BlockPos start = getStartPos();
        BlockPos end = getEndPos();
        Vec3 motion = new Vec3(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ());
        motion = motion.normalize().scale(0.75D);
        setPos(position().add(motion));

        var collided = level.getEntitiesOfClass(LivingEntity.class, new AABB(blockPosition()).inflate(0.25D));

        for (LivingEntity entity : collided)
        {
            if (entity instanceof Player)
                continue;
            DamageHandler.INSTANCE.doDamage(entity, new DamageSource("magic"), getDamage() * 0.2F, 0b1001001);
            DamageHandler.INSTANCE.doDamage(entity, new DamageSource("lava"), getDamage() * 0.2F, 0b101001);
            entity.setSecondsOnFire(5);
        }

        super.tick();

        if (getX() >= end.getX() - 1 && getX() <= end.getX() + 1 && getZ() >= end.getZ() - 1 && getZ() <= end.getZ() + 1)
        {
            Vec3 oldPosVec = new Vec3(start.getX(), start.getY(), start.getZ());
            Vec3 newPosVec = new Vec3(end.getX(), end.getY(), end.getZ());

            var around = level.getEntitiesOfClass(LivingEntity.class, new AABB(blockPosition()).inflate(13F));
            for (LivingEntity entity : around)
            {
                if (entity instanceof Player)
                    continue;
                var rtr = entity.getBoundingBox().inflate(1.8D).intersects(oldPosVec, newPosVec);
                if (rtr)
                {
                    DamageHandler.INSTANCE.doDamage(entity, new DamageSource("magic"), getDamage() * 0.6F, 0b1001001);
                    DamageHandler.INSTANCE.doDamage(entity, new DamageSource("lava"), getDamage() * 0.3F, 0b101001);
                    DamageHandler.INSTANCE.doDamage(entity, null, this, getDamage() * 0.3F, 0b1011);

                    entity.setSecondsOnFire(5);
                }
            }
            discard();
        }
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag cmp)
    {
        cmp.putLong(TAG_START_POS, getStartPos().asLong());
        cmp.putLong(TAG_END_POS, getEndPos().asLong());
        cmp.putFloat(TAG_DAMAGE, getDamage());
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag cmp)
    {
        setStartPos(BlockPos.of(cmp.getLong(TAG_START_POS)));
        setEndPos(BlockPos.of(cmp.getLong(TAG_END_POS)));
        setDamage(cmp.getFloat(TAG_DAMAGE));
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setStartPos(BlockPos pos)
    {
        entityData.set(START_POS, pos);
    }

    public void setEndPos(BlockPos pos)
    {
        entityData.set(END_POS, pos);
    }

    public void setDamage(float dmg)
    {
        entityData.set(DAMAGE, dmg);
    }

    public BlockPos getStartPos()
    {
        return entityData.get(START_POS);
    }

    public BlockPos getEndPos()
    {
        return entityData.get(END_POS);
    }

    public float getDamage()
    {
        return entityData.get(DAMAGE);
    }

}
