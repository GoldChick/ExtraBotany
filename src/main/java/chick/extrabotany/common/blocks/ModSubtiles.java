package chick.extrabotany.common.blocks;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.blocks.subtile.functional.*;
import chick.extrabotany.common.blocks.subtile.generating.*;
import chick.extrabotany.common.libs.LibBlockNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.ModTiles;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.function.BiConsumer;

public class ModSubtiles
{
    private static final Item.Properties props = new Item.Properties().tab(ExtraBotany.ITEM_GROUP);
    private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.copy(Blocks.POPPY);
    private static final BlockBehaviour.Properties FLOATING_PROPS = BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL).lightLevel(s -> 15);

    //generating
    public static final Block sunshinelily = new BlockSpecialFlower(MobEffects.ABSORPTION, 10, FLOWER_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final Block sunshinelilyFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.SUNSHINELILY);
    public static final Block moonlightlily = new BlockSpecialFlower(MobEffects.BLINDNESS, 10, FLOWER_PROPS, () -> ModSubtiles.MOONLIGHTLILY);
    public static final Block moonlightlilyFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.MOONLIGHTLILY);
    public static final Block omniviolet = new BlockSpecialFlower(MobEffects.DAMAGE_BOOST, 10, FLOWER_PROPS, () -> ModSubtiles.OMNIVIOLET);
    public static final Block omnivioletFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.OMNIVIOLET);
    public static final Block geminiorchid = new BlockSpecialFlower(MobEffects.HEALTH_BOOST, 10, FLOWER_PROPS, () -> ModSubtiles.GEMINIORCHID);
    public static final Block geminiorchidFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.GEMINIORCHID);
    public static final Block bellflower = new BlockSpecialFlower(MobEffects.MOVEMENT_SPEED, 10, FLOWER_PROPS, () -> ModSubtiles.BELLFLOWER);
    public static final Block bellflowerFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.BELLFLOWER);
    public static final Block reikarorchid = new BlockSpecialFlower(MobEffects.HARM, 10, FLOWER_PROPS, () -> ModSubtiles.REIKARORCHID);
    public static final Block reikarorchidFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.REIKARORCHID);
    public static final Block bloodyenchantress = new BlockSpecialFlower(MobEffects.HARM, 114, FLOWER_PROPS, () -> ModSubtiles.BLOODYENCHANTRESS);
    public static final Block bloodyenchantressFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.BLOODYENCHANTRESS);
    public static final Block edelweiss = new BlockSpecialFlower(MobEffects.MOVEMENT_SLOWDOWN, 10, FLOWER_PROPS, () -> ModSubtiles.EDELWEISS);
    public static final Block edelweissFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.EDELWEISS);
    public static final Block tinkleflower = new BlockSpecialFlower(MobEffects.DIG_SPEED, 10, FLOWER_PROPS, () -> ModSubtiles.TINKLEFLOWER);
    public static final Block tinkleflowerFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.TINKLEFLOWER);
    //functional
    public static final Block serenitian = new BlockSpecialFlower(MobEffects.ABSORPTION, 48, FLOWER_PROPS, () -> ModSubtiles.SERENITIAN);
    public static final Block serenitianFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.SERENITIAN);
    public static final Block annoying = new BlockSpecialFlower(MobEffects.BAD_OMEN, 48, FLOWER_PROPS, () -> ModSubtiles.ANNOYINGFLOWER);
    public static final Block annoyingFloating = new BlockFloatingSpecialFlower(FLOATING_PROPS, () -> ModSubtiles.ANNOYINGFLOWER);

    //block entity types
    public static final BlockEntityType<SubTileSunshineLily> SUNSHINELILY = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileSunshineLily::new, sunshinelily, sunshinelilyFloating);
    public static final BlockEntityType<SubTileMoonlightLily> MOONLIGHTLILY = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileMoonlightLily::new, moonlightlily, moonlightlilyFloating);
    public static final BlockEntityType<SubTileOmniViolet> OMNIVIOLET = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileOmniViolet::new, omniviolet, omnivioletFloating);
    public static final BlockEntityType<SubTileGeminiOrchid> GEMINIORCHID = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileGeminiOrchid::new, geminiorchid, geminiorchidFloating);
    public static final BlockEntityType<SubTileBellflower> BELLFLOWER = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileBellflower::new, bellflower, bellflowerFloating);
    public static final BlockEntityType<SubTileReikarOrchid> REIKARORCHID = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileReikarOrchid::new, reikarorchid, reikarorchidFloating);
    public static final BlockEntityType<SubTileBloodyEnchantress> BLOODYENCHANTRESS = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileBloodyEnchantress::new, bloodyenchantress, bloodyenchantressFloating);
    public static final BlockEntityType<SubTileEdelweiss> EDELWEISS = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileEdelweiss::new, edelweiss, edelweissFloating);
    public static final BlockEntityType<SubTileTinkle> TINKLEFLOWER = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileTinkle::new, tinkleflower, tinkleflowerFloating);


    public static final BlockEntityType<SubTileSerenitian> SERENITIAN = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileSerenitian::new, serenitian, serenitianFloating);
    public static final BlockEntityType<SubTileAnnoyingFlower> ANNOYINGFLOWER = IXplatAbstractions.INSTANCE.createBlockEntityType(SubTileAnnoyingFlower::new, annoying, annoyingFloating);

    //generating items
    public static final Item sunshinelily_item = new ItemBlockSpecialFlower(sunshinelily, props);
    public static final Item sunshinelilyFloating_item = new ItemBlockSpecialFlower(sunshinelilyFloating, props);
    public static final Item moonlightlily_item = new ItemBlockSpecialFlower(moonlightlily, props);
    public static final Item moonlightlilyFloating_item = new ItemBlockSpecialFlower(moonlightlilyFloating, props);
    public static final Item omniviolet_item = new ItemBlockSpecialFlower(omniviolet, props);
    public static final Item omnivioletFloating_item = new ItemBlockSpecialFlower(omnivioletFloating, props);
    public static final Item geminiorchid_item = new ItemBlockSpecialFlower(geminiorchid, props);
    public static final Item geminiorchidFloating_item = new ItemBlockSpecialFlower(geminiorchidFloating, props);
    public static final Item bellflower_item = new ItemBlockSpecialFlower(bellflower, props);
    public static final Item bellflowerFloating_item = new ItemBlockSpecialFlower(bellflowerFloating, props);
    public static final Item reikarorchid_item = new ItemBlockSpecialFlower(reikarorchid, props);
    public static final Item reikarorchidFloating_item = new ItemBlockSpecialFlower(reikarorchidFloating, props);
    public static final Item bloodyenchantress_item = new ItemBlockSpecialFlower(bloodyenchantress, props);
    public static final Item bloodyenchantressFloating_item = new ItemBlockSpecialFlower(bloodyenchantressFloating, props);
    public static final Item edelweiss_item = new ItemBlockSpecialFlower(edelweiss, props);
    public static final Item edelweissFloating_item = new ItemBlockSpecialFlower(edelweissFloating, props);
    public static final Item tinkleflower_item = new ItemBlockSpecialFlower(tinkleflower, props);
    public static final Item tinkleflowerFloating_item = new ItemBlockSpecialFlower(tinkleflowerFloating, props);
    //functional items
    public static final Item serenitian_item = new ItemBlockSpecialFlower(serenitian, props);
    public static final Item serenitianFloating_item = new ItemBlockSpecialFlower(serenitianFloating, props);
    public static final Item annoying_item = new ItemBlockSpecialFlower(annoying, props);
    public static final Item annoyingFloating_item = new ItemBlockSpecialFlower(annoyingFloating, props);

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r)
    {
        registerFlowerAndFloating(sunshinelily, sunshinelilyFloating, LibBlockNames.SUNSHINE_LILY, r);
        registerFlowerAndFloating(moonlightlily, moonlightlilyFloating, LibBlockNames.MOONLIGHT_LILY, r);
        registerFlowerAndFloating(omniviolet, omnivioletFloating, LibBlockNames.OMNI_VIOLET, r);
        registerFlowerAndFloating(geminiorchid, geminiorchidFloating, LibBlockNames.GEMINI_ORCHID, r);
        registerFlowerAndFloating(bellflower, bellflowerFloating, LibBlockNames.BELL_FLOWER, r);
        registerFlowerAndFloating(reikarorchid, reikarorchidFloating, LibBlockNames.REIKAR_ORCHID, r);
        registerFlowerAndFloating(bloodyenchantress, bloodyenchantressFloating, LibBlockNames.BLOODY_ENCHANTRESS, r);
        registerFlowerAndFloating(edelweiss, edelweissFloating, LibBlockNames.EDELWEISS, r);
        registerFlowerAndFloating(tinkleflower, tinkleflowerFloating, LibBlockNames.TINKLE_FLOWER, r);


        registerFlowerAndFloating(serenitian, serenitianFloating, LibBlockNames.SERENITIAN, r);
        registerFlowerAndFloating(annoying, annoyingFloating, LibBlockNames.ANNOYING_FLOWER, r);
    }

    private static void registerFlowerAndFloating(Block b, Block floating, String name, BiConsumer<Block, ResourceLocation> r)
    {
        r.accept(b, idfor(name));
        r.accept(floating, floating(name));
    }

    public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r)
    {
        //generating
        r.accept(sunshinelily_item, getId(sunshinelily));
        r.accept(sunshinelilyFloating_item, getId(sunshinelilyFloating));
        r.accept(moonlightlily_item, getId(moonlightlily));
        r.accept(moonlightlilyFloating_item, getId(moonlightlilyFloating));
        r.accept(omniviolet_item, getId(omniviolet));
        r.accept(omnivioletFloating_item, getId(omnivioletFloating));
        r.accept(geminiorchid_item, getId(geminiorchid));
        r.accept(geminiorchidFloating_item, getId(geminiorchidFloating));
        r.accept(bellflower_item, getId(bellflower));
        r.accept(bellflowerFloating_item, getId(bellflowerFloating));
        r.accept(reikarorchid_item, getId(reikarorchid));
        r.accept(reikarorchidFloating_item, getId(reikarorchidFloating));
        r.accept(bloodyenchantress_item, getId(bloodyenchantress));
        r.accept(bloodyenchantressFloating_item, getId(bloodyenchantressFloating));
        r.accept(edelweiss_item, getId(edelweiss));
        r.accept(edelweissFloating_item, getId(edelweissFloating));
        r.accept(tinkleflower_item, getId(tinkleflower));
        r.accept(tinkleflowerFloating_item, getId(tinkleflowerFloating));
        //functional
        r.accept(serenitian_item, getId(serenitian));
        r.accept(serenitianFloating_item, getId(serenitianFloating));
        r.accept(annoying_item, getId(annoying));
        r.accept(annoyingFloating_item, getId(annoyingFloating));
    }

    public static void registerWandHudCaps(ModTiles.BECapConsumer<IWandHUD> consumer)
    {
        consumer.accept(be -> new TileEntityGeneratingFlower.GeneratingWandHud<>((TileEntityGeneratingFlower) be),
                SUNSHINELILY, MOONLIGHTLILY, OMNIVIOLET, GEMINIORCHID, BELLFLOWER, REIKARORCHID, BLOODYENCHANTRESS, EDELWEISS, TINKLEFLOWER);
        consumer.accept(be -> new TileEntityFunctionalFlower.FunctionalWandHud<>((TileEntityFunctionalFlower) be),
                SERENITIAN, ANNOYINGFLOWER);
    }

    public static void registerTEs(BiConsumer<BlockEntityType<?>, ResourceLocation> r)
    {
        r.accept(SUNSHINELILY, getBEId(sunshinelily));
        r.accept(MOONLIGHTLILY, getBEId(moonlightlily));
        r.accept(OMNIVIOLET, getBEId(omniviolet));
        r.accept(GEMINIORCHID, getBEId(geminiorchid));
        r.accept(BELLFLOWER, getBEId(bellflower));
        r.accept(REIKARORCHID, getBEId(reikarorchid));
        r.accept(BLOODYENCHANTRESS, getBEId(bloodyenchantress));
        r.accept(EDELWEISS, getBEId(edelweiss));
        r.accept(TINKLEFLOWER, getBEId(tinkleflower));
        //functional
        r.accept(SERENITIAN, getBEId(serenitian));
        r.accept(ANNOYINGFLOWER, getBEId(annoying));
    }

    private static ResourceLocation getBEId(Block b)
    {
        return new ResourceLocation(ExtraBotany.MODID, LibBlockNames.FLOWER_BE_TYPE_PREFIX + b.getRegistryName().getPath());
    }

    private static ResourceLocation getId(Block b)
    {
        return new ResourceLocation(ExtraBotany.MODID, b.getRegistryName().getPath());
    }

    private static ResourceLocation floating(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, "floating_" + path);
    }

    private static ResourceLocation idfor(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, path);
    }
}
