package chick.extrabotany.common.effects;

import chick.extrabotany.common.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class Vegetable extends MobEffect
{
    public Vegetable()
    {
        super(MobEffectCategory.BENEFICIAL, 0X00FA9A);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event)
    {
        if (!(event.getEntityLiving() instanceof Player player))
            return;
        if (event.getSource().getDirectEntity() == null || !(event.getSource().getDirectEntity() instanceof Player attacker))
            return;
        // boolean p = PersistentVariableHandler.contributors.contains(player.getGameProfile().getName()) && BaublesApi.isBaubleEquipped(player, ModItems.mask) == -1;
        //boolean a = PersistentVariableHandler.contributors.contains(attacker.getGameProfile().getName()) && BaublesApi.isBaubleEquipped(attacker, ModItems.mask) == -1;
        //if (p && !a)
        //   ExtraBotanyAPI.addPotionEffect(attacker, ModEffects.VEGETABLE.get(), 200, 10, false);
        //if (a && !p)
        //   ExtraBotanyAPI.addPotionEffect(player, ModEffects.VEGETABLE.get(), 200, 10, false);
        //if (!a && !p)
        {
            if (attacker.hasEffect(ModEffects.VEGETABLE.get()))
            {
                int attackerAmp = attacker.getEffect(ModEffects.VEGETABLE.get()).getAmplifier();
                int playerAmp = player.hasEffect(ModEffects.VEGETABLE.get()) ? player.getEffect(ModEffects.VEGETABLE.get()).getAmplifier() : 0;
                int hatsToGive = Math.min(10 - playerAmp, attackerAmp);
                player.addEffect(new MobEffectInstance(ModEffects.VEGETABLE.get(), 200, playerAmp + hatsToGive));
                attacker.removeEffect(ModEffects.VEGETABLE.get());
                if (attackerAmp > hatsToGive)
                {
                    player.addEffect(new MobEffectInstance(ModEffects.VEGETABLE.get(), 200, attackerAmp - hatsToGive));
                }
            }
        }
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return Lists.newArrayList();
    }
}
