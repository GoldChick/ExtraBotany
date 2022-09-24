package chick.extrabotany.datagen.recipes;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.crafting.*;
import chick.extrabotany.common.libs.LibBlockNames;
import chick.extrabotany.common.libs.LibItemNames;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.crafting.recipe.ArmorUpgradeRecipe;
import vazkii.botania.common.lib.ModTags;
import vazkii.botania.data.recipes.WrapperResult;

import java.util.function.Consumer;

import static chick.extrabotany.ExtraBotany.MODID;
import static vazkii.botania.common.block.ModSubtiles.bergamute;
import static vazkii.botania.data.recipes.RecipeProvider.conditionsFromItem;

public class ModCraftingTableRecipes extends RecipeProvider
{
    public ModCraftingTableRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    public @NotNull String getName()
    {
        return "extrabotany craftingTable recipes";
    }

    protected void buildSpecialCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        SpecialRecipeBuilder.special(CocktailRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "cocktail_upgrade_craft");
        SpecialRecipeBuilder.special(LenPotionRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "len_potion_bind_craft");
        SpecialRecipeBuilder.special(GrenadeRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "grenade_bind_craft");
        SpecialRecipeBuilder.special(DupeRuneRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "rune_dupe_craft");
        SpecialRecipeBuilder.special(GoldClothRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "gold_cloth_craft");
        SpecialRecipeBuilder.special(PrimoGemRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "primo_gem_buy_fate_craft");
        SpecialRecipeBuilder.special(BaublesMultiUpgradeRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "baubles_multi_upgrade_craft");
        SpecialRecipeBuilder.special(BaublesMultiRemoveRecipe.SERIALIZER)
                .save(consumer, "dynamic/" + "baubles_multi_remove_craft");

