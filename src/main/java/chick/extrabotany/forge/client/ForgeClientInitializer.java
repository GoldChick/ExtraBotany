package chick.extrabotany.forge.client;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItemProperties;
import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import chick.extrabotany.common.loots.ModLootModifiers;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import chick.extrabotany.forge.client.model.LayerDefinitions;
import chick.extrabotany.forge.client.render.ColorHandler;
import chick.extrabotany.network.NetworkHandler;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import vazkii.patchouli.api.PatchouliAPI;

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
        bus.addListener(ForgeClientInitializer::commonInit);
        ModItemProperties.init((item, id, prop) -> ItemProperties.register(item.asItem(), id, prop));
        // Anything that touches vanilla registries needs to happen during *a* registry event
        // So just use a random one
        bus.addGenericListener(Block.class, (RegistryEvent.Register<Block> e) -> ModLootModifiers.init());

        var forgebus = MinecraftForge.EVENT_BUS;

        forgebus.addGenericListener(BlockEntity.class, CapabilityInit::attachBlockEntityCaps);
        forgebus.addGenericListener(ItemStack.class, CapabilityInit::attachItemStackCaps);
        NetworkHandler.registerMessage();
    }

    //Not Client LOL
    public static void commonInit(FMLCommonSetupEvent evt)
    {
        PatchouliAPI.get().registerMultiblock(Registry.BLOCK.getKey(ModBlocks.DIMENSION_CATALYST.get()), TileDimensionCatalyst.MULTIBLOCK.get());
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt)
    {
        LayerDefinitions.init(evt::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers evt)
    {
        EntityRendererInit.registerEntityRenderers(evt::registerEntityRenderer);
        EntityRendererInit.registerBlockEntityRenderers(evt::registerBlockEntityRenderer);
    }

    /**
     * set block transparent
     */
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(EntityRendererInit::setRenderType);
    }

    /**
     * Items color (like potions
     */
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item evt)
    {
        ColorHandler.submitItems(evt.getItemColors()::register);
    }
}