package chick.extrabotany.common.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;

public class EntityEffectEvent extends Event
{
    public MobEffectInstance mobEffectInstance;
    public Entity entity;

    public EntityEffectEvent(MobEffectInstance mobEffectInstance, Entity entity)
    {
        this.mobEffectInstance = mobEffectInstance;
        this.entity = entity;
    }
}
