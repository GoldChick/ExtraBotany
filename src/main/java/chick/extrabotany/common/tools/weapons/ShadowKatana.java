package chick.extrabotany.common.tools.weapons;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.BlockHitResult;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.function.Consumer;


public class ShadowKatana extends SwordItem implements ICustomDamageItem
{
    public static final int MANA_PER_DAMAGE = 2;

    public ShadowKatana(Properties prop)
    {
        super(Tiers.GOLD, -1, 0, prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {

        ItemStack stack = player.getItemInHand(hand);
        var hitResult = (BlockHitResult) player.pick(5, 1, false);

        player.setPos(hitResult.getLocation().add(0, 0.5D, 0));

        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 1);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        int manaPerDamage = ((ShadowKatana) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    public int getManaPerDamage()
    {
        return MANA_PER_DAMAGE;
    }
}
