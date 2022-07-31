package chick.extrabotany.common;

<<<<<<< HEAD
import chick.extrabotany.ExtraBotany;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static chick.extrabotany.ExtraBotany.MODID;
<<<<<<<< HEAD:src/main/java/chick/extrabotany/common/ModBlocks.java
========
import static chick.extrabotany.common.ModItems.GetItems;
>>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33:src/main/java/chick/extrabotany/common/Registration.java
=======
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static chick.extrabotany.ExtraBotany.MODID;
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33

public class ModBlocks
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //item for block item
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
<<<<<<< HEAD
<<<<<<<< HEAD:src/main/java/chick/extrabotany/common/ModBlocks.java
    //item properties
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
========
    public static void Init()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        GetItems().register(bus);
    }
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
>>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33:src/main/java/chick/extrabotany/common/Registration.java

    public static final RegistryObject<Block> YLG_ORE = BLOCKS.register("yeluogui_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1f, 4f)));
    public static final RegistryObject<Item> YLG_ORE_ITEM = fromBlock(YLG_ORE);
    public static final TagKey<Block> _YLG_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));
    public static final TagKey<Item> _YLG_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));

=======
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33


    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
    public static DeferredRegister<Block> GetBlocks()
    {
        return BLOCKS;
    }
<<<<<<< HEAD

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
=======
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
}
