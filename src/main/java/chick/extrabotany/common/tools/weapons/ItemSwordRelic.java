package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.forge.IItemWithLeftClick;
import chick.extrabotany.network.NetworkHandler;
import chick.extrabotany.network.inputMessage.LeftClickMessage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.List;

public class ItemSwordRelic extends SwordItem implements IItemWithLeftClick
{
    private static final int MANA_PER_DAMAGE = 120;
    public ItemSwordRelic(Tier tier, int atkDamage, float atkSpeed, Properties prop)
    {
        super(tier, atkDamage, atkSpeed, prop);
        MinecraftForge.EVENT_BUS.addListener(this::leftClick);
        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::attackEntity);
    }

    public void attackEntity(AttackEntityEvent evt)
    {
        if (!evt.getPlayer().level.isClientSide)
        {
            onLeftClick(evt.getPlayer(), evt.getTarget());
        }
    }

    public void leftClick(PlayerInteractEvent.LeftClickEmpty evt)
    {
        if (!evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this)
            NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage(LeftClickMessage.LeftClickType.True_Weapon));
    }

    public void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt)
    {
        if (evt.getPlayer().level.isClientSide && !evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this)
            NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage(LeftClickMessage.LeftClickType.True_Weapon));
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

            if (!player.level.isClientSide && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExact(stack, player, MANA_PER_DAMAGE * 2, true))
            {
                stack.setDamageValue(stack.getDamageValue() - 1);
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
        RelicImpl.addDefaultTooltip(stack, tooltip);
    }
    public static BlockHitResult raytraceFromEntity(Entity e, double distance, boolean fluids)
    {
        return (BlockHitResult) e.pick(distance, 1, fluids);
    }
    @Override
    public void onLeftClick(Player living, Entity target)
    {
    }
}
