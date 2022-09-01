package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.entities.projectile.relic_projectile.EntityPhantomSword;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.client.core.helper.RenderHelper;

public class RenderPhantomSword extends EntityRenderer<EntityPhantomSword>
{
    public RenderPhantomSword(EntityRendererProvider.Context renderManager)
    {
        super(renderManager);
    }

    @Override
    public void render(EntityPhantomSword weapon, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
        Minecraft mc = Minecraft.getInstance();
        if (weapon.getDelay() > 0)
            return;

        matrixStackIn.pushPose();
        float s = 1.5F;
        matrixStackIn.scale(s, s, s);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(weapon.getRotation() + 90F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(weapon.getPitch()));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-45));

        float alpha = weapon.getFake() ? Math.max(0F, 0.75F - weapon.tickCount * (0.75F / 30F) * 1.5F) : 1F;
        var model = MiscellaneousIcons.INSTANCE.firstFractalWeaponModels[weapon.getVariety()];
        int color = 0xFFFFFF | ((int) (alpha * 255F)) << 24;
        RenderHelper.renderItemCustomColor(mc.player, new ItemStack(ModItems.FIRST_FRACTAL.get()), color, matrixStackIn, bufferIn, 0xF000F0, OverlayTexture.NO_OVERLAY, model);

        matrixStackIn.scale(1 / s, 1 / s, 1 / s);
        matrixStackIn.popPose();

        super.render(weapon, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(EntityPhantomSword entity)
    {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
