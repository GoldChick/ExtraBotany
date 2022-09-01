package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.common.advancements.*;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.mixin.AccessorAdvancementProvider;

import java.util.List;
import java.util.function.Consumer;


public class ModAdvancementProvider extends AdvancementProvider
{

    public ModAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn)
    {
        super(generatorIn, fileHelperIn);
    }

    @Override
    public void run(HashCache hashCache)
    {
        ((AccessorAdvancementProvider) this).setTabs(getAdvancements());
        super.run(hashCache);
    }

    protected List<Consumer<Consumer<Advancement>>> getAdvancements()
    {
        return List.of(ModAdvancementProvider::mainAdvancements, ModAdvancementProvider::challengeAdvancements);
    }

    private static void mainAdvancements(Consumer<Advancement> consumer)
    {
        var elvenLexiconUnlock = new CompoundTag();
        elvenLexiconUnlock.putBoolean(ItemLexicon.TAG_ELVEN_UNLOCK, true);
        InventoryChangeTrigger.TriggerInstance elvenLexicon = InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(vazkii.botania.common.item.ModItems.lexicon).hasNbt(elvenLexiconUnlock).build()
        );


        // Main progression line
        Advancement root = Advancement.Builder.advancement()
                .display(rootDisplay(ModItems.PYLON.get(), "advancement.extrabotany:root.title",
                        "advancement.extrabotany:root.desc", prefix("textures/block/dimension_catalyst.png")))
                .addCriterion("nightmare", onPickup(ModItems.NIGHTMARE_FUEL.get()))
                .save(consumer, mainId(LibAdvancementNames.ROOT));

        Advancement nightmarefuel_eat = Advancement.Builder.advancement()
                .display(simple(ModItems.NIGHTMARE_FUEL.get(), LibAdvancementNames.NIGHTMARE_FUEL_EAT, FrameType.TASK))
                .parent(root)
                .addCriterion("nightmare", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.NIGHTMARE_FUEL.get()))
                .save(consumer, mainId(LibAdvancementNames.NIGHTMARE_FUEL_EAT));

        Advancement thechaos_craft = Advancement.Builder.advancement()
                .display(simple(ModItems.THE_CHAOS.get(), LibAdvancementNames.THE_CHAOS_CRAFT, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("pickup", onPickup(ModItems.THE_CHAOS.get()))
                .save(consumer, mainId(LibAdvancementNames.THE_CHAOS_CRAFT));

        Advancement ego_defeat = Advancement.Builder.advancement()
                .display(simple(ModItems.HERO_MEDAL.get(), LibAdvancementNames.EGO_DEFEAT, FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("ego", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntities.EGO)))
                .save(consumer, mainId(LibAdvancementNames.EGO_DEFEAT));

/*
        Advancement runePickup = Advancement.Builder.advancement()
                .display(simple(ModBlocks.runeAltar, "runePickup", FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("rune", onPickup(ModTags.Items.RUNES))
                .save(consumer, mainId("rune_pickup"));

        Advancement terrasteelPickup = Advancement.Builder.advancement()
                .display(simple(vazkii.botania.common.item.ModItems.terrasteel, "terrasteelPickup", FrameType.TASK))
                .parent(runePickup)
                .addCriterion("terrasteel", onPickup(vazkii.botania.common.item.ModItems.terrasteel))
                .save(consumer, mainId("terrasteel_pickup"));

        Advancement elfPortalOpen = Advancement.Builder.advancement()
                .display(simple(ModBlocks.alfPortal, "elfPortalOpen", FrameType.TASK))
                .parent(terrasteelPickup)
                .addCriterion("portal", new AlfPortalTrigger.Instance(EntityPredicate.Composite.ANY, ItemPredicate.ANY, LocationPredicate.ANY))
                .save(consumer, mainId("elf_portal_open"));


*/
        //parent: nightmarefuel
        Advancement.Builder.advancement()
                .display(simple(ModItems.MANA_READER.get(), LibAdvancementNames.MANA_READER_CRAFT, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("manareader", onPickup(ModItems.MANA_READER.get()))
                .save(consumer, mainId(LibAdvancementNames.MANA_READER_CRAFT));
        Advancement.Builder.advancement()
                .display(simple(ModItems.SPIRIT_FRAG.get(), LibAdvancementNames.SPIRIT_FRAG, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("spirit", onPickup(ModItems.SPIRIT_FRAG.get()))
                .save(consumer, mainId(LibAdvancementNames.SPIRIT_FRAG));
        Advancement.Builder.advancement()
                .display(simple(ModItems.SHADOW_WARRIOR_CHEST.get(), LibAdvancementNames.SHADOW_WARRIOR, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("shadow_warrior1", onPickup(ModItems.SHADOW_WARRIOR_HELM.get()))
                .addCriterion("shadow_warrior2", onPickup(ModItems.SHADOW_WARRIOR_CHEST.get()))
                .addCriterion("shadow_warrior3", onPickup(ModItems.SHADOW_WARRIOR_LEGS.get()))
                .addCriterion("shadow_warrior4", onPickup(ModItems.SHADOW_WARRIOR_BOOTS.get()))
                .save(consumer, mainId(LibAdvancementNames.SHADOW_WARRIOR));
        Advancement.Builder.advancement()
                .display(simple(ModItems.GOBLINSLAYER_CHEST.get(), LibAdvancementNames.GOBLIN_SLAYER, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("goblin_slayer1", onPickup(ModItems.GOBLINSLAYER_HELM.get()))
                .addCriterion("goblin_slayer2", onPickup(ModItems.GOBLINSLAYER_CHEST.get()))
                .addCriterion("goblin_slayer3", onPickup(ModItems.GOBLINSLAYER_LEGS.get()))
                .addCriterion("goblin_slayer4", onPickup(ModItems.GOBLINSLAYER_BOOTS.get()))
                .save(consumer, mainId(LibAdvancementNames.GOBLIN_SLAYER));
         Advancement.Builder.advancement()
                .display(simple(ModItems.MIKU_CHEST.get(), LibAdvancementNames.MIKU, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("miku1", onPickup(ModItems.MIKU_HELM.get()))
                .addCriterion("miku2", onPickup(ModItems.MIKU_CHEST.get()))
                .addCriterion("miku3", onPickup(ModItems.MIKU_LEGS.get()))
                .addCriterion("miku4", onPickup(ModItems.MIKU_BOOTS.get()))
                .save(consumer, mainId(LibAdvancementNames.MIKU));
         Advancement.Builder.advancement()
                .display(simple(ModItems.MAID_CHEST.get(), LibAdvancementNames.MAID, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("maid1", onPickup(ModItems.MAID_HELM.get()))
                .addCriterion("maid2", onPickup(ModItems.MAID_CHEST.get()))
                .addCriterion("maid3", onPickup(ModItems.MAID_LEGS.get()))
                .addCriterion("maid4", onPickup(ModItems.MAID_BOOTS.get()))
                .save(consumer, mainId(LibAdvancementNames.MAID));
         Advancement.Builder.advancement()
                .display(simple(ModItems.GOBLINSLAYER_CHEST.get(), LibAdvancementNames.SHOOTING_GUARDIAN, FrameType.TASK))
                .parent(nightmarefuel_eat)
                .addCriterion("shootingguardian1", onPickup(ModItems.GOBLINSLAYER_HELM.get()))
                .addCriterion("shootingguardian2", onPickup(ModItems.GOBLINSLAYER_CHEST.get()))
                .addCriterion("shootingguardian3", onPickup(ModItems.GOBLINSLAYER_LEGS.get()))
                .addCriterion("shootingguardian4", onPickup(ModItems.GOBLINSLAYER_BOOTS.get()))
                .save(consumer, mainId(LibAdvancementNames.SHOOTING_GUARDIAN));
        //parent: thechaos
        Advancement.Builder.advancement()
                .display(simple(ModItems.ROD_OF_DISCORD.get(), LibAdvancementNames.ROD_OF_DISCORD_CRAFT, FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("rodofdiscord", onPickup(ModItems.ROD_OF_DISCORD.get()))
                .save(consumer, mainId(LibAdvancementNames.ROD_OF_DISCORD_CRAFT));
        //parent:ego
        Advancement.Builder.advancement()
                .display(simple(ModItems.FAILNAUGHT.get(), LibAdvancementNames.FAILNAUGHT_CRAFT, FrameType.TASK))
                .parent(ego_defeat)
                .addCriterion("failnaught", onPickup(ModItems.FAILNAUGHT.get()))
                .save(consumer, mainId(LibAdvancementNames.FAILNAUGHT_CRAFT));
        Advancement.Builder.advancement()
                .display(simple(ModItems.FIRST_FRACTAL.get(), LibAdvancementNames.FIRST_FRACTAL_CRAFT, FrameType.TASK))
                .parent(ego_defeat)
                .addCriterion("first_fractal", onPickup(ModItems.FIRST_FRACTAL.get()))
                .save(consumer, mainId(LibAdvancementNames.FIRST_FRACTAL_CRAFT));
        Advancement.Builder.advancement()
                .display(simple(ModItems.EXCALIBER.get(), LibAdvancementNames.EXCALIBER_CRAFT, FrameType.TASK))
                .parent(ego_defeat)
                .addCriterion("excaliber", onPickup(ModItems.EXCALIBER.get()))
                .save(consumer, mainId(LibAdvancementNames.EXCALIBER_CRAFT));
        Advancement.Builder.advancement()
                .display(simple(ModItems.THE_UNIVERSE.get(), LibAdvancementNames.THE_UNIVERSE_CRAFT, FrameType.TASK))
                .parent(ego_defeat)
                .addCriterion("the_universe", onPickup(ModItems.THE_UNIVERSE.get()))
                .save(consumer, mainId(LibAdvancementNames.THE_UNIVERSE_CRAFT));

/*
        // Parent: root
        Advancement.Builder.advancement()
                .display(simple(ModItems.lexicon, "lexiconUse", FrameType.TASK))
                .parent(root)
                .addCriterion("use_lexicon", new UseItemSuccessTrigger.Instance(EntityPredicate.Composite.ANY,
                        ItemPredicate.Builder.item().of(ModItems.lexicon).build(), LocationPredicate.ANY))
                .save(consumer, mainId("lexicon_use"));


        // Parent: mana pool
        Advancement.Builder.advancement()
                .display(simple(ModBlocks.enchanter, "enchanterMake", FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("code_triggered", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, mainId("enchanter_make"));
        Advancement.Builder.advancement()
                .display(simple(ModSubtiles.bellethorn, "functionalFlower", FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("flower", onPickup(ModTags.Items.FUNCTIONAL_SPECIAL_FLOWERS))
                .save(consumer, mainId("functional_flower"));

        Advancement.Builder.advancement()
                .display(simple(ModItems.manaCookie, "manaCookieEat", FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("cookie", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.manaCookie))
                .save(consumer, mainId("mana_cookie_eat"));
        Advancement.Builder.advancement()
                .display(simple(ModItems.manaRing, "baubleWear", FrameType.TASK))
                .parent(thechaos_craft)
                .addCriterion("code_triggered", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, mainId("bauble_wear"));


        // Parent: gaia guardian
        DisplayInfo tiaraWings = simple(ModItems.flightTiara, "tiaraWings", FrameType.TASK);
        tiaraWings.getIcon().getOrCreateTag().putInt("variant", 1);
        InventoryChangeTrigger.TriggerInstance[] variants = IntStream.range(1, ItemFlightTiara.WING_TYPES)
                .mapToObj(i ->
                {
                    CompoundTag tag = new CompoundTag();
                    tag.putInt("variant", i);
                    return tag;
                })
                .map(nbt -> ItemPredicate.Builder.item().of(ModItems.flightTiara).hasNbt(nbt).build())
                .map(InventoryChangeTrigger.TriggerInstance::hasItems)
                .toArray(InventoryChangeTrigger.TriggerInstance[]::new);
        var builder = Advancement.Builder.advancement()
                .display(tiaraWings)
                .parent(gaiaGuardianKill)
                .requirements(RequirementsStrategy.OR);
        for (int i = 0; i < variants.length; i++)
        {
            var variant = variants[i];
            builder.addCriterion("tiara_" + (i + 1), variant);
        }
        builder.save(consumer, mainId("tiara_wings"));

*/

        //just for Lexicon locks
        Advancement.Builder.advancement()
                .parent(root)
                .addCriterion("nightmare", onPickup(ModItems.NIGHTMARE_FUEL.get()))
                .addCriterion("elven_lexicon", elvenLexicon)
                .requirements(RequirementsStrategy.OR)
                .save(consumer, mainId("nightmarefuel_eat_lexicon"));

        Advancement.Builder.advancement()
                .parent(nightmarefuel_eat)
                .addCriterion("pickup", onPickup(ModItems.THE_CHAOS.get()))
                .addCriterion("elven_lexicon", elvenLexicon)
                .requirements(RequirementsStrategy.OR)
                .save(consumer, mainId("thechaos_craft_lexicon"));

        Advancement.Builder.advancement()
                .parent(thechaos_craft)
                .addCriterion("ego", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntities.EGO)))
                .addCriterion("elven_lexicon", elvenLexicon)
                .save(consumer, mainId("ego_defeat_lexicon"));
        /*
        Advancement.Builder.advancement()
                .parent(nightmarefuel_eat)
                .addCriterion("altar", onPickup(ModBlocks.runeAltar))
                .addCriterion("rune", onPickup(ModTags.Items.RUNES))
                .addCriterion("elven_lexicon", elvenLexicon)
                .requirements(RequirementsStrategy.OR)
                .save(consumer, mainId("runic_altar_pickup"));
        Advancement.Builder.advancement()
                .parent(nightmarefuel_eat)
                .addCriterion("terrasteel", onPickup(ModItems.AERO_STONE.get()))
                .addCriterion("elven_lexicon", elvenLexicon)
                .requirements(RequirementsStrategy.OR)
                .save(consumer, mainId("terrasteel_pickup_lexicon"));
        Advancement.Builder.advancement()
                .parent(elfPortalOpen)
                .addCriterion("lexicon", elvenLexicon)
                .save(consumer, mainId("elf_lexicon_pickup"));
        */
    }

    private static void challengeAdvancements(Consumer<Advancement> consumer)
    {
        /*
        Advancement root = Advancement.Builder.advancement()
                .display(rootDisplay(ModItems.dice, "advancement.botania_challenge",
                        "advancement.botania_challenge.desc", prefix("textures/block/livingrock_bricks.png")))
                .addCriterion("flower", onPickup(ModTags.Items.MYSTICAL_FLOWERS))
                .save(consumer, challengeId("root"));

        // hardmode Gaia Guardian related
        CompoundTag hardmodeNbt = new CompoundTag();
        hardmodeNbt.putBoolean("hardMode", true);
        Advancement hardMode = Advancement.Builder.advancement()
                .display(simple(ModItems.lifeEssence, "gaiaGuardianHardmode", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("guardian", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(ModEntities.DOPPLEGANGER)
                                .nbt(new NbtPredicate(hardmodeNbt)).build()))
                .save(consumer, challengeId("gaia_guardian_hardmode"));

        relicBindAdvancement(consumer, hardMode, ModItems.infiniteFruit, "infiniteFruit", "fruit");
        relicBindAdvancement(consumer, hardMode, ModItems.kingKey, "kingKey", "key");
        relicBindAdvancement(consumer, hardMode, ModItems.flugelEye, "flugelEye", "eye");
        relicBindAdvancement(consumer, hardMode, ModItems.thorRing, "thorRing", "ring");
        relicBindAdvancement(consumer, hardMode, ModItems.odinRing, "odinRing", "ring");
        Advancement lokiRing = relicBindAdvancement(consumer, hardMode, ModItems.lokiRing, "lokiRing", "ring");

        Advancement.Builder.advancement()
                .display(simple(ModItems.lokiRing, "lokiRingMany", FrameType.CHALLENGE))
                .parent(lokiRing)
                .addCriterion("place_blocks", new LokiPlaceTrigger.Instance(
                        EntityPredicate.Composite.ANY, EntityPredicate.ANY, ItemPredicate.ANY, MinMaxBounds.Ints.atLeast(255)
                ))
                .save(consumer, challengeId("loki_ring_many"));
        Advancement.Builder.advancement()
                .display(simple(ModItems.pinkinator, "pinkinator", FrameType.CHALLENGE))
                .parent(hardMode)
                .addCriterion("use_pinkinator", new UseItemSuccessTrigger.Instance(
                        EntityPredicate.Composite.ANY, matchItems(ModItems.pinkinator), LocationPredicate.ANY))
                .save(consumer, challengeId("pinkinator"));

        // Misc challenges
        Advancement.Builder.advancement()
                .display(simple(Blocks.PLAYER_HEAD, "gaiaGuardianNoArmor", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("no_armor", new DopplegangerNoArmorTrigger.Instance(
                        EntityPredicate.Composite.ANY, EntityPredicate.ANY, DamageSourcePredicate.ANY))
                .save(consumer, challengeId("gaia_guardian_no_armor"));
        Advancement.Builder.advancement()
                .display(hidden(ModBlocks.motifDaybloom, "old_flower_pickup", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("flower", onPickup(ModBlocks.motifDaybloom, ModBlocks.motifNightshade))
                .requirements(RequirementsStrategy.OR)
                .save(consumer, challengeId("old_flower_pickup"));
        DisplayInfo desuGun = simple(ModItems.manaGun, "desuGun", FrameType.CHALLENGE);
        desuGun.getIcon().setHoverName(new TextComponent("desu gun"));
        Advancement.Builder.advancement()
                .display(desuGun)
                .parent(root)
                .addCriterion("use_gun", new ManaGunTrigger.Instance(
                        EntityPredicate.Composite.ANY, ItemPredicate.ANY, EntityPredicate.ANY, true))
                .save(consumer, challengeId("desu_gun"));
        Advancement.Builder.advancement()
                .display(simple(ModBlocks.corporeaIndex, "superCorporeaRequest", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("big_request", new CorporeaRequestTrigger.Instance(
                        EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(TileCorporeaIndex.MAX_REQUEST), LocationPredicate.ANY))
                .save(consumer, challengeId("super_corporea_request"));
        Advancement.Builder.advancement()
                .display(simple(ModItems.terraPick, "rankSSPick", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("code_triggered", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, challengeId("rank_ss_pick"));
        CompoundTag level20Shard = new CompoundTag();
        level20Shard.putInt("level", 19);
        Advancement.Builder.advancement()
                .display(simple(ModItems.laputaShard, "l20ShardUse", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("use_l20_shard", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(ModItems.laputaShard).hasNbt(level20Shard).build()))
                .save(consumer, challengeId("l20_shard_use"));
        Advancement.Builder.advancement()
                .display(hidden(Items.BREAD, "alfPortalBread", FrameType.CHALLENGE))
                .parent(root)
                .addCriterion("bread", new AlfPortalBreadTrigger.Instance(EntityPredicate.Composite.ANY, LocationPredicate.ANY))
                .save(consumer, challengeId("alf_portal_bread"));
*/
    }

    private static Advancement relicBindAdvancement(Consumer<Advancement> consumer, Advancement parent, Item relicItem,
                                                    String titleKey, String criterionName)
    {
        String id = challengeId(Registry.ITEM.getKey(relicItem).getPath());
        return Advancement.Builder.advancement()
                .display(simple(relicItem, titleKey, FrameType.CHALLENGE))
                .parent(parent)
                .addCriterion(criterionName, new RelicBindTrigger.Instance(EntityPredicate.Composite.ANY,
                        ItemPredicate.Builder.item().of(relicItem).build()))
                .save(consumer, id);
    }

    protected static InventoryChangeTrigger.TriggerInstance onPickup(TagKey<Item> tag)
    {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance onPickup(ItemLike... items)
    {
        return InventoryChangeTrigger.TriggerInstance.hasItems(matchItems(items));
    }

    protected static ItemPredicate matchItems(ItemLike... items)
    {
        return ItemPredicate.Builder.item().of(items).build();
    }

    protected static DisplayInfo simple(ItemLike icon, String name, FrameType frameType)
    {
        String expandedName = "advancement.extrabotany:" + name;
        return new DisplayInfo(new ItemStack(icon.asItem()),
                new TranslatableComponent(expandedName + ".title"),
                new TranslatableComponent(expandedName + ".desc"),
                null, frameType, true, true, false);
    }

    protected static DisplayInfo hidden(ItemLike icon, String name, FrameType frameType)
    {
        String expandedName = "advancement.extrabotany:" + name;
        return new DisplayInfo(new ItemStack(icon.asItem()),
                new TranslatableComponent(expandedName + ".title"),
                new TranslatableComponent(expandedName + ".desc"),
                null, frameType, true, true, true);
    }

    protected static DisplayInfo rootDisplay(ItemLike icon, String titleKey, String descKey, ResourceLocation background)
    {
        return new DisplayInfo(new ItemStack(icon.asItem()),
                new TranslatableComponent(titleKey),
                new TranslatableComponent(descKey),
                background, FrameType.TASK, false, false, false);
    }

    private static String mainId(String name)
    {
        return prefix("main/" + name).toString();
    }

    private static String challengeId(String name)
    {
        return prefix("challenge/" + name).toString();
    }

    private static ResourceLocation prefix(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, path);
    }

    @Override
    public String getName()
    {
        return "extrabotany Advancements";
    }
}
