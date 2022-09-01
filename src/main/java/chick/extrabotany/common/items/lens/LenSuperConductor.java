package chick.extrabotany.common.items.lens;

import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LenSuperConductor extends Lens
{
    @Override
    public void apply(ItemStack stack, BurstProperties props)
    {
        props.maxMana *= 8F;
        props.motionModifier *= 1.5F;
        props.manaLossPerTick *= 16F;
        props.ticksBeforeManaLoss *= 0.8F;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack)
    {
        var entity = burst.entity();
        if (entity.level.isClientSide)
            return;

        AABB axis = new AABB(entity.getX() - 0.5F, entity.getY() - 0.5F, entity.getZ() - 0.5F, entity.xOld + 0.5F, entity.yOld + 0.5F, entity.zOld + 0.5F).inflate(1);
        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        for (LivingEntity living : entities)
        {
            if (!burst.isFake())
            {
                DamageHandler.INSTANCE.dmg(living, null, living instanceof Player ? 25F : 8F, DamageHandler.INSTANCE.MAGIC_PIERCING);
            }
        }
    }
}
