package chick.extrabotany.forge.client.model.entity;

import chick.extrabotany.forge.client.model.armor.ArmorModels;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import vazkii.botania.client.core.helper.CoreShaders;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.entity.EntityDoppleganger;

import javax.annotation.Nonnull;


public class RenderDoppleganger extends HumanoidMobRenderer<EntityDoppleganger, HumanoidModel<EntityDoppleganger>>
{
    private final Model normalModel;
    private final Model slimModel;
    public RenderDoppleganger(EntityRendererProvider.Context ctx)
    {
        super(ctx, new Model(ctx.bakeLayer(ModelLayers.PLAYER)), 0F);
        this.normalModel = (Model) this.getModel();
        this.slimModel = new Model(ctx.bakeLayer(ModelLayers.PLAYER_SLIM));
        // Call this here bc no other place with access to Context
        ArmorModels.init(ctx);
    }
    @Override
    public void render(@Nonnull EntityDoppleganger dopple, float yaw, float partialTicks, PoseStack ms, MultiBufferSource buffers, int light) {
        int invulTime = dopple.getInvulTime();
        ShaderInstance shader = CoreShaders.doppleganger();
        if (shader != null) {
            float grainIntensity, disfiguration;
            if (invulTime > 0) {
                grainIntensity = invulTime > 20 ? 1F : invulTime * 0.05F;
                disfiguration = grainIntensity * 0.3F;
            } else {
                disfiguration = (0.025F + dopple.hurtTime * ((1F - 0.15F) / 20F)) / 2F;
                grainIntensity = 0.05F + dopple.hurtTime * ((1F - 0.15F) / 10F);
            }
            shader.safeGetUniform("ExtrabotanyGrainIntensity").set(grainIntensity);
            shader.safeGetUniform("ExtrabotanyDisfiguration").set(disfiguration);
        }

        var view = Minecraft.getInstance().getCameraEntity();
        if (view instanceof AbstractClientPlayer && DefaultPlayerSkin.getSkinModelName(view.getUUID()).equals("slim")) {
            this.model = slimModel;
        } else {
            this.model = normalModel;
        }

        super.render(dopple, yaw, partialTicks, ms, buffers, light);
    }
    private static class Model extends HumanoidModel<EntityDoppleganger>
    {
        Model(ModelPart root)
        {
            super(root, RenderHelper::getDopplegangerLayer);
        }
    }

}
