package chick.extrabotany.common.crafting;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.brews.ItemBrewBase;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.brew.IBrewItem;

public class GrenadeRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<GrenadeRecipe> SERIALIZER = new SimpleRecipeSerializer<>(GrenadeRecipe::new);

    public GrenadeRecipe(ResourceLocation idIn)
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
                } else if (stack.getItem() == Items.POPPED_CHORUS_FRUIT && !foundItem)
                {
                    foundItem = true;
                } else
                {
                    return false;
                }
            }
        }
        return foundBrew && foundItem;
    }

    @NotNull
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
                break;
            }
        }
        return nonnulllist;
    }

    @NotNull
    @Override
    public ItemStack assemble(CraftingContainer inv)
    {
        ItemStack brewstack = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == ModItems.COCK_TAIL.get())
            {
                brewstack = stack;
                break;
            }
        }
        IBrewItem brew = (IBrewItem) brewstack.getItem();
        ItemStack grenade = new ItemStack(ModItems.SPLASH_GRENADE.get(), ((ItemBrewBase) brewstack.getItem()).getSwigsLeft(brewstack));
        ItemBrewBase.setBrew(grenade, brew.getBrew(brewstack));
        return grenade;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
