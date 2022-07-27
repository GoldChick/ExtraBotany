package chick.extrabotania.datagen;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.ModItems;
import chick.extrabotania.registration.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider
{

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ExtraBotania.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        //ResourceLocation rL = new ResourceLocation(ExampleMod.MODID,"name");

        withExistingParent(Registration.YLG_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/yeluogui_ore"));

        singleTexture(ModItems.RAW_YLG_CHUNK.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/raw_yeluogui_chunk"));
        singleTexture(ModItems.YLG_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/yeluogui_ingot"));
        singleTexture(ModItems.OBSIDIAN_APPLE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_apple"));
        singleTexture(ModItems.OBSIDIAN_SWORD.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_sword"));
        singleTexture(ModItems.OBSIDIAN_HELMET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_helmet"));
        singleTexture(ModItems.OBSIDIAN_CHESTPLATE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_chestplate"));
        singleTexture(ModItems.OBSIDIAN_LEGGINGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_leggings"));
        singleTexture(ModItems.OBSIDIAN_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/obsidian_boots"));

    }
}