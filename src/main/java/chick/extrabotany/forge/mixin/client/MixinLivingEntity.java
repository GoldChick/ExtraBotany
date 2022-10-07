package chick.extrabotany.forge.mixin.client;

import chick.extrabotany.common.events.EntityEffectEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.item.TinyPotatoRenderEvent;

@Mixin(LivingEntity.class)
public class MixinLivingEntity
{
    @Inject(at = @At("HEAD"), method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
    private void onGetEffect(MobEffectInstance mobEffectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        MinecraftForge.EVENT_BUS.post(new EntityEffectEvent(mobEffectInstance, entity));
    }
}
