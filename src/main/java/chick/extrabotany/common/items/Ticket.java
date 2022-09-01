package chick.extrabotany.common.items;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.entities.ego.EntityEGO;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;


import javax.annotation.Nonnull;

public class Ticket extends Item
{
    public Ticket(Properties prop)
    {
        super(prop);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        ItemStack stack = ctx.getItemInHand();

        if (stack.is(ModItems.TICKET.get()))
        {
            return EntityEGO.spawn(ctx.getPlayer(), stack, ctx.getLevel(), ctx.getClickedPos())
                    ? InteractionResult.SUCCESS
                    : InteractionResult.FAIL;
        }
        return super.useOn(ctx);
    }
}
