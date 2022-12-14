package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.lib.ModTags;

import java.util.Comparator;
import java.util.function.Predicate;


public class ModBlockTags extends BlockTagsProvider
{
    public static final Predicate<Block> EXBOTANY_BLOCK = b -> ExtraBotany.MODID.equals(Registry.BLOCK.getKey(b).getNamespace());

    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper)
    {
        super(generator, ExtraBotany.MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLOCK_PHOTONIUM.get())
                .add(ModBlocks.BLOCK_SHADOWIUM.get())
        ;

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BLOCK_ORICHALCOS.get())
        ;
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BLOCK_PHOTONIUM.get())
                .add(ModBlocks.BLOCK_SHADOWIUM.get())
                .add(ModBlocks.BLOCK_ORICHALCOS.get())
        ;
        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.BLOCK_PHOTONIUM.get())
                .add(ModBlocks.BLOCK_SHADOWIUM.get())
                .add(ModBlocks.BLOCK_ORICHALCOS.get())
        ;
        tag(Tags.Blocks.ORES)

        ;
        tag(ModTags.Blocks.GENERATING_SPECIAL_FLOWERS)
                .add(ModSubtiles.sunshinelily)
                .add(ModSubtiles.moonlightlily)
                .add(ModSubtiles.omniviolet)
                .add(ModSubtiles.geminiorchid)
                .add(ModSubtiles.bellflower)
                .add(ModSubtiles.reikarorchid)
                .add(ModSubtiles.bloodyenchantress)
                .add(ModSubtiles.tinkleflower)
                .add(ModSubtiles.edelweiss)
        ;
        tag(ModTags.Blocks.FUNCTIONAL_SPECIAL_FLOWERS)
                .add(ModSubtiles.serenitian)
                .add(ModSubtiles.annoying)
        ;
        tag(ModTags.Blocks.SPECIAL_FLOATING_FLOWERS).add(registry.stream().filter(EXBOTANY_BLOCK)
                .filter(b -> b instanceof BlockFloatingSpecialFlower)
                .sorted(Comparator.comparing(Registry.BLOCK::getKey))
                .toArray(Block[]::new))
        ;

    }

    @Override
    public @NotNull String getName()
    {
        return "extrabotany Block Tags";
    }
}