package chick.extrabotany.common.baubles;

import chick.extrabotany.common.entities.ego.EntityEGO;
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
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;

import java.util.List;

public class NatureOrb extends ItemBauble
{
    public NatureOrb(Properties props)
    {
        super(props);
    }

    public static final String TAG_XP = "xp";
    //Same as ManaRing
    public static final int MAX_XP = 500000;

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(new TranslatableComponent("extrabotany.nature_orb", getXP(stack), getMaxXP()).withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect1").withStyle(getXP(stack) >= 100000 ? ChatFormatting.AQUA : ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect2").withStyle(getXP(stack) >= 300000 ? ChatFormatting.DARK_RED : ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("extrabotany.nature_orb_effect3").withStyle(getXP(stack) >= 400000 ? ChatFormatting.DARK_GREEN : ChatFormatting.GRAY));
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
            ItemManaRing.setMana(full, MAX_XP);
            stacks.add(full);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity)
    {
        super.onWornTick(stack, entity);
        if (entity instanceof Player player)
        {
            if (!player.level.isClientSide)
            {
                if (player.tickCount % 5 == 0)
                {
                    if (getXP(stack) > 100000)
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                    if (getXP(stack) > 200000)
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                    if (getXP(stack) > 300000)
                    {
                        ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                        if (player.tickCount % 60 == 0)
                        {
                            player.heal(1F);
                        }
                    }
                }
                if (getXP(stack) > 400000)
                {
                    if (player.tickCount % 40 == 0)
                    {
                        clearPotions(stack, player);
                    }
                }
            }
        }
    }

    public int getXP(ItemStack stack)
    {
        return ItemNBTHelper.getInt(stack, TAG_XP, 0);
    }

    public int getMaxXP()
    {
        return MAX_XP;
    }

    public void clearPotions(ItemStack stack, Player player)
    {
        player.getActiveEffects().stream()
                .filter(effect -> !effect.getEffect().isBeneficial())
                .filter(effect -> effect.getCurativeItems().stream().anyMatch(e -> e.is(Items.MILK_BUCKET)))
                .map(MobEffectInstance::getEffect)
                .distinct().forEach(potion ->
                {
                    player.removeEffect(potion);
                    //addXP(stack, -50);
                    ((ServerLevel) player.level).getChunkSource().broadcastAndSend(player, new ClientboundRemoveMobEffectPacket(player.getId(), potion));
                });
    }
}
