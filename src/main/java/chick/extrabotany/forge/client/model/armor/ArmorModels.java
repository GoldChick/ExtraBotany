package chick.extrabotany.forge.client.model.armor;

import chick.extrabotany.common.tools.armors.ItemShadowWarriorArmor;
import chick.extrabotany.forge.client.model.ModModelLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ArmorModels
{
    private static Map<EquipmentSlot, ModelArmor> shadow_warrior = Collections.emptyMap();
    private static Map<EquipmentSlot, ModelArmor> make(EntityRendererProvider.Context ctx, ModelLayerLocation inner, ModelLayerLocation outer)
    {
        Map<EquipmentSlot, ModelArmor> ret = new EnumMap<>(EquipmentSlot.class);
        for (var slot : EquipmentSlot.values())
        {
            var mesh = ctx.bakeLayer(slot == EquipmentSlot.LEGS ? inner : outer);
            ret.put(slot, new ModelArmor(mesh, slot));
        }
        return ret;
    }

    public static void init(EntityRendererProvider.Context ctx)
    {
        shadow_warrior = make(ctx, ModModelLayers.SHADOW_INNER_ARMOR, ModModelLayers.SHADOW_OUTER_ARMOR);
    }

    @Nullable
    public static ModelArmor get(ItemStack stack)
    {
        Item item = stack.getItem();
        if (item instanceof ItemShadowWarriorArmor armor)
        {
            return shadow_warrior.get(armor.getSlot());
        }
        return null;
    }
}
