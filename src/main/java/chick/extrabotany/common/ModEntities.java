package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.*;
import chick.extrabotany.common.entities.projectile.EntityAuraFire;
import chick.extrabotany.common.entities.projectile.EntityTrueShadowKatanaProjectile;
import chick.extrabotany.common.entities.projectile.EntityTrueTerraBladeProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.BiConsumer;

public class ModEntities
{
    public static final EntityType<EntityEGO> EGO = EntityType.Builder
            .of(EntityEGO::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .setTrackingRange(128)
            .setUpdateInterval(2)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "ego");
    public static final EntityType<EntityEGOMinion> EGO_MINION = EntityType.Builder
            .<EntityEGOMinion>of(EntityEGOMinion::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .setTrackingRange(128)
            .clientTrackingRange(128)
            .setUpdateInterval(2)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "ego_minion");

    public static final EntityType<EntityEGOLandmine> EGO_LANDMINE = EntityType.Builder
            .<EntityEGOLandmine>of(EntityEGOLandmine::new, MobCategory.MISC)
            .sized(3F, 0.1F)
            .setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(128)
            .setUpdateInterval(2)
            .build(ExtraBotany.MODID + "ego_landmine");
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
    public static final EntityType<EntityFalseLightning> FALSE_LIGHTNING = EntityType.Builder
            .<EntityFalseLightning>of(EntityFalseLightning::new, MobCategory.MISC)
            .sized(0.0F, 0.0F)
            .clientTrackingRange(16)
            .updateInterval(Integer.MAX_VALUE)
            .build(ExtraBotany.MODID + "false_lighting");
    public static final EntityType<EntityTrueShadowKatanaProjectile> TRUE_SHADOW_KATANA = EntityType.Builder
            .<EntityTrueShadowKatanaProjectile>of(EntityTrueShadowKatanaProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "trueshadowkatana");
    public static final EntityType<EntityTrueTerraBladeProjectile> TRUE_TERRA_BLADE = EntityType.Builder
            .<EntityTrueTerraBladeProjectile>of(EntityTrueTerraBladeProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build(ExtraBotany.MODID + "trueterrablade");

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> r)
    {
        r.accept(EGO, new ResourceLocation(ExtraBotany.MODID, "ego"));
        r.accept(EGO_MINION, new ResourceLocation(ExtraBotany.MODID, "ego_minion"));
        r.accept(EGO_LANDMINE, new ResourceLocation(ExtraBotany.MODID, "ego_landmine"));
        r.accept(AURAFIRE, new ResourceLocation(ExtraBotany.MODID, "aurafire"));
        r.accept(SPLASH_GRENADE, new ResourceLocation(ExtraBotany.MODID, "splashgrenade"));
        r.accept(FALSE_LIGHTNING, new ResourceLocation(ExtraBotany.MODID, "false_lighting"));
        r.accept(TRUE_SHADOW_KATANA, new ResourceLocation(ExtraBotany.MODID, "trueshadowkatana"));
        r.accept(TRUE_TERRA_BLADE, new ResourceLocation(ExtraBotany.MODID, "trueterrablade"));
    }

    public static void registerAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> consumer)
    {
        consumer.accept(ModEntities.EGO, Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.MAX_HEALTH, EntityEGO.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0));

        consumer.accept(ModEntities.EGO_MINION, Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.MAX_HEALTH, EntityEGO.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0));
    }
}
