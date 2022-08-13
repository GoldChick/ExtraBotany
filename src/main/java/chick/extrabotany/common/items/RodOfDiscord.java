package chick.extrabotany.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.math.Vector3d;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.UUID;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class RodOfDiscord extends Item
{
    private static final int MANA_PER_DAMAGE = 2000;

    public RodOfDiscord(Properties properties)
    {
        super(properties.durability(81));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND)
        {
            attributes.put(IXplatAbstractions.INSTANCE.getReachDistanceAttribute(), new AttributeModifier(getBaubleUUID(stack), "Rod of Discord", 30, AttributeModifier.Operation.ADDITION));
        }
        return attributes;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity living, InteractionHand hand)
    {
        var level = player.getLevel();
        if (ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true))
        {
            Vec3 end = living.position();
            player.setPos(end.x, end.y + 1, end.z);
            if (!level.isClientSide)
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 3F);
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            if (stack.getDamageValue() > 0)
            {
                float health = Math.max(1F, player.getHealth() - player.getMaxHealth() / 6F);
                player.setHealth(health);
            } else
                player.getMainHandItem().hurtAndBreak(stack.getMaxDamage() - 1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            //TODO:传送到生物的时候似乎未生效
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context)
    {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        //false means NO FLUID
        BlockHitResult rtr = (BlockHitResult) player.pick(64F, 1, false);
        if (rtr.getType() != BlockHitResult.Type.MISS && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true))
        {
            Vec3 end = rtr.getLocation();
            player.setPos(end.x, end.y + 1, end.z);
            if (!level.isClientSide)
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 3F);
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            if (stack.getDamageValue() > 0)
            {
                float health = Math.max(1F, player.getHealth() - player.getMaxHealth() / 6F);
                player.setHealth(health);
            }
            stack.setDamageValue(stack.getMaxDamage() - 1);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!level.isClientSide && stack.getDamageValue() > 0)
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }
}
