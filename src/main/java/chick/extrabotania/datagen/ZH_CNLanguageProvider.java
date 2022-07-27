package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.ModItems;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;


public class ZH_CNLanguageProvider extends LanguageProvider
{
    public ZH_CNLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ExtraBotania.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ExtraBotania.TAB_NAME, "教学");
        add(Registration.YLG_ORE.get(), "叶落归矿石");
        add(ModItems.RAW_YLG_CHUNK.get(),"Raw YeLuoGui Chunk");
        add(ModItems.YLG_INGOT.get(),"Legendary YeLuoGui Ingot");
        add(ModItems.OBSIDIAN_APPLE.get(),"黑曜石苹果");
        add(ModItems.OBSIDIAN_SWORD.get(),"黑曜石剑");
        add(ModItems.OBSIDIAN_HELMET.get(),"Obsidian Helmet");

    }
}
