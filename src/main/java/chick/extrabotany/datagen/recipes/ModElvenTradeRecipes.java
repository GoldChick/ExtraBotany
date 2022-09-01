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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.helper.ItemNBTHelper;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


public class ModElvenTradeRecipes extends RecipeProvider
{
    public ModElvenTradeRecipes(DataGenerator gen)
    {
        super(gen);
    }

    @Override
    public void buildCraftingRecipes(Consumer<net.minecraft.data.recipes.FinishedRecipe> consumer)
    {
        consumer.accept(new ElvenTrade(id("spiritfuel"), new ItemStack(ModItems.SPIRIT_FUEL.get()), Ingredient.of(ModItems.NIGHTMARE_FUEL.get())));
        consumer.accept(new ElvenTrade(id("spirit"), new ItemStack(ModItems.SPIRIT_FRAG.get()), Ingredient.of(ModItems.SPIRIT_FUEL.get())));
    }

    private static ResourceLocation id(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, "elven_trade/" + path);
    }

    @Override
    public String getName()
    {
        return "extrabotany elven trade recipes";
    }

    private static class ElvenTrade implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final List<Ingredient> inputs;
        private final List<ItemStack> outputs;

        public ElvenTrade(ResourceLocation id, ItemStack output, Ingredient... inputs)
        {
            this(id, Arrays.asList(inputs), Collections.singletonList(output));
        }

        protected ElvenTrade(ResourceLocation id, List<Ingredient> inputs, List<ItemStack> outputs)
        {
            this.id = id;
            this.inputs = inputs;
            this.outputs = outputs;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            JsonArray in = new JsonArray();
            for (Ingredient ingr : inputs)
            {
                in.add(ingr.toJson());
            }

            JsonArray out = new JsonArray();
            for (ItemStack s : outputs)
            {
                out.add(ItemNBTHelper.serializeStack(s));
            }

            json.add("ingredients", in);
            json.add("output", out);
        }

        @Override
        public ResourceLocation getId()
        {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipeTypes.ELVEN_TRADE_SERIALIZER;
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