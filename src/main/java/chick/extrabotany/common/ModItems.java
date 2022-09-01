package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibItemNames;
import chick.extrabotany.api.WeightCategory;
import chick.extrabotany.common.baubles.*;
import chick.extrabotany.common.baubles.cosmetic.Cosmetic;
import chick.extrabotany.common.brews.Cocktail;
import chick.extrabotany.common.brews.SplashGrenade;
import chick.extrabotany.common.crafting.*;
import chick.extrabotany.common.foods.NormalFoods;
import chick.extrabotany.common.items.*;
import chick.extrabotany.common.items.lens.*;
import chick.extrabotany.common.tools.armors.*;
import chick.extrabotany.common.tools.others.DirtShield;
import chick.extrabotany.common.tools.others.ForestBook;
import chick.extrabotany.common.tools.others.ManaReader;
import chick.extrabotany.common.tools.others.RodOfDiscord;
import chick.extrabotany.common.tools.weapons.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.item.lens.ItemLens;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static chick.extrabotany.ExtraBotany.MODID;

public class ModItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);

    public static final RegistryObject<Item> SPIRIT_FUEL = register(LibItemNames.SPIRIT_FUEL, new Item.Properties().food(NormalFoods.SPIRITFUEL_PROP).tab(ExtraBotany.ITEM_GROUP));
    public static final RegistryObject<Item> NIGHTMARE_FUEL = register(LibItemNames.NIGHTMARE_FUEL, () -> new NightmareFuel(new Item.Properties().food(NormalFoods.NIGHTMAREFUEL_PROP).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> SPIRIT_FRAG = register(LibItemNames.SPIRIT_FRAG);
    public static final RegistryObject<Item> SHADOW_INGOT = register(LibItemNames.SHADOW_INGOT);
    public static final RegistryObject<Item> PHOTON_INGOT = register(LibItemNames.PHOTON_INGOT);
    public static final RegistryObject<Item> AERIALITE_INGOT = register(LibItemNames.AERIALITE);
    public static final RegistryObject<Item> ORICHALCOS = register(LibItemNames.ORICHALCOS);
    public static final RegistryObject<Item> GILDED_POTATO = register(LibItemNames.GILDED_POTATO);
    public static final RegistryObject<Item> GILDED_MASHED_POTATO = register(LibItemNames.GILDED_MASHED_POTATO, new Item.Properties().food(NormalFoods.GILDED_MASHED_POTATO).stacksTo(1).tab(ExtraBotany.ITEM_GROUP));

    public static final RegistryObject<Item> MANA_READER = register(LibItemNames.MANA_READER, () -> new ManaReader(new Item.Properties().stacksTo(1).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> THE_CHAOS = register(LibItemNames.THE_CHAOS);
    public static final RegistryObject<Item> THE_END = register(LibItemNames.THE_END);
    public static final RegistryObject<Item> THE_ORIGIN = register(LibItemNames.THE_ORIGIN);
    public static final RegistryObject<Item> THE_UNIVERSE = register(LibItemNames.THE_UNIVERSE);
    public static final RegistryObject<Item> EMPTY_BOTTLE = register(LibItemNames.EMPTY_BOTTLE, () -> new EmptyGlassBottle(ITEM_PROPERTIES));
    public static final RegistryObject<Item> MANA_DRINK = register(LibItemNames.MANA_DRINK, () -> new ManaDrink(new Item.Properties().food(NormalFoods.MANA_DRINK).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> HERO_MEDAL = register(LibItemNames.HERO_MEDAL);
    public static final RegistryObject<Item> FRIED_CHICKEN = register(LibItemNames.FRIED_CHICKEN, new Item.Properties().food(NormalFoods.FRIED_CHICKEN).tab(ExtraBotany.ITEM_GROUP));
    public static final RegistryObject<Item> TICKET = register(LibItemNames.TICKET, () -> new Ticket(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOLD_CLOTH = register(LibItemNames.GOLD_CLOTH);

    public static final List<WeightCategory> categoryListA = new ArrayList<>();
    public static final List<WeightCategory> categoryListB = new ArrayList<>();
    public static final List<WeightCategory> categoryListC = new ArrayList<>();
    public static final List<WeightCategory> categoryListD = new ArrayList<>();
    public static final RegistryObject<Item> REWARD_BAG_A = register(LibItemNames.REWARD_BAG_A, () -> new RewardBag(ITEM_PROPERTIES, categoryListA));
    public static final RegistryObject<Item> REWARD_BAG_B = register(LibItemNames.REWARD_BAG_B, () -> new RewardBag(ITEM_PROPERTIES, categoryListB));
    public static final RegistryObject<Item> REWARD_BAG_C = register(LibItemNames.REWARD_BAG_C, () -> new RewardBag(ITEM_PROPERTIES, categoryListC));
    public static final RegistryObject<Item> REWARD_BAG_D = register(LibItemNames.REWARD_BAG_D, () -> new RewardBag(ITEM_PROPERTIES, categoryListD));


    public static final RegistryObject<Item> ELEMENT_RUNE = register(LibItemNames.ELEMENT_RUNE);
    public static final RegistryObject<Item> SIN_RUNE = register(LibItemNames.SIN_RUNE);
    public static final RegistryObject<Item> UNIVERSAL_PETAL = register(LibItemNames.UNIVERSAL_PETAL);

    public static final RegistryObject<Item> GENESIS_CRYSTAL = register(LibItemNames.GENESIS_CRYSTAL);
    public static final RegistryObject<Item> PRIMO_GEM = register(LibItemNames.PRIMO_GEM);

    public static final RegistryObject<Item> INTERTWINED_FATE = register(LibItemNames.INTERTWINED_FATE, () -> new Fates(ITEM_PROPERTIES, Fates.FateType.Intertwined));
    public static final RegistryObject<Item> ACQUAINT_FATE = register(LibItemNames.ACQUAINT_FATE, () -> new Fates(ITEM_PROPERTIES, Fates.FateType.Acquaint));

    /**
     * stacksTo 32
     */
    public static final RegistryObject<Item> SPLASH_GRENADE = register(LibItemNames.SPLASH_GRENADE, () -> new SplashGrenade(ITEM_PROPERTIES.stacksTo(32)));
    /**
     * stacksTo 1
     */
    public static final RegistryObject<Item> TREASURE_BOX = register(LibItemNames.TREASURE_BOX, () -> new TreasureBox(stackTo1()));
    public static final RegistryObject<Item> COCK_TAIL = register(LibItemNames.COCK_TAIL, () -> new Cocktail(stackTo1()));
    public static final RegistryObject<Item> LEN_SMELT = register(LibItemNames.LEN_SMELT, () -> new ItemLens(stackTo1(), new LenSmelt(), vazkii.botania.common.item.lens.ItemLens.PROP_TOUCH));
    public static final RegistryObject<Item> LEN_POTION = register(LibItemNames.LEN_POTION, () -> new LenPotionBase(stackTo1(), new LenPotion(), vazkii.botania.common.item.lens.ItemLens.PROP_INTERACTION));
    public static final RegistryObject<Item> LEN_MANA = register(LibItemNames.LEN_MANA, () -> new ItemLens(stackTo1(), new LenMana(6400), ItemLens.PROP_ORIENTATION));
    public static final RegistryObject<Item> LEN_TRACE = register(LibItemNames.LEN_TRACE, () -> new ItemLens(stackTo1(), new LenTrace(), ItemLens.PROP_ORIENTATION));
    public static final RegistryObject<Item> LEN_SUPERCONDUCTOR = register(LibItemNames.LEN_SUPERCONDUCTOR, () -> new ItemLens(stackTo1(), new LenSuperConductor(), ItemLens.PROP_DAMAGE));

    public static final RegistryObject<Item> FOREST_BOOK = register(LibItemNames.FOREST_BOOK,()->new ForestBook(stackTo1()));
    public static final RegistryObject<Item> DIRT_SHIELD = register(LibItemNames.DIRT_SHIELD,()->new DirtShield(stackTo1()));
    /**
     * these are baubles for curios mod
     **/
    public static final RegistryObject<Item> PYLON = register(LibItemNames.PYLON, () -> new Cosmetic(Cosmetic.Variant.Pylon, ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOX_EAR = register(LibItemNames.FOX_EAR, () -> new Cosmetic(Cosmetic.Variant.FoxEar, ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOX_MASK = register(LibItemNames.FOX_MASK, () -> new Cosmetic(Cosmetic.Variant.FoxMask, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPER_CROWN = register(LibItemNames.SUPER_CROWN, () -> new Cosmetic(Cosmetic.Variant.SuperCrown, ITEM_PROPERTIES));
    public static final RegistryObject<Item> RED_SCARF = register(LibItemNames.RED_SCARF, () -> new Cosmetic(Cosmetic.Variant.RedScarf, ITEM_PROPERTIES));
    public static final RegistryObject<Item> BLACK_GLASSES = register(LibItemNames.BLACK_GLASSES, () -> new Cosmetic(Cosmetic.Variant.BlackGlasses, ITEM_PROPERTIES));
    public static final RegistryObject<Item> STONE_MASK = register(LibItemNames.STONE_MASK, () -> new Cosmetic(Cosmetic.Variant.StoneMask, ITEM_PROPERTIES));
    public static final RegistryObject<Item> THUG_LIFE = register(LibItemNames.THUG_LIFE, () -> new Cosmetic(Cosmetic.Variant.ThugLife, ITEM_PROPERTIES));

    public static final RegistryObject<Item> PEACE_AMULET = register(LibItemNames.PEACE_AMULET);
    public static final RegistryObject<Item> FROST_RING = register(LibItemNames.FROST_RING, () -> new FrostRing(ITEM_PROPERTIES));
    public static final RegistryObject<Item> DEATH_RING = register(LibItemNames.DEATH_RING, () -> new DeathRing(ITEM_PROPERTIES));
    public static final RegistryObject<Item> POWER_GLOVE = register(LibItemNames.POWER_GLOVE, () -> new PowerGlove(ITEM_PROPERTIES));
    public static final RegistryObject<Item> JINGWEI_FEATHER = register(LibItemNames.JINGWEI_FEATHER, () -> new JingweiFeather(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AERO_STONE = register(LibItemNames.AERO_STONE, () -> new AeroStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AQUA_STONE = register(LibItemNames.AQUA_STONE, () -> new AquaStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> EARTH_STONE = register(LibItemNames.EARTH_STONE, () -> new EarthStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IGNITE_STONE = register(LibItemNames.IGNITE_STONE, () -> new IgniteStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOUR_IN_ONE_STONE = register(LibItemNames.FOUR_IN_ONE_STONE, () -> new FourInOneStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPREME_AERO_STONE = register(LibItemNames.SUPREME_AERO_STONE, () -> new SupremeAeroStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_AQUA_STONE = register(LibItemNames.SUPREME_AQUA_STONE, () -> new SupremeAquaStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_EARTH_STONE = register(LibItemNames.SUPREME_EARTH_STONE, () -> new SupremeEarthStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_IGNITE_STONE = register(LibItemNames.SUPREME_IGNITE_STONE, () -> new SupremeIgniteStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SAGES_MANA_RING = register(LibItemNames.SAGES_MANA_RING, () -> new SagesManaRing(ITEM_PROPERTIES));
    /**
     * these are armors and weapons.(items with damage)
     * for some reason, if they are assigned before,
     * items behind will be with nbt "damage" ,and can not stack.
     */
    public static final RegistryObject<Item> LIVINGWOOD_CROSSBOW = register(LibItemNames.LIVINGWOOD_CROSSBOW, () -> new LivingWoodCrossBow(ITEM_PROPERTIES));

    public static final RegistryObject<Item> ROD_OF_DISCORD = register(LibItemNames.ROD_OF_DISCORD, () -> new RodOfDiscord(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SHADOW_WARRIOR_HELM = register(LibItemNames.SHADOWWARRIOR_HELM, () -> new ShadowWarriorArmor(EquipmentSlot.HEAD, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_WARRIOR_CHEST = register(LibItemNames.SHADOWWARRIOR_CHEST, () -> new ShadowWarriorArmor(EquipmentSlot.CHEST, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_WARRIOR_LEGS = register(LibItemNames.SHADOWWARRIOR_LEGS, () -> new ShadowWarriorArmor(EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_WARRIOR_BOOTS = register(LibItemNames.SHADOWWARRIOR_BOOTS, () -> new ShadowWarriorArmor(EquipmentSlot.FEET, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_KATANA = register(LibItemNames.SHADOW_KATANA, () -> new ShadowKatana(ITEM_PROPERTIES));

    public static final RegistryObject<Item> GOBLINSLAYER_HELM = register(LibItemNames.GOBLINSLAYER_HELM, () -> new GoblinSlayerArmor(EquipmentSlot.HEAD, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_CHEST = register(LibItemNames.GOBLINSLAYER_CHEST, () -> new GoblinSlayerArmor(EquipmentSlot.CHEST, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_LEGS = register(LibItemNames.GOBLINSLAYER_LEGS, () -> new GoblinSlayerArmor(EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_BOOTS = register(LibItemNames.GOBLINSLAYER_BOOTS, () -> new GoblinSlayerArmor(EquipmentSlot.FEET, ITEM_PROPERTIES));

    public static final RegistryObject<Item> MIKU_HELM = register(LibItemNames.MIKU_HELM, () -> new MikuArmor(EquipmentSlot.HEAD, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MIKU_CHEST = register(LibItemNames.MIKU_CHEST, () -> new MikuArmor(EquipmentSlot.CHEST, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MIKU_LEGS = register(LibItemNames.MIKU_LEGS, () -> new MikuArmor(EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MIKU_BOOTS = register(LibItemNames.MIKU_BOOTS, () -> new MikuArmor(EquipmentSlot.FEET, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MAID_HELM = register(LibItemNames.MAID_HELM, () -> new MaidArmor(EquipmentSlot.HEAD, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MAID_CHEST = register(LibItemNames.MAID_CHEST, () -> new MaidArmor(EquipmentSlot.CHEST, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MAID_LEGS = register(LibItemNames.MAID_LEGS, () -> new MaidArmor(EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> MAID_BOOTS = register(LibItemNames.MAID_BOOTS, () -> new MaidArmor(EquipmentSlot.FEET, ITEM_PROPERTIES));

    public static final RegistryObject<Item> TRUE_SHADOW_KATANA = register(LibItemNames.TRUE_SHADOW_KATANA, () -> new TrueShadowKatana(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TRUE_TERRA_BLADE = register(LibItemNames.TRUE_TERRA_BLADE, () -> new TrueTerraBlade(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TRUE_THUNSTAR_CALLER = register(LibItemNames.TRUE_THUNSTAR_CALLER, () -> new TrueThunStarCaller(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INFLUX_WAVER = register(LibItemNames.INFLUX_WAVER, () -> new InfluxWaver(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FIRST_FRACTAL = register(LibItemNames.FIRST_FRACTAL, () -> new FirstFractal(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BUDDHISTRELICS = register(LibItemNames.BUDDHISTRELICS);
    public static final RegistryObject<Item> CAMERA = register(LibItemNames.CAMERA);
    public static final RegistryObject<Item> EXCALIBER = register(LibItemNames.EXCALIBER, () -> new Excaliber(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FAILNAUGHT = register(LibItemNames.FAILNAUGHT, () -> new Failnaught(ITEM_PROPERTIES));

    public static final TagKey<Item> CORAL_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "coral_item"));

    public static void registerRecipeSerializers(BiConsumer<RecipeSerializer<?>, ResourceLocation> r)
    {
        r.accept(CocktailRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "cocktail_upgrade"));
        r.accept(LenPotionRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "len_potion_bind"));
        r.accept(GrenadeRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "grenade_bind"));
        r.accept(DupeRuneRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "rune_dupe"));
        r.accept(GoldClothRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "gold_cloth_unbind"));
        r.accept(PrimoGemRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "primo_gem_buy_fate"));
        //just place it here lol
        registerRewardBag();
    }

    private static void registerRewardBag()
    {
        categoryListA.add(new WeightCategory(new ItemStack(UNIVERSAL_PETAL.get(), 4), 10));
        categoryListA.add(new WeightCategory(new ItemStack(UNIVERSAL_PETAL.get(), 8), 10));
        categoryListA.add(new WeightCategory(new ItemStack(UNIVERSAL_PETAL.get(), 6), 30));

        categoryListB.add(new WeightCategory(new ItemStack(ELEMENT_RUNE.get(), 2), 80));
        categoryListB.add(new WeightCategory(new ItemStack(SIN_RUNE.get(), 1), 20));

        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaSteel, 4), 15));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaPearl, 4), 15));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaDiamond, 4), 15));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.elementium, 3), 11));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.pixieDust, 3), 11));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.dragonstone, 3), 11));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaPowder, 8), 10));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.terrasteel, 1), 9));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.lifeEssence, 4), 8));
        categoryListC.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.gaiaIngot, 1), 7));
        categoryListC.add(new WeightCategory(new ItemStack(HERO_MEDAL.get(), 1), 1));

        categoryListD.add(new WeightCategory(new ItemStack(Items.COAL, 6), 40));
        categoryListD.add(new WeightCategory(new ItemStack(Items.IRON_INGOT, 4), 36));
        categoryListD.add(new WeightCategory(new ItemStack(Items.GOLD_INGOT, 4), 24));
        categoryListD.add(new WeightCategory(new ItemStack(Items.REDSTONE, 8), 22));
        categoryListD.add(new WeightCategory(new ItemStack(Items.ENDER_PEARL, 4), 20));
        categoryListD.add(new WeightCategory(new ItemStack(Items.DIAMOND, 1), 18));
        categoryListD.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.blackerLotus, 2), 16));
        categoryListD.add(new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed, 1), 12));

        categoryListD.add(new WeightCategory(new ItemStack(BUDDHISTRELICS.get()), 1));
    }

    private static RegistryObject<Item> register(String name)
    {
        return ITEMS.register(name, () -> new Item(ITEM_PROPERTIES));
    }

    private static RegistryObject<Item> register(String name, Item.Properties prop)
    {
        return ITEMS.register(name, () -> new Item(prop));
    }

    private static RegistryObject<Item> register(String name, Supplier<Item> sup)
    {
        return ITEMS.register(name, sup);
    }

    private static Item.Properties stackTo1()
    {
        return new Item.Properties().tab(ExtraBotany.ITEM_GROUP).stacksTo(1);
    }

    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
}
