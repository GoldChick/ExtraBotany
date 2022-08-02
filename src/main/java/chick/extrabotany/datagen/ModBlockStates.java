package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider
{

    public ModBlockStates(DataGenerator gen, ExistingFileHelper helper)
    {
        super(gen, ExtraBotany.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(ModBlocks.YLG_ORE.get());
        //transparent rendertype in class ForgeClientInitializer
        simpleBlock(ModSubtiles.sunshinelily, new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlily, new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.moonlightlily))));
        //just blockstates for floating flowers. The texture is not valid here!
        simpleBlock(ModSubtiles.sunshinelilyFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.sunshinelilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        simpleBlock(ModSubtiles.moonlightlilyFloating,new ConfiguredModel(models().singleTexture(ModSubtiles.moonlightlilyFloating.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
    }
}