package chick.extrabotany.network.inputMessage;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DamageMessage
{
    private static int damageNum;
    private static int damageSource;   // 0=Generic 1=Magic 2=Magic 3=Fire
    private static int entityNum;

    public DamageMessage(int num, int source, int entity)
    {
        damageNum = num;
        damageSource = source;
        entityNum = entity;
    }

    public void encode(FriendlyByteBuf buf)
    {
    }

    public static DamageMessage decode(FriendlyByteBuf buf)
    {
        return new DamageMessage(damageNum, damageSource, entityNum);
    }

    public static void handleDamage(DamageMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
        {
            ServerLevel level = context.getSender().getLevel();
            DamageSource source = DamageSource.playerAttack(context.getSender());
            if (damageSource == 3)
            {
                source.setIsFire();
                level.getEntity(entityNum).setSecondsOnFire(2);
            }
            level.getEntity(entityNum).hurt(source, damageNum);

        });
        context.setPacketHandled(true);
    }
}
