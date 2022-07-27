package chick.extrabotania.registration.tools.weapons;

import chick.extrabotania.ExtraBotania;
import chick.extrabotania.registration.tools.ModToolsTier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import vazkii.botania.common.item.equipment.ICustomDamageItem;

import java.util.UUID;
import java.util.function.Consumer;

public class ObsidianSword extends SwordItem implements ICustomDamageItem
{

    public ObsidianSword()
    {
        super(ModToolsTier.OBSIDIAN, 114514, 2f, new Item.Properties().tab(ExtraBotania.ITEM_GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = super.getAttributeModifiers(slot, stack);
        if (slot == EquipmentSlot.MAINHAND)
        {
            ret = HashMultimap.create(ret);
            UUID uuid = new UUID(this.hashCode() + slot.toString().hashCode(), 0);
            ret.put(Attributes.JUMP_STRENGTH, new AttributeModifier(uuid, "Maid Modifier", 2, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player)
    {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == this)
        {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 2));
        }
        super.onArmorTick(stack, level, player);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_)
    {

        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }
}
