package chick.extrabotany.datagen.recipes;

<<<<<<< HEAD
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
=======
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.Registration;
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static chick.extrabotany.ExtraBotany.MODID;
<<<<<<< HEAD
import static vazkii.botania.common.block.ModSubtiles.bergamute;
=======
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33

public class ModCraftingTableRecipes extends RecipeProvider
{
    public ModCraftingTableRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    public String getName()
    {
        return "extrabotany craftingTable recipes";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
<<<<<<< HEAD
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.YLG_ORE.get()), ModItems.YLG_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(ModBlocks.YLG_ORE.get()))
=======
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.YLG_ORE.get()),ModItems.YLG_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(Registration.YLG_ORE.get()))
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
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
<<<<<<< HEAD
                .save(consumer, "obsidian_apple");

=======
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
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
        ShapelessRecipeBuilder.shapeless(ModItems.GILDED_MASHED_POTATO.get())
                .requires(ModItems.GILDED_POTATO.get())
                .requires(Items.BOWL)
                .requires(Items.SUGAR)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GILDED_POTATO.get()))
<<<<<<< HEAD
                .save(consumer, "gildedmashedpotato");

        weaponRecipes(consumer);
        armorRecipes(consumer);
    }

    private void weaponRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_KATANA.get())
                .pattern("x  ")
                .pattern("x  ")
                .pattern("#  ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .define('#', bergamute)
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, "shadow_katana");
    }

    private void armorRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_HELM.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("   ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, "shadowwarrior_helm");
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_CHEST.get())
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, "shadowwarrior_chest");
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_LEGS.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, "shadowwarrior_legs");
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_BOOTS.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("   ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, "shadowwarrior_boots");
=======
                .save(consumer,"giledmashedpotato");
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
    }
}