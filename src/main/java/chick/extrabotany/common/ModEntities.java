package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.EntitySplashGrenade;
import chick.extrabotany.common.entities.projectile.EntityAuraFire;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import vazkii.botania.common.entity.EntityDoppleganger;

import java.util.function.BiConsumer;

public class ModEntities
{
    public static final EntityType<EntityDoppleganger> DOPPLEGANGER = EntityType.Builder
            .of(EntityDoppleganger::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .fireImmune()
            .clientTrackingRange(10)
            .updateInterval(10)
            .build(ExtraBotany.MODID + "doppleganger");
    public static final EntityType<EntityAuraFire> AURAFIRE = EntityType.Builder
            .<EntityAuraFire>of(EntityAuraFire::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "aurafire");
    public static final EntityType<EntitySplashGrenade> SPLASH_GRENADE = EntityType.Builder
            .<EntitySplashGrenade>of(EntitySplashGrenade::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "splashgrenade");

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> r)
    {
        r.accept(DOPPLEGANGER, new ResourceLocation(ExtraBotany.MODID, "doppleganger"));
        r.accept(AURAFIRE, new ResourceLocation(ExtraBotany.MODID, "aurafire"));
        r.accept(SPLASH_GRENADE, new ResourceLocation(ExtraBotany.MODID, "splashgrenade"));
    }

    public static void registerAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> consumer)
    {
        consumer.accept(ModEntities.DOPPLEGANGER, Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.MAX_HEALTH, EntityDoppleganger.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0));
    }
}
