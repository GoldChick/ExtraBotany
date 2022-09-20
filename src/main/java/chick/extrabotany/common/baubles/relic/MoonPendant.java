package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.api.advancement.IAdvancementRequirement;
import chick.extrabotany.common.libs.LibAdvancementNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemItemFinder;
import vazkii.botania.common.item.equipment.bauble.ItemSuperLavaPendant;
import vazkii.botania.common.item.relic.ItemRelicBauble;


public class MoonPendant extends ItemRelicBauble implements IAdvancementRequirement
{
    public MoonPendant(Item.Properties props)
    {
        super(props);
        MinecraftForge.EVENT_BUS.addListener(this::onDamage);
    }

    private void onDamage(LivingAttackEvent evt)
    {
        if (evt.getSource().isFire() && !EquipmentHandler.findOrEmpty(this, evt.getEntityLiving()).isEmpty())
        {
            evt.setCanceled(true);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity player)
    {
        super.onWornTick(stack, player);
        ((ItemSuperLavaPendant) ModItems.superLavaPendant).onWornTick(stack, player);
        ((ItemItemFinder) ModItems.itemFinder).onWornTick(stack, player);

        if (!player.level.isClientSide && !player.isShiftKeyDown())
        {
            boolean lastOnGround = player.isOnGround();
            player.setOnGround(true);
            FrostWalkerEnchantment.onEntityMoved(player, player.level, player.blockPosition(), 8);
            player.setOnGround(lastOnGround);
        } else if (player.level.isClientSide && !player.isShiftKeyDown())
        {
            if (player.level.random.nextFloat() >= 0.25F)
            {
                player.level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SNOW_BLOCK.defaultBlockState()),
                        player.getX() + player.level.random.nextFloat() * 0.6 - 0.3, player.getY() + 1.1, player.getZ() + player.level.random.nextFloat() * 0.6 - 0.3,
                        0, -0.15, 0);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        attributes.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(getBaubleUUID(stack), "Moon Pendant", 1, AttributeModifier.Operation.ADDITION));
        return attributes;
    }


    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
