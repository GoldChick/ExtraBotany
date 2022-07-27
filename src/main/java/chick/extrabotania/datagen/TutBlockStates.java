package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider
{

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper)
    {
        super(gen, ExtraBotania.MODID, helper);
    }
    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(Registration.YLG_ORE.get());
    }
}