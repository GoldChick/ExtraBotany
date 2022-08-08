package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.data.util.ModelWithOverrides;
import vazkii.botania.data.util.OverrideHolder;
import vazkii.botania.mixin.AccessorTextureSlot;

import java.util.Optional;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public class ModItemModels extends ItemModelProvider
{
    private static final TextureSlot LAYER1 = AccessorTextureSlot.make("layer1");
    private static final ModelTemplate GENERATED_1 = new ModelTemplate(Optional.of(new ResourceLocation("item/generated")), Optional.empty(), TextureSlot.LAYER0, LAYER1);
    private static final ModelWithOverrides GENERATED_OVERRIDES_1 = new ModelWithOverrides(new ResourceLocation("item/generated"), TextureSlot.LAYER0, LAYER1);

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ExtraBotany.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        //withExistingParent(ModBlocks.YLG_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/yeluogui_ore"));

        singleTexture(ModItems.SPIRITFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit_fuel"));
        singleTexture(ModItems.NIGHTMAREFUEL_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/nightmare_fuel"));
        singleTexture(ModItems.SPIRIT_PROP.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/spirit"));
        singleTexture(ModItems.SHADOW_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/shadowium"));
        singleTexture(ModItems.PHOTON_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/photonium"));
        singleTexture(ModItems.GILDED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedpotato"));
        singleTexture(ModItems.GILDED_MASHED_POTATO.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gildedmashedpotato"));

        singleTexture(ModItems.MANA_READER.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/manareader"));
        singleTexture(ModItems.THE_CHAOS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/thechaos"));
        singleTexture(ModItems.THE_END.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theend"));
        singleTexture(ModItems.THE_ORIGIN.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theorigin"));
        singleTexture(ModItems.THE_UNIVERSE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/theuniverse"));
        singleTexture(ModItems.EMPTY_BOTTLE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/empty_bottle"));
        singleTexture(ModItems.MANA_DRINK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/mana_drink"));


        registerBaubleModels();
        registerFlowerModels();
        registerWeaponModels();
        registereArmorModels();
    }

    private void registerBaubleModels()
    {
        singleTexture(ModItems.DEATH_RING.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/deathring"));
        singleTexture(ModItems.AERO_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aerostone"));
        singleTexture(ModItems.AQUA_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aquastone"));
        singleTexture(ModItems.EARTH_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/earthstone"));
        singleTexture(ModItems.IGNIS_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ignisstone"));
        singleTexture(ModItems.FOUR_IN_ONE_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/fourinonestone"));
        singleTexture(ModItems.SUPREME_AERO_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aerostone"));
        singleTexture(ModItems.SUPREME_AQUA_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/aquastone"));
        singleTexture(ModItems.SUPREME_EARTH_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/earthstone"));
        singleTexture(ModItems.SUPREME_IGNIS_STONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ignisstone"));
    }

    private void registerFlowerModels()
    {
        //no floating flowers here
        //generating
        singleTexture(ModSubtiles.sunshinelily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/sunshine_lily"));
        singleTexture(ModSubtiles.moonlightlily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/moonlight_lily"));
        singleTexture(ModSubtiles.omniviolet_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/omni_violet"));
        singleTexture(ModSubtiles.geminiorchid_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/gemini_orchid"));
        //functional
        singleTexture(ModSubtiles.serenitian_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/serenitian"));
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