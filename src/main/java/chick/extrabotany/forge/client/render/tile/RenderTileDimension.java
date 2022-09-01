package chick.extrabotany.forge.client.render.tile;

import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class RenderTileDimension implements BlockEntityRenderer<TileDimensionCatalyst>
{
    public RenderTileDimension(BlockEntityRendererProvider.Context manager) {}
    @Override
    public void render(TileDimensionCatalyst dimensionCatalyst, float f, PoseStack ms, MultiBufferSource buffers, int light, int overlay)
    {

    }
}
