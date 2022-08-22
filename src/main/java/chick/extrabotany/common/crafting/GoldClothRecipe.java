package chick.extrabotany.common.crafting;

import chick.extrabotany.common.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.xplat.IXplatAbstractions;


public class GoldClothRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<GoldClothRecipe> SERIALIZER = new SimpleRecipeSerializer<>(GoldClothRecipe::new);

    public GoldClothRecipe(ResourceLocation idIn)
    {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        boolean foundGoldCloth = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty())
            {
                if (stack.getItem() == ModItems.GOLD_CLOTH.get() && !foundGoldCloth)
                {
                    foundGoldCloth = true;
                } else if (IXplatAbstractions.INSTANCE.findRelic(stack) != null && !foundItem)
                {
                    foundItem = true;
                } else
                {
                    return false;
                }
            }
        }
        return foundGoldCloth && foundItem;
    }

    @NotNull
    @Override
    public ItemStack assemble(CraftingContainer inv)
    {
        ItemStack item = ItemStack.EMPTY;

        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && IXplatAbstractions.INSTANCE.findRelic(stack) != null)
            {
                item = stack;
                break;
            }
        }

        var copy = item.copy();
        ItemNBTHelper.removeEntry(copy, "soulbindUUID");
        return copy;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public SimpleRecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
