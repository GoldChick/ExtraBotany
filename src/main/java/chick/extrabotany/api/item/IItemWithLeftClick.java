package chick.extrabotany.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface IItemWithLeftClick
{
    void onLeftClick(Player living, Entity target);
}
