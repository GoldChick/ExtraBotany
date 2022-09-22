package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.blocks.DimensionCatalyst;
import chick.extrabotany.common.blocks.PowerFrame;
import chick.extrabotany.common.libs.LibBlockNames;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static chick.extrabotany.ExtraBotany.MODID;
import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

public class ModBlocks
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //item for block item
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    //item properties
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);

    //test blocks

    public static final RegistryObject<Block> DIMENSION_CATALYST = BLOCKS.register(LibBlockNames.DIMENSION_CATALYST, () -> new DimensionCatalyst(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1f, 4f)));
    public static final RegistryObject<Item> DIMENSION_CATALYST_ITEM = fromBlock(DIMENSION_CATALYST);
    public static final RegistryObject<Block> BLOCK_PHOTONIUM = BLOCKS.register(LibBlockNames.BLOCK_PHOTONIUM, () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(2f, 8f)));
    public static final RegistryObject<Item> BLOCK_PHOTONIUM_ITEM = fromBlock(BLOCK_PHOTONIUM);
    public static final RegistryObject<Block> BLOCK_SHADOWIUM = BLOCKS.register(LibBlockNames.BLOCK_SHADOWIUM, () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(2f, 8f)));
    public static final RegistryObject<Item> BLOCK_SHADOWIUM_ITEM = fromBlock(BLOCK_SHADOWIUM);
    public static final RegistryObject<Block> BLOCK_ORICHALCOS = BLOCKS.register(LibBlockNames.BLOCK_ORICHALCOS, () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(4f, 12f)));
    public static final RegistryObject<Item> BLOCK_ORICHALCOS_ITEM = fromBlock(BLOCK_ORICHALCOS);

    public static final RegistryObject<Block> POWER_FRAME = BLOCKS.register(LibBlockNames.POWER_FRAME, () -> new PowerFrame(BlockBehaviour.Properties.of(Material.GLASS).requiresCorrectToolForDrops().strength(1f, 4f)));
    public static final RegistryObject<Item> POWER_FRAME_ITEM = fromBlock(POWER_FRAME);


    //public static final TagKey<Block> _YLG_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));
    //public static final TagKey<Item> _YLG_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));

    public static DeferredRegister<Item> getItems()
    {
        return ITEMS;
    }

    public static DeferredRegister<Block> getBlocks()
    {
        return BLOCKS;
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
