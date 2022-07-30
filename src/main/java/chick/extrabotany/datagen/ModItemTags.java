package chick.extrabotany.datagen;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static chick.extrabotany.ExtraBotany.MODID;

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
                .add(ModBlocks.YLG_ORE_ITEM.get())
        ;
        tag(Tags.Items.INGOTS)
                .add(ModItems.YLG_INGOT.get())
                .add(ModItems.PHOTON_INGOT.get())
                .add(ModItems.SHADOW_INGOT.get())
        ;
        tag(ModBlocks._YLG_ORE_ITEM)
                .add(ModBlocks.YLG_ORE_ITEM.get())
        ;
    }

    @Override
    public String getName()
    {
        return "extrabotany Item Tags";
    }
}