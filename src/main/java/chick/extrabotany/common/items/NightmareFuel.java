package chick.extrabotany.common.items;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class NightmareFuel extends Item
{
    public NightmareFuel(Properties prop)
    {
        super(prop);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (entity instanceof Player)
        {
            ((Player) entity).getInventory().add(new ItemStack(ModItems.SPIRIT_FUEL.get()));
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return 200 * 10;
    }
}
