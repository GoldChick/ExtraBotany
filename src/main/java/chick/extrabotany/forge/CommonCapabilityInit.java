package chick.extrabotany.forge;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.cap.ExtraBotanyCapabilities;
import chick.extrabotany.api.cap.IPassiveFlowerCap;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.baubles.NatureOrb;
import chick.extrabotany.common.baubles.relic.MoonPendant;
import chick.extrabotany.common.baubles.relic.SagesManaRing;
import chick.extrabotany.common.baubles.relic.SunRing;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.ModTiles;
import chick.extrabotany.common.tools.weapons.*;
import chick.extrabotany.common.tools.weapons.ranged.Failnaught;
import chick.extrabotany.forge.impl.IPassiveFlowerCapImpl;
import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;
import vazkii.botania.forge.CapabilityUtil;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public class CommonCapabilityInit
{
    private static final Supplier<Map<Item, Function<ItemStack, IManaItem>>> MANA_ITEM = Suppliers.memoize(() -> Map.of(
            ModItems.SAGES_MANA_RING.get(), SagesManaRing.GreaterManaItem::new
    ));
    private static final Supplier<Map<Item, Function<ItemStack, IManaItem>>> NATURE_ORB = Suppliers.memoize(() -> Map.of(
            ModItems.NATURE_ORB.get(), ItemManaRing.ManaItem::new
    ));

    private static final Supplier<Map<Item, Function<ItemStack, IRelic>>> RELIC = Suppliers.memoize(() -> Map.of(
            ModItems.TRUE_TERRA_BLADE.get(), TrueTerraBlade::makeRelic,
            ModItems.TRUE_SHADOW_KATANA.get(), TrueShadowKatana::makeRelic,
            ModItems.TRUE_THUNSTAR_CALLER.get(), TrueThunStarCaller::makeRelic,
            ModItems.INFLUX_WAVER.get(), InfluxWaver::makeRelic,
            ModItems.FAILNAUGHT.get(), Failnaught::makeRelic,
            ModItems.EXCALIBER.get(), Excaliber::makeRelic,
            ModItems.FIRST_FRACTAL.get(), FirstFractal::makeRelic,

            ModItems.SUN_RING.get(), SunRing::makeRelic,
            ModItems.MOON_PENDANT.get(), MoonPendant::makeRelic
    ));

    /**
     * Register Item Capabilities
     */
    public static void attachItemStackCaps(AttachCapabilitiesEvent<ItemStack> e)
    {
        var stack = e.getObject();
        var makeManaItem = MANA_ITEM.get().get(stack.getItem());
        if (makeManaItem != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "mana_item"),
                    CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_ITEM, makeManaItem.apply(stack)));
        }

        var makeNatureOrb = NATURE_ORB.get().get(stack.getItem());
        if (makeNatureOrb != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "nature_orb"),
                    CapabilityUtil.makeProvider(ExtraBotanyCapabilities.NATURE_ORB, makeNatureOrb.apply(stack)));
        }

        var makeRelic = RELIC.get().get(stack.getItem());
        if (makeRelic != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "relic"),
                    CapabilityUtil.makeProvider(BotaniaForgeCapabilities.RELIC, makeRelic.apply(stack)));
        }
    }

    public static final Supplier<Map<BlockEntityType<?>, Function<BlockEntity, IPassiveFlowerCap>>> PASSIVE_FLOWER = Suppliers.memoize(() -> Map.of(
            vazkii.botania.common.block.ModSubtiles.HYDROANGEAS, IPassiveFlowerCapImpl::new,
            ModSubtiles.SUNSHINELILY, IPassiveFlowerCapImpl::new,
            ModSubtiles.MOONLIGHTLILY, IPassiveFlowerCapImpl::new,
            ModSubtiles.BELLFLOWER, IPassiveFlowerCapImpl::new,
            ModSubtiles.GEMINIORCHID, IPassiveFlowerCapImpl::new
    ));

    /**
     * Register BlockEntity Capabilities
     */
    public static void attachBlockEntityCaps(AttachCapabilitiesEvent<BlockEntity> e)
    {
        var be = e.getObject();

        var passiveFlower = PASSIVE_FLOWER.get().get(be.getType());
        if (passiveFlower != null)
        {
            e.addCapability(new ResourceLocation(ExtraBotany.MODID, "passive_flower"),
                    CapabilityUtil.makeProvider(ExtraBotanyCapabilities.PASSIVE_FLOWER, passiveFlower.apply(be)));
        }
    }

}
