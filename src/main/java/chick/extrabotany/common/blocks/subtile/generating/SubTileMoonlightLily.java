package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.api.block.TilePassiveFlower;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileMoonlightLily extends TilePassiveFlower
{
    private static final int RANGE = 2;
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
            if (getFlower() != null)
            {
                if (getLevel().isNight())
                {
                    if (getMaxMana() == getMana())
                    {
                        particle = false;
                    } else
                    {
                        getFlower().addPassiveTicks();
                        particle = true;
                        addMana(1);
                    }
                    sync();
                } else
                {
                    particle = false;
                    sync();
                }
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
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        particle = cmp.getBoolean("moonB");
    }


    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
