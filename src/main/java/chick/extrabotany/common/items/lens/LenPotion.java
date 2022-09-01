package chick.extrabotany.common.items.lens;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LenPotion extends Lens
{
    @Override
    public void apply(ItemStack stack, BurstProperties props)
    {
        props.motionModifier *= 0.9F;
        props.maxMana *= 4;
        props.manaLossPerTick *= 4;
    }
    @Override
    public boolean collideBurst(IManaBurst burst, HitResult pos, boolean isManaBlock, boolean shouldKill, ItemStack stack)
    {
        var entity = burst.entity();

        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        if (stack.getItem() instanceof LenPotionBase lens)
        {
            Brew brew = lens.getBrew(stack);
            for (LivingEntity living : entities)
            {
                if (!burst.isFake())
                {
                    if (!entity.level.isClientSide)
                    {
                        for (MobEffectInstance effect : brew.getPotionEffects(stack))
                        {
                            var newEffect = new MobEffectInstance(effect.getEffect(), effect.getDuration() / 3,
                                    effect.getAmplifier(), true, true);
                            if (effect.getEffect().isInstantenous())
                                effect.getEffect().applyInstantenousEffect(living, living, living, newEffect.getAmplifier(), 1F);
                            else
                                living.addEffect(newEffect);
                        }
                        shouldKill = true;
                    }
                }
            }
        }
        return shouldKill;
    }

}
