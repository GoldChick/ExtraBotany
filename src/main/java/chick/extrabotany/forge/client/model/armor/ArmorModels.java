package chick.extrabotany.forge.client.model.armor;

import chick.extrabotany.common.tools.armors.GoblinSlayerArmor;
import chick.extrabotany.common.tools.armors.MaidArmor;
import chick.extrabotany.common.tools.armors.MikuArmor;
import chick.extrabotany.common.tools.armors.ShadowWarriorArmor;
import chick.extrabotany.forge.client.model.ModelLayers;
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
    private static Map<EquipmentSlot, ModelArmor> goblin_slayer = Collections.emptyMap();
    private static Map<EquipmentSlot, ModelArmor> miku = Collections.emptyMap();
    private static Map<EquipmentSlot, ModelArmor> maid = Collections.emptyMap();

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
        shadow_warrior = make(ctx, ModelLayers.SHADOW_INNER_ARMOR, ModelLayers.SHADOW_OUTER_ARMOR);
        goblin_slayer = make(ctx, ModelLayers.GOBLIN_SLAYER_INNER, ModelLayers.GOBLIN_SLAYER_OUTER);
        miku = make(ctx, ModelLayers.MIKU_INNER, ModelLayers.MIKU_OUTER);
        maid = make(ctx, ModelLayers.MAID_INNER, ModelLayers.MAID_OUTER);
    }

    @Nullable
    public static ModelArmor get(ItemStack stack)
    {
        Item item = stack.getItem();
        if (item instanceof GoblinSlayerArmor armor)
        {
            return goblin_slayer.get(armor.getSlot());
        } else if (item instanceof ShadowWarriorArmor armor)
        {
            return shadow_warrior.get(armor.getSlot());
        } else if (item instanceof MikuArmor armor)
        {
            return miku.get(armor.getSlot());
        } else if (item instanceof MaidArmor armor)
        {
            return maid.get(armor.getSlot());
        }
        return null;
    }
}
