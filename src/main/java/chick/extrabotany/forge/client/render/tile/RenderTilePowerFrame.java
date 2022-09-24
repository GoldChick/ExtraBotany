package chick.extrabotany.forge.client.render.tile;

import chick.extrabotany.common.blocks.tile.TilePowerFrame;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.handler.ClientTickHandler;

public class RenderTilePowerFrame implements BlockEntityRenderer<TilePowerFrame>
{
    public RenderTilePowerFrame(BlockEntityRendererProvider.Context manager)
    {
        super();
    }

    @Override
    public void render(TilePowerFrame tile, float partialTicks, PoseStack ms, MultiBufferSource buffers, int light, int overlay)
    {
        ms.pushPose();
        ms.translate(0.5, 0.5, 0.5);
        boolean hasItem = !tile.getItemHandler().isEmpty();
        if (hasItem)
        {
            ms.mulPose(Vector3f.YP.rotationDegrees(ClientTickHandler.ticksInGame * 0.5F));
            ItemStack stack = tile.getItemHandler().getItem(0);
            Minecraft.getInstance().getItemRenderer()
                    .renderStatic(stack, ItemTransforms.TransformType.NONE, light, overlay, ms, buffers, 0);
        }
        ms.popPose();
    }
}
