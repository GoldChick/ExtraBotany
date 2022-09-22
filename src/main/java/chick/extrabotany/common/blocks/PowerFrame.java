package chick.extrabotany.common.blocks;

import chick.extrabotany.common.blocks.tile.TilePowerFrame;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.BlockModWaterloggable;

public class PowerFrame extends BlockModWaterloggable implements EntityBlock
{
    public PowerFrame(Properties prop)
    {
        super(prop);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModTiles.POWER_FRAME, TilePowerFrame::serverTick);
/*
        if (!level.isClientSide)
        {//TODO:THE 2nd type is the type in ModTiles
            return createTickerHelper(type, ModTiles.POWER_FRAME, TilePowerFrame::serverTick);
        } else
        {
            return createTickerHelper(type, ModTiles.POWER_FRAME, TilePowerFrame::clientTick);
        }

 */
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new TilePowerFrame(pos, state);
    }
}
