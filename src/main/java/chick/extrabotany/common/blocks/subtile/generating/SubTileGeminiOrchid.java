package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.api.block.TilePassiveFlower;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileGeminiOrchid extends TilePassiveFlower
{
    private static final BlockPos[] OFFSETS = {new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};

    private static final int RANGE = 2;

    public SubTileGeminiOrchid(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.GEMINIORCHID, pos, state);
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
                //TODO:fluids的判定？
                BlockPos pos = this.getEffectivePos().offset(OFFSETS[i]);
                var block = this.getLevel().getFluidState(pos).getType();
                if (!(block instanceof EmptyFluid))
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
            if (getFlower() != null)
            {
                if (getMana() < getMaxMana() && tempMax > tempMin)
                {
                    getFlower().addPassiveTicks();
                    if (getFlower().getPassiveTicks() % 8 == 0)
                    {
                        addMana((tempMax - tempMin) / 100 > 0 ? (tempMax - tempMin) / 100 : 1);
                    }
                    sync();
                }
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
