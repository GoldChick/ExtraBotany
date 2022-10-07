package chick.extrabotany.api.craft;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;

//copied from IPureDaisyRecipe
public interface IStonesiaRecipe extends Recipe<Container>
{
    ResourceLocation TYPE_ID = new ResourceLocation(BotaniaAPI.MODID, "stone_sia");

    /**
     * This gets called every tick, please be careful with your checks.
     */
    boolean matches(Level world, BlockPos pos, TileEntitySpecialFlower pureDaisy, BlockState state);

    /**
     * Returns true if the block was placed (and if the Stone Sia should do particles and stuffs).
     * Should only place the block if !world.isRemote, but should return true if it would've placed
     * it otherwise. You may return false to cancel the normal particles and do your own.
     */
    boolean set(Level world, BlockPos pos, TileEntitySpecialFlower pureDaisy);

    StateIngredient getInput();

    BlockState getOutputState();

    int getTime();

    @Override
    default RecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean matches(Container p_77569_1_, Level p_77569_2_) {
        return false;
    }

    @Override
    default ItemStack assemble(Container p_77572_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return false;
    }

    @Override
    default ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
