package chick.extrabotany.common.tools.others;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.lib.ModTags;

import java.util.List;

public class ManaSteelShield extends ShieldItem implements IManaDiscountArmor
{
    public ManaSteelShield(Properties prop)
    {
        super(prop.durability(250));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack tool)
    {
        return 0.1F;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.magic_protection.desc", "10%").withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, level, list, flags);
    }

    @Override
    public boolean isValidRepairItem(ItemStack item, ItemStack material)
    {
        return material.is(ModTags.Items.INGOTS_MANASTEEL);
    }

    public int getManaPerDamage()
    {
        return 60;
    }
}
