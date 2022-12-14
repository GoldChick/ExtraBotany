package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.base.ConfigHandler;
import chick.extrabotany.common.optional.EXBOTCompat;
import chick.extrabotany.common.optional.tconstruct.TConCompat;
import chick.extrabotany.datagen.lang.EN_USLanguageProvider;
import chick.extrabotany.datagen.lang.ZH_CNLanguageProvider;
import chick.extrabotany.datagen.recipes.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ExtraBotany.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer())
        {
            RecipeData(event, generator);

            generator.addProvider(new ModLootTables(generator));
            ModBlockTags blockTags = new ModBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient())
        {
            generator.addProvider(new ModItemOverrideModels(generator));
            generator.addProvider(new ModAdvancementProvider(generator, event.getExistingFileHelper()));
            generator.addProvider(new ModBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ModItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new FloatingFlowerModelProvider(generator));
            generator.addProvider(new EN_USLanguageProvider(generator, "en_us"));
            generator.addProvider(new ZH_CNLanguageProvider(generator, "zh_cn"));
        }

        if (ModList.get().isLoaded(EXBOTCompat.TCON_ID) && ConfigHandler.COMMON.doCompat.get())
        {
            TConCompat.tConDatagen(event);
        }
    }

    private static void RecipeData(GatherDataEvent event, DataGenerator generator)
    {
        if (event.includeServer())
        {
            generator.addProvider(new ModBrewRecipes(generator));
            generator.addProvider(new ModElvenTradeRecipes(generator));
            generator.addProvider(new ModCraftingTableRecipes(generator));
            generator.addProvider(new ModManaInfusionRecipes(generator));
            generator.addProvider(new ModRunicAltarRecipes(generator));
            generator.addProvider(new ModPetalRecipes(generator));
            generator.addProvider(new ModTerraRecipes(generator));

            generator.addProvider(new StonesiaRecipes(generator));
        }
    }
}