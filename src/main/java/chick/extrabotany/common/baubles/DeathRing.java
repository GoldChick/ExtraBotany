package chick.extrabotany.common.baubles;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class DeathRing extends ItemBauble implements ManaItemHandler
{

    public DeathRing(Properties props)
    {
        super(props);
    }

    private static final int RANGE = 12;
    private static final int MANA_PER_DAMAGE = 80;

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity)
    {
        super.onWornTick(stack, entity);
        if (entity instanceof Player)
        {
            if (!entity.level.isClientSide)
            {
                for (LivingEntity living : entity.getLevel().getEntitiesOfClass(LivingEntity.class, new AABB(entity.position().add(-RANGE, -RANGE, -RANGE), entity.position().add(RANGE + 1, RANGE + 1, RANGE + 1))))
                {
                    if (living.canBeSeenByAnyone()
                            && living != entity
                            && !living.hasPassenger(entity)
                            && ManaItemHandler.instance().requestManaExactForTool(stack, (Player) entity, MANA_PER_DAMAGE, true)
                            && entity.tickCount % 30 == 0
                )
                    {
                        living.startSeenByPlayer((ServerPlayer) entity);
                        living.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
                        living.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 60, 1));
                        living.hurt(DamageSource.MAGIC, 0.5F);
                        //TODO:不对动物造成伤害（和平友好之证
                    }
                }
            }
        }
    }
}
