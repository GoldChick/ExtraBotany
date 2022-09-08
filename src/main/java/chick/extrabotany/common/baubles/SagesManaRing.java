package chick.extrabotany.common.baubles;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;

import javax.annotation.Nonnull;

public class SagesManaRing extends ItemManaRing
{
    private static final int MAX_MANA = Integer.MAX_VALUE - 1;

    public SagesManaRing(Properties props)
    {
        super(props.stacksTo(1));
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> stacks)
    {
        if (allowdedIn(tab))
        {
            stacks.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);
            setMana(full, MAX_MANA);
            stacks.add(full);
        }
    }

    public static class GreaterManaItem extends ItemManaRing.ManaItem
    {
        public GreaterManaItem(ItemStack stack)
        {
            super(stack);
        }

        @Override
        public int getMaxMana()
        {
            return MAX_MANA * stack.getCount();
        }
    }
}
