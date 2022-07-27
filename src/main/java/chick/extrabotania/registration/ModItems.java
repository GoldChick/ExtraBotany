package chick.extrabotania.registration;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.foods.NormalFoods;
import chick.extrabotania.registration.tools.ModArmorsTier;
import chick.extrabotania.registration.tools.ModToolsTier;
import chick.extrabotania.registration.tools.ObsidianArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static chick.extrabotania.ExtraBotania.MODID;

public class ModItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotania.ITEM_GROUP);

    public static final RegistryObject<Item> RAW_YLG_CHUNK = ITEMS.register("raw_yeluogui_chunk", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> YLG_INGOT = ITEMS.register("yeluogui_ingot", () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",()->new SwordItem(ModToolsTier.OBSIDIAN, 114514, 2f, new Item.Properties().tab(ExtraBotania.ITEM_GROUP)));
    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new ObsidianArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new ObsidianArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new ArmorItem(ModArmorsTier.ARMOR_OBSIDIAN, EquipmentSlot.LEGS, ITEM_PROPERTIES));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new ArmorItem(ModArmorsTier.ARMOR_OBSIDIAN, EquipmentSlot.FEET, ITEM_PROPERTIES));
    public static final RegistryObject<Item> OBSIDIAN_APPLE = ITEMS.register("obsidian_apple", () -> new Item(ITEM_PROPERTIES.food(NormalFoods.OBSIDIAN_APPLE)));

    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
}
