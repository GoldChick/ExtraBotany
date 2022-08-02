package chick.extrabotany.datagen.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;

import javax.annotation.Nullable;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider
{
    public RecipeProvider(DataGenerator p_125973_)
    {
        super(p_125973_);
    }
    protected static class SuperFinishedRecipe implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final ItemStack output;
        private final Ingredient[] inputs;

        private SuperFinishedRecipe(ResourceLocation id, ItemStack output, Ingredient... inputs)
        {
            this.id = id;
            this.output = output;
            this.inputs = inputs;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("output", ItemNBTHelper.serializeStack(output));
            JsonArray ingredients = new JsonArray();
            for (Ingredient ingr : inputs)
            {
                ingredients.add(ingr.toJson());
            }
            json.add("ingredients", ingredients);
        }

        @Override
        public ResourceLocation getId()
        {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipeTypes.PETAL_SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement()
        {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId()
        {
            return null;
        }
    }
}
