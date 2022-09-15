package chick.extrabotany.forge.client.model;

import chick.extrabotany.forge.client.model.armor.*;
import chick.extrabotany.forge.client.model.entity.ModelJudahSpear;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LayerDefinitions
{
    public static void init(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer)
    {
        //consumer.accept(ModModelLayers.HOURGLASS, () -> LayerDefinition.create(ModelHourglass.createMesh(), 64, 32));

        consumer.accept(ModelLayers.SHADOW_INNER_ARMOR, () -> LayerDefinition.create(ModelShadowArmor.createInsideMesh(), 128, 128));
        consumer.accept(ModelLayers.SHADOW_OUTER_ARMOR, () -> LayerDefinition.create(ModelShadowArmor.createOutsideMesh(), 128, 128));
        consumer.accept(ModelLayers.GOBLIN_SLAYER_INNER, () -> LayerDefinition.create(ModelGoblinArmor.createInsideMesh(), 128, 128));
        consumer.accept(ModelLayers.GOBLIN_SLAYER_OUTER, () -> LayerDefinition.create(ModelGoblinArmor.createOutsideMesh(), 128, 128));
        consumer.accept(ModelLayers.MIKU_INNER, () -> LayerDefinition.create(ModelMikuArmor.createInsideMesh(), 128, 128));
        consumer.accept(ModelLayers.MIKU_OUTER, () -> LayerDefinition.create(ModelMikuArmor.createOutsideMesh(), 128, 128));
        consumer.accept(ModelLayers.MAID_INNER, () -> LayerDefinition.create(ModelMaidArmor.createInsideMesh(), 128, 128));
        consumer.accept(ModelLayers.MAID_OUTER, () -> LayerDefinition.create(ModelMaidArmor.createOutsideMesh(), 128, 128));

        consumer.accept(ModelLayers.JUDAH_SPEAR, ModelJudahSpear::createBodyLayer);
    }
}
