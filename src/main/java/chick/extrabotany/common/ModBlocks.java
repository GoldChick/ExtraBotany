package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.SubTileSunshineLily;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.client.RenderProperties;
import net.minecraftforge.client.model.obj.MaterialLibrary;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static chick.extrabotany.ExtraBotany.MODID;
import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;
import static net.minecraftforge.fml.loading.FMLEnvironment.dist;

public class ModBlocks
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //item for block item
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    //item properties
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);

    public static final RegistryObject<Block> YLG_ORE = BLOCKS.register("yeluogui_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1f, 4f)));
    public static final RegistryObject<Block> YLG_ORE2 = BLOCKS.register("yeluogui_ore2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Item> YLG_ORE_ITEM = fromBlock(YLG_ORE);
    public static final RegistryObject<Item> YLG_ORE_ITEM2 = fromBlock(YLG_ORE2);
    public static final TagKey<Block> _YLG_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));
    public static final TagKey<Item> _YLG_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "yeluogui_ore"));

    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }

    public static DeferredRegister<Block> GetBlocks()
    {
        return BLOCKS;
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block)
    {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
