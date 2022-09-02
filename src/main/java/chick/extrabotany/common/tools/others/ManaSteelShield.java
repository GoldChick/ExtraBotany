package chick.extrabotany.common.tools.others;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.lib.ModTags;

import java.util.function.Consumer;

public class ManaSteelShield extends ShieldItem implements ICustomDamageItem
{
    public ManaSteelShield(Properties prop)
    {
        super(prop.durability(250));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
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
    public boolean isValidRepairItem(ItemStack item, ItemStack material)
    {
        return material.is(ModTags.Items.INGOTS_MANASTEEL);
    }

    public int getManaPerDamage()
    {
        return 60;
    }
}
