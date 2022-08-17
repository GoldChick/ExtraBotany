package chick.extrabotany.forge.mixin.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemGreaterManaRing;

import java.util.List;

@Mixin(ItemGreaterManaRing.class)
public abstract class MixinGreaterManaRing extends ItemBauble
{
    @Final
    @Shadow
    private static int MAX_MANA;

    public MixinGreaterManaRing(Properties props)
    {
        super(props);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        tooltip.add(new TextComponent(
                "Mana:"
                        + checkInt(String.valueOf(ItemNBTHelper.getInt(stack, "mana", 0)))
                        + "/"
                        + checkInt(String.valueOf(MAX_MANA)))
                .withStyle(ChatFormatting.AQUA)
        );
    }

    private String checkInt(String str)
    {
        StringBuilder newStr = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
        {
            newStr.append(str.charAt(i));
            if ((str.length() - i) % 3 == 0 && i != 0)
            {
                newStr.append(",");
            }
        }
        return new StringBuffer(newStr.toString()).reverse().toString();
    }
}
