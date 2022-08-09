package chick.extrabotany.forge.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;

public class RenderDummy extends EntityRenderer
{
    public RenderDummy(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }
    @Override
    public ResourceLocation getTextureLocation(Entity entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
