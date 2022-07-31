package chick.extrabotany.common;

import chick.extrabotany.common.tools.weapons.ShadowKatana;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static top.theillusivec4.curios.api.SlotTypeMessage.REGISTER_TYPE;

public class Registration
{
    public static void initRegistration()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.GetBlocks().register(bus);
        ModBlocks.GetItems().register(bus);

        ModItems.GetItems().register(bus);

        initEvents();
    }

    private static void initEvents()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener((PlayerInteractEvent.RightClickItem event) -> ShadowKatana.attackEntity(event.getPlayer(), event.getHand(), event.getItemStack()));
    }
}
