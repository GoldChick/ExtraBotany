package chick.extrabotania.registration.tools;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.ModItems;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ObsidianArmor extends ArmorItem
{
    public ObsidianArmor(EquipmentSlot type)
    {
        super(ModArmorsTier.ARMOR_OBSIDIAN, type, new Properties().tab(ExtraBotania.ITEM_GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = super.getAttributeModifiers(slot, stack);
        UUID uuid = new UUID(this.hashCode() + slot.toString().hashCode(), 0);
        if (slot == getSlot())
        {
            ret = HashMultimap.create(ret);
            ret.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Maid Modifier", 2, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.OBSIDIAN_HELMET.get())
        {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 2));
        }
        super.onArmorTick(stack, level, player);
    }
}
