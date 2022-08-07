package chick.extrabotany.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public abstract class SubTilePassive extends TileEntityGeneratingFlower
{
    public SubTilePassive(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }
    protected int passiveDecayTicks;
}
