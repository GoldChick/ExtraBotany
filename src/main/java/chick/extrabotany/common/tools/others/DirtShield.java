package chick.extrabotany.common.tools.others;

import chick.extrabotany.common.entities.projectile.relic_projectile.EntityAction;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class DirtShield extends Item
{
    private final Block block;

    public DirtShield(Properties prop)
    {
        super(prop);
        block = Blocks.DIRT;
    }

    public DirtShield(Properties prop, Block block)
    {
        super(prop);
        this.block = block;
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        var pos = ctx.getClickedPos();
        if (!ctx.getLevel().isClientSide)
            for (int i = -1; i <= 1; i++)
            {
                for (int j = 1; j <= 3; j++)
                {
                    var dir = ctx.getPlayer().getDirection();
                    BlockPos blockPos = BlockPos.ZERO;
                    if (dir == Direction.NORTH || dir == Direction.SOUTH)
                    {
                        blockPos = pos.offset(i, j, 0);
                    } else if (dir == Direction.EAST || dir == Direction.WEST)
                    {
                        blockPos = pos.offset(0, j, i);
                    }
                    BlockPos finalBlockPos = blockPos;
                    if (ctx.getLevel().getBlockState(finalBlockPos).getBlock() != Blocks.AIR)
                    {
                        continue;
                    }
                    var vec = new Vec3(finalBlockPos.getX() + 0.5D, finalBlockPos.getY(), finalBlockPos.getZ() + 0.5D);

                    var item = new ItemEntity(ctx.getLevel(), vec.x, vec.y, vec.z, new ItemStack(Blocks.DIRT));
                    item.setNoGravity(true);
                    item.setNeverPickUp();
                    item.setDeltaMovement(Vec3.ZERO);
                    ctx.getLevel().addFreshEntity(item);
                    var action = new EntityAction(ctx.getLevel(), vec, item, 10, () -> () ->
                    {
                        if (ctx.getLevel().setBlock(finalBlockPos, block.defaultBlockState(), 3))
                            Minecraft.getInstance().level.playLocalSound(finalBlockPos, SoundEvents.ROOTED_DIRT_PLACE, SoundSource.PLAYERS, 1, 1, true);
                    });
                    ctx.getLevel().addFreshEntity(action);
                }
            }
        return InteractionResult.SUCCESS;
    }
}
