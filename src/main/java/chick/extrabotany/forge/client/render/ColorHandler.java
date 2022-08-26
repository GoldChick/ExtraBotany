package chick.extrabotany.forge.client.render;

import chick.extrabotany.common.ModItems;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.brew.ModBrews;

public final class ColorHandler
{
    public interface BlockHandlerConsumer
    {
        void register(BlockColor handler, Block... blocks);
    }

    public interface ItemHandlerConsumer
    {
        void register(ItemColor handler, ItemLike... items);
    }

    public static void submitBlocks(BlockHandlerConsumer blocks)
    {
    }

    public static void submitItems(ItemHandlerConsumer items)
    {
        items.register((s, t) -> t == 0 ? Mth.hsvToRgb(ClientTickHandler.ticksInGame * 2 % 360 / 360F, 0.25F, 1F) : -1, ModItems.UNIVERSAL_PETAL.get());

        items.register((s, t) ->
        {
            if (t != 1)
            {
                return -1;
            }

            Brew brew = ((IBrewItem) s.getItem()).getBrew(s);
            if (brew == ModBrews.fallbackBrew)
            {
                return 0xC6000E;
                // return s.getItem() instanceof ItemBloodPendant ? 0xC6000E : 0x989898;
            }
            int color = brew.getColor(s);
            double speed = 0.2;

            //s.is(chick.extrabotany.common.ModItems.COCK_TAIL.get()) || s.is(ModItems.brewVial) ? 0.1 : 0.2;

            int add = (int) (Math.sin(ClientTickHandler.ticksInGame * speed) * 24);

            int r = Math.max(0, Math.min(255, (color >> 16 & 0xFF) + add));
            int g = Math.max(0, Math.min(255, (color >> 8 & 0xFF) + add));
            int b = Math.max(0, Math.min(255, (color & 0xFF) + add));

            return r << 16 | g << 8 | b;
        }, ModItems.COCK_TAIL.get(), ModItems.SPLASH_GRENADE.get());

        //mana len render
                    /*
        ItemColor lensHandler = (s, t) -> t == 0 ? ((ItemLens) s.getItem()).getLensColor(s, Minecraft.getInstance().level) : -1;
        items.register(lensHandler, ModItems.lensNormal, ModItems.lensSpeed, ModItems.lensPower, ModItems.lensTime,
                ModItems.lensEfficiency, ModItems.lensBounce, ModItems.lensGravity, ModItems.lensMine,
                ModItems.lensDamage, ModItems.lensPhantom, ModItems.lensMagnet, ModItems.lensExplosive,
                ModItems.lensInfluence, ModItems.lensWeight, ModItems.lensPaint, ModItems.lensFire,
                ModItems.lensPiston, ModItems.lensLight, ModItems.lensWarp, ModItems.lensRedirect,
                ModItems.lensFirework, ModItems.lensFlare, ModItems.lensMessenger, ModItems.lensTripwire,
                ModItems.lensStorm);
                */

    }

    private ColorHandler()
    {
    }

}
