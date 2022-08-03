package chick.extrabotany.common.tools.items;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.block.tile.mana.TilePool;

import javax.annotation.Nonnull;

public class ManaReader extends Item
{

    public ManaReader(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        var tile = ctx.getLevel().getBlockEntity(ctx.getClickedPos());
        Player player = ctx.getPlayer();
        int mana = 0;
        if (tile instanceof TilePool) {
            TilePool pool = (TilePool) tile;
            mana = pool.getCurrentMana();
        }else if(tile instanceof IManaPool){
            IManaPool t = (IManaPool) tile;
            mana = t.getCurrentMana();
        }
        if(ctx.getLevel().isClientSide)
            player.sendMessage(new TextComponent(String.format("Mana:%d", mana)), Util.NIL_UUID);
        return InteractionResult.PASS;
    }

}
