package chick.extrabotany.common.tools.armors;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.ModArmorsTier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.gui.TooltipHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.proxy.IProxy;

import java.util.List;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class ShadowWarriorArmor extends ArmorBase
{
    public static final String TAG_NIGHT = "isnight";

    public ShadowWarriorArmor(EquipmentSlot slot, Properties properties)
    {
        super(ModArmorsTier.ARMOR_SHADOWIUM, slot, properties, ExtraBotany.MODID + ":textures/model/armor_shadowwarrior.png");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == getSlot() && ItemNBTHelper.getBoolean(stack, TAG_NIGHT, false))
        {
            ret.put(Attributes.MAX_HEALTH, new AttributeModifier(getBaubleUUID(stack), "Shadow Modifier 1", 4, AttributeModifier.Operation.ADDITION));
            ret.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(getBaubleUUID(stack), "Shadow Modifier 2", 0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return ret;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        super.onArmorTick(stack, level, player);
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.SHADOW_WARRIOR_HELM.get())
        {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0));
        }
        level.updateSkyBrightness();
        ItemNBTHelper.setBoolean(stack, TAG_NIGHT, hasArmorSuit(player) && level.isNight());
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.shadowwarrior.desc2").withStyle(ChatFormatting.AQUA));
        list.add(new TranslatableComponent("extrabotany.armorset.shadowwarrior.desc0").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("extrabotany.armorset.shadowwarrior.desc1").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public boolean hasArmorItem(LivingEntity player, EquipmentSlot slot)
    {
        return player.getItemBySlot(slot).getItem() == switch (slot)
                {
                    case HEAD -> ModItems.SHADOW_WARRIOR_HELM.get();
                    case CHEST -> ModItems.SHADOW_WARRIOR_CHEST.get();
                    case LEGS -> ModItems.SHADOW_WARRIOR_LEGS.get();
                    default -> ModItems.SHADOW_WARRIOR_BOOTS.get();
                };
    }

    @Override
    public float getManaDiscount(Player player)
    {
        return hasArmorSuit(player) ? 0.025F : 0;
    }

}
