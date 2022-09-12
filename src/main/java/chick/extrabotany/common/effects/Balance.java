package chick.extrabotany.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class Balance extends MobEffect
{
    public Balance()
    {
        super(MobEffectCategory.NEUTRAL, 0xffffff);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified)
    {
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return Lists.newArrayList();
    }
}
