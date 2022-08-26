package chick.extrabotany.forge.client;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.forge.client.render.entity.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class EntityRenderers
{
    public interface EntityRendererConsumer
    {
        <E extends Entity> void accept(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory);
    }

    public static void registerEntityRenderers(EntityRendererConsumer consumer)
    {
        consumer.accept(ModEntities.EGO, RenderEGO::new);
        consumer.accept(ModEntities.EGO_MINION, RenderEGO::new);
        consumer.accept(ModEntities.EGO_LANDMINE, RenderEGOLandmine::new);
        consumer.accept(ModEntities.AURAFIRE, RenderDummy::new);
        consumer.accept(ModEntities.MAGIC_ARROW, RenderDummy::new);
        consumer.accept(ModEntities.FALSE_LIGHTNING, LightningBoltRenderer::new);
        consumer.accept(ModEntities.SPLASH_GRENADE, ThrownItemRenderer::new);

        consumer.accept(ModEntities.TRUE_SHADOW_KATANA, RenderProjectile::new);
        consumer.accept(ModEntities.TRUE_TERRA_BLADE, RenderProjectile::new);
        consumer.accept(ModEntities.INFLUX_WAVER, RenderProjectile::new);
    }

    public static void init(EntityRendererProvider.Context ctx)
    {

    }
}
