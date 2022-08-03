package chick.extrabotany.common.items;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import vazkii.botania.common.block.tile.mana.TilePool;

import javax.annotation.Nonnull;

public class EmptyGlassBottle extends Item
{

    public EmptyGlassBottle(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        // ItemStack stack = ctx.getItemInHand();
        var tile = ctx.getLevel().getBlockEntity(ctx.getClickedPos());
        if (tile instanceof TilePool)
        {
            TilePool pool = (TilePool) tile;
            if (ctx.getLevel().isClientSide && pool.getCurrentMana() >= 25000)
            {
                pool.receiveMana(-25000);
                if (!ctx.getPlayer().isCreative())
                {
                    ctx.getItemInHand().shrink(1);
                }
                //ctx.getPlayer().getInventory().add(new ItemStack(ModItems.manadrink));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
