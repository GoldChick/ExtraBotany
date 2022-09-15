package chick.extrabotany.forge.client.render.entity;

import chick.extrabotany.common.entities.judah.EntityJudahSpear;
import chick.extrabotany.forge.client.LibResourse;
import chick.extrabotany.forge.client.model.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static chick.extrabotany.ExtraBotany.prefixRl;

public class RenderJudahSpear extends EntityRenderer<EntityJudahSpear>
{
    public RenderJudahSpear(EntityRendererProvider.Context ctx)
    {
        super(ctx);
        ctx.bakeLayer(ModelLayers.JUDAH_SPEAR);
        shadowRadius = 0;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityJudahSpear entity)
    {
        return prefixRl("textures/model/" + LibResourse.JUDAH_SPEAR_MODEL + ".png");
    }
}
