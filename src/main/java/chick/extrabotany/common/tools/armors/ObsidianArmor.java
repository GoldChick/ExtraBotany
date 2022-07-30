package chick.extrabotany.common.tools.armors;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.ModArmorsTier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.client.gui.TooltipHandler;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.proxy.IProxy;

import java.util.List;
import java.util.UUID;

public class ObsidianArmor extends ArmorItem implements IPhantomInkable
{
    private static final String TAG_PHANTOM_INK = "phantomInk";

    public ObsidianArmor(EquipmentSlot type)
    {
        super(ModArmorsTier.ARMOR_OBSIDIAN, type, new Properties().tab(ExtraBotany.ITEM_GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = super.getAttributeModifiers(slot, stack);
        if (slot == getSlot())
        {
            ret = HashMultimap.create(ret);
            UUID uuid = new UUID(this.hashCode() + slot.toString().hashCode(), 0);
            ret.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Maid Modifier", 2, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        //if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.OBSIDIAN_HELMET.get())
        {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 2));
        }
        super.onArmorTick(stack, level, player);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return hasPhantomInk(stack) ? LibResources.MODEL_INVISIBLE_ARMOR : super.getArmorTexture(stack, entity, slot, type);
        // "extrabotany:textures/models/armor/obsidian_layer.png";
        // it seems that botania uses a different way to make armor texture?
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flags)
    {
        TooltipHandler.addOnShift(list, () -> addInformationAfterShift(stack, world, list, flags));
    }

    public void addInformationAfterShift(ItemStack stack, Level world, List<Component> list, TooltipFlag flags)
    {
        Player player = IProxy.INSTANCE.getClientPlayer();
        addArmorSetDescription(stack, list);
        if (hasPhantomInk(stack))
        {
            list.add(new TranslatableComponent("botaniamisc.hasPhantomInk").withStyle(ChatFormatting.GRAY));
        }
    }

    public void addArmorSetDescription(ItemStack stack, List<Component> list)
    {
        list.add(new TranslatableComponent("botania.armorset.manasteel.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean hasPhantomInk(ItemStack stack)
    {
        return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
    }

    @Override
    public void setPhantomInk(ItemStack stack, boolean ink)
    {
        ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, ink);
    }
}
