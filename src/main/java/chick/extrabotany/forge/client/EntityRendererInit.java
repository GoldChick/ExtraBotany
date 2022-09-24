package chick.extrabotany.forge.client;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.blocks.ModTiles;
import chick.extrabotany.common.blocks.PowerFrame;
import chick.extrabotany.common.libs.LibBlockNames;
import chick.extrabotany.forge.client.render.entity.*;
import chick.extrabotany.forge.client.render.tile.RenderTileDimension;
import chick.extrabotany.forge.client.render.tile.RenderTilePowerFrame;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;

import static net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer;

public final class EntityRendererInit
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

        consumer.accept(ModEntities.JUDAH_OATH, RenderJudah::new);
        consumer.accept(ModEntities.JUDAH_SPEAR, RenderDummy::new);
        consumer.accept(ModEntities.JUDAH_SWORD, RenderDummy::new);
    }

    public static void registerBlockEntityRenderers(vazkii.botania.client.render.entity.EntityRenderers.BERConsumer consumer)
    {
        consumer.register(ModTiles.DIMENSION_CATALYST, RenderTileDimension::new);
        consumer.register(ModTiles.POWER_FRAME, RenderTilePowerFrame::new);
        //Not Safe, But useful
        Registry.BLOCK_ENTITY_TYPE.stream()
                .filter(b -> b.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
                .filter(b -> b.getRegistryName().getPath().startsWith(LibBlockNames.FLOWER_BE_TYPE_PREFIX))
                .map(b -> (BlockEntityType<TileEntitySpecialFlower>) b)
                .forEach(b -> consumer.register(b, RenderTileSpecialFlower::new));
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
                    } else if (b instanceof PowerFrame)
                    {
                        setRenderLayer(b, RenderType.cutout());
                    }
                });
    }

    public static void init(EntityRendererProvider.Context ctx)
    {

    }
}
