package chick.extrabotany.common.items;

import chick.extrabotany.common.ModItems;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ManaDrink extends Item
{
    public ManaDrink(Properties properties)
    {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving)
    {
        Player playerentity = entityLiving instanceof Player ? (Player) entityLiving : null;
        if (playerentity != null)
        {
            playerentity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerentity.isCreative())
            {
                stack.shrink(1);
            }
            if (stack.isEmpty())
            {
                return new ItemStack(ModItems.EMPTY_BOTTLE.get());
            }
            playerentity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60 * 20, 0));
            playerentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60 * 20, 0));
            playerentity.addEffect(new MobEffectInstance(MobEffects.JUMP, 60 * 20, 0));
            playerentity.getInventory().add(new ItemStack(ModItems.EMPTY_BOTTLE.get()));
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_)
    {
        return UseAnim.DRINK;
    }
}