package chick.extrabotany.api.block;

import chick.extrabotany.api.cap.IPassiveFlowerCap;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

/**
 * used together with interface <b>IPassiveFlowerCap</b><p>
 * let your flower class extends this<p>
 * just to store ticks in nbt
 */
public abstract class SubTilePassiveFlower extends TileEntityGeneratingFlower
{
    private IPassiveFlowerCap flower;

    public SubTilePassiveFlower(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        if (getFlower() != null)
        {
            cmp.putInt(flower.getTagPassiveTicks(), flower.getPassiveTicks());
        }
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        if (getFlower() != null)
        {
            flower.setPassiveTicks(cmp.getInt(flower.getTagPassiveTicks()));
        }
    }

    protected IPassiveFlowerCap getFlower()
    {
        if (flower == null)
        {
            flower = IXplatAbstractions.INSTANCE.findPassiveFlower(this);
        }
        return flower;
    }
}
