package chick.extrabotany.common.tools.weapons.ranged;

import chick.extrabotany.api.item.IItemWithLeftClick;
import chick.extrabotany.network.NetworkHandler;
import chick.extrabotany.network.inputMessage.LeftClickMessage;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.function.Consumer;

import static chick.extrabotany.common.tools.weapons.ranged.ManaShortArrow.shootArrow;

public class LivingWoodShortBow extends BowItem implements IItemWithLeftClick
{
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public LivingWoodShortBow(Properties prop)
    {
        super(prop.durability(128));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.2D, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        MinecraftForge.EVENT_BUS.addListener(this::leftClicks);
    }

    public void leftClicks(PlayerInteractEvent event)
    {
        if (event instanceof PlayerInteractEvent.LeftClickEmpty || event instanceof PlayerInteractEvent.LeftClickBlock)
        {
            if (event.getPlayer().level.isClientSide && !event.getItemStack().isEmpty() && event.getItemStack().getItem() == this)
            {
                NetworkHandler.INSTANCE.sendToServer(new LeftClickMessage(LeftClickMessage.LeftClickType.ShortBow));
            }
        }
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }

    public int getManaPerDamage()
    {
        return 40;
    }

    @Override
    public void onLeftClick(Player player, @Nullable Entity target)
    {
        if (!player.level.isClientSide
                && !player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
                && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == this
                && player.getAttackStrengthScale(0) >= 0.25F
                && ManaItemHandler.instance().requestManaExactForTool(player.getItemBySlot(EquipmentSlot.MAINHAND), player, getManaPerDamage(), true)
        )
        {
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            ItemStack projectileStack = player.getProjectile(stack);

            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;

            if (!projectileStack.isEmpty() || flag)
            {
                if (projectileStack.isEmpty())
                {
                    projectileStack = new ItemStack(Items.ARROW);
                }
                int useTicks = Math.round(player.getAttackStrengthScale(0) * 20);

                shootArrow(player, projectileStack, useTicks, 0.5D, 1);

                boolean flag1 = player.getAbilities().instabuild || (projectileStack.getItem() instanceof ArrowItem && ((ArrowItem) projectileStack.getItem()).isInfinite(projectileStack, stack, player));
                if (!flag1)
                {
                    projectileStack.shrink(1);
                    if (projectileStack.isEmpty())
                    {
                        player.getInventory().removeItem(projectileStack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        int manaPerDamage = ((LivingWoodShortBow) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage * 2);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

}
