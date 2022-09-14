package chick.extrabotany.common.baubles;

import chick.extrabotany.common.entities.projectile.EntityAuraFire;
import chick.extrabotany.api.item.IItemWithLeftClick;
import chick.extrabotany.network.NetworkHandler;
import chick.extrabotany.network.inputMessage.LeftClickMessage;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class JingweiFeather extends ItemBauble implements IItemWithLeftClick
{
    public static final int MANA_PER_DAMAGE = 300;

    public JingweiFeather(Properties props)
    {
        super(props.stacksTo(1));
        MinecraftForge.EVENT_BUS.addListener(this::leftClickEmpty);
        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::attackEntity);
    }

    public void attackEntity(AttackEntityEvent evt)
    {
        if (!evt.getPlayer().level.isClientSide && !EquipmentHandler.findOrEmpty(this, evt.getPlayer()).isEmpty())
        {
            onLeftClick(evt.getPlayer(), evt.getTarget());
        }
    }
    @Override
    public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty evt)
    {
        if (!EquipmentHandler.findOrEmpty(this, evt.getPlayer()).isEmpty())
            NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage());

    }

    @Override
    public void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt)
    {
        if (evt.getPlayer().level.isClientSide && !EquipmentHandler.findOrEmpty(this, evt.getPlayer()).isEmpty())
        {
            NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage());
        }
    }

    @Override
    public void onLeftClick(Player player, Entity target)
    {
        if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && player.getAttackStrengthScale(0) == 1)
            if (ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, MANA_PER_DAMAGE, true))
            {
                var proj = new EntityAuraFire(player.level, player);
                proj.setPos(player.getX(), player.getY() + 1.1D, player.getZ());
                proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 0.8F, 0.9F);
                if (!player.level.isClientSide)
                    player.level.addFreshEntity(proj);
            }
    }
}
