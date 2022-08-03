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
        lootTables.put(
                ModBlocks.YLG_ORE.get(),
                createSilkTouchTable("yeluogui_ore", ModBlocks.YLG_ORE.get(), ModItems.RAW_YLG_CHUNK.get(), 1, 3)
        );

        //lootTables.put(ModSubtiles.sunshinelily, createSimpleTable("sunshine_lily",ModSubtiles.sunshinelily));
        //lootTables.put(ModSubtiles.moonlightlily, createSimpleTable("moonlight_lily",ModSubtiles.moonlightlily));
        //lootTables.put(ModSubtiles.sunshinelilyFloating, createSimpleTable("floating_sunshine_lily",ModSubtiles.sunshinelilyFloating));
        //lootTables.put(ModSubtiles.moonlightlilyFloating, createSimpleTable("floating_moonlight_lily",ModSubtiles.moonlightlilyFloating));
    }
}