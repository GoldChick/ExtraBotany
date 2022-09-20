package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;
import vazkii.botania.common.item.relic.RelicImpl;

import javax.annotation.Nonnull;

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
