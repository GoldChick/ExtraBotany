package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
<<<<<<< HEAD
import chick.extrabotany.common.ModBlocks;
=======
import chick.extrabotany.common.Registration;
>>>>>>> 147563fc72a2ad578270984ec90bd0232bd5eb33
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider
{

    public ModBlockStates(DataGenerator gen, ExistingFileHelper helper)
    {
        super(gen, ExtraBotany.MODID, helper);
    }
    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(ModBlocks.YLG_ORE.get());
    }
}