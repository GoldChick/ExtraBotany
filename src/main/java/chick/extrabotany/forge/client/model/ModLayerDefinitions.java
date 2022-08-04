/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package chick.extrabotany.forge.client.model;

import chick.extrabotany.forge.client.model.armor.ModelShadowWarriorArmor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModLayerDefinitions
{
    public static void init(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer)
    {
        //consumer.accept(ModModelLayers.HOURGLASS, () -> LayerDefinition.create(ModelHourglass.createMesh(), 64, 32));

        consumer.accept(ModModelLayers.SHADOW_INNER_ARMOR, () -> LayerDefinition.create(ModelShadowWarriorArmor.createInsideMesh(), 64, 128));
        consumer.accept(ModModelLayers.SHADOW_OUTER_ARMOR, () -> LayerDefinition.create(ModelShadowWarriorArmor.createOutsideMesh(), 64, 128));
    }
}
