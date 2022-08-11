package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.decor.BlockFloatingFlower;
import vazkii.botania.common.block.decor.BlockModMushroom;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.lib.LibMisc;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

public class ModBlockStates extends BlockStateProvider
{

    public ModBlockStates(DataGenerator gen, ExistingFileHelper helper)
    {
        super(gen, ExtraBotany.MODID, helper);
    }

    public interface BERConsumer
    {
        <E extends BlockEntity> void register(BlockEntityType<E> type, BlockEntityRendererProvider<? super E> factory);
    }

    @Override
    protected void registerStatesAndModels()
    {
        //transparent rendertype in class ForgeClientInitializer
        simpleBlock(ModSubtiles.sunshinelily, new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlily, new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.moonlightlily))));
        simpleBlock(ModSubtiles.omniviolet, new ConfiguredModel(models().singleTexture(ModSubtiles.omniviolet.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.omniviolet))));
        simpleBlock(ModSubtiles.geminiorchid, new ConfiguredModel(models().singleTexture(ModSubtiles.geminiorchid.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.geminiorchid))));
        simpleBlock(ModSubtiles.bellflower, new ConfiguredModel(models().singleTexture(ModSubtiles.bellflower.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.bellflower))));

        simpleBlock(ModSubtiles.serenitian, new ConfiguredModel(models().singleTexture(ModSubtiles.serenitian.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.serenitian))));
        simpleBlock(ModSubtiles.annoying, new ConfiguredModel(models().singleTexture(ModSubtiles.annoying.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.annoying))));
        //just blockstates for floating flowers. The texture is not valid here!
        simpleBlock(ModSubtiles.sunshinelilyFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlilyFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.omnivioletFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.omnivioletFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.geminiorchidFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.geminiorchidFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.bellflowerFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.bellflowerFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));

        simpleBlock(ModSubtiles.serenitianFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.serenitianFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.annoyingFloating, new ConfiguredModel(models().singleTexture(ModSubtiles.annoyingFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
    }

    public static void registerBlockEntityRenderers(BERConsumer consumer)
    {
        //floating generating
        consumer.register(ModSubtiles.SUNSHINELILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.MOONLIGHTLILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.OMNIVIOLET, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.GEMINIORCHID, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.BELLFLOWER, RenderTileSpecialFlower::new);
        //floating functional
        consumer.register(ModSubtiles.SERENITIAN, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.ANNOYINGFLOWER, RenderTileSpecialFlower::new);
    }

    public static void setRenderType()
    {
        //transparent
        Registry.BLOCK.stream().filter(b -> Registry.BLOCK.getKey(b).getNamespace().equals(ExtraBotany.MODID))
                .forEach(b ->
                {
                    if (b instanceof BlockSpecialFlower)
                    {
                        setRenderLayer(b, RenderType.cutout());
                    }
                });
    }
}