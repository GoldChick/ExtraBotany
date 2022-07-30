package chick.extrabotany.datagen.recipes;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;

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
        return "extrabotany runic altar recipes";
    }

    @Override
    public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        Ingredient mashed_potato = Ingredient.of(ModItems.GILDED_MASHED_POTATO.get());
        Ingredient element_steel = Ingredient.of(vazkii.botania.common.item.ModItems.elementium);
        Ingredient nightmare = Ingredient.of(ModItems.NIGHTMAREFUEL_PROP.get());
        Ingredient spirit = Ingredient.of(ModItems.SPIRITFUEL_PROP.get());
        Ingredient spirit_frag = Ingredient.of(ModItems.SPIRIT_PROP.get());

        consumer.accept(new RuneRecipe(idFor("gildedpotato"), new ItemStack(ModItems.GILDED_POTATO.get()), 2000, Ingredient.of(Items.POTATO), Ingredient.of(Items.POTATO), Ingredient.of(Items.POTATO), Ingredient.of(Items.POTATO), Ingredient.of(Items.GOLD_INGOT)));
        consumer.accept(new RuneRecipe(idFor("shadowium1"), new ItemStack(ModItems.SHADOW_INGOT.get()), 1000, mashed_potato, element_steel, nightmare, nightmare, nightmare));
        consumer.accept(new RuneRecipe(idFor("shadowium2"), new ItemStack(ModItems.SHADOW_INGOT.get(), 3), 5000, mashed_potato, element_steel, element_steel, element_steel, nightmare, nightmare, nightmare, nightmare, nightmare));
        consumer.accept(new RuneRecipe(idFor("photonium1"), new ItemStack(ModItems.GILDED_POTATO.get()), 800, Ingredient.of(Items.POTATO), Ingredient.of(Items.GOLD_NUGGET)));
        consumer.accept(new RuneRecipe(idFor("photonium2"), new ItemStack(ModItems.GILDED_POTATO.get()), 800, Ingredient.of(Items.POTATO), Ingredient.of(Items.GOLD_NUGGET)));

    }

    private static ResourceLocation idFor(String s)
    {
        return new ResourceLocation(ExtraBotany.MODID, "runic_altar/" + s);
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
