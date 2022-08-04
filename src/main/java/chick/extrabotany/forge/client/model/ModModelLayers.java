/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package chick.extrabotany.forge.client.model;

import chick.extrabotany.ExtraBotany;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers
{
    public static final ModelLayerLocation AVATAR = make("avatar");
    public static final ModelLayerLocation SHADOW_INNER_ARMOR = make("shadowwarrior_armor", "inner_armor");
    public static final ModelLayerLocation SHADOW_OUTER_ARMOR = make("shadowwarrior_armor", "outer_armor");

    private static ModelLayerLocation make(String name)
    {
        return make(name, "main");
    }

    private static ModelLayerLocation make(String name, String layer)
    {
        // Don't add to vanilla's ModelLayers. It seems to only be used for error checking
        // And would be annoying to do under Forge's parallel mod loading
        return new ModelLayerLocation(prefix(name), layer);
    }

    public static ResourceLocation prefix(String path)
    {
        return new ResourceLocation(ExtraBotany.MODID, path);
    }

}
