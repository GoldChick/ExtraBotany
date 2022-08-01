package chick.extrabotany.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

import static chick.extrabotany.common.blocks.ModSubtiles.SUNSHINELILY;

public class SubTileSunshineLily extends TileEntityGeneratingFlower
{
    private static final int RANGE = 2;

    public SubTileSunshineLily(BlockPos pos, BlockState state)
    {
        super(SUNSHINELILY, pos, state);
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
        if (getLevel().isDay())
            addMana(1);
    }


    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
