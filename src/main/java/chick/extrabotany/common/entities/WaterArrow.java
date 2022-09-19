package chick.extrabotany.common.entities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class WaterArrow extends Arrow
{
    public WaterArrow(Level p_36866_, LivingEntity p_36867_)
    {
        super(p_36866_, p_36867_);
    }

    @Override
    protected float getWaterInertia()
    {
        return 0.99F;
    }
}
