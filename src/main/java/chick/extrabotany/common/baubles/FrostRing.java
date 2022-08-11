package chick.extrabotany.common.baubles;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class FrostRing extends ItemBauble
{
    //TODO:霜冻之星-和平友好之证
    public FrostRing(Properties props)
    {
        super(props.stacksTo(1));
    }

    private static final int RANGE = 6;
    private static final int MANA_PER_DAMAGE = 30;

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity)
    {
        super.onWornTick(stack, entity);
        if (entity instanceof Player)
        {
            if (!entity.level.isClientSide)
            {
                boolean lastOnGround = entity.isOnGround();
                entity.setOnGround(true);
                FrostWalkerEnchantment.onEntityMoved(entity, entity.level, entity.blockPosition(), 8);
                entity.setOnGround(lastOnGround);
                for (LivingEntity living : entity.getLevel().getEntitiesOfClass(LivingEntity.class, new AABB(entity.position().add(-RANGE, -RANGE, -RANGE), entity.position().add(RANGE + 1, RANGE + 1, RANGE + 1))))
                {
                    if (living.canBeSeenByAnyone()
                            && living != entity
                            //  && DamageHandler.INSTANCE.checkPassable(living, entity)
                            && entity.tickCount % 20 == 0
                            && ManaItemHandler.instance().requestManaExactForTool(stack, (Player) entity, MANA_PER_DAMAGE, true))
                    {
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
                    }
                }
            }
        }
    }
}
