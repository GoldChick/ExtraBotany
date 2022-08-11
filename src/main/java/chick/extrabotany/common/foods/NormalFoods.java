package chick.extrabotany.common.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NormalFoods
{
    public static final FoodProperties FRIED_CHICKEN = (new FoodProperties.Builder()).alwaysEat().nutrition(6).saturationMod(0.5F).build();
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
    public static final FoodProperties GILDED_MASHED_POTATO = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.2F)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30 * 20, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30 * 20, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1), 1.0F)
            .build();
    public static final FoodProperties MANA_DRINK = (new FoodProperties.Builder()).nutrition(0).saturationMod(0).alwaysEat().build();
}
