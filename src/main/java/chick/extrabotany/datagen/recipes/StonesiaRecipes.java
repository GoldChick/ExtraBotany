package chick.extrabotany.datagen.recipes;

import chick.extrabotany.common.crafting.StonesiaRecipe;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.crafting.StateIngredientHelper;
import vazkii.botania.data.recipes.BotaniaRecipeProvider;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static chick.extrabotany.ExtraBotany.prefixRl;

public class StonesiaRecipes extends BotaniaRecipeProvider
{
    public StonesiaRecipes(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<net.minecraft.data.recipes.FinishedRecipe> consumer)
    {
        consumer.accept(new FinishedRecipe(id("test_coal"), StateIngredientHelper.of(Blocks.DIAMOND_ORE), Blocks.STONE.defaultBlockState()));
    }

    @Override
    public @NotNull String getName()
    {
        return "Extrabotany Stonesia Recipes";
    }

    private static ResourceLocation id(String path)
    {
        return prefixRl("stonesia/" + path);
    }

    protected static class FinishedRecipe implements net.minecraft.data.recipes.FinishedRecipe
    {
        public static final int DEFAULT_TIME = 150;
        public static final int DEFAULT_MANA = 114514;

        protected final ResourceLocation id;
        protected final StateIngredient input;
        protected final BlockState outputState;
        protected final int mana;
        protected final int time;
        @Nullable
        private final ResourceLocation function;

        public FinishedRecipe(ResourceLocation id, StateIngredient input, BlockState state)
        {
            this(id, input, state, DEFAULT_MANA);
        }

        public FinishedRecipe(ResourceLocation id, StateIngredient input, BlockState state, int mana)
        {
            this(id, input, state, mana, DEFAULT_TIME);
        }

        public FinishedRecipe(ResourceLocation id, StateIngredient input, BlockState state, int mana, int time)
        {
            this(id, input, state, mana, time, null);
        }

        public FinishedRecipe(ResourceLocation id, StateIngredient input, BlockState state, int mana, int time, @Nullable ResourceLocation function)
        {
            Preconditions.checkArgument(time >= 0, "Time must be nonnegative");
            this.id = id;
            this.input = input;
            this.outputState = state;
            this.mana = mana;
            this.time = time;
            this.function = function;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", input.serialize());
            json.add("output", StateIngredientHelper.serializeBlockState(outputState));
            if (time != DEFAULT_TIME)
            {
                json.addProperty("time", time);
            }
            json.addProperty("mana", mana);
            if (function != null)
            {
                json.addProperty("success_function", function.toString());
            }
        }

        @Override
        public @NotNull ResourceLocation getId()
        {
            return id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType()
        {
            return StonesiaRecipe.SERIALIZER;
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
