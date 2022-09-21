package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.List;

public class SunRing extends AbstractMultiUpgradedBauble
{
    public SunRing(Properties props)
    {
        super(props);
    }

    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID, "main/" + LibAdvancementNames.SUN_RING_CRAFT));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player)
        {
            var relic = IXplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null)
            {
                relic.tickBinding(player);
            }
        }
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, world, tooltip, flags);
        RelicImpl.addDefaultTooltip(stack, tooltip);
    }
}
