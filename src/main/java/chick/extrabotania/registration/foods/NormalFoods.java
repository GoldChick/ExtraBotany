package chick.extrabotania.registration.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NormalFoods
{
    public static final FoodProperties OBSIDIAN_APPLE = (new FoodProperties.Builder()).nutrition(20)
            .saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1 * 20, 4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60 * 20, 0), 1f)
            .alwaysEat().build();
}
