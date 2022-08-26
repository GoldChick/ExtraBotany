package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.projectile.EntityMagicArrow;
import chick.extrabotany.network.DamageHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.bow.ItemLivingwoodBow;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.List;
import java.util.function.Consumer;

public class Failnaught extends ItemLivingwoodBow implements ICustomDamageItem
{
    private static final int MANA_PER_DAMAGE = 320;

    public Failnaught(Properties prop)
    {
        super(prop);
    }

    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID, "challenge/failnaught"));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        boolean infinity = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
        return ToolCommons.damageItemIfPossible(stack, amount, entity, MANA_PER_DAMAGE / ((infinity) ? 2 : 1));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity living, int timeLeft)
    {
        if (living instanceof Player player)
        {
            int i = (int) ((getUseDuration(stack) - timeLeft) * 1F);
            if (i < 8)
                return;
            int rank = (i - 8) / 5;
            if (ManaItemHandler.instance().requestManaExactForTool(stack, player, Math.min(800, 350 + rank * 20), true))
            {
                EntityMagicArrow arrow = new EntityMagicArrow(worldIn, player);
                arrow.setPos(player.getX(), player.getY() + 1.1D, player.getZ());
                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
                arrow.setDamage((int) Math.min(50, DamageHandler.calcDamage(7 + rank * 0.5F, player)));
                arrow.setYRot(player.getYRot());
                int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER_ARROWS, living);
                if (j > 0)
                {
                    arrow.setDamage(arrow.getDamage() + j);
                }
                arrow.setLife(Math.min(150, 5 + i * 4));

                if (!worldIn.isClientSide)
                    worldIn.addFreshEntity(arrow);

                worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.NEUTRAL, 1.0F, 0.5F);
            }
        }
    }

    @Override
    public float chargeVelocityMultiplier()
    {
        return 0.5F;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player)
        {
            var relic = IXplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null)
            {
                relic.tickBinding(player);
            }
            if (stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE * 2, true))
                stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> list, TooltipFlag flags)
    {
        RelicImpl.addDefaultTooltip(stack, list);
    }

    @NotNull
    @Override
    public Rarity getRarity(ItemStack stack)
    {
        return Rarity.EPIC;
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isValidRepairItem(ItemStack bow, ItemStack material)
    {
        return false;
    }

    //TODO: advancement

    //@Override
    //public String getAdvancementName()
    //{
    //  return LibAdvancementNames.EGODEFEAT;
    //}
}
