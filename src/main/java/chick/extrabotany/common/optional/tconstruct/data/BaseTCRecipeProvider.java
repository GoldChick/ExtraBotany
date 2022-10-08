package chick.extrabotany.common.optional.tconstruct.data;

import chick.extrabotany.ExtraBotany;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;

import java.util.function.Consumer;

public abstract class BaseTCRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper
{
    public BaseTCRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected abstract void buildCraftingRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract String getName();

    @Override
    public String getModId()
    {
        return ExtraBotany.MODID;
    }
}