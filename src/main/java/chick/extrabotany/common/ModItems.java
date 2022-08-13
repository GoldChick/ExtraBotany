package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.baubles.*;
import chick.extrabotany.common.baubles.cosmetic.Cosmetic;
import chick.extrabotany.common.brews.Cocktail;
import chick.extrabotany.common.brews.SplashGrenade;
import chick.extrabotany.common.crafting.CocktailRecipe;
import chick.extrabotany.common.crafting.LenPotionRecipe;
import chick.extrabotany.common.foods.NormalFoods;
import chick.extrabotany.common.items.*;
import chick.extrabotany.common.lens.LenPotion;
import chick.extrabotany.common.lens.LenPotionBase;
import chick.extrabotany.common.lens.LenSmelt;
import chick.extrabotany.common.tools.armors.GoblinSlayerArmor;
import chick.extrabotany.common.tools.armors.ItemShadowWarriorArmor;
import chick.extrabotany.common.tools.weapons.ShadowKatana;
import chick.extrabotany.common.tools.weapons.TrueShadowKatana;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.item.lens.ItemLens;

import java.util.function.BiConsumer;

import static chick.extrabotany.ExtraBotany.MODID;

public class ModItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);

    public static final RegistryObject<Item> SPIRITFUEL_PROP = ITEMS.register("spiritfuel_prop", () -> new Item(new Item.Properties().food(NormalFoods.SPIRITFUEL_PROP).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> NIGHTMAREFUEL_PROP = ITEMS.register("nightmarefuel_prop", () -> new NightmareFuel(new Item.Properties().food(NormalFoods.NIGHTMAREFUEL_PROP).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> SPIRIT_PROP = ITEMS.register("spirit_prop", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_INGOT = ITEMS.register("shadowium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PHOTON_INGOT = ITEMS.register("photonium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GILDED_POTATO = ITEMS.register("gildedpotato", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GILDED_MASHED_POTATO = ITEMS.register("gildedmashedpotato", () -> new Item(new Item.Properties().food(NormalFoods.GILDED_MASHED_POTATO).stacksTo(1).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> MANA_READER = ITEMS.register("manareader", () -> new ManaReader(new Item.Properties().stacksTo(1).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> THE_CHAOS = ITEMS.register("thechaos", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> THE_ORIGIN = ITEMS.register("theorigin", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> THE_END = ITEMS.register("theend", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> THE_UNIVERSE = ITEMS.register("theuniverse", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> EMPTY_BOTTLE = ITEMS.register("empty_bottle", () -> new EmptyGlassBottle(ITEM_PROPERTIES));
    public static final RegistryObject<Item> MANA_DRINK = ITEMS.register("mana_drink", () -> new ManaDrink(new Item.Properties().food(NormalFoods.MANA_DRINK).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> COCK_TAIL = ITEMS.register("cock_tail", () -> new Cocktail(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SPLASH_GRENADE = ITEMS.register("splash_grenade", () -> new SplashGrenade(ITEM_PROPERTIES.stacksTo(32)));
    public static final RegistryObject<Item> HERO_MEDAL = ITEMS.register("heromedal", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FRIED_CHICKEN = ITEMS.register("friedchicken", () -> new Item(new Item.Properties().food(NormalFoods.FRIED_CHICKEN).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> TICKET = ITEMS.register("ticket", () -> new Ticket(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ORICHALCOS  = ITEMS.register("orichalcos", () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> LEN_SMELT = ITEMS.register("len_smelt", () -> new ItemLens(ITEM_PROPERTIES.stacksTo(1), new LenSmelt(), vazkii.botania.common.item.lens.ItemLens.PROP_TOUCH));
    public static final RegistryObject<Item> LEN_POTION = ITEMS.register("len_potion", () -> new LenPotionBase(ITEM_PROPERTIES.stacksTo(1), new LenPotion(), vazkii.botania.common.item.lens.ItemLens.PROP_INTERACTION));
    /**
     * these are baubles for curios mod
     **/
    public static final RegistryObject<Item> PYLON = ITEMS.register("pylon", () -> new Cosmetic(Cosmetic.Variant.Pylon, ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOX_EAR = ITEMS.register("foxear", () -> new Cosmetic(Cosmetic.Variant.FoxEar, ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOX_MASK = ITEMS.register("foxmask", () -> new Cosmetic(Cosmetic.Variant.FoxMask, ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPER_CROWN = ITEMS.register("supercrown", () -> new Cosmetic(Cosmetic.Variant.SuperCrown, ITEM_PROPERTIES));
    public static final RegistryObject<Item> RED_SCARF = ITEMS.register("redscarf", () -> new Cosmetic(Cosmetic.Variant.RedScarf, ITEM_PROPERTIES));
    public static final RegistryObject<Item> BLACK_GLASSES = ITEMS.register("blackglasses", () -> new Cosmetic(Cosmetic.Variant.BlackGlasses, ITEM_PROPERTIES));
    public static final RegistryObject<Item> STONE_MASK = ITEMS.register("stonemask", () -> new Cosmetic(Cosmetic.Variant.StoneMask, ITEM_PROPERTIES));
    public static final RegistryObject<Item> THUG_LIFE = ITEMS.register("thuglife", () -> new Cosmetic(Cosmetic.Variant.ThugLife, ITEM_PROPERTIES));

    public static final RegistryObject<Item> PEACE_AMULET = ITEMS.register("peace_amulet", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FROST_RING = ITEMS.register("frostring", () -> new FrostRing(ITEM_PROPERTIES));
    public static final RegistryObject<Item> DEATH_RING = ITEMS.register("deathring", () -> new DeathRing(ITEM_PROPERTIES));
    public static final RegistryObject<Item> POWER_GLOVE = ITEMS.register("powerglove", () -> new PowerGlove(ITEM_PROPERTIES));
    public static final RegistryObject<Item> JINGWEI_FEATHER = ITEMS.register("jingweifeather", () -> new JingweiFeather(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AERO_STONE = ITEMS.register("aerostone", () -> new AeroStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AQUA_STONE = ITEMS.register("aquastone", () -> new AquaStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> EARTH_STONE = ITEMS.register("earthstone", () -> new EarthStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IGNIS_STONE = ITEMS.register("ignisstone", () -> new IgnisStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOUR_IN_ONE_STONE = ITEMS.register("fourinonestone", () -> new FourInOneStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPREME_AERO_STONE = ITEMS.register("supreme_aerostone", () -> new SupremeAeroStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_AQUA_STONE = ITEMS.register("supreme_aquastone", () -> new SupremeAquaStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_EARTH_STONE = ITEMS.register("supreme_earthstone", () -> new SupremeEarthStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_IGNIS_STONE = ITEMS.register("supreme_ignisstone", () -> new SupremeIgnisStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SAGES_MANA_RING = ITEMS.register("sages_mana_ring", () -> new SagesManaRing(ITEM_PROPERTIES));
    /*
     * these are armors and weapons.(items with damage)
     * for some reason, if they are assigned before,
     * items behind will be with nbt "damage" ,and can not stack.
     */
    public static final RegistryObject<Item> ROD_OF_DISCORD = ITEMS.register("rodofdiscord", () -> new RodOfDiscord(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SHADOW_WARRIOR_HELM = ITEMS.register("shadowwarrior_helm", () -> new ItemShadowWarriorArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> SHADOW_WARRIOR_CHEST = ITEMS.register("shadowwarrior_chest", () -> new ItemShadowWarriorArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> SHADOW_WARRIOR_LEGS = ITEMS.register("shadowwarrior_legs", () -> new ItemShadowWarriorArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> SHADOW_WARRIOR_BOOTS = ITEMS.register("shadowwarrior_boots", () -> new ItemShadowWarriorArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> SHADOW_KATANA = ITEMS.register("shadowkatana", () -> new ShadowKatana(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TRUE_SHADOW_KATANA = ITEMS.register("trueshadowkatana", () -> new TrueShadowKatana(ITEM_PROPERTIES));

    public static final RegistryObject<Item> GOBLINSLAYER_HELM = ITEMS.register("goblinslayer_helm", () -> new GoblinSlayerArmor(EquipmentSlot.HEAD, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_CHEST = ITEMS.register("goblinslayer_chest", () -> new GoblinSlayerArmor(EquipmentSlot.CHEST, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_LEGS = ITEMS.register("goblinslayer_legs", () -> new GoblinSlayerArmor(EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> GOBLINSLAYER_BOOTS = ITEMS.register("goblinslayer_boots", () -> new GoblinSlayerArmor(EquipmentSlot.FEET, ITEM_PROPERTIES));

    public static void registerRecipeSerializers(BiConsumer<RecipeSerializer<?>, ResourceLocation> r)
    {
        r.accept(CocktailRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "cocktail_upgrade"));
        r.accept(LenPotionRecipe.SERIALIZER, new ResourceLocation(ExtraBotany.MODID, "len_potion_bind"));
    }

    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
}
