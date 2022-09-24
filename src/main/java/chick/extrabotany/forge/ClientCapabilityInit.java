package chick.extrabotany.forge;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.ModTiles;
import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.forge.CapabilityUtil;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public class ClientCapabilityInit
{
    private static final Supplier<Map<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>> WAND_HUD = Suppliers.memoize(() ->
    {
        var ret = new IdentityHashMap<BlockEntityType<?>, Function<BlockEntity, IWandHUD>>();
        ModTiles.registerWandHudCaps((factory, types) ->
        {
            for (var type : types)
            {
                ret.put(type, factory);
            }
        });
        ModSubtiles.registerWandHudCaps((factory, types) ->
        {
            for (var type : types)
            {
                ret.put(type, factory);
            }
        });
        return Collections.unmodifiableMap(ret);
    });
    public static void attachBlockEntityCaps(AttachCapabilitiesEvent<BlockEntity> e)
    {
        var be = e.getObject();
        var makeWandHud = WAND_HUD.get().get(be.getType());
        if (makeWandHud != null)
        {
            e.addCapability(prefix("wand_hud"),
                    CapabilityUtil.makeProvider(BotaniaForgeClientCapabilities.WAND_HUD, makeWandHud.apply(be)));
        }

    }
}
