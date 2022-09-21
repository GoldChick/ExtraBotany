package chick.extrabotany.common.entities.judah;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;

import java.util.List;

import static chick.extrabotany.common.entities.judah.EntityJudahOath.JudahType;

public class EntityJudahSpear extends Entity
{
    private static final String TAG_DAMAGE = "damage";
    private static final String TAG_FAKE = "fake";
    private static final String TAG_ROTATION = "rotation";
    private static final String TAG_PLAYERLIST = "playerlist";

    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(EntityJudahSpear.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> FAKE = SynchedEntityData.defineId(EntityJudahSpear.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityJudahSpear.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(EntityJudahSpear.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> OWNER_ID = SynchedEntityData.defineId(EntityJudahSpear.class, EntityDataSerializers.INT);

    public EntityJudahSpear(EntityType<? extends EntityJudahSpear> type, Level level)
    {
        super(type, level);
    }

    public EntityJudahSpear(LivingEntity owner, Level level)
    {
        super(ModEntities.JUDAH_SPEAR, level);
        setOwner_Id(owner.getId());
    }

    public EntityJudahSpear(Level level)
    {
        super(ModEntities.JUDAH_SPEAR, level);
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(ROTATION, 0F);
        entityData.define(FAKE, false);
        entityData.define(DAMAGE, 0F);
        entityData.define(TYPE, JudahType.JUDAH.ordinal());
        entityData.define(OWNER_ID, 0);
    }

    @Override
    public void tick()
    {
        Vec3 color = getJudahType().getColor();
        float r = (float) color.x;
        float g = (float) color.y;
        float b = (float) color.z;
        if (level.isClientSide)
        {
            WispParticleData data = WispParticleData.wisp(1, r, g, b, 1F);
            // ClientProxy.INSTANCE.addParticleForceNear(level, data, getX(), getY(), getZ(), 0, 0, 0);
        }
        super.tick();
        setDeltaMovement(Vec3.ZERO);

        if (getFake())
        {
            setPos(position().add(0, 0.5D, 0));
        } else
        {
            setPos(position().add(0, -0.75D, 0));
        }

        //TODO:对玩家没伤害
        if (!level.isClientSide && !getFake())
        {
            var axis = new AABB(blockPosition()).inflate(1.3F, 6F, 1.3F);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, axis);
            for (LivingEntity living : entities)
            {
                DamageHandler.INSTANCE.doDamage(living, new DamageSource("lightningBolt"), getDamage() * 1.6F, 0b11000001);
                DamageHandler.INSTANCE.doDamage(living, new DamageSource("magic"), getDamage() * 0.15F, 0b11000111);

                for (int i = 0; i < 4; i++)
                {
                    WispParticleData data = WispParticleData.wisp(1, r, g, b, 1F);
                    ClientProxy.INSTANCE.addParticleForceNear(level, data, getX(), getY() + 0.5D, getZ(), 0, 0, 0);
                }
                //ExtraBotanyAPI.addPotionEffect(living, ModPotions.divinejustice, 4);
            }
        }

        if (tickCount > 100)
        {
            discard();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp)
    {
        cmp.putString("Type", this.getJudahType().getName());
        cmp.putFloat(TAG_DAMAGE, getDamage());
        cmp.putBoolean(TAG_FAKE, getFake());
        cmp.putFloat(TAG_ROTATION, getRotation());
        cmp.putInt(TAG_PLAYERLIST, getidid());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp)
    {
        setType(JudahType.getTypeFromString(cmp.getString("Type")));
        setDamage(cmp.getFloat(TAG_DAMAGE));
        setFake(cmp.getBoolean(TAG_FAKE));
        setRotation(cmp.getFloat(TAG_ROTATION));
        setOwner_Id(cmp.getInt(TAG_PLAYERLIST));
    }


    //TODO:TO CHECK
    public int getidid()
    {
        return this.entityData.get(OWNER_ID);
    }

    @Override
    @NotNull
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setOwner_Id(int u)
    {
        entityData.set(OWNER_ID, u);
    }

    public JudahType getJudahType()
    {
        return JudahType.byId(this.entityData.get(TYPE));
    }

    public void setType(JudahType raftType)
    {
        this.entityData.set(TYPE, raftType.ordinal());
    }

    public float getDamage()
    {
        return entityData.get(DAMAGE);
    }

    public void setDamage(float delay)
    {
        entityData.set(DAMAGE, delay);
    }

    public boolean getFake()
    {
        return entityData.get(FAKE);
    }

    public void setFake(boolean delay)
    {
        entityData.set(FAKE, delay);
    }

    public float getRotation()
    {
        return entityData.get(ROTATION);
    }

    public void setRotation(float rot)
    {
        entityData.set(ROTATION, rot);
    }
}
