package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

public class ModBlockStates extends BlockStateProvider
{

    public ModBlockStates(DataGenerator gen, ExistingFileHelper helper)
    {
        super(gen, ExtraBotany.MODID, helper);
    }


    @Override
    protected void registerStatesAndModels()
    {
        //transparent rendertype in class ForgeClientInitializer
        simpleBlock(ModBlocks.DIMENSION_CATALYST.get());
        //just create json for floating flower!
        var stream = Registry.BLOCK.stream().filter(b -> b.getRegistryName().getNamespace().equals(ExtraBotany.MODID));
        stream.forEach(flower ->
        {
            if (flower instanceof BlockSpecialFlower)
            {
                simpleFlowerTexture(flower);
            } else if (flower instanceof BlockFloatingSpecialFlower)
            {
                simpleFloatingFlowerTexture(flower);
            }
        });
    }

    private void simpleFlowerTexture(Block... flowers)
    {
        for (var flower : flowers)
        {
            simpleBlock(flower, new ConfiguredModel(models().singleTexture(flower.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(flower))));
        }
    }

    private void simpleFloatingFlowerTexture(Block... flowers)
    {
        for (var flower : flowers)
        {
            simpleBlock(flower, new ConfiguredModel(models().singleTexture(flower.getRegistryName().getPath(), new ResourceLocation("extrabotany:block/shapes/cross"), "cross", blockTexture(ModSubtiles.sunshinelily))));
        }
    }
}