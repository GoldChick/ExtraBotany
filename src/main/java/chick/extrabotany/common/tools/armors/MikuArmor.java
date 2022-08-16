package chick.extrabotany.common.tools.armors;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.ModArmorsTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MikuArmor extends ArmorBase
{
    public MikuArmor(EquipmentSlot slot, Properties properties)
    {
        super(ModArmorsTier.MIKU, slot, properties, ExtraBotany.MODID + ":textures/model/armor_miku.png");
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerAttacked(LivingHurtEvent event)
    {
        Entity target = event.getEntityLiving();
        if (target instanceof LivingEntity living)
        {
            if (hasArmorSuit(living) && getSlot() == EquipmentSlot.HEAD)
            {
                if (event.getSource() == DamageSource.MAGIC)
                {
                    event.setAmount(event.getAmount() * 0.25F);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.miku.desc").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list, Player player)
    {
        super.addArmorSetDescription(stack, list, player);
        if (hasArmorSuit(player))
            list.add(new TranslatableComponent("extrabotany.armorset.magic_protection.desc", "75%").withStyle(ChatFormatting.AQUA));
        else
            list.add(new TranslatableComponent("extrabotany.armorset.magic_protection.desc", "0%").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean hasArmorItem(LivingEntity player, EquipmentSlot slot)
    {
        return player.getItemBySlot(slot).getItem() == switch (slot)
                {
                    case HEAD -> ModItems.MIKU_HELM.get();
                    case CHEST -> ModItems.MIKU_CHEST.get();
                    case LEGS -> ModItems.MIKU_LEGS.get();
                    default -> ModItems.MIKU_BOOTS.get();
                };
    }

    @Override
    public int getManaPerDamage()
    {
        return 70;
    }

    @Override
    public float getManaDiscount(Player player)
    {
        return hasArmorSuit(player) ? 0.15F : 0;
    }
}
