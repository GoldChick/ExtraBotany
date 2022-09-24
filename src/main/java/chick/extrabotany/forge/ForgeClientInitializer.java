package chick.extrabotany.forge;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItemProperties;
import chick.extrabotany.forge.client.EntityRendererInit;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import chick.extrabotany.forge.client.model.LayerDefinitions;
import chick.extrabotany.forge.client.render.ColorHandler;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, ClientCapabilityInit::attachBlockEntityCaps);
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