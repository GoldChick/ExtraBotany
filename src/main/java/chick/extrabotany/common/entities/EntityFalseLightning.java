package chick.extrabotany.common.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class EntityFalseLightning extends LightningBolt
{
    //false lightning for Reikar Orchid
    //no damage
    public EntityFalseLightning(EntityType<? extends LightningBolt> type, Level level)
    {
        super(type, level);
        setVisualOnly(true);
    }

}
