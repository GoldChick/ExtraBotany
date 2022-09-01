package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.entities.ego.EntityEGOLandmine;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;

import javax.annotation.Nonnull;

public class RenderEGOLandmine extends EntityRenderer<EntityEGOLandmine>
{
    private static final double INITIAL_OFFSET = -1.0 / 16 + 0.005;
    // Global y offset so that overlapping landmines do not Z-fight
    public static double offY = INITIAL_OFFSET;

    public RenderEGOLandmine(EntityRendererProvider.Context renderManager)
    {
        super(renderManager);
    }

    public static void onWorldRenderLast(RenderLevelLastEvent evt)
    {
        offY = INITIAL_OFFSET;
    }

    @Override
    public void render(EntityEGOLandmine e, float entityYaw, float partialTicks, PoseStack ms, MultiBufferSource buffers, int light)
    {
        super.render(e, entityYaw, partialTicks, ms, buffers, light);

        ms.pushPose();
        AABB aabb = e.getBoundingBox().move(e.position().scale(-1));

        float gs = (float) (Math.sin(ClientTickHandler.total() / 20) + 1) * 0.2F + 0.6F;
        int r = 0, g = 0, b = 0;
        switch (e.getLandmineType())
        {
            case 0 -> b = 240;
            case 1 -> g = 240;
            case 2 -> r = 240;
        }
        r = (int) (r * gs);
        g = (int) (g * gs);
        b = (int) (b * gs);

        int color = r << 16 | g << 8 | b;

        int alpha = 32;
        if (e.tickCount < 8)
        {
            alpha *= Math.min((e.tickCount + partialTicks) / 8F, 1F);
        } else if (e.tickCount > 47)
        {
            alpha *= Math.min(1F - (e.tickCount - 47 + partialTicks) / 8F, 1F);
        }

        RenderTileSpecialFlower.renderRectangle(ms, buffers, aabb, false, color, (byte) alpha);
        offY += 0.001;
        ms.popPose();
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@NotNull EntityEGOLandmine entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
