package chick.extrabotany.common.tools.others;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.PixieHandler;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class ElementSteelShield extends ManaSteelShield
{
    public ElementSteelShield(Properties prop)
    {
        super(prop.durability(300));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND)
        {
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, new AttributeModifier(getBaubleUUID(stack), "Shield modifier", 0.25, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

}
