package chick.extrabotany.common.blocks.subtile.generating;


import chick.extrabotany.api.block.ISubTilePassiveFlower;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public class SubTileSunshineLily extends TileEntityGeneratingFlower
{
    private static final int RANGE = 2;
    private boolean particle = false;
    private final ISubTilePassiveFlower flower;

    public SubTileSunshineLily(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.SUNSHINELILY, pos, state);
        flower = IXplatAbstractions.INSTANCE.findPassiveFlower(this);
    }

    @Override
    public int getMaxMana()
    {
        return 200;
    }

    @Override
    public int getColor()
    {
        return 0xFFA500;
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        if (!getLevel().isClientSide)
        {
            if (getLevel().isDay())
            {
                if (getMana() == getMaxMana())
                {
                    particle = false;
                } else
                {
                    flower.addPassiveTicks();
                    particle = true;
                    addMana(1);
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
        cmp.putBoolean("sunB", particle);
        cmp.putInt(flower.getTagPassiveTicks(), flower.getPassiveTicks());
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        particle = cmp.getBoolean("sunB");
        flower.setPassiveTicks(cmp.getInt(flower.getTagPassiveTicks()));
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
