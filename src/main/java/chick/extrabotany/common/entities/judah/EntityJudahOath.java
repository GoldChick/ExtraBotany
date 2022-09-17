package chick.extrabotany.common.entities.judah;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;

import javax.annotation.Nonnull;
import java.awt.*;

public class EntityJudahOath extends ThrowableItemProjectile
{

    public EntityJudahOath(EntityType<? extends ThrowableItemProjectile> type, Level level)
    {
        super(type, level);
    }

    public EntityJudahOath(Level level, LivingEntity player)
    {
        super(ModEntities.JUDAH_OATH, level);
        setOwner(player);
    }

    @Override
    protected @NotNull Item getDefaultItem()
    {
        return ModItems.JUDAH_OATH.get();
    }

    private static final String TAG_DAMAGE = "damage";
    private static final String TAG_ROTATION = "rotation";
    private static final String TAG_PLAYERLIST = "playerlist";

    private static final EntityDataAccessor<Integer> DAMAGE = SynchedEntityData.defineId(EntityJudahOath.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(EntityJudahOath.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityJudahOath.class, EntityDataSerializers.INT);

    private EntityJudahOath.Status status;
    private float range = 5F;
    private int fakecount = 0;
    private int count = 0;
    private int standby = 0;

    @Override
    protected float getGravity()
    {
        return 0.15F;
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        //setSize(0F, 0F);
        entityData.define(DAMAGE, 0);
        entityData.define(ROTATION, 0F);
        entityData.define(TYPE, EntityJudahOath.JudahType.JUDAH.ordinal());
    }

    @Override
    public void tick()
    {
        Status preStatus = this.status;
        if (this.status != EntityJudahOath.Status.STANDBY)
        {
            setDeltaMovement(getDeltaMovement().multiply(1, 0.85D, 1));
            this.status = getStatus();
        } else
        {
            setDeltaMovement(Vec3.ZERO);
        }
        super.tick();

        if (!level.isClientSide && (getOwner() == null || getOwner().isRemoved()))
        {
            discard();
            return;
        }

        if (preStatus == EntityJudahOath.Status.INAIR && this.status == EntityJudahOath.Status.STANDBY)
        {
            //TODO: change this
            level.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(), 1D, 0D, 0D);
        }
        if (status == EntityJudahOath.Status.STANDBY)
        {
            standby++;

            if (this.range <= 13F)
            {
                this.range += 0.5F;
            }
            if (this.getJudahType() != EntityJudahOath.JudahType.SAKURA)
            {
                if (this.tickCount % 4 == 0 && this.fakecount < 13)
                {
                    if (getOwner() instanceof Player player)
                    {
                        EntityJudahSpear spear = new EntityJudahSpear(player, level);
                        spear.setRotation(180F);
                        spear.setPos(position());
                        spear.setDamage(getDamage());
                        spear.setFake(true);
                        spear.setType(JudahType.byId(getJudahType().ordinal()));
                        if (!this.level.isClientSide)
                        {
                            this.level.addFreshEntity(spear);
                        }
                    }
                    this.fakecount += 1;
                }

                AABB axis = new AABB(blockPosition()).inflate(range - 2.5F);
                var entities = level.getEntitiesOfClass(LivingEntity.class, axis);
                double tx = getX();
                double ty = getY() + 10;
                double tz = getZ();

                for (var living : entities)
                {
                    if (living.getId() == getId() || living.isRemoved())
                        continue;
                    //      living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 9));
                    tx = living.getX();
                    ty = living.getY() + 10;
                    tz = living.getZ();
                }

                if (this.standby > 20 && this.tickCount % 10 == 0 && this.count < 13)
                {
                    EntityJudahSpear spear = new EntityJudahSpear(level);
                    spear.setRotation(0F);
                    spear.setPos(tx, ty, tz);
                    spear.setDamage(getDamage());
                    spear.setFake(false);
                    spear.setType(JudahType.byId(getJudahType().ordinal()));
                    if (!this.level.isClientSide)
                    {
                        this.level.addFreshEntity(spear);
                    }
                    this.count += 1;
                }
            } else
            {
                //sakura
                if (this.standby >= 20 && this.standby % 30 == 0 && this.count <= 2)
                {
                    Point center = new Point(getBlockX(), getBlockZ());
                    double angle = 72D;
                    Point p = new Point(getBlockX() - 11, getBlockZ());
                    p = rotatePointAbout(p, center, getRotation());
                    Point[] points = new Point[5];
                    for (int i = 0; i < 5; i++)
                    {
                        p = rotatePointAbout(p, center, angle);
                        points[i] = p;
                    }
                    for (int i = 0; i < 5; i++)
                    {
                        int index = i + 2 > 4 ? i - 3 : i + 2;
                        Point pstart = points[i];
                        pstart = rotatePointAbout(pstart, center, 36 * count);
                        Point pend = points[index];
                        pend = rotatePointAbout(pend, center, 36 * count);
                        BlockPos start = new BlockPos(pstart.x, getY() + 1, pstart.y);
                        BlockPos end = new BlockPos(pend.x, getY() + 1, pend.y);
                        //TODO:SWORD

                        EntityJudahSword sword = new EntityJudahSword(level, start, end);
                        sword.setDamage(6F);
                        sword.setPos(start.getX(), start.getY(), start.getZ());
                        if (!level.isClientSide)
                        {
                            level.addFreshEntity(sword);
                        }

                    }
                    count++;
                }
            }

            for (int i = 0; i < 360; i += 2)
            {
                Vec3 color = getJudahType().getColor();

                float r = (float) color.x;
                float g = (float) color.y;
                float b = (float) color.z;
                float rad = i * (float) Math.PI / 180F;
                double x = getX() + 0.5 - Math.cos(rad) * this.range;
                double y = getY() + 0.2;
                double z = getZ() + 0.5 - Math.sin(rad) * this.range;
                if (level.isClientSide)
                {
                    WispParticleData data = WispParticleData.wisp(1, r, g, b, 1F);
                    ClientProxy.INSTANCE.addParticleForceNear(level, data, x, y, z, 0, 0, 0);
                }
            }
        }

        if (!level.isClientSide && standby > 140 || tickCount > 300)
        {
            discard();
        }
    }


