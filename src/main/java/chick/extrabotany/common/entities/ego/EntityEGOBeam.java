package chick.extrabotany.common.entities.ego;

import chick.extrabotany.common.base.DamageHandler;
import chick.extrabotany.api.WeightCategory;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chick.extrabotany.common.blocks.tile.TileDimensionCatalyst.VEC_OFFSET;


public class EntityEGOBeam extends Entity
{
    private boolean doAttack;
    private List<WeightCategory> stackList = new ArrayList<>();

    private Player target;
    public EntityEGO summoner;

    private static final String TAG_COLOR_R = "colorr";
    private static final String TAG_COLOR_G = "colorg";
    private static final String TAG_COLOR_B = "colorb";
    private static final String TAG_TARGET_COLOR_R = "targetcolorr";
    private static final String TAG_TARGET_COLOR_G = "targetcolorg";
    private static final String TAG_TARGET_COLOR_B = "targetcolorb";
    private static final String TAG_SPEED_MODIFIER = "speedmodifier";
    private static final String TAG_DAMAGE = "damage";
    private static final EntityDataAccessor<Float> COLOR_R = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> COLOR_G = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> COLOR_B = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TARGET_COLOR_R = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TARGET_COLOR_G = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TARGET_COLOR_B = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SPEED_MODIFIER = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(EntityEGOBeam.class, EntityDataSerializers.FLOAT);

    public EntityEGOBeam(EntityType<?> entityTypeIn, Level level)
    {
        super(entityTypeIn, level);
    }

    public EntityEGOBeam(Level level)
    {
        super(ModEntities.EGO_BEAM, level);
        doAttack = true;
    }

