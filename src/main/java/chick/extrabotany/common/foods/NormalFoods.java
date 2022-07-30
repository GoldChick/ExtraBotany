package chick.extrabotany.common.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NormalFoods
{
    public static final FoodProperties OBSIDIAN_APPLE = (new FoodProperties.Builder()).nutrition(20).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60 * 20, 0), 1f)
            .alwaysEat()
            .build();
    public static final FoodProperties SPIRITFUEL_PROP = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 25 * 20), 1.0F)
            .build();
    public static final FoodProperties NIGHTMAREFUEL_PROP = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.HARM, 1, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.UNLUCK, 25 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 25 * 20), 1.0F)
            .build();
}
