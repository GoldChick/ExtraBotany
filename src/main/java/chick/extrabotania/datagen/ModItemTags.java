package chick.extrabotania.datagen;

import chick.extrabotania.common.ModItems;
import chick.extrabotania.common.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static chick.extrabotania.ExtraBotania.MODID;

public class ModItemTags extends ItemTagsProvider
{
    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper)
    {
        super(generator, blockTags, MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(Tags.Items.ORES)
                .add(Registration.YLG_ORE_ITEM.get());
        tag(Tags.Items.INGOTS)
                .add(ModItems.YLG_INGOT.get());
        tag(Registration._YLG_ORE_ITEM)
                .add(Registration.YLG_ORE_ITEM.get());
    }

    @Override
    public String getName()
    {
        return "ExtraBotania Item Tags";
    }
}