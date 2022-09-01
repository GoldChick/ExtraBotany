package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.entities.ego.EntityEGOBeam;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class RenderEGOBeam extends EntityRenderer<EntityEGOBeam>
{
    public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");

    public RenderEGOBeam(EntityRendererProvider.Context renderManager)
    {
        super(renderManager);
    }

    @Override
    public boolean shouldRender(EntityEGOBeam p_114491_, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_)
    {
        return true;
    }

    @Override
    public void render(EntityEGOBeam e, float entityYaw, float partialTicks, PoseStack ms, MultiBufferSource buffers, int light)
    {
        super.render(e, entityYaw, partialTicks, ms, buffers, light);
        ms.pushPose();
        float s = 2.0F;
        ms.scale(s, s, s);
        renderBeamSegment(ms, buffers, partialTicks, e.getLevel().getGameTime(), 0, 128, e.getBeamColor());
        ms.popPose();
    }

    private static void renderBeamSegment(PoseStack matrixStackIn, MultiBufferSource bufferIn, float partialTicks, long totalWorldTime, int yOffset, int height, float[] colors)
    {
        renderBeamSegment(matrixStackIn, bufferIn, TEXTURE_BEACON_BEAM, partialTicks, 1.0F, totalWorldTime, yOffset, height, colors, 0.2F, 0.25F);
    }

    public static void renderBeamSegment(PoseStack matrixStackIn, MultiBufferSource bufferIn, ResourceLocation textureLocation, float partialTicks, float textureScale, long totalWorldTime, int yOffset, int height, float[] colors, float beamRadius, float glowRadius)
    {
        int i = yOffset + height;
        matrixStackIn.pushPose();
        float f = (float) Math.floorMod(totalWorldTime, 40L) + partialTicks;
        float f1 = height < 0 ? f : -f;
        float f2 = Mth.frac(f1 * 0.2F - (float) Mth.floor(f1 * 0.1F));
        float f3 = colors[0];
        float f4 = colors[1];
        float f5 = colors[2];
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f * 2.25F - 45.0F));
        float f6 = 0.0F;
        float f8 = 0.0F;
        float f9 = -beamRadius;
        float f12 = -beamRadius;
        float f15 = -1.0F + f2;
        float f16 = (float) height * textureScale * (0.5F / beamRadius) + f15;
        renderPart(matrixStackIn, bufferIn.getBuffer(RenderType.beaconBeam(textureLocation, false)), f3, f4, f5, 1.0F, yOffset, i, 0.0F, beamRadius, beamRadius, 0.0F, f9, 0.0F, 0.0F, f12, 0.0F, 1.0F, f16, f15);
        matrixStackIn.popPose();
        f6 = -glowRadius;
        float f7 = -glowRadius;
        f8 = -glowRadius;
        f9 = -glowRadius;
        f15 = -1.0F + f2;
        f16 = (float) height * textureScale + f15;
        renderPart(matrixStackIn, bufferIn.getBuffer(RenderType.beaconBeam(textureLocation, true)), f3, f4, f5, 0.125F, yOffset, i, f6, f7, glowRadius, f8, f9, glowRadius, glowRadius, glowRadius, 0.0F, 1.0F, f16, f15);
        matrixStackIn.popPose();
    }

    private static void renderPart(PoseStack poseStack, VertexConsumer bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float p_228840_8_, float p_228840_9_, float p_228840_10_, float p_228840_11_, float p_228840_12_, float p_228840_13_, float p_228840_14_, float p_228840_15_, float u1, float u2, float v1, float v2)
    {
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_8_, p_228840_9_, p_228840_10_, p_228840_11_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_14_, p_228840_15_, p_228840_12_, p_228840_13_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_10_, p_228840_11_, p_228840_14_, p_228840_15_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_12_, p_228840_13_, p_228840_8_, p_228840_9_, u1, u2, v1, v2);
    }

    private static void addQuad(Matrix4f matrixPos, Matrix3f matrixNormal, VertexConsumer bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2)
    {
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x1, z1, u2, v1);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x1, z1, u2, v2);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x2, z2, u1, v2);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x2, z2, u1, v1);
    }

    private static void addVertex(Matrix4f matrixPos, Matrix3f matrixNormal, VertexConsumer bufferIn, float red, float green, float blue, float alpha, int y, float x, float z, float texU, float texV)
    {
        bufferIn.vertex(matrixPos, x, (float) y, z).color(red, green, blue, alpha).uv(texU, texV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }


    @NotNull
    @Override
    public ResourceLocation getTextureLocation(EntityEGOBeam entityEGOBeam)
    {
        return TEXTURE_BEACON_BEAM;
    }
}
