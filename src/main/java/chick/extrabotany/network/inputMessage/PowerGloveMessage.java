package chick.extrabotany.network.inputMessage;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PowerGloveMessage
{
    private static int entity;

    public PowerGloveMessage(int e)
    {
        entity = e;
    }

    public void encode(FriendlyByteBuf buf)
    {
    }

    public static PowerGloveMessage decode(FriendlyByteBuf buf)
    {
        return new PowerGloveMessage(entity);
    }

    public static void handlePowerGLove(PowerGloveMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
        {
            ServerPlayer player = context.getSender();
            if (player != null)
            {
                var a = player.level.getEntity(entity);
                if (a != null)
                    player.attack(a);
            }
        });
        context.setPacketHandled(true);
    }
}