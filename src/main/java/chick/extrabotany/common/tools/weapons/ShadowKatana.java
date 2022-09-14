package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.function.Consumer;

import static java.lang.Math.PI;


public class ShadowKatana extends SwordItem implements ICustomDamageItem
{
    public static final int MANA_PER_DAMAGE = 2;

    public ShadowKatana(Properties prop)
    {
        super(Tiers.GOLD, -1, 0, prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        var hitResult = (BlockHitResult) player.pick(5, 1, false);
        Vec3 offset;
        switch (hitResult.getDirection())
        {
            case DOWN -> offset = new Vec3(0, -0.5, 0);
            case EAST -> offset = new Vec3(0.35, 0, 0);
            case WEST -> offset = new Vec3(-0.35, 0, 0);
            case SOUTH -> offset = new Vec3(0, 0, 0.35);
            case NORTH -> offset = new Vec3(0, 0, -0.35);
            default -> offset = new Vec3(0, 0, 0);
        }
        player.setPos(hitResult.getLocation().add(offset));
        if (level.isClientSide)
        {
            for (int i = 0; i < 360; i += 10)
            {
                double j = ((double) i) / 180F * PI;
                level.addParticle(ParticleTypes.PORTAL, player.getX() + Math.cos(j), player.getY() + 1D, player.getZ() + Math.sin(j), 0, -0.3D, 0);
            }
        }
        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 1);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        int manaPerDamage = ((ShadowKatana) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true))
        {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    public int getManaPerDamage()
    {
        return MANA_PER_DAMAGE;
    }
}
