package chick.extrabotany.datagen.lang;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.common.Mod;

public class EN_USLanguageProvider extends LanguageProvider
{

    public EN_USLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, ExtraBotany.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup." + ExtraBotany.TAB_NAME, "extrabotany");
        add(ModBlocks.YLG_ORE.get(), "YeLuoGui ore");
        add(ModItems.RAW_YLG_CHUNK.get(), "Raw YeLuoGui Chunk");
        add(ModItems.YLG_INGOT.get(), "Legendary YeLuoGui Ingot");
        add(ModItems.OBSIDIAN_APPLE.get(), "Obsidian Apple");
        add(ModItems.NIGHTMAREFUEL_PROP.get(), "Nightmare Fuel");
        add(ModItems.SPIRITFUEL_PROP.get(), "Spirit Fuel");
        add(ModItems.SPIRIT_PROP.get(), "Spirit Fragment");
        add(ModItems.SHADOW_INGOT.get(), "Shadowium");
        add(ModItems.PHOTON_INGOT.get(), "Photonium");
        add(ModItems.GILDED_POTATO.get(), "Gilded Potato");
        add(ModItems.GILDED_MASHED_POTATO.get(), "Gilded Mashed Potato");

        add(ModItems.AERO_STONE.get(), "Aero Stone");
        add(ModItems.AQUA_STONE.get(), "Aqua Stone");
        add(ModItems.EARTH_STONE.get(), "Earth Stone");
        add(ModItems.IGNIS_STONE.get(), "Ignis Stone");
        add(ModItems.SUPREME_AERO_STONE.get(), "Supreme Aero Stone");

        add(ModItems.SHADOW_KATANA.get(), "Shadow Katana");
        add(ModItems.SHADOW_WARRIOR_HELM.get(), "Shadow Warrior Helmet");
        add(ModItems.SHADOW_WARRIOR_CHEST.get(), "Shadow Warrior Chestplate");
        add(ModItems.SHADOW_WARRIOR_LEGS.get(), "Shadow Warrior Leggings");
        add(ModItems.SHADOW_WARRIOR_BOOTS.get(), "Shadow Warrior Boots");

