/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package chick.extrabotany.datagen.recipes;

import chick.extrabotany.common.blocks.ModSubtiles;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nullable;

import java.util.function.Consumer;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public class ModPetalRecipes extends RecipeProvider
{
    public ModPetalRecipes(DataGenerator gen)
    {
        super(gen);
    }

    @Override
    public String getName()
    {
        return "Botania petal apothecary recipes";
    }

    @Override
    public void buildCraftingRecipes(Consumer<net.minecraft.data.recipes.FinishedRecipe> consumer)
    {
        //TODO: 其实可以直接继承bot的类的，限于时间地点原因就不这么做了
        Ingredient white = tagIngr("petals/white");
        Ingredient orange = tagIngr("petals/orange");
        Ingredient magenta = tagIngr("petals/magenta");
        Ingredient lightBlue = tagIngr("petals/light_blue");
        Ingredient yellow = tagIngr("petals/yellow");
        Ingredient lime = tagIngr("petals/lime");
        Ingredient pink = tagIngr("petals/pink");
        Ingredient gray = tagIngr("petals/gray");
        Ingredient lightGray = tagIngr("petals/light_gray");
        Ingredient cyan = tagIngr("petals/cyan");
        Ingredient purple = tagIngr("petals/purple");
        Ingredient blue = tagIngr("petals/blue");
        Ingredient brown = tagIngr("petals/brown");
        Ingredient green = tagIngr("petals/green");
        Ingredient red = tagIngr("petals/red");
        Ingredient black = tagIngr("petals/black");
        Ingredient runeWater = Ingredient.of(ModItems.runeWater);
        Ingredient runeFire = Ingredient.of(ModItems.runeFire);
        Ingredient runeEarth = Ingredient.of(ModItems.runeEarth);
        Ingredient runeAir = Ingredient.of(ModItems.runeAir);
        Ingredient runeSpring = Ingredient.of(ModItems.runeSpring);
        Ingredient runeSummer = Ingredient.of(ModItems.runeSummer);
        Ingredient runeAutumn = Ingredient.of(ModItems.runeAutumn);
        Ingredient runeWinter = Ingredient.of(ModItems.runeWinter);
        Ingredient runeMana = Ingredient.of(ModItems.runeMana);
        Ingredient runeLust = Ingredient.of(ModItems.runeLust);
        Ingredient runeGluttony = Ingredient.of(ModItems.runeGluttony);
        Ingredient runeGreed = Ingredient.of(ModItems.runeGreed);
        Ingredient runeSloth = Ingredient.of(ModItems.runeSloth);
        Ingredient runeWrath = Ingredient.of(ModItems.runeWrath);
        Ingredient runeEnvy = Ingredient.of(ModItems.runeEnvy);
        Ingredient runePride = Ingredient.of(ModItems.runePride);

        Ingredient redstoneRoot = Ingredient.of(ModItems.redstoneRoot);
        Ingredient pixieDust = Ingredient.of(ModItems.pixieDust);
        Ingredient manaPowder = Ingredient.of(ModItems.manaPowder);
        Ingredient gaiaSpirit = Ingredient.of(ModItems.lifeEssence);

        Ingredient spirit_frag = Ingredient.of(chick.extrabotany.common.ModItems.SPIRIT_FRAG.get());

        consumer.accept(make(ModSubtiles.sunshinelily, yellow, yellow, yellow, white));
        consumer.accept(make(ModSubtiles.moonlightlily, red, red, red, white));
        consumer.accept(make(ModSubtiles.omniviolet, blue, blue, purple, purple, runeMana, runeSpring, runeLust));
        consumer.accept(make(ModSubtiles.geminiorchid, yellow, yellow, yellow, orange, orange, orange, manaPowder, manaPowder));
        consumer.accept(make(ModSubtiles.reikarorchid, cyan, cyan, lightBlue, lightBlue, blue, runeEnvy, runeSloth, runePride));
        consumer.accept(make(ModSubtiles.bellflower, yellow, yellow, green, green, manaPowder));
        consumer.accept(make(ModSubtiles.bloodyenchantress, red, red, red, red, runeFire, runeSummer, runeWrath));

        consumer.accept(make(ModSubtiles.serenitian, purple, purple, blue, blue, gaiaSpirit, runeMana, runeSloth, runeGreed, redstoneRoot));
        consumer.accept(make(ModSubtiles.annoying, green,pink,pink,white,white,runeMana,spirit_frag));

    }

    protected static Ingredient tagIngr(String tag)
    {
        //ingrediant from Botania
        return Ingredient.of(TagKey.create(Registry.ITEM_REGISTRY, prefix(tag)));
    }

    protected static Petal make(ItemLike item, Ingredient... ingredients)
    {
        return new Petal(idFor(Registry.ITEM.getKey(item.asItem())), new ItemStack(item), ingredients);
    }

    protected static ResourceLocation idFor(ResourceLocation name)
    {
        return new ResourceLocation(name.getNamespace(), "petal_apothecary/" + name.getPath());
    }

    protected static class Petal implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final ItemStack output;
        private final Ingredient[] inputs;

        private Petal(ResourceLocation id, ItemStack output, Ingredient... inputs)
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
