package chick.extrabotany.common.tools.others;

import chick.extrabotany.common.ModEffects;
import chick.extrabotany.common.base.DamageHandler;
import chick.extrabotany.common.effects.Remember;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ForestBook extends Item
{
    public ForestBook(Properties prop)
    {
        super(prop);
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        var stack = player.getItemInHand(hand);
        if (!level.isClientSide && player.getHealth() > 0)
        {
            player.addEffect(new MobEffectInstance(ModEffects.REMEMBER.get(), getEffectTicks(), 0));

            var num = Remember.getRememberMaxAbsorptionPerLevel(0);
            if (player.getAbsorptionAmount() < num)
            {
                DamageHandler.INSTANCE.doDamage(player, DamageSource.MAGIC, 6.0F, DamageHandler.INSTANCE.BYPASS_INVUL + DamageHandler.INSTANCE.BYPASS_MAGIC + DamageHandler.INSTANCE.BYPASS_ABSORB);
                player.setAbsorptionAmount(num);
                player.getCooldowns().addCooldown(this, getEffectTicks() / 20);
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.fail(stack);
    }

    protected int getEffectTicks()
    {
        return 5 * 20;
    }
}
