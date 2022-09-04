package chick.extrabotany.common;

import chick.extrabotany.common.effects.*;
import chick.extrabotany.common.libs.LibEffectNames;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static chick.extrabotany.ExtraBotany.MODID;

public class ModEffects
{
    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

    public static final RegistryObject<MobEffect> BLOOD_TEMPTATION = MOB_EFFECTS.register(LibEffectNames.BLOOD_TEMPTATION, BloodTempation::new);
    public static final RegistryObject<MobEffect> VEGETABLE = MOB_EFFECTS.register(LibEffectNames.VEGETABLE, Vegetable::new);
    public static final RegistryObject<MobEffect> DIVINE_JUSTICE = MOB_EFFECTS.register(LibEffectNames.DIVINE_JUSTICE, DivineJustice::new);
    public static final RegistryObject<MobEffect> TIME_LOCK = MOB_EFFECTS.register(LibEffectNames.TIME_LOCK, TimeLock::new);
    public static final RegistryObject<MobEffect> BALANCE = MOB_EFFECTS.register(LibEffectNames.BALANCE, Balance::new);
    public static final RegistryObject<MobEffect> REMEMBER = MOB_EFFECTS.register(LibEffectNames.REMEMBER, Remember::new);

    public static DeferredRegister<MobEffect> getEffects()
    {
        return MOB_EFFECTS;
    }
}
