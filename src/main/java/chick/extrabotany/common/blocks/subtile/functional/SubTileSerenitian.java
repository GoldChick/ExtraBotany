package chick.extrabotany.common.blocks.subtile.functional;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;

public class SubTileSerenitian extends TileEntityFunctionalFlower
{
    private static final int RANGE = 5;

    public SubTileSerenitian(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.SERENITIAN, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();

        if (redstoneSignal > 0)
            return;
        for (int dx = -RANGE; dx <= RANGE; dx++)
            for (int dz = -RANGE; dz <= RANGE; dz++)
            {
                BlockPos pos = getEffectivePos().offset(dx, 0, dz);
                var tile = level.getBlockEntity(pos);
                if (tile != null)
                {
                    var c = IXplatAbstractions.INSTANCE.findPassiveFlower(tile);
                    if (c != null && c.getPassiveTicks() >= 100)
                    {
                        c.setPassiveTicks(0);
                        c.serenitianWorks();
                    }
                }
            }
    }

    @Override
    public boolean acceptsRedstone()
    {
        return true;
    }

    @Override
    public int getColor()
    {
        return 0x000000;
    }

    @Override
    public int getMaxMana()
    {
        return 1;
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }


}
