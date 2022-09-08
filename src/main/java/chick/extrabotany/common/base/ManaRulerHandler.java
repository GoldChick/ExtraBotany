package chick.extrabotany.common.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;

/**
 * add numbers to items extend ItemManaRing in Botania
 */
@Mod.EventBusSubscriber
public class ManaRulerHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltip(ItemTooltipEvent event)
    {
        var stack = event.getItemStack();
        if (stack.getItem() instanceof ItemManaRing)
        {
            var cap = stack.getCapability(BotaniaForgeCapabilities.MANA_ITEM);
            if (cap.resolve().isPresent())
            {
                var item = cap.resolve().get();
                event.getToolTip().add(2, new TextComponent("Mana:" + item.getMana() + "/" + item.getMaxMana()).withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
