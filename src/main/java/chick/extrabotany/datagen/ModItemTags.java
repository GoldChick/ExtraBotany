package chick.extrabotany.datagen;

import chick.extrabotany.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        
        tag(ModItems.CORAL_ITEM)
                .add(Items.BRAIN_CORAL)
                .add(Items.TUBE_CORAL)
                .add(Items.FIRE_CORAL)
                .add(Items.BUBBLE_CORAL)
                .add(Items.HORN_CORAL)
                .add(Items.BRAIN_CORAL_FAN)
                .add(Items.TUBE_CORAL_FAN)
                .add(Items.FIRE_CORAL_FAN)
                .add(Items.BUBBLE_CORAL_FAN)
                .add(Items.HORN_CORAL_FAN);
        for (var color : DyeColor.values())
        {
            tag(ModTags.Items.getPetalTag(color)).add(ModItems.UNIVERSAL_PETAL.get());
        }
        tag(ModTags.Items.LENS)
                .add(ModItems.LEN_MANA.get())
                .add(ModItems.LEN_POTION.get())
                .add(ModItems.LEN_SMELT.get())
                .add(ModItems.LEN_TRACE.get())
        ;

        tag(ModTags.Items.RUNES)
                .add(ModItems.SIN_RUNE.get())
                .add(ModItems.ELEMENT_RUNE.get())
        ;

        tag(ModTags.Items.RODS)
                .add(ModItems.ROD_OF_DISCORD.get())
        ;

        tag(ModTags.Items.MANA_USING_ITEMS)
                .add(ModItems.ROD_OF_DISCORD.get())
                .add(ModItems.SHADOW_KATANA.get())
                .add(ModItems.TRUE_SHADOW_KATANA.get())
                .add(ModItems.TRUE_TERRA_BLADE.get())
                .add(ModItems.TRUE_THUNSTAR_CALLER.get())
                .add(ModItems.INFLUX_WAVER.get())
                .add(ModItems.FIRST_FRACTAL.get())
                .add(ModItems.FAILNAUGHT.get())

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
                .add(ModItems.IGNITE_STONE.get())
                .add(ModItems.POWER_GLOVE.get())
                .add(ModItems.JINGWEI_FEATHER.get())
                .add(ModItems.SUPREME_AERO_STONE.get())
                .add(ModItems.SUPREME_AQUA_STONE.get())
                .add(ModItems.SUPREME_EARTH_STONE.get())
                .add(ModItems.SUPREME_IGNITE_STONE.get())
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
        tag(accessory("necklace"))
                .add(ModItems.PURE_DAISY_PENDANT.get())
        ;
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