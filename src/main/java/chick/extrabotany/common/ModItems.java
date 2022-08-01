package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.bauble.*;
import chick.extrabotany.common.foods.NormalFoods;
import chick.extrabotany.common.tools.armors.GoblinSlayerArmor;
import chick.extrabotany.common.tools.armors.ShadowWarriorArmor;
import chick.extrabotany.common.tools.items.NightmareFuel;
import chick.extrabotany.common.tools.weapons.ShadowKatana;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static chick.extrabotany.ExtraBotany.MODID;

public class ModItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
    public static final RegistryObject<Item> RAW_YLG_CHUNK = ITEMS.register("raw_yeluogui_chunk", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> YLG_INGOT = ITEMS.register("yeluogui_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> OBSIDIAN_APPLE = ITEMS.register("obsidian_apple", () -> new Item(new Item.Properties().stacksTo(16).food(NormalFoods.OBSIDIAN_APPLE).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> SPIRITFUEL_PROP = ITEMS.register("spiritfuel_prop", () -> new Item(new Item.Properties().food(NormalFoods.SPIRITFUEL_PROP).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> NIGHTMAREFUEL_PROP = ITEMS.register("nightmarefuel_prop", () -> new NightmareFuel(new Item.Properties().food(NormalFoods.NIGHTMAREFUEL_PROP).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> SPIRIT_PROP = ITEMS.register("spirit_prop", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHADOW_INGOT = ITEMS.register("shadowium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PHOTON_INGOT = ITEMS.register("photonium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GILDED_POTATO = ITEMS.register("gildedpotato", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GILDED_MASHED_POTATO = ITEMS.register("gildedmashedpotato", () -> new Item(new Item.Properties().stacksTo(1).tab(ExtraBotany.ITEM_GROUP)));
    /*
     *
     * these are baubles for curios mod
     *
     */
    public static final RegistryObject<Item> DEATH_RING = ITEMS.register("deathring", () -> new DeathRing(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AERO_STONE = ITEMS.register("aerostone", () -> new AeroStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> AQUA_STONE = ITEMS.register("aquastone", () -> new AquaStone(new Item.Properties().stacksTo(1).tab(ExtraBotany.ITEM_GROUP)));
    public static final RegistryObject<Item> EARTH_STONE = ITEMS.register("earthstone", () -> new EarthStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IGNIS_STONE = ITEMS.register("ignisstone", () -> new IgnisStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> FOUR_IN_ONE_STONE = ITEMS.register("fourinonestone", () -> new FourInOneStone(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPREME_AERO_STONE = ITEMS.register("supreme_aerostone", () -> new SupremeAeroStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_AQUA_STONE = ITEMS.register("supreme_aquastone", () -> new SupremeAquaStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_EARTH_STONE = ITEMS.register("supreme_earthstone", () -> new SupremeEarthStone(ITEM_PROPERTIES.stacksTo(1)));
    public static final RegistryObject<Item> SUPREME_IGNIS_STONE = ITEMS.register("supreme_ignisstone", () -> new SupremeIgnisStone(ITEM_PROPERTIES.stacksTo(1)));
    /*
     * these are armors and weapons.(items with damage)
     * for some reason, if they are assigned before,
     * items behind will be with nbt "damage" ,and can not stack.
     */
    public static final RegistryObject<Item> SHADOW_WARRIOR_HELM = ITEMS.register("shadowwarrior_helm", () -> new ShadowWarriorArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> SHADOW_WARRIOR_CHEST = ITEMS.register("shadowwarrior_chest", () -> new ShadowWarriorArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> SHADOW_WARRIOR_LEGS = ITEMS.register("shadowwarrior_legs", () -> new ShadowWarriorArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> SHADOW_WARRIOR_BOOTS = ITEMS.register("shadowwarrior_boots", () -> new ShadowWarriorArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> SHADOW_KATANA = ITEMS.register("shadowkatana", () -> new ShadowKatana(ITEM_PROPERTIES));

    public static final RegistryObject<Item> GOBLINSLAYER_HELM = ITEMS.register("goblinslayer_helm", () -> new GoblinSlayerArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> GOBLINSLAYER_CHEST = ITEMS.register("goblinslayer_chest", () -> new GoblinSlayerArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> GOBLINSLAYER_LEGS = ITEMS.register("goblinslayer_legs", () -> new GoblinSlayerArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> GOBLINSLAYER_BOOTS = ITEMS.register("goblinslayer_boots", () -> new GoblinSlayerArmor(EquipmentSlot.FEET));


    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
}