        ShapelessRecipeBuilder.shapeless(vazkii.botania.common.item.ModItems.runeFire, 2)
                .requires(ModItems.ELEMENT_RUNE.get())
                .requires(vazkii.botania.common.item.ModItems.runeFire)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELEMENT_RUNE.get()))
                .save(consumer, "rune_dupe_example");
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        buildSpecialCraftingRecipes(consumer);

        for (var block : new Block[]{
                ModSubtiles.bloodyenchantress, ModSubtiles.reikarorchid, ModSubtiles.bellflower,
                ModSubtiles.sunshinelily, ModSubtiles.moonlightlily, ModSubtiles.omniviolet,
                ModSubtiles.geminiorchid,
                ModSubtiles.annoying, ModSubtiles.serenitian
        })
        {
            createFloatingFlowerRecipe(consumer, block);
        }
        ShapedRecipeBuilder.shaped(ModItems.MANA_READER.get())
                .pattern(" xo")
                .pattern("azx")
                .pattern("za ")
                .define('o', vazkii.botania.common.item.ModItems.manaDiamond)
                .define('x', vazkii.botania.common.item.ModItems.manaPowder)
                .define('z', vazkii.botania.common.item.ModItems.livingwoodTwig)
                .define('a', ModItems.NIGHTMARE_FUEL.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NIGHTMARE_FUEL.get()))
                .save(consumer, "manareader");
        ShapedRecipeBuilder.shaped(ModItems.THE_CHAOS.get())
                .pattern(" x ")
                .pattern("xzb")
                .pattern(" b ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .define('z', ModItems.SPIRIT_FRAG.get())
                .define('b', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("ingots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get(), ModItems.PHOTON_INGOT.get()))
                .save(consumer, "the_chaos");
        ShapedRecipeBuilder.shaped(ModItems.THE_ORIGIN.get())
                .pattern(" x ")
                .pattern("xzb")
                .pattern(" b ")
                .define('x', vazkii.botania.common.item.ModItems.terrasteel)
                .define('z', Blocks.POINTED_DRIPSTONE)
                .define('b', ModItems.AERIALITE_INGOT.get())
                .group(MODID)
                .unlockedBy("ingots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AERIALITE_INGOT.get(), ModItems.PHOTON_INGOT.get()))
                .save(consumer, "the_origin");
        ShapedRecipeBuilder.shaped(ModItems.THE_END.get())
                .pattern(" x ")
                .pattern("xzb")
                .pattern(" b ")
                .define('x', ModItems.ORICHALCOS.get())
                .define('z', Items.ENDER_EYE)
                .define('b', vazkii.botania.common.item.ModItems.gaiaIngot)
                .group(MODID)
                .unlockedBy("ingots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ORICHALCOS.get()))
                .save(consumer, "the_end");
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
                .define('x', ModItems.SPIRIT_FRAG.get())
                .define('a', vazkii.botania.common.item.ModItems.redPetal)
                .group(MODID)
                .unlockedBy("redPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.redPetal))
                .save(consumer, "redscarf");
        ShapedRecipeBuilder.shaped(ModItems.FOX_EAR.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_FRAG.get())
                .define('a', vazkii.botania.common.item.ModItems.pinkPetal)
                .group(MODID)
                .unlockedBy("pinkPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.pinkPetal))
                .save(consumer, "foxear");
        ShapedRecipeBuilder.shaped(ModItems.BLACK_GLASSES.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_FRAG.get())
                .define('a', vazkii.botania.common.item.ModItems.blackPetal)
                .group(MODID)
                .unlockedBy("blackPetal", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.blackPetal))
                .save(consumer, "blackglasses");
        ShapedRecipeBuilder.shaped(ModItems.STONE_MASK.get())
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.SPIRIT_FRAG.get())
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
                .define('c', ModItems.SPIRIT_FUEL.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModBlocks.alchemyCatalyst))
                .save(consumer, LibBlockNames.DIMENSION_CATALYST);
        ShapedRecipeBuilder.shaped(ModBlocks.POWER_FRAME.get())
                .pattern("aaa")
                .pattern("gxg")
                .pattern("aaa")
                .define('x', vazkii.botania.common.block.ModBlocks.livingrock)
                .define('a', ModTags.Items.INGOTS_MANASTEEL)
                .define('g', vazkii.botania.common.item.ModItems.pixieDust)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.pixieDust))
                .save(consumer, LibBlockNames.POWER_FRAME);
        ShapedRecipeBuilder.shaped(ModItems.PEACE_AMULET.get())
                .pattern(" a ")
                .pattern("axa")
                .pattern(" a ")
                .define('x', vazkii.botania.common.block.ModBlocks.livingwood)
                .define('a', vazkii.botania.common.block.ModBlocks.livingrock)
                .group(MODID)
                .unlockedBy("rock", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModBlocks.livingrock))
                .save(consumer, "peace_amulet");
        ShapedRecipeBuilder.shaped(ModItems.GOLD_CLOTH.get())
                .pattern("bab")
                .pattern("axa")
                .pattern("bab")
                .define('x', Items.GOLD_INGOT)
                .define('a', vazkii.botania.common.item.ModItems.manaweaveCloth)
                .define('b', vazkii.botania.common.item.ModItems.lifeEssence)
                .group(MODID)
                .unlockedBy("life_essence", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(consumer, "gold_cloth");
        ShapedRecipeBuilder.shaped(ModItems.UNIVERSAL_PETAL.get(), 8)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', vazkii.botania.common.item.ModItems.lifeEssence)
                .define('a', ModTags.Items.PETALS)
                .group(MODID)
                .unlockedBy("life_essence", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(consumer, "universal_petal");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 32)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', vazkii.botania.common.item.ModItems.lifeEssence)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem1");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 24)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', Tags.Items.GEMS_AMETHYST)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem2");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 3)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', ModTags.Items.INGOTS_MANASTEEL)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem3");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 27)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', ModTags.Items.BLOCKS_MANASTEEL)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem4");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 48)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', ModTags.Items.GEMS_MANA_DIAMOND)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem5");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 480)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', vazkii.botania.common.block.ModBlocks.manaDiamondBlock)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem6");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 460)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', vazkii.botania.common.item.ModItems.gaiaIngot)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem7");
        ShapedRecipeBuilder.shaped(ModItems.PRIMO_GEM.get(), 114)
                .pattern("aaa")
                .pattern("axa")
                .pattern("aaa")
                .define('x', ModItems.GENESIS_CRYSTAL.get())
                .define('a', Tags.Items.STORAGE_BLOCKS_AMETHYST)
                .group(MODID)
                .unlockedBy("genesis_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENESIS_CRYSTAL.get()))
                .save(consumer, "crystal_to_gem8");
        ShapedRecipeBuilder.shaped(ModItems.PURE_DAISY_PENDANT.get())
                .pattern("axa")
                .pattern(" a ")
                .pattern("   ")
                .define('x', ModTags.Items.NUGGETS_MANASTEEL)
                .define('a', vazkii.botania.common.block.ModSubtiles.pureDaisy)
                .group(MODID)
                .unlockedBy("pure_daisy", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.block.ModSubtiles.pureDaisy))
                .save(consumer, LibItemNames.PURE_DAISY_PENDANT);
        ShapedRecipeBuilder.shaped(ModItems.SUN_RING.get())
                .pattern("edc")
                .pattern("fmb")
                .pattern("gza")
                .define('m', ModItems.HERO_MEDAL.get())
                .define('z', ModItems.ELF_KING_RING.get())
                .define('a', vazkii.botania.common.item.ModItems.runeEnvy)
                .define('b', vazkii.botania.common.item.ModItems.runeSloth)
                .define('c', vazkii.botania.common.item.ModItems.runePride)
                .define('d', vazkii.botania.common.item.ModItems.runeLust)
                .define('e', vazkii.botania.common.item.ModItems.runeGreed)
                .define('f', vazkii.botania.common.item.ModItems.runeWrath)
                .define('g', vazkii.botania.common.item.ModItems.runeGluttony)
                .group(MODID)
                .unlockedBy(LibItemNames.HERO_MEDAL, InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(consumer, LibItemNames.SUN_RING);
        ShapedRecipeBuilder.shaped(ModItems.MOON_PENDANT.get())
                .pattern("edc")
                .pattern("fmb")
                .pattern("gza")
                .define('m', ModItems.HERO_MEDAL.get())
                .define('z', ModItems.PURE_DAISY_PENDANT.get())
                .define('a', vazkii.botania.common.item.ModItems.runeEnvy)
                .define('b', vazkii.botania.common.item.ModItems.runeSloth)
                .define('c', vazkii.botania.common.item.ModItems.runePride)
                .define('d', vazkii.botania.common.item.ModItems.runeLust)
                .define('e', vazkii.botania.common.item.ModItems.runeGreed)
                .define('f', vazkii.botania.common.item.ModItems.runeWrath)
                .define('g', vazkii.botania.common.item.ModItems.runeGluttony)
                .group(MODID)
                .unlockedBy(LibItemNames.HERO_MEDAL, InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(consumer, LibItemNames.MOON_PENDANT);
        ShapedRecipeBuilder.shaped(ModItems.CORE_GOD.get())
                .pattern("zmz")
                .pattern("ztz")
                .pattern("zzz")
                .define('m', ModItems.HERO_MEDAL.get())
                .define('z', vazkii.botania.common.item.ModItems.sunnyQuartz)
                .define('t', vazkii.botania.common.item.ModItems.flightTiara)
                .group(MODID)
                .unlockedBy(LibItemNames.HERO_MEDAL, InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(consumer, LibItemNames.CORE_GOD);
        ShapedRecipeBuilder.shaped(ModItems.NATURE_ORB.get())
                .pattern("zbz")
                .pattern("btb")
                .pattern("zbz")
                .define('t', vazkii.botania.common.item.ModItems.manaPearl)
                .define('z', vazkii.botania.common.item.ModItems.terrasteel)
                .define('b', vazkii.botania.common.item.ModItems.dragonstone)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.terrasteel))
                .save(consumer, LibItemNames.NATURE_ORB);


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
                .save(consumer, LibItemNames.GILDED_MASHED_POTATO);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BLOCK_SHADOWIUM.get())
                .requires(ModItems.SHADOW_INGOT.get(), 9)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibBlockNames.BLOCK_SHADOWIUM);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BLOCK_PHOTONIUM.get())
                .requires(ModItems.PHOTON_INGOT.get(), 9)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, LibBlockNames.BLOCK_PHOTONIUM);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BLOCK_ORICHALCOS.get())
                .requires(ModItems.ORICHALCOS.get(), 9)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ORICHALCOS.get()))
                .save(consumer, LibBlockNames.BLOCK_ORICHALCOS);
        ShapelessRecipeBuilder.shapeless(ModItems.SHADOW_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_SHADOWIUM.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibItemNames.SHADOW_INGOT);
        ShapelessRecipeBuilder.shapeless(ModItems.PHOTON_INGOT.get(), 9)
                .requires(ModBlocks.BLOCK_PHOTONIUM.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, LibItemNames.PHOTON_INGOT);
        ShapelessRecipeBuilder.shapeless(ModItems.ORICHALCOS.get(), 9)
                .requires(ModBlocks.BLOCK_ORICHALCOS.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ORICHALCOS.get()))
                .save(consumer, LibItemNames.ORICHALCOS);

        ShapelessRecipeBuilder.shapeless(ModItems.LEN_SMELT.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .requires(vazkii.botania.common.item.ModItems.runeFire)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, LibItemNames.LEN_SMELT);
        ShapelessRecipeBuilder.shapeless(ModItems.LEN_POTION.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .requires(vazkii.botania.common.item.ModItems.runeSpring)
                .requires(vazkii.botania.common.item.ModItems.dragonstone)
                .requires(vazkii.botania.common.item.ModItems.enderAirBottle)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, LibItemNames.LEN_POTION);
        ShapelessRecipeBuilder.shapeless(ModItems.LEN_TRACE.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.runeGreed)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, LibItemNames.LEN_TRACE);
        ShapelessRecipeBuilder.shapeless(ModItems.LEN_MANA.get())
                .requires(vazkii.botania.common.item.ModItems.lensNormal)
                .requires(vazkii.botania.common.item.ModItems.runeMana)
                .requires(vazkii.botania.common.item.ModItems.manaPowder)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lensNormal))
                .save(consumer, LibItemNames.LEN_MANA);
        ShapelessRecipeBuilder.shapeless(ModItems.TICKET.get())
                .requires(vazkii.botania.common.item.ModItems.gaiaIngot)
                .requires(ModItems.THE_CHAOS.get())
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(consumer, "ticket");
        ShapelessRecipeBuilder.shapeless(ModItems.ELEMENT_RUNE.get(), 8)
                .requires(vazkii.botania.common.item.ModItems.lifeEssence)
                .requires(vazkii.botania.common.item.ModItems.runeFire)
                .requires(vazkii.botania.common.item.ModItems.runeAir)
                .requires(vazkii.botania.common.item.ModItems.runeEarth)
                .requires(vazkii.botania.common.item.ModItems.runeWater)
                .requires(vazkii.botania.common.item.ModItems.runeSpring)
                .requires(vazkii.botania.common.item.ModItems.runeSummer)
                .requires(vazkii.botania.common.item.ModItems.runeAutumn)
                .requires(vazkii.botania.common.item.ModItems.runeWinter)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(consumer, "element_rune");
        ShapelessRecipeBuilder.shapeless(ModItems.SIN_RUNE.get(), 8)
                .requires(vazkii.botania.common.item.ModItems.lifeEssence)
                .requires(vazkii.botania.common.item.ModItems.runeMana)
                .requires(vazkii.botania.common.item.ModItems.runeGreed)
                .requires(vazkii.botania.common.item.ModItems.runeEnvy)
                .requires(vazkii.botania.common.item.ModItems.runeWrath)
                .requires(vazkii.botania.common.item.ModItems.runeSloth)
                .requires(vazkii.botania.common.item.ModItems.runeGluttony)
                .requires(vazkii.botania.common.item.ModItems.runePride)
                .requires(vazkii.botania.common.item.ModItems.runeLust)
                .group(MODID)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(consumer, "sin_rune");

    }

    private void weaponRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(ModItems.JUDAH_OATH.get())
                .pattern("zmz")
                .pattern("ztz")
                .pattern("zzz")
                .define('m', ModItems.GILDED_MASHED_POTATO.get())
                .define('z', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('t', ModItems.THE_ORIGIN.get())
                .group(MODID)
                .unlockedBy(LibItemNames.THE_ORIGIN, InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(consumer, LibItemNames.JUDAH_OATH);
        ShapedRecipeBuilder.shaped(ModItems.LIVINGWOOD_CROSSBOW.get())
                .pattern("xdx")
                .pattern("aba")
                .pattern(" x ")
                .define('x', vazkii.botania.common.item.ModItems.livingwoodTwig)
                .define('d', vazkii.botania.common.item.ModItems.manaSteel)
                .define('a', vazkii.botania.common.item.ModItems.manaString)
                .define('b', Items.TRIPWIRE_HOOK)
                .group(MODID)
                .unlockedBy("manasteel", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaSteel))
                .save(consumer, LibItemNames.LIVINGWOOD_CROSSBOW);
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_KATANA.get())
                .pattern("x  ")
                .pattern("x  ")
                .pattern("#  ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .define('#', bergamute)
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibItemNames.SHADOW_KATANA);
        ShapedRecipeBuilder.shaped(ModItems.TRUE_SHADOW_KATANA.get())
                .pattern(" b#")
                .pattern("xab")
                .pattern("#x ")
                .define('x', vazkii.botania.common.item.ModItems.lifeEssence)
                .define('#', ModItems.SHADOW_KATANA.get())
                .define('a', ModItems.THE_ORIGIN.get())
                .define('b', Blocks.BLACKSTONE)
                .group(MODID)
                .unlockedBy("gaia", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.TRUE_SHADOW_KATANA);
        ShapedRecipeBuilder.shaped(ModItems.TRUE_TERRA_BLADE.get())
                .pattern(" db")
                .pattern("xad")
                .pattern("#x ")
                .define('x', vazkii.botania.common.item.ModItems.lifeEssence)
                .define('#', vazkii.botania.common.item.ModItems.terraSword)
                .define('a', ModItems.THE_ORIGIN.get())
                .define('b', vazkii.botania.common.item.ModItems.terrasteel)
                .define('d', Blocks.MOSS_BLOCK)
                .group(MODID)
                .unlockedBy("gaia", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.TRUE_TERRA_BLADE);
        ShapedRecipeBuilder.shaped(ModItems.TRUE_THUNSTAR_CALLER.get())
                .pattern("bdk")
                .pattern("xad")
                .pattern("#xb")
                .define('x', vazkii.botania.common.item.ModItems.lifeEssence)
                .define('#', vazkii.botania.common.item.ModItems.thunderSword)
                .define('k', vazkii.botania.common.item.ModItems.starSword)
                .define('a', ModItems.THE_ORIGIN.get())
                .define('b', Blocks.LIGHTNING_ROD)
                .define('d', ModItems.AERIALITE_INGOT.get())
                .group(MODID)
                .unlockedBy("gaia", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.TRUE_THUNSTAR_CALLER);
        ShapedRecipeBuilder.shaped(ModItems.INFLUX_WAVER.get())
                .pattern(" bd")
                .pattern("xab")
                .pattern("#x ")
                .define('x', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('#', vazkii.botania.common.item.ModItems.terraSword)
                .define('a', ModItems.THE_ORIGIN.get())
                .define('b', Ingredient.of(Items.BRAIN_CORAL, Items.TUBE_CORAL, Items.FIRE_CORAL, Items.BUBBLE_CORAL, Items.HORN_CORAL, Items.BRAIN_CORAL_FAN, Items.TUBE_CORAL_FAN, Items.FIRE_CORAL_FAN, Items.BUBBLE_CORAL_FAN, Items.HORN_CORAL_FAN))
                .define('d', Items.HEART_OF_THE_SEA)
                .group(MODID)
                .unlockedBy("gaia", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lifeEssence))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.INFLUX_WAVER);
        ShapedRecipeBuilder.shaped(ModItems.EXCALIBER.get())
                .pattern(" b ")
                .pattern(" a ")
                .pattern(" x ")
                .define('x', vazkii.botania.common.item.ModItems.dreamwoodTwig)
                .define('b', ModItems.HERO_MEDAL.get())
                .define('a', vazkii.botania.common.item.ModItems.terraSword)
                .group(MODID)
                .unlockedBy("hero_medal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HERO_MEDAL.get()))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.EXCALIBER);
        ShapedRecipeBuilder.shaped(ModItems.FAILNAUGHT.get())
                .pattern(" xd")
                .pattern("#ad")
                .pattern(" xd")
                .define('x', vazkii.botania.common.item.ModItems.terrasteel)
                .define('#', ModItems.AERIALITE_INGOT.get())
                .define('a', ModItems.ORICHALCOS.get())
                .define('d', vazkii.botania.common.item.ModItems.manaString)
                .group(MODID)
                .unlockedBy("orichalcos", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ORICHALCOS.get()))
                .save(consumer, LibItemNames.FAILNAUGHT);
        ShapedRecipeBuilder.shaped(ModItems.DIRT_SHIELD.get())
                .pattern("b#b")
                .pattern("bab")
                .pattern("bbb")
                .define('#', vazkii.botania.common.item.ModItems.manaSteel)
                .define('a', Items.SHIELD)
                .define('b', Items.DIRT)
                .group(MODID)
                .unlockedBy("shield", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHIELD))
                .save(consumer, LibItemNames.DIRT_SHIELD);
        ShapedRecipeBuilder.shaped(ModItems.MANASTEEL_SHIELD.get())
                .pattern(" # ")
                .pattern("#a#")
                .pattern(" # ")
                .define('#', vazkii.botania.common.item.ModItems.manaSteel)
                .define('a', Items.SHIELD)
                .group(MODID)
                .unlockedBy("shield", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaSteel))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.MANASTEEL_SHIELD);
        ShapedRecipeBuilder.shaped(ModItems.ELEMENT_SHIELD.get())
                .pattern(" # ")
                .pattern("#a#")
                .pattern(" # ")
                .define('#', vazkii.botania.common.item.ModItems.elementium)
                .define('a', Items.SHIELD)
                .group(MODID)
                .unlockedBy("shield", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.elementium))
                .save(WrapperResult.ofType(ToolsMultiUpgradeRecipe.SERIALIZER, consumer), LibItemNames.ELEMENT_SHIELD);
        ShapedRecipeBuilder.shaped(ModItems.FOREST_BOOK.get())
                .pattern("bab")
                .pattern("b#b")
                .pattern("bcb")
                .define('#', vazkii.botania.common.item.ModItems.lexicon)
                .define('c', vazkii.botania.common.item.ModItems.runeFire)
                .define('a', Items.GOLDEN_APPLE)
                .define('b', ItemTags.LEAVES)
                .group(MODID)
                .unlockedBy("lexicon", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.lexicon))
                .save(consumer, LibItemNames.FOREST_BOOK);
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
                .save(consumer, LibItemNames.SHADOWWARRIOR_HELM);
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_CHEST.get())
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibItemNames.SHADOWWARRIOR_CHEST);
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_LEGS.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibItemNames.SHADOWWARRIOR_LEGS);
        ShapedRecipeBuilder.shaped(ModItems.SHADOW_WARRIOR_BOOTS.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("   ")
                .define('x', ModItems.SHADOW_INGOT.get())
                .group(MODID)
                .unlockedBy("shadowium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SHADOW_INGOT.get()))
                .save(consumer, LibItemNames.SHADOWWARRIOR_BOOTS);
        ShapedRecipeBuilder.shaped(ModItems.GOBLINSLAYER_HELM.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("   ")
                .define('x', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("photonium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, "goblinslayer_helm");
        ShapedRecipeBuilder.shaped(ModItems.GOBLINSLAYER_CHEST.get())
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("photonium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, "goblinslayer_chest");
        ShapedRecipeBuilder.shaped(ModItems.GOBLINSLAYER_LEGS.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x")
                .define('x', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("photonium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, "goblinslayer_legs");
        ShapedRecipeBuilder.shaped(ModItems.GOBLINSLAYER_BOOTS.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("   ")
                .define('x', ModItems.PHOTON_INGOT.get())
                .group(MODID)
                .unlockedBy("photonium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PHOTON_INGOT.get()))
                .save(consumer, "goblinslayer_boots");
        ShapedRecipeBuilder.shaped(ModItems.MIKU_HELM.get())
                .pattern("xxx")
                .pattern("xax")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.manaweaveCloth)
                .define('a', vazkii.botania.common.item.ModItems.manasteelHelm)
                .group(MODID)
                .unlockedBy("manaweave", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaweaveCloth))
                .save(consumer, "miku_helm");
        ShapedRecipeBuilder.shaped(ModItems.MIKU_CHEST.get())
                .pattern("xxx")
                .pattern("xax")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.manaweaveCloth)
                .define('a', vazkii.botania.common.item.ModItems.manasteelChest)
                .group(MODID)
                .unlockedBy("manaweave", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaweaveCloth))
                .save(consumer, "miku_chest");
        ShapedRecipeBuilder.shaped(ModItems.MIKU_LEGS.get())
                .pattern("xxx")
                .pattern("xax")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.manaweaveCloth)
                .define('a', vazkii.botania.common.item.ModItems.manasteelLegs)
                .group(MODID)
                .unlockedBy("manaweave", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaweaveCloth))
                .save(consumer, "miku_legs");
        ShapedRecipeBuilder.shaped(ModItems.MIKU_BOOTS.get())
                .pattern("xxx")
                .pattern("xax")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.manaweaveCloth)
                .define('a', vazkii.botania.common.item.ModItems.manasteelBoots)
                .group(MODID)
                .unlockedBy("manaweave", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.manaweaveCloth))
                .save(consumer, "miku_boots");
        ShapedRecipeBuilder.shaped(ModItems.MAID_HELM.get())
                .pattern("xxx")
                .pattern("bab")
                .pattern("   ")
                .define('x', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('a', vazkii.botania.common.item.ModItems.terrasteelHelm)
                .define('b', ModItems.GOLD_CLOTH.get())
                .group(MODID)
                .unlockedBy("gaia_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(WrapperResult.ofType(ArmorUpgradeRecipe.SERIALIZER, consumer), LibItemNames.MAID_HELM);
        ShapedRecipeBuilder.shaped(ModItems.MAID_CHEST.get())
                .pattern("b b")
                .pattern("bab")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('a', vazkii.botania.common.item.ModItems.terrasteelChest)
                .define('b', ModItems.GOLD_CLOTH.get())
                .group(MODID)
                .unlockedBy("gaia_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(WrapperResult.ofType(ArmorUpgradeRecipe.SERIALIZER, consumer), LibItemNames.MAID_CHEST);
        ShapedRecipeBuilder.shaped(ModItems.MAID_LEGS.get())
                .pattern("xxx")
                .pattern("bab")
                .pattern("b b")
                .define('x', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('a', vazkii.botania.common.item.ModItems.terrasteelLegs)
                .define('b', ModItems.GOLD_CLOTH.get())
                .group(MODID)
                .unlockedBy("gaia_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(WrapperResult.ofType(ArmorUpgradeRecipe.SERIALIZER, consumer), LibItemNames.MAID_LEGS);
        ShapedRecipeBuilder.shaped(ModItems.MAID_BOOTS.get())
                .pattern("   ")
                .pattern("bab")
                .pattern("xxx")
                .define('x', vazkii.botania.common.item.ModItems.gaiaIngot)
                .define('a', vazkii.botania.common.item.ModItems.terrasteelBoots)
                .define('b', ModItems.GOLD_CLOTH.get())
                .group(MODID)
                .unlockedBy("gaia_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(vazkii.botania.common.item.ModItems.gaiaIngot))
                .save(WrapperResult.ofType(ArmorUpgradeRecipe.SERIALIZER, consumer), LibItemNames.MAID_BOOTS);
    }

    protected void createFloatingFlowerRecipe(Consumer<FinishedRecipe> consumer, ItemLike input)
    {
        ResourceLocation inputName = Registry.ITEM.getKey(input.asItem());
        Item output = Registry.ITEM.getOptional(new ResourceLocation(inputName.getNamespace(), "floating_" + inputName.getPath())).get();
        ShapelessRecipeBuilder.shapeless(output)
                .requires(ModTags.Items.FLOATING_FLOWERS)
                .requires(input)
                .unlockedBy("has_item", conditionsFromItem(input))
                .save(consumer);
    }
}