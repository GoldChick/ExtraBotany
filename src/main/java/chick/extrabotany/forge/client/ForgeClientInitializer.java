package chick.extrabotany.forge.client;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.blocks.ModSubtiles;
import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.forge.CapabilityUtil;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;
import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

@Mod.EventBusSubscriber(modid = ExtraBotany.MODID,bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientInitializer
{
    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent evt)
    {
        onRenderTypeSetup(evt);
        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, ForgeClientInitializer::attachBeCapabilities);
    }
    private static void onRenderTypeSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            //to make them transparent
            setRenderLayer(ModSubtiles.sunshinelily, RenderType.cutout());
        });
    }
    private static final Supplier<Map<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>> WAND_HUD = Suppliers.memoize(() -> {
        var ret = new IdentityHashMap<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>();
        ModSubtiles.registerWandHudCaps((factory, types) -> {
            for (var type : types) {
                ret.put(type, factory);
            }
        });
        return Collections.unmodifiableMap(ret);
    });
    private static void attachBeCapabilities(AttachCapabilitiesEvent<BlockEntity> e) {
        var be = e.getObject();

        var makeWandHud = WAND_HUD.get().get(be.getType());
        if (makeWandHud != null) {
            e.addCapability(prefix("wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, makeWandHud.apply(be)));
        }
    }

}