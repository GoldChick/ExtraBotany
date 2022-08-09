package chick.extrabotany.network.inputMessage;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.bauble.JingweiFeather;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import vazkii.botania.common.handler.EquipmentHandler;

import java.util.function.Supplier;

public class JingweiMessage
{
    public JingweiMessage()
    {
    }

    public void encode(FriendlyByteBuf buf)
    {
    }

    public static JingweiMessage decode(FriendlyByteBuf buf)
    {
        return new JingweiMessage();
    }

    public static void handleJingweiFeather(JingweiMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
        {
            ServerPlayer player = context.getSender();
            var item = (JingweiFeather) EquipmentHandler.findOrEmpty(ModItems.JINGWEI_FEATHER.get(), player).getItem();
            context.enqueueWork(() -> item.onLeftClick(player, null));
        });
        context.setPacketHandled(true);
    }
}
