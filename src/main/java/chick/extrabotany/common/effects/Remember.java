package chick.extrabotany.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Remember extends MobEffect
{
    public Remember()
    {
        super(MobEffectCategory.BENEFICIAL, 0x00FF7F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amp)
    {
        return duration == 1;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amp)
    {
        if (living instanceof Player player)
        {
            float amount = Math.min(player.getAbsorptionAmount(), getRememberMaxAbsorptionPerLevel(amp));
            player.heal(amount / 2F);
            player.setAbsorptionAmount(player.getAbsorptionAmount() - amount);
        }
    }

    public static float getRememberMaxAbsorptionPerLevel(int amp)
    {
        return (amp + 1) * 12F;
    }
}
