package chick.extrabotany.common.crafting;

import chick.extrabotany.common.baubles.relic.AbstractMultiUpgradedBauble;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class BaublesMultiRemoveRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<BaublesMultiRemoveRecipe> SERIALIZER = new SimpleRecipeSerializer<>(BaublesMultiRemoveRecipe::new);

    public BaublesMultiRemoveRecipe(ResourceLocation rl)
    {
        super(rl);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            var stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractMultiUpgradedBauble bauble && bauble.getBaublesAmount(stack) > 0)
            {
                stacks.add(stack);
            }
        }
        return stacks.size() == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv)
    {
        ItemStack bauble = ItemStack.EMPTY;
        int index = 0;
        for (; index < inv.getContainerSize(); index++)
        {
            var stack = inv.getItem(index);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractMultiUpgradedBauble)
            {
                bauble = stack.copy();
                break;
            }
        }
        if (!bauble.isEmpty())
        {
            ((AbstractMultiUpgradedBauble) bauble.getItem()).removeBauble(bauble, index);
        }
        return bauble;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv)
    {
        NonNullList<ItemStack> list = super.getRemainingItems(inv);
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            var stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractMultiUpgradedBauble bauble)
            {
                var stacks = bauble.getBaubles(stack);
                list.set(i, stacks.get(Math.min(i, stacks.size() - 1)));
                break;
            }
        }
        return list;
    }

    @Override
    public boolean canCraftInDimensions(int w, int l)
    {
        return w > 1 && l > 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
