package chick.extrabotany.api.item;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * <b>NOT A CAPABILITY<hr></b>
 * designed for baubles and other things<p>
 * if you want to right-click blocks when holding the item<p>
 * then use <b>'useOn(UseOnContext ctx)'</b> instead<hr>
 * need to be registered in <b>MinecraftForge.Event_Bus</b>
 */
public interface IItemWithRightClick
{
    default void rightClickBlock(PlayerInteractEvent.RightClickBlock evt)
    {

    }

    default void rightClickEmpty(PlayerInteractEvent.RightClickEmpty evt)
    {

    }

    default void rightClickItem(PlayerInteractEvent.RightClickItem evt)
    {

    }
}
