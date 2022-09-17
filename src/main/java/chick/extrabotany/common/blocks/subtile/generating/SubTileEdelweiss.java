package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.client.core.proxy.ClientProxy;

public class SubTileEdelweiss extends TileEntityGeneratingFlower
{
    private static final String TAG_BURN_TIME = "burnTime";
    private static final int RANGE = 2;
    private int burnTime = 0;

    public SubTileEdelweiss(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.EDELWEISS, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();

        if (burnTime > 0)
        {
            burnTime--;
            emitParticle(ParticleTypes.ITEM_SNOWBALL, 0, 0, 0, 0, 0, 0);
        }

        if (!level.isClientSide && isValidBinding() && burnTime == 0)
        {
            for (SnowGolem golem : level.getEntitiesOfClass(SnowGolem.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
            {
                if (!golem.isRemoved())
                {
                    Vec3 vec = golem.position();
                    ClientProxy.INSTANCE.addParticleForceNear(level, ParticleTypes.SNOWFLAKE, vec.x, vec.y, vec.z, 0, 0, 0);
                    golem.kill();
                    //make it no drop lol
                    golem.setBaby(true);
                    addMana(1600);
                    burnTime += 40;
                    var item = new ItemEntity(level, vec.x, vec.y, vec.z, new ItemStack(Items.PUMPKIN_SEEDS));
                    level.addFreshEntity(item);
                    break;
                }
            }
            for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
            {
                if (!itemEntity.isRemoved())
                {
                    var stack = itemEntity.getItem();
                    if (stack.is(Items.SNOWBALL))
                    {
                        itemEntity.discard();
                        addMana(80);
                        burnTime += 5;
                        break;

                    } else if (stack.is(Items.SNOW_BLOCK))
                    {
                        itemEntity.discard();
                        addMana(320);
                        burnTime += 12;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getMaxMana()
    {
        return 2500;
    }

    @Override
    public int getColor()
    {
        return 0X4169E1;
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putInt(TAG_BURN_TIME, burnTime);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        burnTime = cmp.getInt(TAG_BURN_TIME);
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
