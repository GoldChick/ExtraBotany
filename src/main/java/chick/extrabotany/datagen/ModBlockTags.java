package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
<<<<<<< HEAD
import chick.extrabotany.common.ModBlocks;
=======
import chick.extrabotany.common.Registration;
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTags extends BlockTagsProvider
{

    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper)
    {
        super(generator, ExtraBotany.MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
<<<<<<< HEAD
                .add(ModBlocks.YLG_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.YLG_ORE.get());
        tag(Tags.Blocks.ORES)
                .add(ModBlocks.YLG_ORE.get());
        tag(ModBlocks._YLG_ORE)
                .add(ModBlocks.YLG_ORE.get());
=======
                .add(Registration.YLG_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.YLG_ORE.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.YLG_ORE.get());
        tag(Registration._YLG_ORE)
                .add(Registration.YLG_ORE.get());
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
    }

    @Override
    public String getName()
    {
        return "Tutorial Tags";
    }
}