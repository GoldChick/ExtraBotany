package chick.extrabotany.common.base;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.advancement.IAdvancementRequirement;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import top.theillusivec4.curios.api.CuriosApi;
import vazkii.botania.common.helper.PlayerHelper;

import java.util.Map;

@Mod.EventBusSubscriber
public final class AdvancementHandler
{
    public static final AdvancementHandler INSTANCE = new AdvancementHandler();

    public void grantAdvancement(ServerPlayer player, String id)
    {
        PlayerHelper.grantCriterion(player, new ResourceLocation(ExtraBotany.MODID, "main/" + id), "code_triggered");
    }

    public static boolean checkAdvancement(Player player, String modid, String advancement)
    {
        ResourceLocation id = ResourceLocation.tryParse(modid + ":main/" + advancement);
        if (id != null)
        {
            if (player instanceof ServerPlayer)
            {
                ServerAdvancementManager manager = player.getServer().getAdvancements();
                PlayerAdvancements advancements = ((ServerPlayer) player).getAdvancements();
                Advancement adv = manager.getAdvancement(id);
                if (adv != null)
                {
                    return advancements.getOrStartProgress(adv).isDone();
                }
            }
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public static Advancement getSideAdvancement(String modid, String advancement)
    {
        ResourceLocation id = ResourceLocation.tryParse(modid + ":main/" + advancement);
        if (id != null)
        {
            var netHandler = Minecraft.getInstance().player.connection;
            var manager = netHandler.getAdvancements();
            return manager.getAdvancements().get(id);
        }
        return null;
    }

    public static boolean hasDone(String modid, String advancement)
    {
        ResourceLocation id = ResourceLocation.tryParse(modid + ":main/" + advancement);
        if (id != null)
        {
            var conn = Minecraft.getInstance().getConnection();
            if (conn != null)
            {
                var cm = conn.getAdvancements();
                Advancement adv = cm.getAdvancements().get(id);
                if (adv != null)
                {
                    Map<Advancement, AdvancementProgress> progressMap =
                            ObfuscationReflectionHelper.getPrivateValue(ClientAdvancements.class, cm,
                                    "progress");
                    AdvancementProgress progress = progressMap.get(adv);
                    return progress != null && progress.isDone();
                }
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.isCancelable() && !event.getPlayer().isCreative())
        {
            if (event.getItemStack().getItem() instanceof IAdvancementRequirement r)
            {
                if (!checkAdvancement(event.getPlayer(), ExtraBotany.MODID, r.getAdvancementName()))
                    event.setCanceled(true);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltip(ItemTooltipEvent event)
    {
        if (event.getItemStack().getItem() instanceof IAdvancementRequirement r)
        {
            var playerSP = Minecraft.getInstance().player;
            if (playerSP != null)
            {
                if (!hasDone(ExtraBotany.MODID, r.getAdvancementName()))
                {
                    event.getToolTip().add(1,new TranslatableComponent("extrabotanymisc.description", new TranslatableComponent("advancement.extrabotany:" + r.getAdvancementName() + ".title").withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntity() instanceof Player && !event.getEntityLiving().level.isClientSide)
        {
            final Player player = (Player) event.getEntityLiving();
            if (player.isCreative())
            {
                return;
            }

            CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent((c) ->
            {
                for (int i = 0; i < c.getSlots(); i++)
                {
                    final ItemStack stack = c.getStackInSlot(i);
                    if (stack.getItem() instanceof IAdvancementRequirement r)
                    {
                        if (!checkAdvancement(player, ExtraBotany.MODID, r.getAdvancementName()))
                        {
                            c.setStackInSlot(i, ItemStack.EMPTY);
                            player.drop(stack, false);
                        }
                    }
                }
            });

            for (final EquipmentSlot slot : EquipmentSlot.values())
            {
                final ItemStack stack = player.getItemBySlot(slot);
                if (stack.getItem() instanceof IAdvancementRequirement r)
                {
                    if (!checkAdvancement(player, ExtraBotany.MODID, r.getAdvancementName()))
                    {
                        player.setItemSlot(slot, ItemStack.EMPTY);
                        player.drop(stack, false);
                    }
                }
            }
        }
    }
}

