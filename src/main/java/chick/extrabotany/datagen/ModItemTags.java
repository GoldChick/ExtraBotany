package chick.extrabotany.datagen;

import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.lib.ModTags;

import static chick.extrabotany.ExtraBotany.MODID;

public class ModItemTags extends ItemTagsProvider
{
    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper)
    {
        super(generator, blockTags, MODID, helper);
    }

    @Override
    protected void addTags()
    {
        tag(Tags.Items.ORES)
        //        .add(ModBlocks.YLG_ORE_ITEM.get())
        ;
        tag(Tags.Items.INGOTS)
                .add(ModItems.ORICHALCOS.get())
                .add(ModItems.PHOTON_INGOT.get())
                .add(ModItems.SHADOW_INGOT.get())
        ;
        this.copy(ModTags.Blocks.MUNDANE_FLOATING_FLOWERS, ModTags.Items.MUNDANE_FLOATING_FLOWERS);
        this.copy(ModTags.Blocks.SPECIAL_FLOATING_FLOWERS, ModTags.Items.SPECIAL_FLOATING_FLOWERS);
        this.copy(ModTags.Blocks.FLOATING_FLOWERS, ModTags.Items.FLOATING_FLOWERS);

        this.copy(ModTags.Blocks.MISC_SPECIAL_FLOWERS, ModTags.Items.MISC_SPECIAL_FLOWERS);
        this.copy(ModTags.Blocks.GENERATING_SPECIAL_FLOWERS, ModTags.Items.GENERATING_SPECIAL_FLOWERS);
        this.copy(ModTags.Blocks.FUNCTIONAL_SPECIAL_FLOWERS, ModTags.Items.FUNCTIONAL_SPECIAL_FLOWERS);
        this.copy(ModTags.Blocks.SPECIAL_FLOWERS, ModTags.Items.SPECIAL_FLOWERS);
        this.copy(ModTags.Blocks.MINI_FLOWERS, ModTags.Items.MINI_FLOWERS);

        tag(ModTags.Items.MANA_USING_ITEMS)
                .add(ModItems.ROD_OF_DISCORD.get())
                .add(ModItems.SHADOW_KATANA.get())
                .add(ModItems.TRUE_SHADOW_KATANA.get())

                .add(ModItems.SHADOW_WARRIOR_HELM.get())
                .add(ModItems.SHADOW_WARRIOR_CHEST.get())
                .add(ModItems.SHADOW_WARRIOR_LEGS.get())
                .add(ModItems.SHADOW_WARRIOR_BOOTS.get())
                .add(ModItems.GOBLINSLAYER_HELM.get())
                .add(ModItems.GOBLINSLAYER_CHEST.get())
                .add(ModItems.GOBLINSLAYER_LEGS.get())
                .add(ModItems.GOBLINSLAYER_BOOTS.get())
                .add(ModItems.MIKU_HELM.get())
                .add(ModItems.MIKU_CHEST.get())
                .add(ModItems.MIKU_LEGS.get())
                .add(ModItems.MIKU_BOOTS.get())
        ;
        baublesTags();

    }

    private void baublesTags()
    {

        tag(accessory("curio"))
                .add(ModItems.AERO_STONE.get())
                .add(ModItems.AQUA_STONE.get())
                .add(ModItems.EARTH_STONE.get())
                .add(ModItems.IGNIS_STONE.get())
                .add(ModItems.POWER_GLOVE.get())
                .add(ModItems.JINGWEI_FEATHER.get())
                .add(ModItems.SUPREME_AERO_STONE.get())
                .add(ModItems.SUPREME_AQUA_STONE.get())
                .add(ModItems.SUPREME_EARTH_STONE.get())
                .add(ModItems.SUPREME_IGNIS_STONE.get())
        ;
        tag(accessory("ring"))
                .add(ModItems.DEATH_RING.get())
                .add(ModItems.FROST_RING.get())
                .add(ModItems.SAGES_MANA_RING.get())
        ;

        tag(accessory("belt"));
        tag(accessory("body"))
                .add(ModItems.RED_SCARF.get())
        ;
        tag(accessory("charm"));
        tag(accessory("head"))
                .add(ModItems.FOX_EAR.get())
                .add(ModItems.FOX_MASK.get())
                .add(ModItems.SUPER_CROWN.get())
                .add(ModItems.PYLON.get())
                .add(ModItems.STONE_MASK.get())
                .add(ModItems.THUG_LIFE.get())
                .add(ModItems.BLACK_GLASSES.get())
        ;
        tag(accessory("necklace"));
    }

    private static TagKey<Item> accessory(String name)
    {
        return ItemTags.create(new ResourceLocation("curios", name));
    }

    @Override
    public String getName()
    {
        return "extrabotany Item Tags";
    }
}