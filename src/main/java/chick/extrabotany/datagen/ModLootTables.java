package chick.extrabotany.datagen;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.data.DataGenerator;

public class ModLootTables extends BaseLootTableProvider
{

    public ModLootTables(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables()
    {
        //lootTables.put(
        //        ModBlocks.YLG_ORE.get(),
        //        createSilkTouchTable("yeluogui_ore", ModBlocks.YLG_ORE.get(), ModItems.RAW_YLG_CHUNK.get(), 1, 3)
        //);
        lootTables.put(ModSubtiles.omniviolet, createSimpleTable("omniviolet",ModSubtiles.omniviolet));
        lootTables.put(ModSubtiles.omnivioletFloating, createSimpleTable("floating_omniviolet",ModSubtiles.omnivioletFloating));

        lootTables.put(ModSubtiles.serenitian, createSimpleTable("serenitian",ModSubtiles.serenitian));
        lootTables.put(ModSubtiles.serenitianFloating, createSimpleTable("floating_serenitian",ModSubtiles.serenitianFloating));
    }
}