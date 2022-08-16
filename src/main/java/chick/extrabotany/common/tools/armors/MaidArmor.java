package chick.extrabotany.common.tools.armors;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.ModArmorsTier;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.ArrayList;
import java.util.List;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class MaidArmor extends ArmorBase
{
    public List<DamageSource> source = new ArrayList();

    public MaidArmor(EquipmentSlot slot, Properties properties)
    {
        super(ModArmorsTier.MAID, slot, properties, ExtraBotany.MODID + ":textures/model/armor_maid.png");
        MinecraftForge.EVENT_BUS.addListener(this::onAttackingEntity);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
        source.add(DamageSource.ANVIL);
        source.add(DamageSource.CACTUS);
        source.add(DamageSource.DROWN);
        source.add(DamageSource.FALL);
        source.add(DamageSource.FALLING_BLOCK);
        source.add(DamageSource.IN_FIRE);
        source.add(DamageSource.LAVA);
        source.add(DamageSource.ON_FIRE);
        source.add(DamageSource.LIGHTNING_BOLT);
        source.add(DamageSource.FLY_INTO_WALL);
        source.add(DamageSource.HOT_FLOOR);
        source.add(DamageSource.SWEET_BERRY_BUSH);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == getSlot())
        {
            ret.put(Attributes.MAX_HEALTH, new AttributeModifier(getBaubleUUID(stack), "Maid Modifier 1", 5F, AttributeModifier.Operation.ADDITION));
            ret.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(getBaubleUUID(stack), "Maid modifier 2", (float) ModArmorsTier.MAID.getDefenseForSlot(getSlot()) / 20F, AttributeModifier.Operation.ADDITION));
        }
        return ret;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        super.onArmorTick(stack, level, player);
        if (!level.isClientSide && hasArmorSuit(player) && getSlot() == EquipmentSlot.HEAD)
        {
            ManaItemHandler.instance().dispatchManaExact(stack, player, 1, true);
            if (player.getHealth() < player.getMaxHealth() && level.dayTime() % 30 == 0)
                player.heal(1F);
            if (level.dayTime() % 20 == 0)
                ClearPotions(stack, player);
        }
    }

    @SubscribeEvent
    public void onAttackingEntity(LivingHurtEvent event)
    {
        Entity attacker = event.getSource().getDirectEntity();
        LivingEntity target = event.getEntityLiving();
        if (attacker instanceof Player player && hasArmorSuit(player) && getSlot() == EquipmentSlot.HEAD && target != null && target != attacker)
        {
            if (player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() && player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()
                    && ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, 200, true))
                event.setAmount(event.getAmount() + 9F);

            if (player.getHealth() < player.getMaxHealth()
                    && ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, 80, true))
                player.heal(event.getAmount() / 10F);
        }
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list, Player player)
    {
        super.addArmorSetDescription(stack, list, player);
        if (hasArmorSuit(player))
            list.add(new TranslatableComponent("extrabotany.armorset.magic_protection.desc", "25%").withStyle(ChatFormatting.AQUA));
        else
            list.add(new TranslatableComponent("extrabotany.armorset.magic_protection.desc", "0%").withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerAttacked(LivingHurtEvent event)
    {
        Entity target = event.getEntityLiving();
        if (target instanceof Player player)
        {
            if (hasArmorSuit(player) && getSlot() == EquipmentSlot.HEAD)
            {
                if (source.contains(event.getSource()))
                {
                    event.setAmount(0F);
                } else if (event.getSource() == DamageSource.MAGIC)
                {
                    event.setAmount(event.getAmount() * 0.75F);
                }
            }
        }
    }

    public void ClearPotions(ItemStack stack, Player player)
    {
        List<MobEffect> potionsToRemove = player.getActiveEffects()
                .stream()
                .map(MobEffectInstance::getEffect)
                .filter(effect -> !effect.isBeneficial())
                .distinct()
                .toList();

        potionsToRemove.forEach(potion ->
        {
            //if (ManaItemHandler.instance().requestManaExactForTool(stack, player, 100, true))
            {
                player.removeEffect(potion);
                ((ServerLevel) player.level).getChunkSource().broadcastAndSend(player, new ClientboundRemoveMobEffectPacket(player.getId(), potion));
            }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag)
    {
        list.add(new TranslatableComponent("extrabotany.armorset.maid.desc0").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("extrabotany.armorset.maid.desc1").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("extrabotany.armorset.maid.desc2").withStyle(ChatFormatting.GRAY));
        list.add(new TranslatableComponent("extrabotany.armorset.maid.desc3").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public boolean hasArmorItem(LivingEntity player, EquipmentSlot slot)
    {
        return player.getItemBySlot(slot).getItem() == switch (slot)
                {
                    case HEAD -> ModItems.MAID_HELM.get();
                    case CHEST -> ModItems.MAID_CHEST.get();
                    case LEGS -> ModItems.MAID_LEGS.get();
                    default -> ModItems.MAID_BOOTS.get();
                };
    }

    @Override
    public float getManaDiscount(Player player)
    {
        return hasArmorSuit(player) ? 0.075F : 0;
    }
}
