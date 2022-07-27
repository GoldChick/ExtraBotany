package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider
{

    public TutBlockTags(DataGenerator generator, ExistingFileHelper helper)
    {
        super(generator, ExtraBotania.MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Registration.YLG_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.YLG_ORE.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.YLG_ORE.get());
        tag(Registration._YLG_ORE)
                .add(Registration.YLG_ORE.get());
    }

    @Override
    public String getName()
    {
        return "Tutorial Tags";
    }
}