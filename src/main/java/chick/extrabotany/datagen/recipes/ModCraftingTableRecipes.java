package chick.extrabotany.datagen.recipes;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.crafting.CocktailRecipe;
import chick.extrabotany.common.crafting.LenPotionRecipe;
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
        SpecialRecipeBuilder.special(CocktailRecipe.SERIALIZER)
                .save(consumer, "cock_tail");
        SpecialRecipeBuilder.special(LenPotionRecipe.SERIALIZER)
                .save(consumer, "len_potion_brew");


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
        ShapedRecipeBuilder.shaped(ModItems.THE_END.get())
                .pattern(" x ")
                .pattern("xzb")
                .pattern(" b ")
                .define('x', ModItems.ORICHALCOS.get())
                .define('z', Items.ENDER_EYE)
                .define('b', vazkii.botania.common.item.ModItems.gaiaIngot)
                .group(MODID)
                .unlockedBy("ingots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ORICHALCOS.get()))
                .save(consumer, "theend");
        ShapedRecipeBuilder.shaped(ModItems.EMPTY_BOTTLE.get())
                .pattern("x x")
                .pattern("x x")
                .pattern(" x ")
                .define('x', vazkii.botania.common.block.ModBlocks.manaGlass)
                .group(MODID)
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModBlocks.manaGlass))
                .save(consumer, "empty_bottle");
        ShapedRecipeBuilder.shaped(ModItems.ROD_OF_DISCORD.get())
                .pattern(" ag")
                .pattern(" xa")
                .pattern("x  ")
                .define('x', vazkii.botania.common.item.ModItems.livingwoodTwig)
                .define('a', vazkii.botania.common.item.ModItems.pixieDust)
                .define('g', ModItems.THE_CHAOS.get())
                .group(MODID)
                .unlockedBy("thechaos", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.THE_CHAOS.get()))
                .save(consumer, "rodofdiscord");
        ShapedRecipeBuilder.shaped(ModItems.JINGWEI_FEATHER.get())
                .pattern("gag")
                .pattern("bxb")
                .pattern("geg")
                .define('x', Items.FEATHER)
                .define('b', vazkii.botania.common.item.ModItems.runeFire)
                .define('a', vazkii.botania.common.item.ModItems.pixieDust)
                .define('g', vazkii.botania.common.item.ModItems.redQuartz)
                .define('e', vazkii.botania.common.item.ModItems.lensFire)
                .group(MODID)
                .unlockedBy("feather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FEATHER))
                .save(consumer, "jingweifeather");
        ShapedRecipeBuilder.shaped(ModItems.RED_SCARF.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_PROP.get())
                .define('a', vazkii.botania.common.item.ModItems.redPetal)
                .group(MODID)
                .unlockedBy("redPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.redPetal))
                .save(consumer, "redscarf");
        ShapedRecipeBuilder.shaped(ModItems.FOX_EAR.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_PROP.get())
                .define('a', vazkii.botania.common.item.ModItems.pinkPetal)
                .group(MODID)
                .unlockedBy("pinkPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.pinkPetal))
                .save(consumer, "foxear");
        ShapedRecipeBuilder.shaped(ModItems.BLACK_GLASSES.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_PROP.get())
                .define('a', vazkii.botania.common.item.ModItems.blackPetal)
                .group(MODID)
                .unlockedBy("blackPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.blackPetal))
                .save(consumer, "blackglasses");
        ShapedRecipeBuilder.shaped(ModItems.STONE_MASK.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_PROP.get())
                .define('a', vazkii.botania.common.item.ModItems.grayPetal)
                .group(MODID)
                .unlockedBy("grayPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.grayPetal))
                .save(consumer, "stonemask");
        ShapedRecipeBuilder.shaped(ModItems.SAGES_MANA_RING.get())
                .pattern("aba")
                .pattern("cxc")
                .pattern("aca")
                .define('x', vazkii.botania.common.item.ModItems.manaRingGreater)
                .define('a', vazkii.botania.common.item.ModItems.terrasteel)
                .define('b', ModItems.HERO_MEDAL.get())
                .define('c', vazkii.botania.common.item.ModItems.manaTablet)
                .group(MODID)
                .unlockedBy("heromedal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(consumer, "sages_mana_ring");
        ShapedRecipeBuilder.shaped(ModBlocks.DIMENSION_CATALYST.get())
                .pattern("aba")
                .pattern("gxw")
                .pattern("aca")
                .define('x', vazkii.botania.common.block.ModBlocks.alchemyCatalyst)
                .define('a', vazkii.botania.common.block.ModBlocks.livingrock)
                .define('b', Items.ENDER_PEARL)
                .define('g', Items.DIAMOND)
                .define('w', Items.BLAZE_ROD)
                .define('c', ModItems.SPIRITFUEL_PROP.get())
                .group(MODID)
                .unlockedBy("alchemycatalyst", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModBlocks.alchemyCatalyst))
                .save(consumer, "dimension_catalyst");

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
        ShapelessRecipeBuilder.shapeless(ModItems.LEN_SMELT.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .requires(vazkii.botania.common.item.ModItems.runeFire)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, "len_smelt");
        ShapelessRecipeBuilder.shapeless(ModItems.LEN_POTION.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .requires(vazkii.botania.common.item.ModItems.runeSpring)
                .requires(vazkii.botania.common.item.ModItems.dragonstone)
                .requires(vazkii.botania.common.item.ModItems.enderAirBottle)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, "len_potion");
        ShapelessRecipeBuilder.shapeless(ModItems.TICKET.get())
                .requires(vazkii.botania.common.item.ModItems.gaiaIngot)
                .requires(ModItems.THE_CHAOS.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(consumer, "ticket");

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