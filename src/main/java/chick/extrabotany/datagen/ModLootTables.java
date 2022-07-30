package chick.extrabotany.datagen;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
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
    }
}