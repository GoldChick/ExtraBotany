package chick.extrabotany.common.blocks;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.blocks.generating.SubTileMoonlightLily;
import chick.extrabotany.common.blocks.generating.SubTileOmniViolet;
import chick.extrabotany.common.blocks.generating.SubTileSunshineLily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.ModTiles;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.function.BiConsumer;

public class ModSubtiles
{
    private static final Item.Properties props = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
    private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.copy(Blocks.POPPY);
    private static final BlockBehaviour.Properties FLOATING_PROPS = BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL).lightLevel(s -> 15);

    public static final Block sunshinelily = new BlockSpecialFlower(MobEffects.ABSORPTION, 10, FLOWER_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final Block sunshinelilyFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final BlockEntityType<SubTileSunshineLily> SUNSHINELILY = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileSunshineLily::new, sunshinelily, sunshinelilyFloating);

    public static final Block moonlightlily = new BlockSpecialFlower(MobEffects.BLINDNESS, 10, FLOWER_PROPS, () -> ModSubtiles.MOONLIGHTLILY);
    public static final Block moonlightlilyFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.MOONLIGHTLILY);
    public static final BlockEntityType<SubTileMoonlightLily> MOONLIGHTLILY = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileMoonlightLily::new, moonlightlily, moonlightlilyFloating);

    public static final Block omniviolet = new BlockSpecialFlower(MobEffects.DAMAGE_BOOST, 10, FLOWER_PROPS, () -> ModSubtiles.OMNIVIOLET);
    public static final Block omnivioletFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.OMNIVIOLET);
    public static final BlockEntityType<SubTileOmniViolet> OMNIVIOLET = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileOmniViolet::new, omniviolet, omnivioletFloating);

    ///
    public static final Item sunshinelily_item = new ItemBlockSpecialFlower(sunshinelily, props);
    public static final Item sunshinelilyFloating_item = new ItemBlockSpecialFlower(sunshinelilyFloating, props);
    public static final Item moonlightlily_item = new ItemBlockSpecialFlower(moonlightlily, props);
    public static final Item moonlightlilyFloating_item = new ItemBlockSpecialFlower(moonlightlilyFloating, props);
    public static final Item omniviolet_item = new ItemBlockSpecialFlower(omniviolet, props);
    public static final Item omnivioletFloating_item = new ItemBlockSpecialFlower(omnivioletFloating, props);

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r)
    {
        r.accept(sunshinelily, idfor("sunshine_lily"));
        r.accept(moonlightlily, idfor("moonlight_lily"));
        r.accept(omniviolet, idfor("omni_violet"));
        r.accept(sunshinelilyFloating, floating(idfor("sunshine_lily")));
        r.accept(moonlightlilyFloating, floating(idfor("moonlight_lily")));
        r.accept(omnivioletFloating, floating(idfor("omni_violet")));
    }

    public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r)
    {
        r.accept(sunshinelily_item, getId(sunshinelily));
        r.accept(moonlightlily_item, getId(moonlightlily));
        r.accept(omniviolet_item, getId(omniviolet));
        r.accept(sunshinelilyFloating_item, getId(sunshinelilyFloating));
        r.accept(moonlightlilyFloating_item, getId(moonlightlilyFloating));
        r.accept(omnivioletFloating_item, getId(omnivioletFloating));
    }

    public static void registerWandHudCaps(ModTiles.BECapConsumer<IWandHUD> consumer)
    {
        consumer.accept(be -> new TileEntityGeneratingFlower.GeneratingWandHud<>((TileEntityGeneratingFlower) be),
                SUNSHINELILY, MOONLIGHTLILY, OMNIVIOLET);
    }

    public static void registerTEs(BiConsumer<BlockEntityType<?>, ResourceLocation> r)
    {
        r.accept(SUNSHINELILY, getId(sunshinelily));
        r.accept(MOONLIGHTLILY, getId(moonlightlily));
        r.accept(OMNIVIOLET, getId(omniviolet));
    }

    private static ResourceLocation getId(Block b)
    {
        return new ResourceLocation(ExtraBotany.MODID, b.getRegistryName().getPath());
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
