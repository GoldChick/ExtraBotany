package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.List;


public class MoonPendant extends AbstractMultiUpgradedBauble
{
    public MoonPendant(Item.Properties props)
    {
        super(props);
    }

    @Override
    public int getMaxAmount()
    {
        return 6;
    }

    @Override
    public List<TagKey<Item>> getTagKeys()
    {
        return List.of(ItemTags.create(new ResourceLocation("curios", "necklace")));
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }

    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID, "main/" + LibAdvancementNames.MOON_PENDANT_CRAFT));
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
