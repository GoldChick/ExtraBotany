package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModItemModels extends ItemModelProvider
{
    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ExtraBotany.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        withExistingParent(ModBlocks.DIMENSION_CATALYST_ITEM.get().getRegistryName().getPath(), modLoc("block/dimension_catalyst"));

        singleTexture(ModItems.SPIRITFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit_fuel"));
        singleTexture(ModItems.NIGHTMAREFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/nightmare_fuel"));
        singleTexture(ModItems.SPIRIT_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit"));
        singleTexture(ModItems.SHADOW_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowium"));
        singleTexture(ModItems.AERIALITE_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aerialite"));
        singleTexture(ModItems.PHOTON_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/photonium"));
        singleTexture(ModItems.GILDED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedpotato"));
        singleTexture(ModItems.GILDED_MASHED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedmashedpotato"));

        singleTexture(ModItems.MANA_READER.get().getRegistryName().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/manareader"));
        singleTexture(ModItems.THE_CHAOS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/thechaos"));
        singleTexture(ModItems.THE_END.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theend"));
        singleTexture(ModItems.THE_ORIGIN.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theorigin"));
        singleTexture(ModItems.THE_UNIVERSE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theuniverse"));
        singleTexture(ModItems.EMPTY_BOTTLE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/empty_bottle"));
        singleTexture(ModItems.MANA_DRINK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/mana_drink"));
        singleTexture(ModItems.ROD_OF_DISCORD.get().getRegistryName().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/rodofdiscord"));
        singleTexture(ModItems.SPLASH_GRENADE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/splash_grenade"));
        singleTexture(ModItems.SPLASH_GRENADE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer1", modLoc("item/splash_grenade1"));
        singleTexture(ModItems.HERO_MEDAL.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/heromedal"));
        singleTexture(ModItems.FRIED_CHICKEN.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/friedchicken"));
        singleTexture(ModItems.TICKET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ticket"));
        singleTexture(ModItems.ORICHALCOS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/orichalcos"));
        singleTexture(ModItems.GOLD_CLOTH.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gold_cloth"));

        singleTexture(ModItems.LEN_SMELT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/len_smelt"));
        singleTexture(ModItems.LEN_POTION.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/len_potion"));
        singleTexture(ModItems.LEN_MANA.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/len_mana"));


        registerBaubleModels();
        registerFlowerModels();
        registerWeaponModels();
        registereArmorModels();
    }

    private void registerBaubleModels()
    {
        singleTexture(ModItems.PYLON.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/pylon"));
        singleTexture(ModItems.FOX_EAR.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/foxear"));
        singleTexture(ModItems.FOX_MASK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/foxmask"));
        singleTexture(ModItems.SUPER_CROWN.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/supercrown"));
        singleTexture(ModItems.RED_SCARF.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/redscarf"));
        singleTexture(ModItems.BLACK_GLASSES.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/blackglasses"));
        singleTexture(ModItems.STONE_MASK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/stonemask"));
        singleTexture(ModItems.THUG_LIFE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/thuglife"));


        singleTexture(ModItems.PEACE_AMULET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/peace_amulet"));
        singleTexture(ModItems.DEATH_RING.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/deathring"));
        singleTexture(ModItems.FROST_RING.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/frostring"));
        singleTexture(ModItems.POWER_GLOVE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/powerglove"));
        singleTexture(ModItems.JINGWEI_FEATHER.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jingweifeather"));
        singleTexture(ModItems.AERO_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aerostone"));
        singleTexture(ModItems.AQUA_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aquastone"));
        singleTexture(ModItems.EARTH_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/earthstone"));
        singleTexture(ModItems.IGNIS_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ignisstone"));
        singleTexture(ModItems.FOUR_IN_ONE_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/fourinonestone"));
        singleTexture(ModItems.SUPREME_AERO_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aerostone"));
        singleTexture(ModItems.SUPREME_AQUA_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aquastone"));
        singleTexture(ModItems.SUPREME_EARTH_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/earthstone"));
        singleTexture(ModItems.SUPREME_IGNIS_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ignisstone"));
        singleTexture(ModItems.SAGES_MANA_RING.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/sages_mana_ring"));
    }

    private void registerFlowerModels()
    {
        //no floating flowers here
        //generating
        singleTexture(ModSubtiles.sunshinelily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/sunshine_lily"));
        singleTexture(ModSubtiles.moonlightlily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/moonlight_lily"));
        singleTexture(ModSubtiles.omniviolet_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/omni_violet"));
        singleTexture(ModSubtiles.geminiorchid_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/gemini_orchid"));
        singleTexture(ModSubtiles.bellflower_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/bell_flower"));
        singleTexture(ModSubtiles.reikarorchid_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/reikar_orchid"));
        //functional
        singleTexture(ModSubtiles.serenitian_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/serenitian"));
        singleTexture(ModSubtiles.annoying_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/annoying_flower"));
    }

    private void registerWeaponModels()
    {
        singleTexture(ModItems.SHADOW_KATANA.get().getRegistryName().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/shadowkatana"));
        singleTexture(ModItems.TRUE_SHADOW_KATANA.get().getRegistryName().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/trueshadowkatana"));

    }

    private void registereArmorModels()
    {
        singleTexture(ModItems.SHADOW_WARRIOR_HELM.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowwarrior_helm"));
        singleTexture(ModItems.SHADOW_WARRIOR_CHEST.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowwarrior_chest"));
        singleTexture(ModItems.SHADOW_WARRIOR_LEGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowwarrior_legs"));
        singleTexture(ModItems.SHADOW_WARRIOR_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowwarrior_boots"));
        singleTexture(ModItems.GOBLINSLAYER_HELM.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/goblinslayer_helm"));
        singleTexture(ModItems.GOBLINSLAYER_CHEST.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/goblinslayer_chest"));
        singleTexture(ModItems.GOBLINSLAYER_LEGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/goblinslayer_legs"));
        singleTexture(ModItems.GOBLINSLAYER_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/goblinslayer_boots"));
        singleTexture(ModItems.MIKU_HELM.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/miku_helm"));
        singleTexture(ModItems.MIKU_CHEST.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/miku_chest"));
        singleTexture(ModItems.MIKU_LEGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/miku_legs"));
        singleTexture(ModItems.MIKU_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/miku_boots"));
        singleTexture(ModItems.MAID_HELM.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/maid_helm"));
        singleTexture(ModItems.MAID_CHEST.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/maid_chest"));
        singleTexture(ModItems.MAID_LEGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/maid_legs"));
        singleTexture(ModItems.MAID_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/maid_boots"));

    }
}