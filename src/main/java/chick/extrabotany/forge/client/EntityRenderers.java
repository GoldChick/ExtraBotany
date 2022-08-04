package chick.extrabotany.forge.client;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.forge.client.model.entity.RenderDoppleganger;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class EntityRenderers
{
    public interface EntityRendererConsumer
    {
        <E extends Entity> void accept(EntityType<? extends E> entityType,
                                       EntityRendererProvider<E> entityRendererFactory);
    }

    public static void registerEntityRenderers(EntityRendererConsumer consumer)
    {
       consumer.accept(ModEntities.DOPPLEGANGER, RenderDoppleganger::new);
    }
}
