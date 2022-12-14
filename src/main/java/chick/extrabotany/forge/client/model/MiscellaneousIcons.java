package chick.extrabotany.forge.client.model;

import chick.extrabotany.ExtraBotany;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import vazkii.botania.client.model.GunModel;
import vazkii.botania.mixin.client.AccessorModelBakery;

import java.util.Map;
import java.util.Set;

import static net.minecraftforge.client.model.ForgeModelBakery.addSpecialModel;


public class MiscellaneousIcons
{

    public static final MiscellaneousIcons INSTANCE = new MiscellaneousIcons();

    public final Material dimensionCatalystOverlay = mainAtlas("block/dimensioncatalyst");

    public final BakedModel[] firstFractalWeaponModels = new BakedModel[10];
    public final BakedModel[] strengthenSlashModel = new BakedModel[1];
    public final BakedModel[] flamescionringModel = new BakedModel[1];
    public final BakedModel[] influxwaverprojectileModel = new BakedModel[1];
    public final BakedModel[] trueterrabladeprojectileModel = new BakedModel[1];
    public final BakedModel[] trueshadowkatanaprojectileModel = new BakedModel[1];
    public final BakedModel[] coregodWingsModel = new BakedModel[4];
    public final BakedModel[] coregodModel = new BakedModel[1];
    public final BakedModel[] butterflyprojectileModel = new BakedModel[1];

    public void onModelRegister(ModelRegistryEvent evt)
    {
        Set<Material> materials = AccessorModelBakery.getMaterials();
        materials.add(dimensionCatalystOverlay);
        for (int i = 0; i < 10; i++)
        {
            addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/sworddomain_" + i));
        }
        //addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/strengthenslash"));
        //addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/flamescionring"));
        addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/influxwaverprojectile"));
        addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/trueterrabladeprojectile"));
        addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/trueshadowkatanaprojectile"));
        for (int i = 0; i < 4; i++)
        {
            addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/wing_" + i));
        }
        addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/wing_coregod"));
        //addSpecialModel(new ResourceLocation(ExtraBotany.MODID, "icon/butterflyprojectile"));
    }

    public void onModelBake(ModelBakeEvent evt)
    {
        onItemModelBake(evt.getModelLoader(), evt.getModelRegistry());
        onEntityModelBake(evt);
        //projectiles
        for (int i = 0; i < firstFractalWeaponModels.length; i++)
        {
            firstFractalWeaponModels[i] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/sworddomain_" + i));
        }
        // strengthenSlashModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/strengthenslash"));
        // flamescionringModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/flamescionring"));
        influxwaverprojectileModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/influxwaverprojectile"));
        trueterrabladeprojectileModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/trueterrabladeprojectile"));
        trueshadowkatanaprojectileModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/trueshadowkatanaprojectile"));
        for (int i = 0; i < coregodWingsModel.length; i++)
        {
            coregodWingsModel[i] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/wing_" + i));
        }
        coregodModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/wing_coregod"));
        // butterflyprojectileModel[0] = evt.getModelRegistry().get(new ResourceLocation(ExtraBotany.MODID, "icon/butterflyprojectile"));
    }

    private void onItemModelBake(ModelBakery loader, Map<ResourceLocation, BakedModel> map)
    {
        /*
        ModelResourceLocation key = new ModelResourceLocation("botania:mana_gun", "inventory");
        BakedModel originalModel = map.get(key);
        ModelResourceLocation clipKey = new ModelResourceLocation("botania:mana_gun_clip", "inventory");
        BakedModel originalModelClip = map.get(clipKey);
        map.put(key, new GunModel(loader, originalModel, originalModelClip));
         */
    }

    private void onEntityModelBake(ModelBakeEvent evt)
    {

    }

    private static Material mainAtlas(String name)
    {
        return new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation(ExtraBotany.MODID, name));
    }

}
