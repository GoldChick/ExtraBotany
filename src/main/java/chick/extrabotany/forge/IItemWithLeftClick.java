package chick.extrabotany.forge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface IItemWithLeftClick
{
    void onLeftClick(Player living, Entity target);
}
