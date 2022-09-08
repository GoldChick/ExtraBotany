package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.api.item.IItemWithLeftClick;
import chick.extrabotany.network.NetworkHandler;
import chick.extrabotany.network.inputMessage.LeftClickMessage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.List;
import java.util.function.Consumer;

public abstract class SwordRelicBase extends SwordItem implements IItemWithLeftClick, ICustomDamageItem
{
    public SwordRelicBase(Tier tier, int atkDamage, float atkSpeed, Properties prop)
    {
        super(tier, atkDamage, atkSpeed, prop.durability(1999));
        MinecraftForge.EVENT_BUS.addListener(this::leftClicks);
        MinecraftForge.EVENT_BUS.addListener(this::attackEntity);
    }

    public abstract int getManaPerDamage();

    public abstract void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime);

    public void attackEntity(AttackEntityEvent evt)
    {
        if (!evt.getPlayer().level.isClientSide)
        {
            onLeftClick(evt.getPlayer(), evt.getTarget());
        }
    }

    public void leftClicks(PlayerInteractEvent evt)
    {
        if (evt instanceof PlayerInteractEvent.LeftClickEmpty || evt instanceof PlayerInteractEvent.LeftClickBlock)
        {
            if (evt.getPlayer().level.isClientSide && !evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this)
            {
                NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage(LeftClickMessage.LeftClickType.True_Weapon));
            }
        }
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

            if (!player.level.isClientSide && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExact(stack, player, getManaPerDamage() * 2, true))
            {
                stack.setDamageValue(stack.getDamageValue() - 1);
            }
        }
    }

    @NotNull
    @Override
    public Rarity getRarity(ItemStack stack)
    {
        return Rarity.EPIC;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        RelicImpl.addDefaultTooltip(stack, tooltip);
    }

    public static BlockHitResult raytraceFromEntity(Entity e, double distance, boolean fluids)
    {
        return (BlockHitResult) e.pick(distance, 1, fluids);
    }

    public void attack(LivingEntity player, Entity target)
    {
        attack(player, target, 1, 1D, 1F);
    }


    @Override
    public void onLeftClick(Player player, Entity target)
    {
        if (!player.level.isClientSide
                && !player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
                && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == this
                && player.getAttackStrengthScale(0) == 1
                && ManaItemHandler.instance().requestManaExactForTool(player.getItemBySlot(EquipmentSlot.MAINHAND), player, getManaPerDamage(), true))
        {
            attack(player, target);
        }
    }
}
