package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import javax.annotation.Nonnull;
import java.util.List;

public class SagesManaRing extends ItemManaRing
{
    //cause INT_MAX problem
    //luckily I can solve it
    private static final int MAX_MANA = Integer.MAX_VALUE;

    public SagesManaRing(Properties props)
    {
        super(props);
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
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> stacks)
    {
        if (allowdedIn(tab))
        {
            stacks.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);
            setMana(full, MAX_MANA);
            stacks.add(full);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, world, tooltip, flags);
        RelicImpl.addDefaultTooltip(stack, tooltip);
    }

    public static class GreaterManaItem extends ItemManaRing.ManaItem
    {
        public GreaterManaItem(ItemStack stack)
        {
            super(stack);
        }

        @Override
        public void addMana(int mana)
        {
            setMana(stack, ((getMana() + mana) > 0 ? Math.min(getMana() + mana, getMaxMana()) : getMaxMana()) / stack.getCount());
        }

        @Override
        public boolean canReceiveManaFromItem(ItemStack otherStack)
        {
            return getMana() != getMaxMana();
        }

        @Override
        public int getMaxMana()
        {
            return MAX_MANA * stack.getCount();
        }
    }

    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID, "main/" + LibAdvancementNames.SAGES_MANA_RING_CRAFT));
    }
}
