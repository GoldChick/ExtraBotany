package chick.extrabotany.common.tools.weapons.ranged;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.item.BowItem.getPowerForTime;

public class ManaShortArrow extends Item
{
    public ManaShortArrow(Properties prop)
    {
        super(prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        var stack = player.getItemInHand(hand);
        int ticks = Math.round(player.getAttackStrengthScale(0) * 20);
        shootArrow(player, null, ticks, 0.52D, 1);
        player.awardStat(Stats.ITEM_USED.get(this));
        stack.shrink(1);
        //TODO: check it
        return InteractionResultHolder.consume(stack);
    }

    /**
     * just shoot
     * it does not check whether you can shoot
     *
     * @param living       shooter
     * @param useTicks     0tick-20tick
     * @param damageFactor dmg will * this float
     * @param kbBase       kb will + this int
     */
    public static void shootArrow(LivingEntity living, @Nullable ItemStack arrow, int useTicks, double damageFactor, int kbBase)
    {
        ItemStack stack = living.getItemInHand(InteractionHand.MAIN_HAND);

        float power = getPowerForTime(useTicks);
        var level = living.getLevel();

        if (arrow == null)
        {
            arrow = ItemStack.EMPTY;
        }
        if (!level.isClientSide)
        {
            ArrowItem arrowitem = (ArrowItem) Items.ARROW;

            var abstractarrow = arrowitem.createArrow(level, arrow, living);

            abstractarrow.shootFromRotation(living, living.getXRot(), living.getYRot(), 0.0F, power * 3.0F, 1.0F);

            abstractarrow.setCritArrow(useTicks == 20);

            abstractarrow.setPierceLevel((byte) 1);

            int power_level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
            if (power_level > 0)
            {
                abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) power_level * 0.5D + 0.5D);
            }
            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() * damageFactor);

            abstractarrow.setKnockback(kbBase + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack));

            abstractarrow.setSecondsOnFire(100 * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack));

            stack.hurtAndBreak(1, living, (p) -> p.broadcastBreakEvent(living.getUsedItemHand()));

            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) <= 0 && (arrow.is(Items.SPECTRAL_ARROW) || arrow.is(Items.TIPPED_ARROW)))
            {
                abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            level.addFreshEntity(abstractarrow);

            level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);
        }
    }
}
