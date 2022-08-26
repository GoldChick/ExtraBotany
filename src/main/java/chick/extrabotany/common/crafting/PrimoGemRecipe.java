package chick.extrabotany.common.crafting;

import chick.extrabotany.common.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PrimoGemRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<PrimoGemRecipe> SERIALIZER = new SimpleRecipeSerializer<>(PrimoGemRecipe::new);

    public PrimoGemRecipe(ResourceLocation idin)
    {
        super(idin);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        int validCount = 0;
        int count = 0;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            var stack = inv.getItem(i);
            if (!stack.isEmpty())
            {
                count++;
                if (stack.getItem() == ModItems.PRIMO_GEM.get() && stack.getCount() >= 32)
                {
                    if (i % 2 == 0)
                    {
                        validCount++;
                    }
                }
            }
        }
        if (validCount == 1 && count == 5)
        {
            validCount = 0;
            for (int i = 1; i < inv.getContainerSize(); i += (i == 3 || i == 4) ? 1 : 2)
            {
                var stack = inv.getItem(i);
                if (!stack.isEmpty() && stack.getItem() == ModItems.PRIMO_GEM.get() && stack.getCount() >= 32)
                    validCount++;
            }
        }
        return validCount == 5 && count == 5;
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv)
    {
        NonNullList<ItemStack> list = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            if (!inv.getItem(i).isEmpty())
            {
                inv.getItem(i).shrink(31);
            }
        }
        return list;
    }

    @NotNull
    @Override
    public ItemStack assemble(@NotNull CraftingContainer inv)
    {
        return (inv.getItem(0).isEmpty()) ? new ItemStack(ModItems.INTERTWINED_FATE.get()) : new ItemStack(ModItems.ACQUAINT_FATE.get());
    }

    @Override
    public boolean canCraftInDimensions(int length, int width)
    {
        return length > 2 && width > 2;
    }

    @NotNull
    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
