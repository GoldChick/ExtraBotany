package chick.extrabotany.common.tools.armors;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.ManaDiscountEvent;
import vazkii.botania.client.gui.TooltipHandler;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.proxy.IProxy;

import java.util.List;

public abstract class ArmorBase extends ArmorItem implements IPhantomInkable, IManaDiscountArmor
{
    private static final String TAG_PHANTOM_INK = "phantomInk";
    private final String armorTexture;

    public ArmorBase(ArmorMaterial material, EquipmentSlot slot, Properties properties, String armorTexture)
    {
        super(material, slot, properties);
        this.armorTexture = armorTexture;
    }

    public boolean hasArmorSuit(LivingEntity player)
    {
        if (player == null)
            return false;
        return hasArmorItem(player, EquipmentSlot.HEAD) && hasArmorItem(player, EquipmentSlot.CHEST) && hasArmorItem(player, EquipmentSlot.LEGS) && hasArmorItem(player, EquipmentSlot.FEET);
    }

    public abstract boolean hasArmorItem(LivingEntity player, EquipmentSlot slot);

    public int getManaPerDamage()
    {
        return 140;
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack tool)
    {
        return getManaDiscount(player);
    }

    public abstract float getManaDiscount(Player player);

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, list, flag);
        TooltipHandler.addOnShift(list, () -> addInformationAfterShift(stack, level, list, flag));
    }

    public void addInformationAfterShift(ItemStack stack, Level level, List<Component> list, TooltipFlag flags)
    {
        Player player = IProxy.INSTANCE.getClientPlayer();
        addArmorSetDescription(stack, list, player);
        if (hasPhantomInk(stack))
        {
            list.add(new TranslatableComponent("botaniamisc.hasPhantomInk").withStyle(ChatFormatting.GRAY));
        }
    }

    public void addArmorSetDescription(ItemStack stack, List<Component> list, Player player)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.mana.desc", (int) (4 * getManaDiscount(player) * 100) + "%").withStyle(getManaDiscount(player) > 0 ? ChatFormatting.AQUA : ChatFormatting.GRAY));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return hasPhantomInk(stack) ? LibResources.MODEL_INVISIBLE_ARMOR : armorTexture;
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
