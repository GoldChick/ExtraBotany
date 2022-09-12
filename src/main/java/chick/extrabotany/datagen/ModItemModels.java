package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.libs.LibBlockNames;
import chick.extrabotany.common.libs.LibItemNames;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.ArrayList;


public class ModItemModels extends ItemModelProvider
{
    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ExtraBotany.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {

        var list = new ArrayList<>(Registry.ITEM.stream()
                .filter(p -> p.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
                .filter(p -> !(p instanceof ItemBlockSpecialFlower))
                .filter(p -> !(p instanceof ShieldItem))
                .filter(p -> !(p instanceof SwordItem))
                .filter(p->!(p instanceof ProjectileWeaponItem))
                .toList());
        list.remove(ModBlocks.DIMENSION_CATALYST_ITEM.get());
        //write in ModItemOverrideModel
        list.remove(ModItems.COCK_TAIL.get());
        //write by hand
        list.remove(ModItems.UNIVERSAL_PETAL.get());
        //not completed yet
        list.remove(ModItems.SUPREME_EARTH_STONE.get());
        list.remove(ModItems.SUPREME_AERO_STONE.get());
        list.remove(ModItems.SUPREME_IGNITE_STONE.get());
        list.remove(ModItems.SUPREME_AQUA_STONE.get());
        for (var item : list)
        {
            simpleTexture(item.getRegistryName().getPath());
        }
        singleTexture(ModItems.SPLASH_GRENADE.getId().getPath(), mcLoc("item/generated"), "layer1", modLoc("item/" + LibItemNames.SPLASH_GRENADE + "1"));

        registerFlowerModels();
        registerWeaponModels();
    }

    private void registerFlowerModels()
    {
        withExistingParent(ModBlocks.DIMENSION_CATALYST_ITEM.get().getRegistryName().getPath(), modLoc("block/"+LibBlockNames.DIMENSION_CATALYST));
        //no floating flowers here
        //generating
        singleTexture(ModSubtiles.sunshinelily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/" + LibBlockNames.SUNSHINE_LILY));
        singleTexture(ModSubtiles.moonlightlily_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.MOONLIGHT_LILY));
        singleTexture(ModSubtiles.omniviolet_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.OMNI_VIOLET));
        singleTexture(ModSubtiles.geminiorchid_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.GEMINI_ORCHID));
        singleTexture(ModSubtiles.bellflower_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.BELL_FLOWER));
        singleTexture(ModSubtiles.reikarorchid_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/" + LibBlockNames.REIKAR_ORCHID));
        singleTexture(ModSubtiles.bloodyenchantress_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/" + LibBlockNames.BLOODY_ENCHANTRESS));
        //functional
        singleTexture(ModSubtiles.serenitian_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.SERENITIAN));
        singleTexture(ModSubtiles.annoying_item.getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/"+LibBlockNames.ANNOYING_FLOWER));
    }

    private void registerWeaponModels()
    {
        simpleTexture(LibItemNames.SHADOW_KATANA, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.TRUE_SHADOW_KATANA, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.TRUE_TERRA_BLADE, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.TRUE_THUNSTAR_CALLER, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.INFLUX_WAVER, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.EXCALIBER, mcLoc("item/handheld"));
        simpleTexture(LibItemNames.FIRST_FRACTAL, mcLoc("item/handheld"));
    }

    private ItemModelBuilder simpleTexture(String name, ResourceLocation parent)
    {
        return singleTexture(name, parent, "layer0", modLoc("item/" + name));
    }

    private ItemModelBuilder simpleTexture(String name)
    {
        return singleTexture(name, mcLoc("item/generated"), "layer0", modLoc("item/" + name));
    }
}