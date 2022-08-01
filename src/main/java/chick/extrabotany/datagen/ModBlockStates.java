package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
        simpleBlock(ModSubtiles.sunshinelily, new ConfiguredModel(models()
                //              .cross(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/sunshine_lily")))
                //      .withExistingParent(ModSubtiles.sunshinelily.getRegistryName().getPath(), modLoc("block/yeluogui_ore")))

                .singleTexture(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily)))
        );
        simpleBlock(ModBlocks.YLG_ORE2.get(), new ConfiguredModel(models()
                //           .cross(ModSubtiles.sunshinelily.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/sunshine_lily")))
                //      .withExistingParent(ModSubtiles.sunshinelily.getRegistryName().getPath(), modLoc("block/yeluogui_ore")))

                .singleTexture(ModBlocks.YLG_ORE2.get().getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModBlocks.YLG_ORE2.get())))
        );
    }
}