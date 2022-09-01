package chick.extrabotany.forge.client;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.ModTiles;
import chick.extrabotany.forge.client.render.entity.*;
import chick.extrabotany.forge.client.render.tile.RenderTileDimension;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

public final class EntityRenderers
{
    public interface EntityRendererConsumer
    {
        <E extends Entity> void accept(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory);
    }

    public static void registerEntityRenderers(EntityRendererConsumer consumer)
    {
        consumer.accept(ModEntities.EGO, RenderEGO::new);
        consumer.accept(ModEntities.EGO_MINION, RenderEGO::new);
        consumer.accept(ModEntities.EGO_LANDMINE, RenderEGOLandmine::new);
        consumer.accept(ModEntities.EGO_BEAM, RenderEGOBeam::new);
        consumer.accept(ModEntities.AURAFIRE, RenderDummy::new);
        consumer.accept(ModEntities.MAGIC_ARROW, RenderDummy::new);
        consumer.accept(ModEntities.SPLASH_GRENADE, ThrownItemRenderer::new);

        consumer.accept(ModEntities.TRUE_SHADOW_KATANA, RenderProjectile::new);
        consumer.accept(ModEntities.TRUE_TERRA_BLADE, RenderProjectile::new);
        consumer.accept(ModEntities.INFLUX_WAVER, RenderProjectile::new);
        consumer.accept(ModEntities.PHANTOM_SWORD, RenderProjectile::new);

        consumer.accept(ModEntities.FALSE_LIGHTNING, LightningBoltRenderer::new);
        consumer.accept(ModEntities.ACTION_ENTITY, RenderDummy::new);
    }

    public static void registerBlockEntityRenderers(vazkii.botania.client.render.entity.EntityRenderers.BERConsumer consumer)
    {
        consumer.register(ModTiles.DIMENSION_CATALYST, RenderTileDimension::new);
        //floating generating
        consumer.register(ModSubtiles.SUNSHINELILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.MOONLIGHTLILY, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.OMNIVIOLET, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.GEMINIORCHID, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.BELLFLOWER, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.REIKARORCHID, RenderTileSpecialFlower::new);
        //floating functional
        consumer.register(ModSubtiles.SERENITIAN, RenderTileSpecialFlower::new);
        consumer.register(ModSubtiles.ANNOYINGFLOWER, RenderTileSpecialFlower::new);
    }
    public static void setRenderType()
    {
        //transparent
        Registry.BLOCK.stream().filter(b -> Registry.BLOCK.getKey(b).getNamespace().equals(ExtraBotany.MODID))
                .forEach(b ->
                {
                    if (b instanceof BlockSpecialFlower)
                    {
                        setRenderLayer(b, RenderType.cutout());
                    }
                });
    }
    public static void init(EntityRendererProvider.Context ctx)
    {

    }
}
