package chick.extrabotany.common.blocks;

import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DimensionCatalyst extends Block implements EntityBlock
{
    public DimensionCatalyst(Properties prop)
    {
        super(prop);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new TileDimensionCatalyst(pos,state);
    }
}
