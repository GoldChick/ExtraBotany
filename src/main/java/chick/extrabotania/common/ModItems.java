package chick.extrabotania.common;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.common.foods.NormalFoods;
import chick.extrabotania.common.tools.ModArmorsTier;
import chick.extrabotania.common.tools.armors.ObsidianArmor;
import chick.extrabotania.common.tools.weapons.ObsidianSword;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
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
    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", ObsidianSword::new);
    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new ObsidianArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new ObsidianArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new ObsidianArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new ArmorItem(ModArmorsTier.ARMOR_OBSIDIAN, EquipmentSlot.FEET, ITEM_PROPERTIES));
    //for some reason, if "stacksTo()" is before "tab(ExtraBotania.ITEM_GROUP)", it will crash
    public static final RegistryObject<Item> OBSIDIAN_APPLE = ITEMS.register("obsidian_apple", () -> new Item(new Item.Properties().stacksTo(16).food(NormalFoods.OBSIDIAN_APPLE).tab(ExtraBotania.ITEM_GROUP)));
    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
}
