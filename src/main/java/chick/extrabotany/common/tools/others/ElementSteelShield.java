package chick.extrabotany.common.tools.others;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.lib.ModTags;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class ElementSteelShield extends ManaSteelShield
{
    protected final String TAG_CHARGED = "charged";

    public ElementSteelShield(Properties prop)
    {
        super(prop.durability(250));
        MinecraftForge.EVENT_BUS.addListener(this::onShieldBlock);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND)
        {
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, new AttributeModifier(getBaubleUUID(stack), "Shield modifier1", ItemNBTHelper.getBoolean(stack, TAG_CHARGED, false) ? 1F : 0.25F, AttributeModifier.Operation.ADDITION));
        }
        if (slot == EquipmentSlot.MAINHAND)
        {
            ret.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 4D, AttributeModifier.Operation.ADDITION));
            ret.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", ItemNBTHelper.getBoolean(stack, TAG_CHARGED, false) ? -2.4D : -3.2D, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

    public void onShieldBlock(ShieldBlockEvent event)
    {
        ItemNBTHelper.setBoolean(event.getEntityLiving().getUseItem(), TAG_CHARGED, true);
    }

    @Override
    public boolean isValidRepairItem(ItemStack item, ItemStack material)
    {
        return material.is(ModTags.Items.INGOTS_ELEMENTIUM);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity attacker)
    {
        ItemNBTHelper.setBoolean(stack, TAG_CHARGED, false);
        return super.hurtEnemy(stack, entity, attacker);
    }
}
