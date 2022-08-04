package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;
import vazkii.botania.common.entity.ModEntities;

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
        simpleBlock(ModBlocks.YLG_ORE.get());
        //transparent rendertype in class ForgeClientInitializer
        simpleBlock(ModSubtiles.sunshinelily, new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlily, new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.moonlightlily))));
        simpleBlock(ModSubtiles.omniviolet, new ConfiguredModel(models().singleTexture(ModSubtiles.omniviolet.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.omniviolet))));
        simpleBlock(ModSubtiles.geminiorchid, new ConfiguredModel(models().singleTexture(ModSubtiles.geminiorchid.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.geminiorchid))));

        simpleBlock(ModSubtiles.serenitian, new ConfiguredModel(models().singleTexture(ModSubtiles.serenitian.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.serenitian))));
        //just blockstates for floating flowers. The texture is not valid here!
        simpleBlock(ModSubtiles.sunshinelilyFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlilyFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.omnivioletFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.omnivioletFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.geminiorchidFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.geminiorchidFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));

        simpleBlock(ModSubtiles.serenitianFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.serenitianFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
    }
    public static void registerBlockEntityRenderers(BERConsumer consumer)
    {
        //floating generating
        consumer.register(ModSubtiles.SUNSHINELILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.MOONLIGHTLILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.OMNIVIOLET, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.GEMINIORCHID, RenderTileSpecialFlower::new);
        //floating functional
        consumer.register(ModSubtiles.SERENITIAN, RenderTileSpecialFlower::new);
    }
}