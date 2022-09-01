/**
 * Entity just for action!
 * no other use!
 */
package chick.extrabotany.common.entities.projectile.relic_projectile;

import chick.extrabotany.common.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EntityAction extends Entity
{
    private final Supplier<Runnable> run;
    private final Entity attachedEntity;
    private final int actionTick;

    public EntityAction(EntityType<?> type, Level level)
    {
        super(type, level);
        run = null;
        attachedEntity = null;
        actionTick = 0;
    }

    public EntityAction(Level level, Vec3 pos, Entity attachedEntity, int actionTick, Supplier<Runnable> run)
    {
        super(ModEntities.ACTION_ENTITY, level);
        setPos(pos);
        this.run = run;
        this.attachedEntity = attachedEntity;
        this.actionTick = actionTick;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (tickCount >= actionTick)
        {
            if (run != null)
                run.get().run();
            if (attachedEntity != null)
                attachedEntity.discard();
            discard();
        }
    }

    @Override
    protected void defineSynchedData()
    {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp)
    {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp)
    {

    }

    @NotNull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
