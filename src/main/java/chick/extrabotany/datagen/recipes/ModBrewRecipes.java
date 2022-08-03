/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package chick.extrabotany.datagen.recipes;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.brews.ModBrews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.crafting.ModRecipeTypes;

import javax.annotation.Nullable;

import java.util.function.Consumer;

public class ModBrewRecipes extends RecipeProvider
{
    public ModBrewRecipes(DataGenerator gen)
    {
        super(gen);
    }

    @Override
    public String getName()
    {
        return "extrabotany Brew recipes";
    }

    @Override
    public void buildCraftingRecipes(Consumer<net.minecraft.data.recipes.FinishedRecipe> consumer)
    {
        consumer.accept(new Brew(idFor("revolution"), ModBrews.revolution, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SUGAR), Ingredient.of(Items.IRON_PICKAXE)));
        consumer.accept(new Brew(idFor("allmighty"), ModBrews.allmighty, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.GOLDEN_CARROT), Ingredient.of(Items.GHAST_TEAR),Ingredient.of(Items.GLOWSTONE_DUST)));
        consumer.accept(new Brew(idFor("deadpool"), ModBrews.deadpool, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.ROTTEN_FLESH), Ingredient.of(Items.BONE),Ingredient.of(Items.BLAZE_POWDER)));
        consumer.accept(new Brew(idFor("floating"), ModBrews.floating, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SUGAR), Ingredient.of(Items.CHORUS_FRUIT)));
        consumer.accept(new Brew(idFor("shell"), ModBrews.shell, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.GOLDEN_APPLE), Ingredient.of(Items.SCUTE),Ingredient.of(Items.OBSIDIAN)));
    }

    private static ResourceLocation idFor(String s)
    {
        return new ResourceLocation(ExtraBotany.MODID, "brew/" + s);
    }

    protected static class Brew implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final vazkii.botania.api.brew.Brew brew;
        private final Ingredient[] inputs;

        private Brew(ResourceLocation id, vazkii.botania.api.brew.Brew brew, Ingredient... inputs)
        {
            this.id = id;
            this.brew = brew;
            this.inputs = inputs;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.addProperty("brew", BotaniaAPI.instance().getBrewRegistry().getKey(brew).toString());
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
            return ModRecipeTypes.BREW_SERIALIZER;
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
