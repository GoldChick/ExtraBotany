package chick.extrabotany.common;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry
{
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            //to make them transparent
            setRenderLayer(ModSubtiles.sunshinelily, RenderType.cutout());
        });
    }
}