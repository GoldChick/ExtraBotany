package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.entities.projectile.EntityProjectileBase;
import chick.extrabotany.common.entities.projectile.EntityTrueTerraBladeProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.core.helper.RenderHelper;

@OnlyIn(Dist.CLIENT)
public class RenderProjectile extends EntityRenderer<EntityProjectileBase>
{
    public RenderProjectile(EntityRendererProvider.Context ctx)
    {
        super(ctx);
    }

    @Override
    public void render(EntityProjectileBase weapon, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
        Minecraft mc = Minecraft.getInstance();

        matrixStackIn.pushPose();
        float s = 1.2F;
        //matrixStackIn.scale(s, s, s);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(weapon.getRotation() + 90F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(weapon.getPitch()));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-45));

        float alpha = 0.9F;
        BakedModel model = weapon.getIcon();
        int color = 0xFFFFFF | ((int) (alpha * 255F)) << 24;

        var item = ModItems.TRUE_SHADOW_KATANA.get();//default
        if (weapon instanceof EntityTrueTerraBladeProjectile)
        {
            item = ModItems.TRUE_TERRA_BLADE.get();
        }
        RenderHelper.renderItemCustomColor(mc.player, new ItemStack(item), color, matrixStackIn, bufferIn, 0xF000F0, OverlayTexture.NO_OVERLAY, model);

        //matrixStackIn.scale(1 / s, 1 / s, 1 / s);

        matrixStackIn.popPose();
        super.render(weapon, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityProjectileBase entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
