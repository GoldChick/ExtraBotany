package chick.extrabotany.forge.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.NotNull;

public class RenderDummy<T extends Entity> extends EntityRenderer<T>
{
    public RenderDummy(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
