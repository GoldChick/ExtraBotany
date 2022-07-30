package chick.extrabotany.common.tools.items;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class NightmareFuel extends Item
{
    public NightmareFuel(Properties p_41383_)
    {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (entity instanceof Player)
        {
            ((Player) entity).getInventory().add(new ItemStack(ModItems.SPIRITFUEL_PROP.get()));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
