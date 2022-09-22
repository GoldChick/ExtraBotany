package chick.extrabotany.forge.client.render.tile;

import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import vazkii.botania.common.block.tile.TileMod;

public class RenderTileDimension <T extends TileMod>implements BlockEntityRenderer<T>
{
    public RenderTileDimension(BlockEntityRendererProvider.Context manager) {}
    @Override
    public void render(T blank_tile, float f, PoseStack ms, MultiBufferSource buffers, int light, int overlay)
    {

    }

}
