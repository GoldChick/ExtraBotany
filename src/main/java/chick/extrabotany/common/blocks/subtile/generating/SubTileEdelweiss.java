package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

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
        }

        if (isValidBinding() && burnTime == 0)
        {
            for (SnowGolem golem : level.getEntitiesOfClass(SnowGolem.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
            {
                if (!golem.isRemoved())
                {
                    golem.discard();
                    addMana(1600);
                    burnTime += 20;
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
                        burnTime += 10;
                        break;
                    }
                }
            }
            if (burnTime > 0)
            {
                emitParticle(ParticleTypes.ITEM_SNOWBALL, 0, 0, 0, 0, 0, 0);
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
    //TODO:check 雪绒花
}
