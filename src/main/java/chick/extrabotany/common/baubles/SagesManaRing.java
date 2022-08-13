package chick.extrabotany.common.baubles;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;

import javax.annotation.Nonnull;
import java.util.List;

public class SagesManaRing extends ItemManaRing
{
    private static final int MAX_MANA = Integer.MAX_VALUE;

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

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        tooltip.add(new TextComponent("Mana:"
                + checkInt(String.valueOf(ItemNBTHelper.getInt(stack, "mana", 0)))
                + "/"
                + checkInt(Integer.toString(MAX_MANA)))
                .withStyle(ChatFormatting.AQUA)
        );
    }

    private String checkInt(String str)
    {
        String newStr = "";
        for (int i = str.length() - 1; i >= 0; i--)
        {
            newStr += str.charAt(i);
            if ((str.length() - i) % 3 == 0 && i != 0)
            {
                newStr += ",";
            }
        }
        return new StringBuffer(newStr).reverse().toString();
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