        add(ModItems.GOBLINSLAYER_HELM.get(), "Goblin Slayer Helmet");
        add(ModItems.GOBLINSLAYER_CHEST.get(), "Goblin Slayer Chestplate");
        add(ModItems.GOBLINSLAYER_LEGS.get(), "Goblin Slayer Leggings");
        add(ModItems.GOBLINSLAYER_BOOTS.get(), "Goblin Slayer Boots");
        Pantchouli();
    }

    private void Pantchouli()
    {
        add("extrabotany.category.extrabotany", "Extra Botania");

        add("extrabotany.entry.header", "Welcome to the World");
        add("extrabotany.page.header0", "Welcome to the World of the supreme principle of Mana! You are the one who has great potential and I believe you will have a shiny, bright future. I am sure you are hunger for more knowledge. Follow me, I will pass on my knowledge and experience to you.");
        add("extrabotany.page.header1", "The more progress you have done, the more knowledge you will have. I could give you one hint, you could obtain Spirit Fuel by eating Nightmare Fuel.");

        add("extrabotany.entry.nightmarefuel", "Nightmare Fuel");
        add("extrabotany.page.nightmarefuel0", "$(item)Nightmare Fuel$(0) is the aggregation of the nightmare, you will suffer after eating it, but it may be the beginning of a sweet dream.");
        add("extrabotany.page.nightmarefuel1", "The brain trembles.");

        add("extrabotany.entry.spiritfuel", "Spirit Fuel");
        add("extrabotany.page.spiritfuel0", "$(item)Spirit Fuel$(0) is an important material which could be obtained by two ways. The first way is to eat $(item)Nightmare Fuel$(0). The second is by Elven Trade through Alfheim Portal. Spirit Fuel can be refined into $(item)Spirit Fragment$(0) through Alfheim Portal.");
        add("extrabotany.page.spiritfuel1", "Spirit Fuel");
        add("extrabotany.page.spiritfuel2", "Spirit Fragment");

        add("extrabotany.entry.photonium", "Photonium");
        add("extrabotany.page.photonium0", "Photonium is the opposite side of Shadowium. It combines the power of Spirit Fuel and Elementium.");
        add("extrabotany.page.photonium1", "Three for one, Ten for three.");
        add("extrabotany.page.photonium2", "");
        add("extrabotany.page.photonium3", "It can be used to make $(item)Goblin Slayer Armor$(0), which powers player during day time, enabling him to regenerate more quickly.");
        add("extrabotany.page.photonium4", "Goblin Slayer Helmet");
        add("extrabotany.page.photonium5", "Goblin Slayer Chestplate");
        add("extrabotany.page.photonium6", "Goblin Slayer Leggings");
        add("extrabotany.page.photonium7", "Goblin Slayer Boots");

        add("extrabotany.entry.shadowium", "Shadowium");
        add("extrabotany.page.shadowium0", "With the magic of Gilded Mashed Potato, we can use Nightmare Fuel and a Elementium Ingot to make Shadowium. It's a very special kind of material and it performs better than Elementium.");
        add("extrabotany.page.shadowium1", "And Then There Were None.");
        add("extrabotany.page.shadowium2", "");
        add("extrabotany.page.shadowium3", "With the power of Shadowium, $(item)Shadow Warrior Armor$(0) will give wearer speed, strength, and night vision when the night comes. A sword made of it, $(item)Shadow Katana$(0), can allow you to teleport behind mobs.");
        add("extrabotany.page.shadowium4", "Silent Death.");
        add("extrabotany.page.shadowium5", "Shadow Warrior Helmet");
        add("extrabotany.page.shadowium6", "Shadow Warrior Chestplate");
        add("extrabotany.page.shadowium7", "Shadow Warrior Leggings");
        add("extrabotany.page.shadowium8", "Shadow Warrior Boots");

        add("extrabotany.entry.gildedpotato", "Gilded Potato");
        add("extrabotany.page.gildedpotato0", "The Gilded Potato may have other uses. It might be useful in the future.");
        add("extrabotany.page.gildedpotato1", "There is a miracle, something magical.");
        add("extrabotany.page.gildedpotato2", "A mashed potato made from gilded potato. It could be delicious.");
        add("extrabotany.page.gildedpotato3", "Golden Legendary");


        add("extrabotany.armorset.miku.desc", "Super Mana Affinity.");
        add("extrabotany.armorset.maid.desc0", "Super Empty-handed Power.");
        add("extrabotany.armorset.maid.desc1", "Greater Regeneration.");
        add("extrabotany.armorset.maid.desc2", "Mana Affinity.");
        add("extrabotany.armorset.shadowwarrior.desc0", "This night is so frightful and boundless. That my eyes come down with gloomy darkness.");
        add("extrabotany.armorset.shadowwarrior.desc1", "But just by them both. I am seeking my rosiness.");
        add("extrabotany.armorset.goblinslayer.desc0", "Praise the Sun.");
        add("extrabotany.armorset.goblinslayer.desc1", "May the sun enlighten you.");
        add("extrabotany.armorset.shootingguardian.desc0", "Faster Bow Drawing.");
        add("extrabotany.armorset.shootingguardian.desc1", "Grant Armor-Piercing and Life Stealing.");
        add("extrabotany.armorset.shootingguardian.desc2", "Faster Speed.");
        add("extrabotany.armorset.shootingguardian.desc3", "Greatly decrease natural life regeneration.");
        add("extrabotany.armorset.shadowwarrior.name", "Shadow Warrior");
        add("extrabotany.armorset.goblinslayer.name", "Goblin Slayer");
        add("extrabotany.armorset.miku.name", "Starry Idol");
        add("extrabotany.armorset.maid.name", "Pleiades Combat Maid");
        add("extrabotany.armorset.shootingguardian.name", "Shooting Guardian");

        add("extrabotany.entry.elementstone","Stone of Four Element");
        add("extrabotany.page.elementstone0","Element Stone made by corresponding element rune, it provides different amplifications. $(item);Aero Stone$(0); amplify movement, $(item);Earth Stone$(0); provides armor protection, $(item);Aqua Stone$(0); provides mana discount and $(item);Ignis Stone$(0); amplify damage.");
        add("extrabotany.page.elementstone1","Aero Stone");
        add("extrabotany.page.elementstone2","Earth Stone");
        add("extrabotany.page.elementstone3","Aqua Stone");
        add("extrabotany.page.elementstone4","Ignis Stone");

        add("extrabotany.entry.advancedmaterial", "Advanced Form");
    }
}