package chick.extrabotany.common.crafting;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.brews.ItemBrewBase;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import vazkii.botania.api.brew.IBrewItem;

public class LenPotionRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<LenPotionRecipe> SERIALIZER = new SimpleRecipeSerializer<>(LenPotionRecipe::new);

    public LenPotionRecipe(ResourceLocation idIn)
    {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        boolean foundBrew = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty())
            {
                if (stack.getItem() == ModItems.COCK_TAIL.get() && !foundBrew)
                {
                    foundBrew = true;
                } else if (!foundItem)
                {
                    if (stack.getItem() == ModItems.LEN_POTION.get())
                    {
                        foundItem = true;
                    } else
                    {
                        return false;
                    }
                }
            }
        }
        return foundBrew && foundItem;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv)
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == ModItems.COCK_TAIL.get())
            {
                nonnulllist.set(i, new ItemStack(ModItems.EMPTY_BOTTLE.get()));
            }
        }
        return nonnulllist;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv)
    {
        ItemStack brewstack = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty())
            {
                if (stack.getItem() == ModItems.COCK_TAIL.get() && brewstack.isEmpty())
                {
                    brewstack = stack;
                }
            }
        }
        IBrewItem brew = (IBrewItem) brewstack.getItem();
        ItemStack lens = new ItemStack(ModItems.LEN_POTION.get());
        ItemBrewBase.setBrew(lens, brew.getBrew(brewstack));
        return lens;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width > 1 || height > 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
