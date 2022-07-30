package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
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
        withExistingParent(ModBlocks.YLG_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/yeluogui_ore"));

        singleTexture(ModItems.RAW_YLG_CHUNK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/raw_yeluogui_chunk"));
        singleTexture(ModItems.YLG_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/yeluogui_ingot"));
        singleTexture(ModItems.OBSIDIAN_APPLE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_apple"));
        singleTexture(ModItems.SPIRITFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit_fuel"));
        singleTexture(ModItems.NIGHTMAREFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/nightmare_fuel"));
        singleTexture(ModItems.SPIRIT_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit"));
        singleTexture(ModItems.SHADOW_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowium"));
        singleTexture(ModItems.PHOTON_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/photonium"));
        singleTexture(ModItems.GILDED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedpotato"));
        singleTexture(ModItems.GILDED_MASHED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedmashedpotato"));
        registerWeaponModels();
        registereArmorModels();
    }
    private void registerWeaponModels()
    {
        singleTexture(ModItems.SHADOW_KATANA.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowkatana"));

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

    }
}