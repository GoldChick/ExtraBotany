package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.api.block.ISubTileDecay;
import chick.extrabotany.common.blocks.SubTilePassive;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileMoonlightLily extends SubTilePassive implements ISubTileDecay
{
    private static final int RANGE = 2;
    private static final int DECAY_TIME = 72000;
    private boolean particle = false;

    public SubTileMoonlightLily(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.MOONLIGHTLILY, pos, state);
    }

    @Override
    public int getMaxMana()
    {
        return 200;
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
            if (getLevel().isNight())
            {
                if (getMaxMana() == getMana())
                {
                    particle = false;
                } else
                {
                    if (++passiveDecayTicks > DECAY_TIME)
                    {
                        getLevel().destroyBlock(getBlockPos(), false);
                        if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(getLevel(), getBlockPos()))
                        {
                            getLevel().setBlockAndUpdate(getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                        }
                    } else
                    {
                        particle = true;
                        addMana(1);
                    }
                }
                sync();
            } else
            {
                particle = false;
                sync();
            }
        } else if (getLevel().isClientSide)
        {
            if (particle && getLevel().random.nextInt(10) == 0)
            {
                emitParticle(ParticleTypes.FLAME, 0.4 + Math.random() * 0.2, 0.7, 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putBoolean("moonB", particle);
        cmp.putInt("moonI", passiveDecayTicks);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        particle = cmp.getBoolean("moonB");
        passiveDecayTicks = cmp.getInt("moonI");
    }

    @Nullable
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
