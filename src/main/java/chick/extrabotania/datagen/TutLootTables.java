package chick.extrabotania.datagen;

import chick.extrabotania.registration.ModItems;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;

public class TutLootTables extends BaseLootTableProvider
{

    public TutLootTables(DataGenerator dataGeneratorIn)
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