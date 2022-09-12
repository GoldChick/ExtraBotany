package chick.extrabotany.network.inputMessage;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.api.item.IItemWithLeftClick;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;
import vazkii.botania.common.handler.EquipmentHandler;

import java.util.function.Supplier;

public class LeftClickMessage
{
    public enum LeftClickType
    {
        Jingwei(), True_Weapon(1), ShortBow(2);
        public final int type;

        LeftClickType()
        {
            type = 0;
        }

        LeftClickType(int x)
        {
            type = x;
        }
    }

    private static LeftClickType lcType;

    public LeftClickMessage(LeftClickType type)
    {
        lcType = type;
    }

    public LeftClickMessage()
    {
        lcType = LeftClickType.Jingwei;
    }

    public void encode(FriendlyByteBuf buf)
    {
    }

    public static LeftClickMessage decode(FriendlyByteBuf buf)
    {
        return new LeftClickMessage(lcType);
    }

    public static void handleLeftClick(LeftClickMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
        {
            ServerPlayer player = context.getSender();
            if (player != null)
            {
                var item = switch (lcType.type)
                        {
                            default -> EquipmentHandler.findOrEmpty(ModItems.JINGWEI_FEATHER.get(), player).getItem();
                            case 1, 2 -> player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
                        };
                context.enqueueWork(() -> ((IItemWithLeftClick) item).onLeftClick(player, null));
            }
        });
        context.setPacketHandled(true);
    }
}
