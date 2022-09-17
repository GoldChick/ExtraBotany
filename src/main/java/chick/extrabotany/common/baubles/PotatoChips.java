package chick.extrabotany.common.baubles;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class PotatoChips extends ItemBauble
{
    public static final int MANA_PER_DAMAGE = 1919810;

    public PotatoChips(Properties props)
    {
        super(props);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerDeath);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerDeath(LivingDeathEvent event)
    {
        if (!event.isCanceled() && event.getEntityLiving() instanceof Player player)
        {
            if (!EquipmentHandler.findOrEmpty(this, player).isEmpty()
                    && !player.getCooldowns().isOnCooldown(this)
                    && ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, MANA_PER_DAMAGE, true))
            {
                player.level.playSound(player, player.blockPosition(), SoundEvents.TOTEM_USE, player.getSoundSource(), 1.0F, 1.0F);
                player.level.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, player.getSoundSource(), 1.0F, 1.0F, false);

                event.setCanceled(true);
                player.setHealth(10.0F);
                player.invulnerableTime = 3 * 20;
                player.removeAllEffects();
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                int ticks = 60 * 20;
                Minecraft.getInstance().particleEngine.createTrackingEmitter(player, ParticleTypes.TOTEM_OF_UNDYING, 30);

                //TODO: IDK WHY THIS IS NON-BOSS!
                if (event.getSource().getEntity() != null && !event.getSource().getEntity().canChangeDimensions())
                {
                    ticks = 120 * 20;
                }
                player.getCooldowns().addCooldown(this, ticks);
                if (player instanceof ServerPlayer)
                {
                    //  NetworkHandler.sendTo((ServerPlayer) player, new PotatoChipsPack());
                }
            }
        }
    }


    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }

    /*
        @Override
        @OnlyIn(Dist.CLIENT)
        public void doRender(BipedModel<?> bipedModel, ItemStack stack, LivingEntity player, MatrixStack ms, IRenderTypeBuffer buffers, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
        {
            ms.push();
            bipedModel.bipedHead.translateRotate(ms);
            ms.translate(0, -0.3, -0.3);
            ms.scale(0.6F, -0.6F, -0.6F);
            renderItem(stack, ms, buffers, light);
            ms.pop();
        }



    public static void renderItem(ItemStack stack, MatrixStack ms, IRenderTypeBuffer buffers, int light)
    {
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.NONE, light, OverlayTexture.NO_OVERLAY, ms, buffers);
    }
*/
}
