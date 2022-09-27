package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.PowerFrame;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;


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

        //just create json for floating flower!
        var stream = Registry.BLOCK.stream().filter(b -> b.getRegistryName().getNamespace().equals(ExtraBotany.MODID));
        stream.filter(b -> !(b instanceof PowerFrame))
                .forEach(block ->
                {
                    if (block instanceof BlockSpecialFlower)
                    {
                        simpleFlowerTexture(block);
                    } else if (block instanceof BlockFloatingSpecialFlower)
                    {
                        simpleFloatingFlowerTexture(block);
                    } else if (block.getRegistryName().getPath().startsWith("molten"))
                    {

                    } else
                    //simple block
                    {
                        simpleBlock(block);
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