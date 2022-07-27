package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.ModItems;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.common.Mod;

public class EN_USLanguageProvider extends LanguageProvider
{

    public EN_USLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ExtraBotania.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ExtraBotania.TAB_NAME, "Tutorial");
        add(Registration.YLG_ORE.get(), "YeLuoGui ore");
        add(ModItems.RAW_YLG_CHUNK.get(),"Raw YeLuoGui Chunk");
        add(ModItems.YLG_INGOT.get(),"Legendary YeLuoGui Ingot");
        add(ModItems.OBSIDIAN_APPLE.get(),"Obsidian Apple");
        add(ModItems.OBSIDIAN_SWORD.get(),"Obsidian Sword");
        add(ModItems.OBSIDIAN_HELMET.get(),"Obsidian Helmet");
        add(ModItems.OBSIDIAN_CHESTPLATE.get(),"Obsidian Chestplate");
        add(ModItems.OBSIDIAN_LEGGINGS.get(),"Obsidian Leggings");
        add(ModItems.OBSIDIAN_BOOTS.get(),"Obsidian Boots");
    }
}