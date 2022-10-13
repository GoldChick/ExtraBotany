package chick.extrabotany.common.base;

import chick.extrabotany.common.baubles.NatureOrb;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
            var cap = vazkii.botania.xplat.IXplatAbstractions.INSTANCE.findManaItem(stack);
            if (cap != null)
            {
                event.getToolTip().add(2, new TextComponent("Mana:" + cap.getMana() + "/" + cap.getMaxMana()).withStyle(ChatFormatting.AQUA));
            }
        } else if (stack.getItem() instanceof NatureOrb)
        {
            var cap = IXplatAbstractions.INSTANCE.findNatureOrbItem(stack);
            if (cap != null)
            {
                event.getToolTip().add(2, new TranslatableComponent("extrabotany.nature_orb", cap.getNature(), cap.getMaxNature()).withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
