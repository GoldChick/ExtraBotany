package chick.extrabotany.common.baubles;

import chick.extrabotany.common.ModItems;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class SupremeAeroStone extends ItemBauble
{
    public SupremeAeroStone(Properties props)
    {
        super(props);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Aero Stone 1", 0.75F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.FLYING_SPEED, new AttributeModifier(getBaubleUUID(stack), "Aero Stone 2", 0.75F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(getBaubleUUID(stack), "Aero Stone 3", -8F, AttributeModifier.Operation.ADDITION));
        return attributes;
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.AERO_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.SUPREME_EARTH_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.EARTH_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.FOUR_IN_ONE_STONE.get(), entity).isEmpty()
                ;
    }


}