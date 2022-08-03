package chick.extrabotany.common.items;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.block.tile.mana.TileSpreader;

import javax.annotation.Nonnull;

public class ManaReader extends Item
{

    public ManaReader(Properties properties)
    {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        var tile = ctx.getLevel().getBlockEntity(ctx.getClickedPos());
        Player player = ctx.getPlayer();
        int mana = -1;
        if (tile instanceof TilePool)
        {
            TilePool pool = (TilePool) tile;
            mana = pool.getCurrentMana();
        } else if (tile instanceof IManaPool)
        {
            IManaPool t = (IManaPool) tile;
            mana = t.getCurrentMana();
        } else if (tile instanceof TileEntityGeneratingFlower)
        {
            mana = ((TileEntityGeneratingFlower) tile).getMana();
        } else if (tile instanceof TileSpreader)
        {
            mana = ((TileSpreader) tile).getCurrentMana();
        }
        if (ctx.getLevel().isClientSide)
        {
            if (mana >= 0)
                player.sendMessage(new TextComponent(String.format("Mana:%d", mana)), Util.NIL_UUID);
            else
                player.sendMessage(new TextComponent(String.format("No Mana")), Util.NIL_UUID);
        }

        return InteractionResult.PASS;
    }

}
