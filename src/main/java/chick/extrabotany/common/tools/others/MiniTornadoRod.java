package chick.extrabotany.common.tools.others;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.handler.ModSounds;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.rod.ItemTornadoRod;

import javax.annotation.Nonnull;

public class MiniTornadoRod extends ItemTornadoRod
{
    public MiniTornadoRod(Properties props)
    {
        super(props);
    }

    protected static final int MAX_COUNTER = 10;

    protected static final String TAG_FLYING = "flying";
    protected static final String TAG_FLYCOUNTER = "flyCounter";

    protected static final int COST = 60;

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (getFlyCounter(stack) == 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, COST, false))
        {
            ManaItemHandler.instance().requestManaExactForTool(stack, player, COST, true);
            setFlying(stack, true);

            player.fallDistance = 0F;
            double my = ManaItemHandler.instance().hasProficiency(player, stack) ? 2 : 1;

            Vec3 oldMot = player.getDeltaMovement();

            if (player.isFallFlying())
            {
                Vec3 lookDir = player.getLookAngle();
                player.setDeltaMovement(new Vec3(lookDir.x() * my, lookDir.y() * my, lookDir.z() * my));
            } else
            {
                player.setDeltaMovement(new Vec3(oldMot.x(), my, oldMot.z()));
            }

            player.playSound(ModSounds.airRod, 1F, 1F);
            for (int i = 0; i < 5; i++)
            {
                WispParticleData data = WispParticleData.wisp(0.35F + (float) Math.random() * 0.1F, 0.25F, 0.25F, 0.25F);
                world.addParticle(data, player.getX(), player.getY(), player.getZ(),
                        0.2F * (float) (Math.random() - 0.5),
                        -0.01F * (float) Math.random(),
                        0.2F * (float) (Math.random() - 0.5));
            }

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity ent, int slot, boolean active)
    {
        if (ent instanceof Player player)
        {
            boolean damaged = getFlyCounter(stack) > 0;

            if (damaged && !isFlying(stack))
            {
                setFlyCounter(stack, getFlyCounter(stack) - 1);
            }
            if (isFlying(stack))
            {
                setFlyCounter(stack, getFlyCounter(stack) + 1);
                if (getFlyCounter(stack) == MAX_COUNTER)
                {
                    setFlying(stack, false);
                }
            }

            if (damaged)
            {
                player.fallDistance = 0;
            }
        }
    }

    @Override
    public boolean isBarVisible(@Nonnull ItemStack stack)
    {
        return getFlyCounter(stack) > 0;
    }

    @Override
    public int getBarWidth(@Nonnull ItemStack stack)
    {
        float frac = 1 - (getFlyCounter(stack) / (float) MAX_COUNTER);
        return Math.round(13 * frac);
    }

    @Override
    public int getBarColor(@Nonnull ItemStack stack)
    {
        float frac = 1 - (getFlyCounter(stack) / (float) MAX_COUNTER);
        return Mth.hsvToRgb(frac / 3.0F, 1.0F, 1.0F);
    }

    protected void setFlying(ItemStack stack, boolean flying)
    {
        ItemNBTHelper.setBoolean(stack, TAG_FLYING, flying);
    }

    protected int getFlyCounter(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt(TAG_FLYCOUNTER);
    }

    protected void setFlyCounter(ItemStack stack, int counter)
    {
        stack.getOrCreateTag().putInt(TAG_FLYCOUNTER, counter);
    }
}
