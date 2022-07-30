package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.tools.ModToolsTier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import java.util.function.Consumer;

public class ObsidianSword extends SwordItem implements ICustomDamageItem
{
    public static final int MANA_PER_DAMAGE = 10000;

    public ObsidianSword()
    {
        super(ModToolsTier.OBSIDIAN, 114514, 2f, new Item.Properties().tab(ExtraBotany.ITEM_GROUP));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        int manaPerDamage = ((ObsidianSword) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
        if (entity instanceof LivingEntity && selected)
        {
           ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 2));
        }
    }

    public int getManaPerDamage()
    {
        return MANA_PER_DAMAGE;
    }
}
