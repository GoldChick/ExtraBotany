package chick.extrabotany.datagen;

<<<<<<< HEAD
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
=======
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.Registration;
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
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