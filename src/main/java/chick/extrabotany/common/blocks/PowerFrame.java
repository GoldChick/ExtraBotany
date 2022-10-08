package chick.extrabotany.common.blocks;

import chick.extrabotany.common.blocks.tile.TilePowerFrame;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.BlockModWaterloggable;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.helper.InventoryHelper;
import vazkii.botania.xplat.IXplatAbstractions;

public class PowerFrame extends BlockModWaterloggable implements EntityBlock
{
    public PowerFrame(Properties prop)
    {
        super(prop);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        TilePowerFrame frame = (TilePowerFrame) level.getBlockEntity(pos);
        ItemStack stack = player.getItemInHand(hand);
        if (frame != null)
        {
            if (!frame.getItemHandler().isEmpty())
            {
                InventoryHelper.withdrawFromInventory(frame, player);
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(frame);
                return InteractionResult.SUCCESS;
            } else if (!stack.isEmpty() && (IXplatAbstractions.INSTANCE.findManaItem(stack) != null || chick.extrabotany.xplat.IXplatAbstractions.INSTANCE.findNatureOrbItem(stack) != null))
            {
                boolean result = frame.addItem(player, stack, hand);
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(frame);
                return result ? InteractionResult.SUCCESS : InteractionResult.PASS;
            }
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        if (!level.isClientSide)
        {
            return createTickerHelper(type, ModTiles.POWER_FRAME, TilePowerFrame::serverTick);
        } else
        {
            return createTickerHelper(type, ModTiles.POWER_FRAME, TilePowerFrame::clientTick);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new TilePowerFrame(pos, state);
    }


    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity te = level.getBlockEntity(pos);
            if (te instanceof TileSimpleInventory t)
            {
                Containers.dropContents(level, pos, t.getItemHandler());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }
}
