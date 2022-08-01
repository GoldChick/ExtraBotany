package chick.extrabotany.client.render.entity;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import vazkii.botania.client.render.tile.*;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.ModTiles;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.entity.ModEntities;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public final class EntityRenderers
{
    public interface EntityRendererConsumer
    {
        <E extends Entity> void accept(EntityType<? extends E> entityType,
                                       EntityRendererProvider<E> entityRendererFactory);
    }

    public static void registerEntityRenderers(EntityRendererConsumer consumer)
    {
        consumer.accept(ModEntities.MANA_BURST, NoopRenderer::new);
    }

    public static void addAuxiliaryPlayerRenders(PlayerRenderer renderer, Consumer<RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>> consumer)
    {
    }

    public interface BERConsumer
    {
        <E extends BlockEntity> void register(BlockEntityType<E> type, BlockEntityRendererProvider<? super E> factory);
    }

    public static void registerBlockEntityRenderers(BERConsumer consumer)
    {
        consumer.register(ModSubtiles.SUNSHINELILY, RenderTileSpecialFlower::new);
    }

    public static final Map<Block, Function<Block, TEISR>> BE_ITEM_RENDERER_FACTORIES = Map.of(
            ModBlocks.manaPylon, RenderTilePylon.ItemRenderer::new,
            ModBlocks.naturaPylon, RenderTilePylon.ItemRenderer::new,
            ModBlocks.gaiaPylon, RenderTilePylon.ItemRenderer::new,
            ModBlocks.teruTeruBozu, TEISR::new,
            ModBlocks.avatar, TEISR::new,
            ModBlocks.bellows, TEISR::new,
            ModBlocks.brewery, TEISR::new,
            ModBlocks.corporeaIndex, TEISR::new,
            ModBlocks.hourglass, TEISR::new
    );

    private EntityRenderers()
    {
    }
}
