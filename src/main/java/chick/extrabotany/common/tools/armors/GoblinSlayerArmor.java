package chick.extrabotany.common.tools.armors;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.ModArmorsTier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.client.gui.TooltipHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.proxy.IProxy;

import java.util.List;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class GoblinSlayerArmor extends ArmorBase
{
    public static final String TAG_DAY = "isday";

    public GoblinSlayerArmor(EquipmentSlot slot, Properties properties)
    {
        super(ModArmorsTier.ARMOR_PHOTONIUM, slot, properties, ExtraBotany.MODID + ":textures/model/armor_goblinslayer.png");
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == getSlot() && ItemNBTHelper.getBoolean(stack, TAG_DAY, false))
        {
            ret.put(Attributes.MAX_HEALTH, new AttributeModifier(getBaubleUUID(stack), "Goblin Modifier 1", 7.5F, AttributeModifier.Operation.ADDITION));
            ret.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Goblin Modifier 2", 0.05F, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return ret;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        super.onArmorTick(stack, level, player);
        level.updateSkyBrightness();
        ItemNBTHelper.setBoolean(stack, TAG_DAY, hasArmorSuit(player) && level.isDay());
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.goblinslayer.desc2").withStyle(ChatFormatting.AQUA));
        list.add(new TranslatableComponent("extrabotany.armorset.goblinslayer.desc0").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("extrabotany.armorset.goblinslayer.desc1").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public boolean hasArmorItem(LivingEntity player, EquipmentSlot slot)
    {
        return player.getItemBySlot(slot).getItem() == switch (slot)
                {
                    case HEAD -> ModItems.GOBLINSLAYER_HELM.get();
                    case CHEST -> ModItems.GOBLINSLAYER_CHEST.get();
                    case LEGS -> ModItems.GOBLINSLAYER_LEGS.get();
                    default -> ModItems.GOBLINSLAYER_BOOTS.get();
                };
    }

    @Override
    public float getManaDiscount(Player player)
    {
        return hasArmorSuit(player) ? 0.025F : 0;
    }
}