package chick.extrabotany.datagen.lang;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModEffects;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.libs.LibAdvancementNames;
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
        add(ModEffects.BLOOD_TEMPTATION.get(), "Blood Temptation");
        add(ModEffects.DIVINE_JUSTICE.get(), "Divine Justice");
        add(ModEffects.VEGETABLE.get(), "Green Hat");
        add(ModEffects.TIME_LOCK.get(), "Time Lock");
        add(ModEffects.REMEMBER.get(), "Remember");
        add(ModEffects.BALANCE.get(), "Balance");

        add(ModItems.NIGHTMARE_FUEL.get(), "Nightmare Fuel");
        add(ModItems.SPIRIT_FUEL.get(), "Spirit Fuel");
        add(ModItems.SPIRIT_FRAG.get(), "Spirit Fragment");
        add(ModItems.SHADOW_INGOT.get(), "Shadowium");
        add(ModItems.PHOTON_INGOT.get(), "Photonium");
        add(ModItems.GILDED_POTATO.get(), "Gilded Potato");
        add(ModItems.GILDED_MASHED_POTATO.get(), "Gilded Mashed Potato");

        add(ModItems.MANA_READER.get(), "Mana Reader");
        add(ModItems.ROD_OF_DISCORD.get(), "Rod of Discord");
        add(ModItems.THE_CHAOS.get(), "The Chaos");
        add(ModItems.THE_ORIGIN.get(), "The Origin");
        add(ModItems.THE_END.get(), "The End");
        add(ModItems.THE_UNIVERSE.get(), "The Universe");
        add(ModItems.FRIED_CHICKEN.get(), "Fried Chicken");
        add(ModBlocks.DIMENSION_CATALYST.get(), "Dimension Catalyst");
        add(ModItems.ORICHALCOS.get(), "Orichalcos Ingot");
        add(ModItems.TICKET.get(), "Invitation Letter to Yourself");
        add(ModItems.AERIALITE_INGOT.get(), "Aerialite");
        add(ModItems.GOLD_CLOTH.get(), "Das Rheingold");
        add(ModItems.LEN_SMELT.get(), "Mana Lens: Smelt");
        add(ModItems.LEN_SMELT.get().getDescriptionId() + ".short", "Smelt");
        add(ModItems.LEN_POTION.get(), "Mana Lens: Potion: %s");
        add(ModItems.LEN_POTION.get().getDescriptionId() + ".short", "Potion");
        add(ModItems.LEN_TRACE.get(), "Mana Lens: Trace");
        add(ModItems.LEN_TRACE.get().getDescriptionId() + ".short", "Trace");
        add(ModItems.LEN_MANA.get(), "Mana Lens: Mana");
        add(ModItems.LEN_MANA.get().getDescriptionId() + ".short", "Mana");
        add(ModItems.LEN_SUPERCONDUCTOR.get(), "Mana Lens: Superconductor");
        add(ModItems.LEN_SUPERCONDUCTOR.get().getDescriptionId() + ".short", "Superconductor");

        add(ModItems.TREASURE_BOX.get(), "Pandora's Box");
        add(ModItems.REWARD_BAG_A.get(), "Reward Bag Eins");
        add(ModItems.REWARD_BAG_B.get(), "Reward Bag Zwei");
        add(ModItems.REWARD_BAG_C.get(), "Reward Bag Drei");
        add(ModItems.REWARD_BAG_D.get(), "Reward Bag Vier");
        add(ModItems.UNIVERSAL_PETAL.get(), "Universal Petal");
        add(ModItems.ELEMENT_RUNE.get(), "Rune of Element");
        add(ModItems.SIN_RUNE.get(), "Rune of Sin");
        add(ModItems.GENESIS_CRYSTAL.get(), "Genesis Crystal");
        add(ModItems.PRIMO_GEM.get(), "Primogem");
        add(ModItems.ACQUAINT_FATE.get(), "Acquaint Fate");
        add(ModItems.INTERTWINED_FATE.get(), "Intertwined Fate");

        add(ModItems.FOX_EAR.get(), "Fox Ear");
        add(ModItems.FOX_MASK.get(), "Mask with Memory");
        add(ModItems.PYLON.get(), "Sims");
        add(ModItems.SUPER_CROWN.get(), "Super Crown");
        add(ModItems.THUG_LIFE.get(), "THUG LIFE");
        add(ModItems.BLACK_GLASSES.get(), "Black Glasses");
        add(ModItems.RED_SCARF.get(), "Red Scarf");
        add(ModItems.STONE_MASK.get(), "Stone Mask");

        add(ModItems.PEACE_AMULET.get(), "Peace Amulet");
        add(ModItems.DEATH_RING.get(), "Curse Ring");
        add(ModItems.FROST_RING.get(), "Frost Star");
        add(ModItems.POWER_GLOVE.get(), "Power Glove");
        add(ModItems.JINGWEI_FEATHER.get(), "Feather of Jingwei");
        add(ModItems.AERO_STONE.get(), "Aero Stone");
        add(ModItems.AQUA_STONE.get(), "Aqua Stone");
        add(ModItems.EARTH_STONE.get(), "Earth Stone");
        add(ModItems.IGNITE_STONE.get(), "Ignis Stone");
        add(ModItems.SUPREME_AERO_STONE.get(), "Supreme Aero Stone");
        add(ModItems.SUPREME_AQUA_STONE.get(), "Supreme Aqua Stone");
        add(ModItems.SUPREME_EARTH_STONE.get(), "Supreme Earth Stone");
        add(ModItems.SUPREME_IGNITE_STONE.get(), "Supreme Ignis Stone");
        add(ModItems.SAGES_MANA_RING.get(), "Sages Mana Ring");

        add(ModItems.MANASTEEL_SHIELD.get(), "Mana Steel Shield");
        add(ModItems.ELEMENT_SHIELD.get(), "Elementium Shield");
        add(ModItems.DIRT_SHIELD.get(), "Magic Dirt Shield");
        add(ModItems.FOREST_BOOK.get(), "Aranyaka");
        add(ModItems.SHADOW_KATANA.get(), "Shadow Katana");
        add(ModItems.LIVINGWOOD_CROSSBOW.get(), "Livingwood Crossbow");
        add(ModItems.LIVINGWOOD_SHORTBOW.get(), "Livingwood Shortbow");
        add(ModItems.TRUE_SHADOW_KATANA.get(), "True Shadow Katana");
        add(ModItems.TRUE_TERRA_BLADE.get(), "True Terra Blade");
        add(ModItems.TRUE_THUNSTAR_CALLER.get(), "True Thunstar Caller");
        add(ModItems.INFLUX_WAVER.get(), "Influx Waver");
        add(ModItems.FIRST_FRACTAL.get(), "First Fractal");
        add(ModItems.FAILNAUGHT.get(), "Failnaught");
        add(ModItems.EXCALIBER.get(), "Excaliber");

        add(ModItems.SHADOW_WARRIOR_HELM.get(), "Shadow Warrior Helmet");
        add(ModItems.SHADOW_WARRIOR_CHEST.get(), "Shadow Warrior Chestplate");
        add(ModItems.SHADOW_WARRIOR_LEGS.get(), "Shadow Warrior Leggings");
        add(ModItems.SHADOW_WARRIOR_BOOTS.get(), "Shadow Warrior Boots");
        add(ModItems.GOBLINSLAYER_HELM.get(), "Goblin Slayer Helmet");
        add(ModItems.GOBLINSLAYER_CHEST.get(), "Goblin Slayer Chestplate");
        add(ModItems.GOBLINSLAYER_LEGS.get(), "Goblin Slayer Leggings");
        add(ModItems.GOBLINSLAYER_BOOTS.get(), "Goblin Slayer Boots");
        add(ModItems.MIKU_HELM.get(), "Starry Idol Headgear");
        add(ModItems.MIKU_CHEST.get(), "Starry Idol Suit");
        add(ModItems.MIKU_LEGS.get(), "Starry Idol Skirt");
        add(ModItems.MIKU_BOOTS.get(), "Starry Idol Boots");
        add(ModItems.MAID_HELM.get(), "Pleiades Combat Maid Headgear");
        add(ModItems.MAID_CHEST.get(), "Pleiades Combat Maid Suit");
        add(ModItems.MAID_LEGS.get(), "Pleiades Combat Maid Skirt");
        add(ModItems.MAID_BOOTS.get(), "Pleiades Combat Maid Boots");

        add(ModItems.HERO_MEDAL.get(), "Hero Medal");
        add(ModItems.EMPTY_BOTTLE.get(), "Empty Mana Glass Bottle");
        add(ModItems.MANA_DRINK.get(), "Mana Cocktail");
        add(ModItems.COCK_TAIL.get(), "Special-made Cocktail of %s (%s)");
        add("item.extrabotany.infinitewine", "Infinite Wine of %s (%s)");
        add(ModItems.SPLASH_GRENADE.get(), "Holy Grenade of %s");
        add("extrabotany.brew.revolution", "Revolution");
        add("extrabotany.brew.allmighty", "All-mighty");
        add("extrabotany.brew.shell", "Shell");
        add("extrabotany.brew.deadpool", "Deadpool");
        add("extrabotany.brew.floating", "Floating");
        add("extrabotanymisc.inventoryUnfeasible", "You have something that isn't allowed in this boss fight. Check your inventory and tips in the lexicon again!");
        add("extrabotanymisc.unlegalPlayercount", "There are more people than there were when the boss is summoned. That's illegal!");
        add("extrabotanymisc.description", "You can not use it until you complete corresponding advancement <%s>.");
        otherLangs();
        langFlower();
        langAdvancement();
        langPantchouli();
        add(ModEntities.EGO, "EGO");
        add(ModEntities.EGO_LANDMINE, "EGO Landmine");
        add(ModEntities.EGO_MINION, "EGO Minion");
        add(ModEntities.AURAFIRE, "Aura Fire");
        add(ModEntities.SPLASH_GRENADE, "Splash Grenade");
        add(ModEntities.FALSE_LIGHTNING, "Simulated Lightning");
        add(ModEntities.TRUE_SHADOW_KATANA, "True Shadow Katana");
        add(ModEntities.TRUE_TERRA_BLADE, "True Terra Blade");
        add(ModEntities.INFLUX_WAVER, "Influx Waver");
        add(ModEntities.MAGIC_ARROW, "Magic Arrow");
        add(ModEntities.PHANTOM_SWORD, "Sword of Zenith");
    }

    private void langFlower()
    {
        add(ModSubtiles.sunshinelily, "Sunshine Lily");
        add(ModSubtiles.sunshinelilyFloating, "Floating Sunshine Lily");
        add(ModSubtiles.sunshinelily.getDescriptionId() + ".reference", "May the light heal and enlighten you");
        add(ModSubtiles.moonlightlily, "Moonlight Lily");
        add(ModSubtiles.moonlightlilyFloating, "Floating Moonlight Lily");
        add(ModSubtiles.moonlightlily.getDescriptionId() + ".reference", "May you find all you have lost");
        add(ModSubtiles.omniviolet, "Omniviolet");
        add(ModSubtiles.omnivioletFloating, "Floating Omniviolet");
        add(ModSubtiles.omniviolet.getDescriptionId() + ".reference", "Need not to know");
        add(ModSubtiles.geminiorchid, "Gemini Orchid");
        add(ModSubtiles.geminiorchidFloating, "Floating Gemini Orchid");
        add(ModSubtiles.geminiorchid.getDescriptionId() + ".reference", "Why is a raven like a writing desk?");
        add(ModSubtiles.bellflower, "Bell Flower");
        add(ModSubtiles.bellflowerFloating, "Floating Bell Flower");
        add(ModSubtiles.bellflower.getDescriptionId() + ".reference", "Lost wind");
        add(ModSubtiles.reikarorchid, "Reikar Orchid");
        add(ModSubtiles.reikarorchidFloating, "Floating Reikar Orchid");
        add(ModSubtiles.reikarorchid.getDescriptionId() + ".reference", "Game Crash");
        add(ModSubtiles.bloodyenchantress, "Bloody Enchantress");
        add(ModSubtiles.bloodyenchantressFloating, "Floating Bloody Enchantress");
        add(ModSubtiles.bloodyenchantress.getDescriptionId() + ".reference", "A bargain forged in blood and shadow");

        add(ModSubtiles.serenitian, "Serenitian");
        add(ModSubtiles.serenitianFloating, "Floating Serenitian");
        add(ModSubtiles.serenitian.getDescriptionId() + ".reference", "Torn to oblivion");
        add(ModSubtiles.annoying, "Annoying Flower");
        add(ModSubtiles.annoyingFloating, "Floating Annoying Flower");
        add(ModSubtiles.annoying.getDescriptionId() + ".reference", "Time to rest");
    }

    private void langAdvancement()
    {
        prefixAdvancement(LibAdvancementNames.ROOT, "Welcome to the World", "Don't have a good day, have a great day");
        prefixAdvancement(LibAdvancementNames.NIGHTMARE_FUEL_EAT, "Deep Dark Fantasy", "Eat a Nightmare Fuel (Unbelievable)");
        prefixAdvancement(LibAdvancementNames.THE_CHAOS_CRAFT, "Chaos With No Chaos", "Craft The Chaos");
        prefixAdvancement(LibAdvancementNames.EGO_DEFEAT, "KiLLER LADY", "Defeat Ego");

        prefixAdvancement(LibAdvancementNames.MANA_READER_CRAFT, "Satisfaction", "Obtain Mana Reader");
        prefixAdvancement(LibAdvancementNames.SPIRIT_FRAG, "PONPONPON", "Obtain Spirit Fragment");
        prefixAdvancement(LibAdvancementNames.SHADOW_WARRIOR, "Crazy ∞ nighT", "Equip Shadow Warrior Armor Set");
        prefixAdvancement(LibAdvancementNames.GOBLIN_SLAYER, "Befall", "Equip Goblin Slayer Armor Set");
        prefixAdvancement(LibAdvancementNames.MIKU, "Cat's Dance", "Equip Starry Idol Armor Set");
        prefixAdvancement(LibAdvancementNames.BLOODY_ENCHANTRESS_USE, "Sweet Devil", "Use a Bloody Enchantress to generate mana");


        prefixAdvancement(LibAdvancementNames.MAID, "Drug Of Gold", "Equip Pleiades Combat Maid Armor Set");
        prefixAdvancement(LibAdvancementNames.SHOOTING_GUARDIAN, "CONNECT", "Equip Shooting Guardian Armor Set");
        prefixAdvancement(LibAdvancementNames.ROD_OF_DISCORD_CRAFT, "NOT Discord!", "Craft Rod of Discord");


        prefixAdvancement(LibAdvancementNames.FAILNAUGHT_CRAFT, "from Y to Y", "Craft Failnaught");
        prefixAdvancement(LibAdvancementNames.EXCALIBER_CRAFT, "ReAct", "Craft First Fractal");
        prefixAdvancement(LibAdvancementNames.FIRST_FRACTAL_CRAFT, "Infinity +1 Sword", "Craft Excaliber");
        prefixAdvancement(LibAdvancementNames.THE_UNIVERSE_CRAFT, "Gears of Love", "Craft The Universe");
        prefixAdvancement(LibAdvancementNames.SAGES_MANA_RING_CRAFT, "COLOR", "Craft Sages Mana Ring");

        add("advancement.extrabotany:sunringcraft.title", "Promise");
        add("advancement.extrabotany:sunringcraft.desc", "Obtain Ring of Sacred Sun");
        add("advancement.extrabotany:moonpendantcraft.title", "Crystalline");
        add("advancement.extrabotany:moonpendantcraft.desc", "Obtain Heart of Corrupted Moon");
    }

    private void prefixAdvancement(String name, String title, String text)
    {
        add("advancement.extrabotany:" + name + ".title", title);
        add("advancement.extrabotany:" + name + ".desc", text);
    }

    private void langPantchouli()
    {
        add("extrabotany.category.extrabotany", "Extra Knowledge");
        add("extrabotany.entry.header", "Welcome to the World");
        add("extrabotany.page.header0", "Welcome to the World of the supreme principle of Mana! You are the one who has great potential and I believe you will have a shiny, bright future. I am sure you are hunger for more knowledge. Follow me, I will pass on my knowledge and experience to you.");
        add("extrabotany.page.header1", "The more progress you have done, the more knowledge you will have. I could give you one hint, you could obtain Spirit Fuel by eating Nightmare Fuel.");
        add("extrabotany.entry.nightmarefuel", "Nightmare Fuel");
        add("extrabotany.page.nightmarefuel0", "$(item)Nightmare Fuel$(0) is the aggregation of the nightmare, you will suffer after eating it, but it may be the beginning of a sweet dream. Elves may be interested in this. (Warning: instant damage III will cause 24 damage to player!)");
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
        add("extrabotany.armorset.mana.desc", "Totally %s less Mana cost on Mana Tools and Rods");
        add("extrabotany.armorset.magic_protection.desc", "Totally %s less Magic Damage received");
        add("extrabotany.armorset.miku.desc", "Super Mana Affinity.");
        add("extrabotany.armorset.maid.desc0", "Super Empty-handed Power.");
        add("extrabotany.armorset.maid.desc1", "Greater Regeneration.");
        add("extrabotany.armorset.maid.desc2", "Mana Affinity.");
        add("extrabotany.armorset.maid.desc3", "Clear Negative Effects.");
        add("extrabotany.armorset.shadowwarrior.desc0", "This night is so frightful and boundless. That my eyes come down with gloomy darkness.");
        add("extrabotany.armorset.shadowwarrior.desc1", "But just by them both. I am seeking my rosiness.");
        add("extrabotany.armorset.shadowwarrior.desc2", "More powerful during the night");
        add("extrabotany.armorset.goblinslayer.desc0", "Praise the Sun.");
        add("extrabotany.armorset.goblinslayer.desc1", "May the sun enlighten you.");
        add("extrabotany.armorset.goblinslayer.desc2", "More powerful during the day");
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
        add("extrabotany.entry.advancedmaterial", "Advanced Form");
        add("extrabotany.page.advancedmaterial0", "Combine two forms of material into one with the catalyst of $(item)Spirit Fragment$(0), those material has greater power and proficiency.");
        add("extrabotany.page.advancedmaterial1", "The Chaos");
        add("extrabotany.page.advancedmaterial2", "The Origin");
        add("extrabotany.page.advancedmaterial3", "The End");
        add("extrabotany.page.advancedmaterial4", "The Universe");
        add("extrabotany.entry.brew", "New types of brew");
        add("extrabotany.page.brew0", "Hold an Empty Mana Glass Bottle and right-click a Mana Pool with enough mana, and you will acquire a tasty drink.");
        add("extrabotany.page.brew1", "");
        add("extrabotany.page.brew2", "By crafting any flask potion with a Mana Cocktail , you can make a Special-made Cocktail that can be drinked two more times than the original flask and it has a longer effect.");
        add("extrabotany.page.brew3", "When a Special-made Cocktail is crafted with Popped Chorus Fruit. You make a Holy Grenade! The Holy Grenade will deal massive damage after exploding, giving bad effects to other creatures and giving the good effects to players. <br>A perfect item, but why is it a grenade?");
        add("extrabotany.page.brew4", "Greatly improve the efficiency of mining, but overdraft your luck.");
        add("extrabotany.page.brew5", "High level of resistance, but slow as turtle.");
        add("extrabotany.page.brew6", "Gain super strength, but what's the cost?");
        add("extrabotany.page.brew7", "Almost all positive effects! But wouldn't last long.");
        add("extrabotany.page.brew8", "One can make you floating, may be better if used on enemies?");
        add("extrabotany.entry.powerglove", "Power Glove");
        add("extrabotany.page.powerglove0", "$(item)Power Glove$(0) allows you to auto leftclick at maximum attack speed if you keep pressing your mouse. It also slightly increase your attack speed.");
        add("extrabotany.page.powerglove1", "");
        add("extrabotany.entry.rodofdiscord", "Rod of Discord");
        add("extrabotany.page.rodofdiscord0", "$(item)Rod of Discord$(0) could teleport you to where you are aiming at with nausea effect. If you try to use it when it is cooling down, you will receive damage.");
        add("extrabotany.page.rodofdiscord1", "");
        add("extrabotany.entry.jingweifeather", "Feather of Jingwei");
        add("extrabotany.page.jingweifeather0", "Equipped with $(item)Feather of Jingwei$(0), every empty-handed punch will shoot a fire ball which could recover you 1 absorption and stack to 10 at max.");
        add("extrabotany.page.jingweifeather1", "");
        add("extrabotany.entry.frostring", "Frost Star");
        add("extrabotany.page.frostring0", "The $(item)Frost Star$(0) freezes large areas of water and lava. It also applies slowness creatures looked at by the wearer.");
        add("extrabotany.page.frostring1", "I am the frozen heart of the Scourge!");
        add("extrabotany.entry.sages_mana_ring", "Sages Mana Ring");
        add("extrabotany.page.sages_mana_ring0", "The $(item)Sages Mana Ring$(0) can store nearly a infinite amount of $(thing)Mana$(0).");
        add("extrabotany.page.sages_mana_ring1", "The Cake is a Lie.");
        add("extrabotany.foxmaskinfo0", "When shall we meet again after this parting? For life is like the morning dew.");
        add("extrabotany.foxmaskinfo1", "Now that Hanachirusato has been separated from this mask,");
        add("extrabotany.foxmaskinfo2", "Paimon doesn't get to call her the \"Masked Maiden\" anymore, right?");
        add("extrabotany.entry.lens", "Brand-new Mana Lens");
        add("extrabotany.page.lens0", "$(item)Mana Lens: Mana$(0) is like a Mana Pool, it will infuse the item with mana. If you place a Alchemy Catalyst or a Conjuration Catalyst under the spreader, it will do the cocorresponding work.");
        add("extrabotany.page.lens1", "Mana");
        add("extrabotany.page.lens2", "$(item)Mana Lens: Push$(0) will enable Mana Burst to force the entities it collide to move with it.");
        add("extrabotany.page.lens3", "Push");
        add("extrabotany.page.lens4", "$(item)Mana Lens: Trace$(0) will enable Mana Burst to locate the first entity within 3 meters and keep tracking until either is dead.");
        add("extrabotany.page.lens5", "Trace");
        add("extrabotany.page.lens6", "$(item)Mana Lens: Smelt$(0) is like a furnace, it will enable Mana Burst to smelt the block ti collide.");
        add("extrabotany.page.lens7", "Smelt");
        add("extrabotany.page.lens8", "$(item)Mana Lens: Potion$(0) will add potion effects to the first entity collided. Craft the lens with the brew flask to decide its effect.");
        add("extrabotany.page.lens9", "Potion");
        add("extrabotany.page.lens10", "$(item)Mana Lens: Superconductor(0) will increase the mana num, mana loss and speed of the Mana Burst. It will do damage to players and other creature. Note that$(thing) Superconductor will not decrease target's defense.$(0)");

        add("extrabotany.entry.dimension_catalyst", "Dimension Catalyst");
        add("extrabotany.page.dimension_catalyst0", "$(item)Dimension Catalyst$(0) connects the Overworld with other two dimensions. You can use $(thing)Mana$(0) to transform resources from overworld to those from other dimensions.");
        add("extrabotany.page.dimension_catalyst1", "");
        add("extrabotany.entry.peace_amulet", "Peace Amulet");
        add("extrabotany.page.peace_amulet0", "$(item)Peace Amulet$(0) could guarantee that all weapons in extrabotany will no longer deal damage to other players and friendly mobs.");
        add("extrabotany.page.peace_amulet1", "");
        add("extrabotany.entry.ego", "Ego");
        add("extrabotany.page.ego0", "200,000 Natural breath from a Nature Orb or an Invitation Letter can be used to summon an even more powerful Gaia Guardian. Its great powers and new abilties should be feared. New rules should be obeyed. Equipped armor and weapons have to be from Vanilla Minecraft or Botania or extrabotany(except if its changed in the config);. Any others will be disarmed and dropped on the ground.");
        add("extrabotany.page.ego1", "Challengers can not outnumber the number when it is summoned, or Ego will vanish. Ego is proficient in every type of weapon. In Stage One, Ego will use $(item)True Terra Blade$(0) or $(item)True Shadow Katana$(0). When entering Stage Two, Ego will be invulnerable for a period of time and meanwhile keep summoning landmines. Different colors means different effects.");
        add("extrabotany.page.ego2", "Battle Field");
        add("extrabotany.page.ego3", "Invitation Letter");
        add("extrabotany.page.ego4", "Ego could use $(item)Influx Waver$(0) and $(item)Star Wrath$(0) in Stage Two. After its health reaches a threshold, Stage Three begins. Ego starts to be invulnerable again, meanwhile it keeps recovering and summons a wave of minions. Four minions use four different weapons. Defeating all minions in advance could terminate Ego's invulnerability.");
        add("extrabotany.page.ego5", "Ego only use $(item)First Fractal$(0) in Stage Three, and all its abilities maximize. Challengers could be awarded with $(item)Pandora's Box$(0) after defeating Ego, with a $(item)Medal for Heroism$(0) inside it.");
        add("extrabotany.page.ego6", "Hallowed Dogma");
        add("extrabotany.entry.gold_cloth", "Das Rheingold");
        add("extrabotany.page.gold_cloth0", "$(item)Das Rheingold$(0) can empty the binding of a relic, just crafting the relic with it.");
        add("extrabotany.page.gold_cloth1", "");
        add("extrabotany.page.gold_cloth2", "An improved version of Terrasteel Armor. The wearer will gain the ability of regeneration while wearing a full set of it. It will also clear negative effects and give the wearer super powers when empty-handed.");
        add("extrabotany.page.gold_cloth3", "Pleiades Combat Maid Headgear");
        add("extrabotany.page.gold_cloth4", "Pleiades Combat Maid Suit");
        add("extrabotany.page.gold_cloth5", "Pleiades Combat Maid Skirt");
        add("extrabotany.page.gold_cloth6", "Pleiades Combat Maid Boots");
        add("extrabotany.entry.miku", "Starry Idol Armor Set");
        add("extrabotany.page.miku0", "An improved version of Manasteel Armor,it gives the wearer a high mana discount.");
        add("extrabotany.page.miku1", "Starry Idol Headgear");
        add("extrabotany.page.miku2", "Starry Idol Suit");
        add("extrabotany.page.miku3", "Starry Idol Skirt");
        add("extrabotany.page.miku4", "Starry Idol Boots");
        add("extrabotany.entry.aerialite", "Aerialite");
        add("extrabotany.page.aerialite0", "Infuse the power of the sky and mana into $(item)Dragonstone$(0), and then it forms into $(item)Aerialite$(0), a magical alloy. Its property is as great as $(item)Terrasteel$(0).");
        add("extrabotany.page.aerialite1", "");
        add("extrabotany.page.aerialite2", "Armor made by Aerialite provides considerable protection. With a set of armors, player grants higher motility, greater attack and life stealing ability. Also the speed of drawing bows is boosted. However, it greatly nerfs player's regeneration ability.");
        add("extrabotany.page.aerialite3", "Shooting Guardian Helmet");
        add("extrabotany.page.aerialite4", "Shooting Guardian Chestplate");
        add("extrabotany.page.aerialite5", "Shooting Guardian Leggings");
        add("extrabotany.page.aerialite6", "Shooting Guardian Boots");
        add("extrabotany.entry.life_essence", "Gaia Spirit Materials");
        add("extrabotany.page.life_essence0", "Mixing $(item)Gaia Spirit(0) in some materials can make them indistinguishable from each other. For example, $(item)Universal Petal$(0) can directly replace other petals in crafting. $(item)Rune of Element(0) and $(item)Rune of Sin(0) can be transformed into other runes. However, this sometimes leads to crafting conflicts.");
        add("extrabotany.page.life_essence1", "One For All");
        add("extrabotany.page.life_essence2", "When crafts with any one of the 8 kinds of runes required for crafting itself, you will obtain two base runes.");
        add("extrabotany.entry.genesis_crystal", "Genesis Crystal");
        add("extrabotany.page.genesis_crystal0", "You can obtain some useful items from unknown space with the help of a large number of EGO drops.");
        add("extrabotany.page.genesis_crystal1", "Craft Primo Gem.");
        add("extrabotany.page.genesis_crystal2", "Craft Intertwined Fate.");
        add("extrabotany.page.genesis_crystal3", "Craft Acquaint Fate.");
        add("extrabotany.page.genesis_crystal4", "Wish Pool");
        add("extrabotany.page.genesis_crystal5", "Right click the $(item)Dimension Catalyst$(0) with $(item)Intertwined Fate$(0) or $(item)Acquaint Fate$(0) to start wish!");

        add("extrabotany.entry.livingwood_crossbow", "Livingwood Crossbow");
        add("extrabotany.page.livingwood_crossbow0", "Similar to how Livingwood Bow was created, $(item)Livingwood$(0), $(item)Mana Steel$(0) and $(item)Mana String$(0) can be used to craft sturdier %(item)Livingwood Crossbow$(0). It will also repair itself using $(thing)Mana$(0).");
        add("extrabotany.page.livingwood_crossbow1", "Does it shoot airplanes?");
        add("extrabotany.entry.true_swords", "True Swords");
        add("extrabotany.page.true_swords0", "$(item)Gaia Spirit$(0) and other materials can unleash the true power of some of the weapons you have. These weapons will also be more powerful in Gaia's hands.");
        add("extrabotany.page.true_thunstar_caller", "An upgrade to $(item)Thunder Caller$(0) and $(item)Star Caller$(0). It summons lightning from sky. However, it is not true lightning. A burning enemy will increase the damage due to Overload.");
        add("extrabotany.page.true_terra_blade", "An upgrade to $(item)Terra Blade$(0). Its projectile will pierce through enemies.");
        add("extrabotany.page.true_shadow_katana", "An upgrade to $(item)Shadow Katana$(0). It could summon two projection that automatically trace enemies.");
        add("extrabotany.page.influx_waver", "Its projectile will constantly attack the first entity it contacts..");
        add("extrabotany.entry.failnaught", "Failnaught");
        add("extrabotany.page.failnaught0", "The $(item)Failnaught$(0) uses $(thing)Mana$(0) to shoot magic arrows which pierce through enemies and deal damage to the creatures in the area. The longer you draw the bow, the more damage it will deal.");
        add("extrabotany.page.failnaught1", "A hundred shots, a hundred bullseyes.");
        add("extrabotany.entry.buddhistrelics", "Origin Creation - Omniscience");
        add("extrabotany.page.buddhistrelics0", "$(item)Origin Creation - The Omniscience$(0) relic can simulate some of the Relics by pressing ctrl to switch its form. Keeping other forms will cost $(thing)Mana$(0). With insufficient mana it will change to its original form.");
        add("extrabotany.entry.excaliber", "Excaliber");
        add("extrabotany.page.excaliber0", "$(item)Excaliber$(0) could shoot golden mana beam that will automatically trace enemies in a large area.");
        add("extrabotany.page.excaliber1", "");
        add("extrabotany.entry.manasteel_shield", "Mana Shield");
        add("extrabotany.page.manasteel_shield0", "Wooden Shield is too easy to damage. Using $(item)Mana Steel$(0) to replace $(item)Iron$(0) may be better.");
        add("extrabotany.page.manasteel_shield1", "Circling the square.");
        add("extrabotany.page.manasteel_shield2", "Adding $(item)Dirt(0) to the crafting materials will make the shield more compatible with the ground. Right-clicking at the ground will quickly create a wall of $(item)Dirt$(0). However, this makes the shield less durable and lose its original defensive ability.");
        add("extrabotany.page.manasteel_shield3", "Is it strong enough?");
        add("extrabotany.entry.forest_book", "Aranyaka");
        add("extrabotany.page.forest_book0", "With $(item)Golden Apple$(0) and the power of Nature, you can craft $(item)Aranyaka$(0). Right click will cost 8 $(thing)HP$(0) to let you gain 5 $(thing)Golden Hearts$(0) and Effect $(thing)Remember$(0) for 5 seconds. After 5 seconds, every $(thing)Golden Heart$(0) will be converted into 1.6 $(thing)hp$(0), capped at 5 hearts.");
        add("extrabotany.page.forest_book1", "\"Hello,\" \"Thank You,\" and the Final \"Goodbye\".");
        add("extrabotany.entry.puredaisy_pendant", "Pure Daisy Pendant");
        add("extrabotany.page.puredaisy_pendant0", "By holding a $(item)Pure Daisy Pendant$(0) and right-clicking a block, you can instantly transform blocks like a $(item)Pure Daisy$(0), at the cost of a little mana. The more you use it, the more it cools down, even if you find some methods to repair it.");
        add("extrabotany.page.puredaisy_pendant1", "My beauty is in position.");
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
        add("extrabotany.entry.omni_violet", "Omniviolet");
        add("extrabotany.page.omni_violet0", "According to a saying, knowledge is power. The $(item)Omniviolet$(0) converts Books or Written Books into $(thing)Mana$(0). To increase the amount of mana generated, Bookshelves(or something else with enchanting power) can be placed around the Omniviolet in a similar fashion to an Enchantment Table. Too close or too far will make bookshelves useless. Mana output can be increased 300% than before.");
        add("extrabotany.page.omni_violet1", "Akashic Records.");
        add("extrabotany.entry.gemini_orchid", "Gemini Orchid");
        add("extrabotany.page.gemini_orchid0", "A $(item)Gemini Orchid$(0) is like a thermoelectric generator. If you place a block of lava in one of eight blocks nearby, and then place a block of water in another position, it will begin to produce mana. The higher the gap between two blocks' temperatures, the quicker mana will be generated. Since it is a passive flower, it will decay after working for 3 days in minecraft..");
        add("extrabotany.page.gemini_orchid1", "I am power incarnate!");
        add("extrabotany.entry.bell_flower", "Bell Flower");
        add("extrabotany.page.bell_flower0", "When placed in a high and open place, the $(item)Bell Flower$(0) creates $(thing)Mana$(0) from wind. The higher it is, the more mana it produces, which is capped at y=319. Blocks and other $(item)Bell Flower$(0) will decrease the mana output. A Livingrock Pedestal nearby with a Nature Orb in it can increase its mana generation efficiency. Please note that it will decay after working for 3 days in minecraft.");
        add("extrabotany.page.bell_flower1", "Only the storms can knock down the trees.");
        add("extrabotany.entry.reikarorchid", "Reikar Lily");
        add("extrabotany.page.reikarorchid0", "The $(item)Reikar Lily$(0) turns the tremendous energy of lightning into $(thing)Mana$(0). When a lightning bolt hits nearby, it will generate a great amount of $(thing)Mana$(0) immediately. $(item)Reikar Lily$(0) itself could attract thunder. To absorb the lightning, the flower must not have any $(thing)Mana$(0) stored in it-- otherwise, it will explode.");
        add("extrabotany.page.reikarorchid1", "I Choose You!");
        add("extrabotany.entry.bloodyenchantress", "Bloody Enchantress");
        add("extrabotany.page.bloodyenchantress0", "The $(item)Bloody Enchantress$(0) creates $(thing)Mana$(0) by consuming nearby creatures' life. Creatures sacrificed to the Bloody Enchantress will not produce any drops or experience.");
        add("extrabotany.page.bloodyenchantress1", "Every bite will add a effect called Blood Temptation and overlay with higher amplifier one, the mana production will decrease according to the level of Blood Temptation, and the flower will stop working if the total level of Blood Temptation of all the creatures nearby is over a threshold.");
        add("extrabotany.page.bloodyenchantress2", "The only cure is death.");

        add("extrabotany.entry.serenitian", "Serenitian");
        add("extrabotany.page.serenitian0", "$(item)Serenitian$(0) could prevent passive flowers nearby at the same floor from fading away, and set the fading progress to zero.");
        add("extrabotany.page.serenitian1", "All shall fade away");
        add("extrabotany.entry.annoying_flower", "Annoying Flower");
        add("extrabotany.page.annoying_flower0", "The Annoying Flower can fish in nearby Petal Apothecary filled with water. For that process it will use mana. Feeding Fried Chicken Legs to it will significantly increase the efficiency and the chance of getting treasure.");
        add("extrabotany.page.annoying_flower1", "RK800.");
        add("extrabotany.page.annoying_flower2", "Cooked Chicken can be fried by soaking it in $(thing)Mana$(0). Fried Chicken Legs restore more hunger and give saturation.");
        add("extrabotany.page.annoying_flower3", "Neither an angel or a devil.");

    }

    private void otherLangs()
    {
        add("extrabotany.wish.is_active", "Someone is using this!!");
    }
}