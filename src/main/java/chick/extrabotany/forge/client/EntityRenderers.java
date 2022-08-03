package chick.extrabotany.forge.client;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import vazkii.botania.client.render.tile.*;

public final class EntityRenderers
{
    public interface BERConsumer
    {
        <E extends BlockEntity> void register(BlockEntityType<E> type, BlockEntityRendererProvider<? super E> factory);
    }

    public static void registerBlockEntityRenderers(BERConsumer consumer)
    {
        //floating?
        consumer.register(ModSubtiles.SUNSHINELILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.MOONLIGHTLILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.OMNIVIOLET, RenderTileSpecialFlower::new);
    }

    private EntityRenderers()
    {
    }
}
