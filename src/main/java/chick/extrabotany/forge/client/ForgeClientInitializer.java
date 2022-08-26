package chick.extrabotany.forge.client;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItemProperties;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.baubles.SagesManaRing;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.loot.ModLootModifiers;
import chick.extrabotany.common.tools.weapons.*;
import chick.extrabotany.datagen.ModBlockStates;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import chick.extrabotany.forge.client.model.LayerDefinitions;
import chick.extrabotany.forge.client.render.ColorHandler;
import chick.extrabotany.network.NetworkHandler;
import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.forge.CapabilityUtil;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

@Mod.EventBusSubscriber(modid = ExtraBotany.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientInitializer
{

    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent evt)
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //projectile model
        bus.addListener(MiscellaneousIcons.INSTANCE::onModelRegister);
        bus.addListener(MiscellaneousIcons.INSTANCE::onModelBake);
        ModItemProperties.init((item, id, prop) -> ItemProperties.register(item.asItem(), id, prop));
        // Anything that touches vanilla registries needs to happen during *a* registry event
        // So just use a random one
        bus.addGenericListener(Block.class, (RegistryEvent.Register<Block> e) -> ModLootModifiers.init());

        var forgebus = MinecraftForge.EVENT_BUS;
        forgebus.addGenericListener(BlockEntity.class, ForgeClientInitializer::attachBeCapabilities);
        forgebus.addGenericListener(ItemStack.class, ForgeClientInitializer::attachItemCaps);
        NetworkHandler.registerMessage();
    }


    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt)
    {
        LayerDefinitions.init(evt::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers evt)
    {
        ModBlockStates.registerBlockEntityRenderers(evt::registerBlockEntityRenderer);
        EntityRenderers.registerEntityRenderers(evt::registerEntityRenderer);


    }

    /**
     * set block transparent
     */
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ModBlockStates::setRenderType);
    }

    /**
     * Items color (like potions
     */
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item evt)
    {
        ColorHandler.submitItems(evt.getItemColors()::register);
    }

    /**
     * 森林法杖，下同
     */
    private static final Supplier<Map<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>> WAND_HUD = Suppliers.memoize(() ->
    {
        var ret = new IdentityHashMap<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>();
        ModSubtiles.registerWandHudCaps((factory, types) ->
        {
            for (var type : types)
            {
                ret.put(type, factory);
            }
        });
        return Collections.unmodifiableMap(ret);
    });

    private static void attachBeCapabilities(AttachCapabilitiesEvent<BlockEntity> e)
    {
        var be = e.getObject();

        var makeWandHud = WAND_HUD.get().get(be.getType());
        if (makeWandHud != null)
        {
            e.addCapability(prefix("wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, makeWandHud.apply(be)));
        }
    }

    private static final Supplier<Map<Item, Function<ItemStack, IManaItem>>> MANA_ITEM = Suppliers.memoize(() -> Map.of(
            ModItems.SAGES_MANA_RING.get(), SagesManaRing.GreaterManaItem::new
    ));
    private static final Supplier<Map<Item, Function<ItemStack, IRelic>>> RELIC = Suppliers.memoize(() -> Map.of(
            ModItems.TRUE_TERRA_BLADE.get(), TrueTerraBlade::makeRelic,
            ModItems.TRUE_SHADOW_KATANA.get(), TrueShadowKatana::makeRelic,
            ModItems.TRUE_THUNSTAR_CALLER.get(), TrueThunStarCaller::makeRelic,
            ModItems.INFLUX_WAVER.get(), InfluxWaver::makeRelic,
            ModItems.FAILNAUGHT.get(), Failnaught::makeRelic
    ));

    private static void attachItemCaps(AttachCapabilitiesEvent<ItemStack> e)
    {
        var stack = e.getObject();
        var makeManaItem = MANA_ITEM.get().get(stack.getItem());
        if (makeManaItem != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "mana_item"),
                    CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_ITEM, makeManaItem.apply(stack)));
        }

        var makeRelic = RELIC.get().get(stack.getItem());
        if (makeRelic != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "relic"),
                    CapabilityUtil.makeProvider(BotaniaForgeCapabilities.RELIC, makeRelic.apply(stack)));
        }
    }
}