    private Point rotatePointAbout(Point in, Point about, double degrees)
    {
        double rad = degrees * Math.PI / 180.0;
        double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
        double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
        return new Point((int) newX, (int) newY);
    }

    private EntityJudahOath.Status getStatus()
    {
        if (!this.level.getBlockState(this.blockPosition().offset(0, -1, 0)).isAir())
            return EntityJudahOath.Status.STANDBY;
        return EntityJudahOath.Status.INAIR;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag cmp)
    {
        super.addAdditionalSaveData(cmp);
        cmp.putString("Type", this.getJudahType().getName());
        cmp.putInt(TAG_DAMAGE, getDamage());
        cmp.putFloat(TAG_ROTATION, getRotation());
        cmp.putUUID(TAG_PLAYERLIST, getUUID());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag cmp)
    {
        super.readAdditionalSaveData(cmp);
        setType(EntityJudahOath.JudahType.getTypeFromString(cmp.getString("Type")));
        setDamage(cmp.getInt(TAG_DAMAGE));
        setRotation(cmp.getFloat(TAG_ROTATION));
        setUUID(cmp.getUUID(TAG_PLAYERLIST));
    }

    public void setType(JudahType raftType)
    {
        this.entityData.set(TYPE, raftType.ordinal());
    }

    public JudahType getJudahType()
    {
        return EntityJudahOath.JudahType.byId(this.entityData.get(TYPE));
    }

    public int getDamage()
    {
        return entityData.get(DAMAGE);
    }

    public void setDamage(int delay)
    {
        entityData.set(DAMAGE, delay);
    }

    public float getRotation()
    {
        return entityData.get(ROTATION);
    }

    public void setRotation(float rot)
    {
        entityData.set(ROTATION, rot);
    }

    public enum Status
    {
        INAIR,
        STANDBY;
    }

    public enum JudahType
    {
        JUDAH(0, "judah"),
        KIRA(1, "kira"),
        SAKURA(2, "sakura");

        private final String name;
        private final int metadata;

        JudahType(int metadataIn, String nameIn)
        {
            this.name = nameIn;
            this.metadata = metadataIn;
        }

        public String getName()
        {
            return this.name;
        }

        public static JudahType byId(int id)
        {
            if (id < 0 || id >= values().length)
            {
                id = 0;
            }

            return values()[id];
        }

        public static JudahType getTypeFromString(String nameIn)
        {
            for (int i = 0; i < values().length; ++i)
            {
                if (values()[i].getName().equals(nameIn))
                {
                    return values()[i];
                }
            }
            return values()[0];
        }

        /**
         * @return r g b
         */
        public Vec3 getColor()
        {
            return (switch (metadata)
                    {
                        case 1 -> new Vec3(0.01, 0.6, 0.75);
                        case 2 -> new Vec3(1, 0.8, 0.8);
                        default -> new Vec3(0.85, 0.6, 0.02);
                    });
        }
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
