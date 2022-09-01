package chick.extrabotany.common.entities.projectile;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;


public class EntityMagicArrow extends ThrowableProjectile
{

    private static final String TAG_DAMAGE = "damage";
    private static final String TAG_LIFE = "life";

    private static final EntityDataAccessor<Integer> DAMAGE = SynchedEntityData.defineId(EntityMagicArrow.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LIFE = SynchedEntityData.defineId(EntityMagicArrow.class, EntityDataSerializers.INT);

    public EntityMagicArrow(EntityType<? extends EntityMagicArrow> type, Level level)
    {
        super(type, level);
    }

    public EntityMagicArrow(Level level, LivingEntity thrower)
    {
        this(ModEntities.MAGIC_ARROW, level);
        setOwner(thrower);
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(DAMAGE, 0);
        entityData.define(LIFE, 0);
    }


    @Override
    public void tick()
    {
        super.tick();

        if (!level.isClientSide && (getOwner() == null || !(getOwner() instanceof Player) || getOwner().isRemoved()))
        {
            discard();
            return;
        }

        if (level.isClientSide)
        {
            WispParticleData data = WispParticleData.wisp(0.5F, 0.1F, 0.85F, 0.1F, 1F);
            ClientProxy.INSTANCE.addParticleForceNear(level, data, getX(), getY(), getZ(), 0, 0, 0);
        }

        Player player = (Player) getOwner();
        if (!level.isClientSide)
        {
            AABB axis = new AABB(getX() - 2F, getY() - 2F, getZ() - 2F, xOld + 2F, yOld + 2F, zOld + 2F);
            var entities = level.getEntitiesOfClass(LivingEntity.class, axis);
            var livings = DamageHandler.INSTANCE.getFilteredEntities(entities, player);
            for (LivingEntity living : livings)
            {
                DamageHandler.INSTANCE.dmg(living, player, getDamage(), DamageHandler.INSTANCE.NETURAL_PIERCING);
            }
        }

        if (tickCount > getLife())
            discard();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp)
    {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_LIFE, getLife());
        cmp.putInt(TAG_DAMAGE, getDamage());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp)
    {
        super.readAdditionalSaveData(cmp);
        setLife(cmp.getInt(TAG_LIFE));
        setDamage(cmp.getInt(TAG_DAMAGE));
    }

    @Override
    public boolean ignoreExplosion()
    {
        return true;
    }

    @Override
    public boolean isNoGravity()
    {
        return true;
    }

    public int getLife()
    {
        return entityData.get(LIFE);
    }

    public void setLife(int delay)
    {
        entityData.set(LIFE, delay);
    }

    public int getDamage()
    {
        return entityData.get(DAMAGE);
    }

    public void setDamage(int delay)
    {
        entityData.set(DAMAGE, delay);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    public boolean isPushedByFluid()
    {
        return false;
    }

    @NotNull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
