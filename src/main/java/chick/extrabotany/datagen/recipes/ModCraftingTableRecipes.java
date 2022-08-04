package chick.extrabotany.datagen.recipes;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import vazkii.botania.common.lib.ModTags;

import java.util.function.Consumer;

import static chick.extrabotany.ExtraBotany.MODID;
import static vazkii.botania.common.block.ModSubtiles.bergamute;

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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.YLG_ORE.get()), ModItems.YLG_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(ModBlocks.YLG_ORE.get()))
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
                .save(consumer, "obsidian_apple");


        ShapedRecipeBuilder.shaped(ModItems.MANA_READER.get())
                .pattern(" xo")
                .pattern("azx")
                .pattern("za ")
                .define('o', vazkii.botania.common.item.ModItems.manaDiamond)
                .define('x', vazkii.botania.common.item.ModItems.manaPowder)
                .define('z', vazkii.botania.common.item.ModItems.livingwoodTwig)
                .define('a', ModItems.NIGHTMAREFUEL_PROP.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NIGHTMAREFUEL_PROP.get()))
                .save(consumer, "manareader");
        ShapedRecipeBuilder.shaped(ModItems.THE_CHAOS.get())
                .pattern(" x ")
                .pattern("xzb")
                .pattern(" b ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .define('z', ModItems.SPIRIT_PROP.get())
                .define('b', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("ingots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get(), ModItems.PHOTON_INGOT.get()))
                .save(consumer, "thechaos");
        ShapedRecipeBuilder.shaped(ModItems.EMPTY_BOTTLE.get())
                .pattern("x x")
                .pattern("x x")
                .pattern(" x ")
                .define('x', vazkii.botania.common.block.ModBlocks.manaGlass)
                .group(MODID)
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModBlocks.manaGlass))
                .save(consumer, "empty_bottle");
        shapelessRecipes(consumer);
        weaponRecipes(consumer);
        armorRecipes(consumer);
    }

    private void shapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapelessRecipeBuilder.shapeless(ModItems.GILDED_MASHED_POTATO.get())
                .requires(ModItems.GILDED_POTATO.get())
                .requires(Items.BOWL)
                .requires(Items.SUGAR)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GILDED_POTATO.get()))
                .save(consumer, "gildedmashedpotato");
        ShapelessRecipeBuilder.shapeless(ModSubtiles.sunshinelilyFloating)
                .requires(ModSubtiles.sunshinelily)
                .requires(ModTags.Items.MUNDANE_FLOATING_FLOWERS)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModSubtiles.sunshinelily))
                .save(consumer, "floating_sunshine_lily");
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
    }
}