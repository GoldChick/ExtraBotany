package chick.extrabotany.common.blocks;

import chick.extrabotany.ExtraBotany;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.ModTiles;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.function.BiConsumer;

public class ModSubtiles
{
    private static final Item.Properties props = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
    private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.copy(Blocks.POPPY);
    private static final BlockBehaviour.Properties FLOATING_PROPS = ModBlocks.FLOATING_PROPS;

    public static final Block sunshinelily = new BlockSpecialFlower(MobEffects.ABSORPTION, 10, FLOWER_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final Block sunshinelilyFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final BlockEntityType<SubTileSunshineLily> SUNSHINELILY = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileSunshineLily::new, sunshinelily, sunshinelilyFloating);


    public static final Item sunshinelily_item =new ItemBlockSpecialFlower(sunshinelily, props);
    public static final Item sunshinelilyFloating_item =new ItemBlockSpecialFlower(sunshinelilyFloating, props);
    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r)
    {
        r.accept(sunshinelily, idfor("sunshine_lily"));
        r.accept(sunshinelilyFloating, floating(idfor("sunshine_lily")));
    }

    public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r)
    {
        r.accept(sunshinelily_item, getId(sunshinelily));
        r.accept(sunshinelilyFloating_item, getId(sunshinelilyFloating));
    }

    public static void registerWandHudCaps(ModTiles.BECapConsumer<IWandHUD> consumer)
    {
        consumer.accept(be -> new TileEntityGeneratingFlower.GeneratingWandHud<>((TileEntityGeneratingFlower) be),
                SUNSHINELILY);
    }

    private static ResourceLocation getId(Block b)
    {
        return new ResourceLocation(ExtraBotany.MODID,b.getRegistryName().getPath());
    }

    private static ResourceLocation floating(ResourceLocation orig)
    {
        return new ResourceLocation(ExtraBotany.MODID, "floating_" + orig.getPath());
    }

    private static ResourceLocation idfor(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, path);
    }
}
