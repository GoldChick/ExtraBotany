package chick.extrabotany.common.items;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TreasureBox extends Item
{
    public TreasureBox(Properties prop)
    {
        super(prop);
    }

    @NotNull
    @Override
    public InteractionResult useOn(@NotNull UseOnContext context)
    {
        var player = context.getPlayer();
        if (player != null)
        {
            if (!player.level.isClientSide)
            {
                player.drop(new ItemStack(ModItems.REWARD_BAG_A.get(), 32), true).setNoPickUpDelay();
                player.drop(new ItemStack(ModItems.REWARD_BAG_B.get(), 16), true).setNoPickUpDelay();

                player.drop(new ItemStack(ModItems.REWARD_BAG_C.get(), 10), true).setNoPickUpDelay();
                player.drop(new ItemStack(ModItems.REWARD_BAG_D.get(), 10), true).setNoPickUpDelay();

                player.drop(new ItemStack(ModItems.HERO_MEDAL.get()), true).setNoPickUpDelay();

                var random = new Random();
                player.drop(new ItemStack(ModItems.GENESIS_CRYSTAL.get(), random.nextInt(5)), true).setNoPickUpDelay();
                player.drop(new ItemStack(ModItems.PRIMO_GEM.get(), 1 + random.nextInt(160)), true).setNoPickUpDelay();

                player.drop(new ItemStack(ModItems.INTERTWINED_FATE.get(), random.nextInt(3)), true);
                player.drop(new ItemStack(ModItems.ACQUAINT_FATE.get(), random.nextInt(3)), true);

                context.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.PASS;
    }
}
