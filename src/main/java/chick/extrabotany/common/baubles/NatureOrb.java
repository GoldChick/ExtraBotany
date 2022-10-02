package chick.extrabotany.common.baubles;

import chick.extrabotany.api.cap.INatureOrbItem;
import chick.extrabotany.common.entities.ego.EntityEGO;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

import java.util.List;

//TODO:to be checked
public class NatureOrb extends ItemBauble
{
    protected static final String TAG_NATURE = "tag_nature";
    protected static final int MAX_NATURE = 500000;

    public NatureOrb(Properties props)
    {
        super(props);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, world, tooltip, flags);
        var manaItem = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        if (manaItem != null)
        {
            tooltip.add(new TranslatableComponent("extrabotany.nature_orb", manaItem.getNature(), manaItem.getMaxNature()).withStyle(ChatFormatting.GRAY));
            tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect1").withStyle(manaItem.getNature() >= 100000 ? ChatFormatting.AQUA : ChatFormatting.GRAY));
            tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect2").withStyle(manaItem.getNature() >= 300000 ? ChatFormatting.DARK_RED : ChatFormatting.GRAY));
            tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect3").withStyle(manaItem.getNature() >= 400000 ? ChatFormatting.DARK_GREEN : ChatFormatting.GRAY));
        }
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext ctx)
    {
        ItemStack stack = ctx.getItemInHand();
        return EntityEGO.spawn(ctx.getPlayer(), stack, ctx.getLevel(), ctx.getClickedPos()) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> stacks)
    {
        if (allowdedIn(tab))
        {
            stacks.add(new ItemStack(this));

            ItemStack full = new ItemStack(this);

            ItemNBTHelper.setInt(full, TAG_NATURE, MAX_NATURE);

            stacks.add(full);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity)
    {
        super.onWornTick(stack, entity);
        if (entity instanceof Player player && !player.level.isClientSide)
        {
            var orbItem = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
            if (orbItem != null)
            {
                if (player.tickCount % 5 == 0)
                {
                    if (orbItem.getNature() > 100000)
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                    if (orbItem.getNature() > 200000)
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                    if (orbItem.getMaxNature() > 300000)
                    {
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                        if (player.tickCount % 60 == 0)
                        {
                            player.heal(1F);
                        }
                    }
                }
                if (orbItem.getNature() > 400000)
                {
                    if (player.tickCount % 40 == 0)
                    {
                        clearPotions(stack, player);
                    }
                }
            }
        }
    }

    public void clearPotions(ItemStack stack, Player player)
    {
        var orb = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        if (orb != null)
        {
            player.getActiveEffects().stream()
                    .filter(effect -> !effect.getEffect().isBeneficial())
                    .filter(effect -> effect.getCurativeItems().stream().anyMatch(e -> e.is(Items.MILK_BUCKET)))
                    .map(MobEffectInstance::getEffect)
                    .distinct().forEach(potion ->
                    {
                        player.removeEffect(potion);
                        orb.addNature(-1000);
                        player.getCooldowns().addCooldown(this, 3);
                        ((ServerLevel) player.level).getChunkSource().broadcastAndSend(player, new ClientboundRemoveMobEffectPacket(player.getId(), potion));
                    });
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack)
    {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack)
    {
        var manaItem = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        return Math.round(13 * manaItem.getNature() / (float) manaItem.getMaxNature());
    }

    @Override
    public int getBarColor(ItemStack stack)
    {
        var manaItem = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        return Mth.hsvToRgb(manaItem.getNature() / (float) manaItem.getMaxNature() / 3.0F, 1.0F, 1.0F);
    }

    public static class NatureOrbItem implements INatureOrbItem
    {
        protected final ItemStack stack;

        public NatureOrbItem(ItemStack stack)
        {
            this.stack = stack;
        }

        @Override
        public int getNature()
        {
            return ItemNBTHelper.getInt(stack, TAG_NATURE, 0) * stack.getCount();
        }

        @Override
        public int getMaxNature()
        {
            return MAX_NATURE * stack.getCount();
        }

        @Override
        public void addNature(int x)
        {
            ItemNBTHelper.setInt(stack, TAG_NATURE, Math.min(getMaxNature(), getNature() + x) / stack.getCount());
        }

        @Override
        public boolean canReceiveNatureFromNatureAdder(BlockEntity adder)
        {
            return true;
        }

        @Override
        public boolean canReceiveManaFromItem(ItemStack otherStack)
        {
            return true;
        }

        @Override
        public boolean canExportManaToPool(BlockEntity pool)
        {
            return true;
        }

        @Override
        public boolean canExportManaToItem(ItemStack otherStack)
        {
            return true;
        }

        @Override
        public boolean isNoExport()
        {
            return false;
        }
    }
}
