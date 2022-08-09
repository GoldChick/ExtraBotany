package chick.extrabotany.network.inputMessage;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PowerGloveMessage
{
    private static int entity;

    public PowerGloveMessage(int entity)
    {
        this.entity = entity;
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
            player.attack(context.getSender().level.getEntity(entity));
        });
        context.setPacketHandled(true);
    }
}