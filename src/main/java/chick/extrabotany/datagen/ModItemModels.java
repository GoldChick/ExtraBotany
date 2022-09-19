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
import net.minecraft.world.item.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.ArrayList;
import java.util.stream.Stream;


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
                .filter(p -> !(p instanceof BlockItem))
                .filter(p -> !(p instanceof ShieldItem))
                .filter(p -> !(p instanceof SwordItem))
                .filter(p -> !(p instanceof ProjectileWeaponItem))
                .toList());
        //write in ModItemOverrideModel
        list.remove(ModItems.COCK_TAIL.get());
        //write by hand
        list.remove(ModItems.UNIVERSAL_PETAL.get());
        //not completed yet
        list.remove(ModItems.SUPREME_EARTH_STONE.get());
        list.remove(ModItems.SUPREME_AERO_STONE.get());
        list.remove(ModItems.SUPREME_IGNITE_STONE.get());
        list.remove(ModItems.SUPREME_AQUA_STONE.get());
        list.remove(ModItems.MANA_SHORTARROW.get());
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
        Registry.ITEM.stream().filter(p -> p.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
                .forEach(b ->
                        {
                            var path = b.getRegistryName().getPath();
                            if (b instanceof ItemBlockSpecialFlower)//this extends BlockItem
                            {
                                //no floating flowers here
                                if (!path.startsWith("floating_"))
                                {
                                    singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("block/" + path));
                                }
                            } else if (b instanceof BlockItem)
                            {
                                withExistingParent(path, modLoc("block/" + path));
                            }
                        }
                );

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