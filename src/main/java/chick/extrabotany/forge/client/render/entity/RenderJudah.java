package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.entities.judah.EntityJudahOath;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;


public class RenderJudah extends ThrownItemRenderer<EntityJudahOath>
{
    public RenderJudah(EntityRendererProvider.Context ctx)
    {
        super(ctx);
        shadowRadius = 0;
    }

    @Override
    public void render(EntityJudahOath p_116085_, float p_116086_, float p_116087_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_)
    {
        p_116088_.scale(5, 5, 5);
        super.render(p_116085_, p_116086_, p_116087_, p_116088_, p_116089_, p_116090_);
    }
}
