package chick.extrabotania.datagen;

import chick.extrabotania.common.ModItems;
import chick.extrabotania.common.Registration;
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
                Registration.YLG_ORE.get(),
                createSilkTouchTable("yeluogui_ore", Registration.YLG_ORE.get(), ModItems.RAW_YLG_CHUNK.get(), 1, 3)
        );
    }
}