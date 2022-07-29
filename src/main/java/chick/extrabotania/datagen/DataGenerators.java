package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.datagen.lang.EN_USLanguageProvider;
import chick.extrabotania.datagen.lang.ZH_CNLanguageProvider;
import chick.extrabotania.datagen.recipes.ModCraftingTableRecipes;
import chick.extrabotania.datagen.recipes.ModManaInfusionRecipes;
import chick.extrabotania.datagen.recipes.ModRunicAltarRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ExtraBotania.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new ModCraftingTableRecipes(generator));
            generator.addProvider(new ModManaInfusionRecipes(generator));
            generator.addProvider(new ModRunicAltarRecipes(generator));
            generator.addProvider(new ModLootTables(generator));
            TutBlockTags blockTags = new TutBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new ModBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new TutItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new EN_USLanguageProvider(generator, "en_us"));
            generator.addProvider(new ZH_CNLanguageProvider(generator,"zh_cn"));
        }
    }
}