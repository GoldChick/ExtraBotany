package chick.extrabotany.common.blocks.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.SubTileDecay;
import chick.extrabotany.common.blocks.SubTilePassive;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileGeminiOrchid extends SubTilePassive implements SubTileDecay
{
    private static final BlockPos[] OFFSETS = {new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};

    private static final int RANGE = 2;
    private static final int DECAY_TIME = 72000;

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
                if (++passiveDecayTicks > DECAY_TIME)
                {
                    getLevel().destroyBlock(getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(getLevel(), getBlockPos()))
                    {
                        getLevel().setBlockAndUpdate(getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                    }
                } else if (ticksExisted % 8 == 0)
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
        cmp.putInt("geminiI", passiveDecayTicks);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        passiveDecayTicks = cmp.getInt("geminiI");
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    @Override
    public void setPassiveTicks(int x)
    {
        sync();
        this.passiveDecayTicks = x;
        if (x == 0)
        {
            emitParticle(ParticleTypes.ANGRY_VILLAGER, Math.random() * 0.2, 0.7, Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public int getPassiveTicks()
    {
        return passiveDecayTicks;
    }
}
