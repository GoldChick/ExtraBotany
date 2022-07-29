package chick.extrabotania.datagen.recipes;

import chick.extrabotania.ExtraBotania;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.ModTags;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModRunicAltarRecipes extends RecipeProvider
{
    public ModRunicAltarRecipes(DataGenerator gen)
    {
        super(gen);
    }

    @Override
    public String getName()
    {
        return "ExtraBotania runic altar recipes";
    }

    @Override
    public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        final int costTier3 = 12000;

        Ingredient manaDiamond = Ingredient.of(ModTags.Items.GEMS_MANA_DIAMOND);
        Ingredient air = Ingredient.of(ModItems.runeAir);
        consumer.accept(new RuneRecipe(idFor("lust"), new ItemStack(chick.extrabotania.common.ModItems.OBSIDIAN_APPLE.get()), costTier3, manaDiamond, manaDiamond, air));
    }

    private static ResourceLocation idFor(String s)
    {
        return new ResourceLocation(ExtraBotania.MODID, "runic_altar/" + s);
    }

    //class for runes
    protected static class RuneRecipe implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final ItemStack output;
        private final int mana;
        private final Ingredient[] inputs;

        protected RuneRecipe(ResourceLocation id, ItemStack output, int mana, Ingredient... inputs)
        {
            this.id = id;
            this.output = output;
            this.mana = mana;
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
            json.addProperty("mana", mana);
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
            return ModRecipeTypes.RUNE_SERIALIZER;
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
