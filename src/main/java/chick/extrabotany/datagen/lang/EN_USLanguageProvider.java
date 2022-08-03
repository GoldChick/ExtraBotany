package chick.extrabotany.datagen.lang;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class EN_USLanguageProvider extends LanguageProvider
{

    public EN_USLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, ExtraBotany.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup." + ExtraBotany.TAB_NAME, "Extra Botany");

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

        add(ModItems.MANA_READER.get(), "Mana Reader");
        add(ModItems.THE_CHAOS.get(), "The Chaos");
        add(ModItems.THE_ORIGIN.get(), "The Origin");
        add(ModItems.THE_END.get(), "The End");
        add(ModItems.THE_UNIVERSE.get(), "The Universe");

        add(ModItems.DEATH_RING.get(), "Curse Ring");
        add(ModItems.AERO_STONE.get(), "Aero Stone");
        add(ModItems.AQUA_STONE.get(), "Aqua Stone");
        add(ModItems.EARTH_STONE.get(), "Earth Stone");
        add(ModItems.IGNIS_STONE.get(), "Ignis Stone");
        add(ModItems.SUPREME_AERO_STONE.get(), "Supreme Aero Stone");
        add(ModItems.SUPREME_AQUA_STONE.get(), "Supreme Aqua Stone");
        add(ModItems.SUPREME_EARTH_STONE.get(), "Supreme Earth Stone");
        add(ModItems.SUPREME_IGNIS_STONE.get(), "Supreme Ignis Stone");


        add(ModItems.SHADOW_KATANA.get(), "Shadow Katana");
        add(ModItems.SHADOW_WARRIOR_HELM.get(), "Shadow Warrior Helmet");
        add(ModItems.SHADOW_WARRIOR_CHEST.get(), "Shadow Warrior Chestplate");
        add(ModItems.SHADOW_WARRIOR_LEGS.get(), "Shadow Warrior Leggings");
        add(ModItems.SHADOW_WARRIOR_BOOTS.get(), "Shadow Warrior Boots");

        add(ModItems.GOBLINSLAYER_HELM.get(), "Goblin Slayer Helmet");
        add(ModItems.GOBLINSLAYER_CHEST.get(), "Goblin Slayer Chestplate");
        add(ModItems.GOBLINSLAYER_LEGS.get(), "Goblin Slayer Leggings");
        add(ModItems.GOBLINSLAYER_BOOTS.get(), "Goblin Slayer Boots");

        add(ModItems.EMPTY_BOTTLE.get(), "Empty Mana Glass Bottle");
        add(ModItems.MANA_DRINK.get(), "Mana Cocktail");
        add("extrabotany.brew.revolution","Revolution");
        add("extrabotany.brew.allmighty","All-mighty");
        add("extrabotany.brew.shell","Shell");
        add("extrabotany.brew.deadpool","Deadpool");
        add("extrabotany.brew.floating","Floating");

        langFlower();
        langAdvancement();
        langPantchouli();
    }

    private void langFlower()
    {
        add(ModSubtiles.sunshinelily, "Sunshine Lily");
        add(ModSubtiles.sunshinelilyFloating, "Floating Sunshine Lily");
        add(ModSubtiles.sunshinelily.getDescriptionId() + ".reference", "May the light heal and enlighten you");
        add(ModSubtiles.moonlightlily, "Moonlight Lily");
        add(ModSubtiles.moonlightlilyFloating, "Floating Moonlight Lily");
        add(ModSubtiles.moonlightlily.getDescriptionId() + ".reference", "May you find all you have lost");
        add(ModSubtiles.omniviolet,"Omniviolet");
        add(ModSubtiles.omnivioletFloating,"Floating Omniviolet");
        add(ModSubtiles.omniviolet.getDescriptionId()+".reference","Need not to know");
    }
    private void langAdvancement()
    {
        add("advancement.extrabotany:root.title","Welcome to the World");
        add("advancement.extrabotany:root.desc","Don't have a good day, have a great day");
        add("advancement.extrabotany:nightmarefuel_eat.title","Deep Dark Fantasy");
        add("advancement.extrabotany:nightmarefuel_eat.desc","Eat a Nightmare Fuel (Unbelievable)");
        add("advancement.extrabotany:manareader_craft.title","Satisfaction");
        add("advancement.extrabotany:manareader_craft.desc","Obtain Mana Reader");
        add("advancement.extrabotany:thechaos_craft.title","Chaos With No Chaos");
        add("advancement.extrabotany:thechaos_craft.desc","Craft The Chaos");

    }
    private void langPantchouli()
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

        add("extrabotany.entry.manareader", "Mana Reader");
        add("extrabotany.page.manareader0", "By right clicking a Mana Pool with a Mana Reader in your main-hand, you can check out how much mana it has. Not very accurate for small figures.");
        add("extrabotany.page.manareader1", "Lemme check it!");

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

        add("extrabotany.entry.elementstone", "Stones of Four Element");
        add("extrabotany.page.elementstone0", "Element Stone made by corresponding element rune, it provides different amplifications. $(item)Aero Stone$(0) amplify movement, $(item)Earth Stone$(0) provides armor protection, $(item)Aqua Stone$(0) provides mana discount and $(item)Ignis Stone$(0) amplify damage.");
        add("extrabotany.page.elementstone1", "Aero Stone");
        add("extrabotany.page.elementstone2", "Earth Stone");
        add("extrabotany.page.elementstone3", "Aqua Stone");
        add("extrabotany.page.elementstone4", "Ignis Stone");

        add("extrabotany.entry.supreme_elementstone", "Supreme Stones");
        add("extrabotany.page.supreme_elementstone0", "Element Stones are so powerful. But there is one way to get even stronger power from element. However, this results in some disadvantages. And this also exacerbates the conflict between the different elements. For example, $(item)Aero Stone$(0) and $(item)Earth Stone$(0) can not work together if one of them is $(item)Supreme$(0) version. So will $(item)Aqua Stone$(0) and $(item)Ignis Stone$(0) do.");
        add("extrabotany.page.supreme_elementstone1", "Aero Stone");
        add("extrabotany.page.supreme_elementstone2", "Earth Stone");
        add("extrabotany.page.supreme_elementstone3", "Aqua Stone");
        add("extrabotany.page.supreme_elementstone4", "Ignis Stone");

        add("extrabotany.entry.deathring", "Death Ring");
        add("extrabotany.page.deathring0", "The $(item)Death Ring$(0) applies the Wither and Unluck effect on creatures near the wearer at the cost of some $(thing)Mana$(0).");
        add("extrabotany.page.deathring1", "Do not fear power... fear those who wield it!");

        add("extrabotany.entry.advancedmaterial","Advanced Form");
        add("extrabotany.page.advancedmaterial0","Combine two forms of material into one with the catalyst of $(item)Spirit Fragment$(0), those material has greater power and proficiency.");
        add("extrabotany.page.advancedmaterial1","The Chaos");
        add("extrabotany.page.advancedmaterial2","The Origin");
        add("extrabotany.page.advancedmaterial3","The End");
        add("extrabotany.page.advancedmaterial4","The Universe");

        add("extrabotany.entry.brew","New types of brew");
        add("extrabotany.page.brew0","Hold an Empty Mana Glass Bottle and right-click a Mana Pool with enough mana, and you will acquire a tasty drink.");
        add("extrabotany.page.brew1","");
        add("extrabotany.page.brew2","By crafting any flask potion with a Mana Cocktail , you can make a Special-made Cocktail that can be drinked two more times than the original flask and it has a longer effect.");
        add("extrabotany.page.brew3","When a Special-made Cocktail is crafted with Popped Chorus Fruit. You make a Holy Grenade! The Holy Grenade will deal massive damage after exploding, giving bad effects to other creatures and giving the good effects to players. <br>A perfect item, but why is it a grenade?");
        add("extrabotany.page.brew4","Greatly improve the efficiency of mining, but overdraft your luck.");
        add("extrabotany.page.brew5","High level of resistance, but slow as turtle.");
        add("extrabotany.page.brew6","Gain super strength, but what's the cost?");
        add("extrabotany.page.brew7","Almost all positive effects! But wouldn't last long.");
        add("extrabotany.page.brew8","One can make you floating, may be better if used on enemies?");


        pantchouliFlower();
    }
    private void pantchouliFlower()
    {
        add("extrabotany.entry.sunshine_lily", "Sunshine Lily");
        add("extrabotany.page.sunshine_lily0", "Sunshine Lily produces $(thing)Mana$(0) from sunlight, but it is not very efficient. Please note that it will decay after working for 3 days in minecraft.");
        add("extrabotany.page.sunshine_lily1", "Heal and heat.");
        add("extrabotany.entry.moonlight_lily", "Moonlight Lily");
        add("extrabotany.page.moonlight_lily0", "Moonlight Lily generates $(thing)Mana$(0) at night, but it is not very efficient. Please note that it will decay after working for 3 days in minecraft.");
        add("extrabotany.page.moonlight_lily1", "Sowing of the fool.");
        add("extrabotany.entry.omniviolet","Omniviolet");
        add("extrabotany.page.omniviolet0","According to a saying, knowledge is power. The $(item)Omniviolet$(0) converts Books or Written Books into $(thing)Mana$(0). To increase the amount of mana generated, Bookshelves can be placed around the Omniviolet in a similar fashion to an Enchantment Table. Mana output can be increased 300% than before.");
        add("extrabotany.page.omniviolet1","Akashic Records.");
    }
}