    public EntityEGOBeam(Level level, boolean doAttack, List<WeightCategory> stackList, Color targetColor, Player target)
    {
        super(ModEntities.EGO_BEAM, level);
        this.doAttack = doAttack;
        this.stackList.addAll(stackList);
        this.target = target;
        setTargetColorR((float) (targetColor.getRed()) / 255F);
        setTargetColorG((float) (targetColor.getGreen()) / 255F);
        setTargetColorB((float) (targetColor.getBlue()) / 255F);
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(COLOR_R, 0.0F);
        entityData.define(COLOR_G, 0.0F);
        entityData.define(COLOR_B, 0.0F);
        entityData.define(TARGET_COLOR_R, 0.0F);
        entityData.define(TARGET_COLOR_G, 0.0F);
        entityData.define(TARGET_COLOR_B, 0.0F);
        entityData.define(SPEED_MODIFIER, 0.0F);
        entityData.define(DAMAGE, 0.0F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp)
    {
        this.setColor(cmp.getFloat(TAG_COLOR_R), cmp.getFloat(TAG_COLOR_G), cmp.getFloat(TAG_COLOR_B));
        this.setSpeedModifier(cmp.getFloat(TAG_SPEED_MODIFIER));
        this.setBeamDamage(cmp.getFloat(TAG_DAMAGE));
        this.setTargetColorR(cmp.getFloat(TAG_TARGET_COLOR_R));
        this.setTargetColorG(cmp.getFloat(TAG_TARGET_COLOR_G));
        this.setTargetColorB(cmp.getFloat(TAG_TARGET_COLOR_B));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp)
    {
        cmp.putFloat(TAG_COLOR_R, this.getColorR());
        cmp.putFloat(TAG_COLOR_G, this.getColorG());
        cmp.putFloat(TAG_COLOR_B, this.getColorB());
        cmp.putFloat(TAG_TARGET_COLOR_R, this.getTargetColorR());
        cmp.putFloat(TAG_TARGET_COLOR_G, this.getTargetColorG());
        cmp.putFloat(TAG_TARGET_COLOR_B, this.getTargetColorB());
        cmp.putFloat(TAG_SPEED_MODIFIER, this.getSpeedModifier());
        cmp.putFloat(TAG_DAMAGE, this.getBeamDamage());
    }

    @NotNull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (doAttack)
        {
            if (this.tickCount >= 460 || level.getDifficulty() == Difficulty.PEACEFUL)
            {
                this.discard();
            }

            double RANGE = 16.0D;

            AABB axis = new AABB(this.position().add(-RANGE, -RANGE, -RANGE), this.position().add(RANGE + 1.0D, RANGE + 1.0D, RANGE + 1.0D));
            if (this.target != null && !this.target.isRemoved())
            {
                Vec3 vecMotion = this.target.position().subtract(this.position()).normalize().scale(0.1D).scale(this.getSpeedModifier());

                Vec3 newVec = this.position().add(vecMotion);

                this.setPos(newVec.x(), this.getY(), newVec.z());

                double m = 0.35D;

                for (int i = 0; i < 2; ++i)
                {
                    WispParticleData data = WispParticleData.wisp(0.5F, 1.0F, 1.0F, 1.0F);
                    this.level.addParticle(data, this.getX(), this.getY(), this.getZ(), (Math.random() - 0.5D) * m, (Math.random() - 0.5D) * m, (Math.random() - 0.5D) * m);
                }

                List<Player> players = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(0.0, 12.0, 0.0));

                for (Player player : players)
                {
                    if (player.invulnerableTime <= 5)
                    {
                        DamageHandler.INSTANCE.dmg(player, summoner, this.getBeamDamage(), DamageHandler.INSTANCE.LIFE_LOSINT_ABSORB);
                    }
                }
            } else
            {
                List<Player> players = this.level.getEntitiesOfClass(Player.class, axis);
                if (players.size() > 0)
                {
                    this.target = players.get(0);
                }
            }
        } else
        {
            //used for dimension catalyst
            if (this.tickCount <= 80)
            {
                setColor(1F - (1F - getTargetColorR()) * (float) tickCount / 80F, 1F - (1F - getTargetColorG()) * (float) tickCount / 80F, 1F - (1F - getTargetColorB()) * (float) tickCount / 80F);
            }
            if (this.tickCount >= 160)
            {
                if (stackList != null)
                {
                    double d0 = (getX());
                    double d1 = (getY() + 0.5D + 4D);
                    double d2 = (getZ());
                    Collections.shuffle(VEC_OFFSET);
                    for (int i = 0; i < stackList.size(); i++)
                    {
                        var vec = VEC_OFFSET.get(i);
                        var item = stackList.get(i).getCategory().copy();
                        ItemEntity entity = new ItemEntity(level, d0 + vec.x, d1 + vec.y, d2 + vec.z, item);
                        entity.setPickUpDelay(30);
                        entity.setNoGravity(true);
                        entity.setDeltaMovement(Vec3.ZERO);
                        level.addFreshEntity(entity);
                    }
                }
                var catalyst = level.getBlockEntity(new BlockPos(position().add(-0.5D, 3.3D, -0.5D)));
                if (catalyst instanceof TileDimensionCatalyst c)
                {
                    c.setActive(false);
                }
                this.discard();
            }
            for (int i = -1; i <= 1; i++)
            {
                for (int j = -1; j <= 1; j++)
                {
                    WispParticleData data = WispParticleData.wisp(0.5F, 0.1F, 0.85F, 0.1F, 1F);
                    ClientProxy.INSTANCE.addParticleForceNear(level, data, getX() + i, getY() + 4.5F, getZ() + j, 0, 0, 0);

                }
            }
        }
    }

    public float getColorR()
    {
        return entityData.get(COLOR_R);
    }

    public float getColorG()
    {
        return entityData.get(COLOR_G);
    }

    public float getColorB()
    {
        return entityData.get(COLOR_B);
    }

    public float getTargetColorR()
    {
        return entityData.get(TARGET_COLOR_R);
    }

    public float getTargetColorG()
    {
        return entityData.get(TARGET_COLOR_G);
    }

    public float getTargetColorB()
    {
        return entityData.get(TARGET_COLOR_B);
    }

    public float getSpeedModifier()
    {
        return entityData.get(SPEED_MODIFIER);
    }

    public float getBeamDamage()
    {
        return entityData.get(DAMAGE);
    }

    public void setColorR(float f)
    {
        entityData.set(COLOR_R, f);
    }

    public void setColorG(float f)
    {
        entityData.set(COLOR_G, f);
    }

    public void setColorB(float f)
    {
        entityData.set(COLOR_B, f);
    }

    public void setTargetColorR(float f)
    {
        entityData.set(TARGET_COLOR_R, f);
    }

    public void setTargetColorG(float f)
    {
        entityData.set(TARGET_COLOR_G, f);
    }

    public void setTargetColorB(float f)
    {
        entityData.set(TARGET_COLOR_B, f);
    }

    public void setSpeedModifier(float f)
    {
        entityData.set(SPEED_MODIFIER, f);
    }

    public void setBeamDamage(float f)
    {
        entityData.set(DAMAGE, f);
    }

    public void setColor(float r, float g, float b)
    {
        this.setColorR(r > 1 || r < 0 ? 1 : r);
        this.setColorG(g > 1 || g < 0 ? 1 : g);
        this.setColorB(b > 1 || b < 0 ? 1 : b);
    }

    public float[] getBeamColor()
    {
        return new float[]{this.getColorR(), this.getColorG(), this.getColorB()};
    }
}
