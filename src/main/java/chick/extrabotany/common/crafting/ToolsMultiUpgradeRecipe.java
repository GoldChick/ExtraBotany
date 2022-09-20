package chick.extrabotany.common.crafting;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.crafting.RecipeSerializerBase;

import javax.annotation.Nonnull;

/**
 *  used for tools upgrading recipes like True Shadow Katana
 *  <p>output will inherit Enchantments from all materials</p>
 */
public class ToolsMultiUpgradeRecipe extends ShapedRecipe
{
    public ToolsMultiUpgradeRecipe(ShapedRecipe compose)
    {
        super(compose.getId(), compose.getGroup(), compose.getWidth(), compose.getHeight(), compose.getIngredients(), compose.getResultItem());
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer inv)
    {
        ItemStack out = super.assemble(inv);
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.hasTag())
            {
                EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), out);
            }
        }
        return out;
    }

    @Override
    public boolean canCraftInDimensions(int width, int length)
    {
        return width > 2 || length > 2;
    }

    public static final RecipeSerializer<ToolsMultiUpgradeRecipe> SERIALIZER = new Serializer();

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }

    private static class Serializer extends RecipeSerializerBase<ToolsMultiUpgradeRecipe>
    {
        @Override
        public @NotNull ToolsMultiUpgradeRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json)
        {
            return new ToolsMultiUpgradeRecipe(SHAPED_RECIPE.fromJson(recipeId, json));
        }

        @Override
        public ToolsMultiUpgradeRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer)
        {
            return new ToolsMultiUpgradeRecipe(SHAPED_RECIPE.fromNetwork(recipeId, buffer));
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull ToolsMultiUpgradeRecipe recipe)
        {
            SHAPED_RECIPE.toNetwork(buffer, recipe);
        }
    }
}
