package chick.extrabotany.forge.client.handler;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.network.NetworkHandler;
import chick.extrabotany.network.inputMessage.PowerGloveMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.common.handler.EquipmentHandler;

@Mod.EventBusSubscriber
public class KeyInputHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent e)
    {
        Minecraft mc = Minecraft.getInstance();
        if (e.phase == TickEvent.Phase.END
                && mc.player != null
                && mc.player.getAttackStrengthScale(0) == 1
                && mc.options.keyAttack.isDown())
        {
            if (!EquipmentHandler.findOrEmpty(ModItems.POWER_GLOVE.get(), mc.player).isEmpty())
            {
                if (mc.hitResult.getType() == EntityHitResult.Type.ENTITY)
                {
                    var hitResult = (EntityHitResult) mc.hitResult;
                    var entity = hitResult.getEntity();
                    mc.player.attack(entity);
                    NetworkHandler.INSTANCE.sendToServer(new PowerGloveMessage(entity.getId()));
                } else if (mc.hitResult.getType() == EntityHitResult.Type.MISS)
                {
                    mc.player.resetAttackStrengthTicker();
                    net.minecraftforge.common.ForgeHooks.onEmptyLeftClick(mc.player);
                    mc.player.swing(InteractionHand.MAIN_HAND);
                }
            }
        }
    }


}
