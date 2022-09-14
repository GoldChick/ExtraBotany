package chick.extrabotany.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

/**
 * <b>NOT A CAPABILITY<hr></b>
 * need to be registered in <b>MinecraftForge Event_Bus</b><p>
 * and usually need send Message to Server
 */
public interface IItemWithLeftClick
{
    //TODO:check if it needs Network support
    void onLeftClick(Player player, @Nullable Entity target);


    default void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty evt)
    {

    }

    default void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt)
    {

    }

}
