package chick.extrabotany.common.baubles;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class PowerGlove extends ItemBauble
{
    public PowerGlove(Properties props)
    {
        super(props);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(getBaubleUUID(stack), "Power Glove", 0.12F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }


    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }
}
