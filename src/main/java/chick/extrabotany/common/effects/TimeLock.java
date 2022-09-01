package chick.extrabotany.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class TimeLock extends MobEffect
{
    public TimeLock()
    {
        super(MobEffectCategory.HARMFUL, 0xFFD700);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified)
    {
        living.setDeltaMovement(Vec3.ZERO);
    }

}
