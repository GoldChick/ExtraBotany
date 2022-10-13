package chick.extrabotany.common.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

/**
 * post when addEffect ends
 */
public class EntityAddEffectReturnEvent extends LivingEvent
{
    public MobEffectInstance mobEffectInstance;

    public EntityAddEffectReturnEvent(MobEffectInstance mobEffectInstance, LivingEntity entity)
    {
        super(entity);
        this.mobEffectInstance = mobEffectInstance;
    }

}
