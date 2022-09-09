package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.api.block.ISubTilePassiveFlower;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public class SubTileGeminiOrchid extends TileEntityGeneratingFlower
{
    private static final BlockPos[] OFFSETS = {new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};

    private static final int RANGE = 2;
    private final ISubTilePassiveFlower flower;
    public SubTileGeminiOrchid(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.GEMINIORCHID, pos, state);
        flower = IXplatAbstractions.INSTANCE.findPassiveFlower(this);
    }

    @Override
    public int getMaxMana()
    {
        return 1000;
    }

    @Override
    public int getColor()
    {
        return 0xFFFF00;
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        if (!getLevel().isClientSide)
        {
            int tempMax = -114514;
            int tempMin = 114514;
            for (int i = 0; i < OFFSETS.length; i++)
            {
                BlockPos pos = this.getEffectivePos().offset(OFFSETS[i]);
                var block = this.getLevel().getFluidState(pos).getType();
                if (block != null && !(block instanceof EmptyFluid))
                {
                    if (block instanceof Fluid)
                    {
                        tempMax = Math.max(tempMax, block.getAttributes().getTemperature(getLevel(), pos));
                        tempMin = Math.min(tempMin, block.getAttributes().getTemperature(getLevel(), pos));

                    } else if (block instanceof IFluidBlock)
                    {
                        IFluidBlock fluid = (IFluidBlock) block;
                        tempMax = Math.max(tempMax, fluid.getFluid().getAttributes().getTemperature(getLevel(), pos));
                        tempMin = Math.min(tempMin, fluid.getFluid().getAttributes().getTemperature(getLevel(), pos));
                    }
                }
            }
            if (getMana() < getMaxMana() && tempMax > tempMin)
            {
               flower.addPassiveTicks();
                 if (flower.getPassiveTicks() % 8 == 0)
                {
                    addMana((tempMax - tempMin) / 100 > 0 ? (tempMax - tempMin) / 100 : 1);
                }
                sync();
            }
        }
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putInt(flower.getTagPassiveTicks(), flower.getPassiveTicks());
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        flower.setPassiveTicks(cmp.getInt(flower.getTagPassiveTicks()));
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
