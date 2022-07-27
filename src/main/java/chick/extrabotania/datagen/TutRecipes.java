package chick.extrabotania.datagen;

import chick.extrabotania.registration.ModItems;
import chick.extrabotania.registration.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static chick.extrabotania.ExtraBotania.MODID;

public class TutRecipes extends RecipeProvider
{
    public TutRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.YLG_ORE.get()),ModItems.YLG_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(Registration.YLG_ORE.get()))
                .save(consumer, "mysterious_ingot1");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_YLG_CHUNK.get()), ModItems.YLG_INGOT.get(), 0.0f, 100)
                .unlockedBy("has_chunk", has(ModItems.RAW_YLG_CHUNK.get()))
                .save(consumer, "mysterious_ingot2");
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_APPLE.get())
                .pattern(" x ")
                .pattern("x#x")
                .pattern(" x ")
                .define('x', Tags.Items.OBSIDIAN)
                .define('#', Items.APPLE)
                .group(MODID)
                .unlockedBy("obsidian_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OBSIDIAN))
                .save(consumer,"obsidian_apple");
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_SWORD.get())
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" x ")
                .define('x', Items.STICK)
                .define('#', Items.OBSIDIAN)
                .group(MODID)
                .unlockedBy("obsidian_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OBSIDIAN))
                .save(consumer,"obsidian_sword");

    }
}