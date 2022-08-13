package chick.extrabotany.forge.mixin.client;

import chick.extrabotany.forge.IMixinGetData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightningBolt.class)
public abstract class MixinLightning extends Entity implements IMixinGetData
{
    @Shadow
    private int life;

    public MixinLightning(EntityType<?> p_19870_, Level p_19871_)
    {
        super(p_19870_, p_19871_);
    }

    /**
     * @return lightning life
     */
    @Override
    public int getInt()
    {
        return life;
    }
}
