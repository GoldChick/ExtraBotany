package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.entities.EntityFalseLightning;
import chick.extrabotany.api.IMixinGetData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;


public class SubTileReikarOrchid extends TileEntityGeneratingFlower
{
    private static final int RANGE = 3;

    public SubTileReikarOrchid(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.REIKARORCHID, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        if (level.isRaining() && level.canSeeSky(worldPosition))
        {
            int baseY = 64;
            if (level.random.nextInt((4000 * baseY / Math.abs(worldPosition.getY()))) == 1)
            {
                var bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                bolt.setPos(getEffectivePos().getX(), getEffectivePos().getY(), getEffectivePos().getZ());
                if (!level.isClientSide)
                    level.addFreshEntity(bolt);
            }
        }
        for (var bolt : level.getEntitiesOfClass(LightningBolt.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
        {
            if (bolt instanceof EntityFalseLightning)
                continue;
            if (getMana() == 0)
            {
                if (bolt instanceof IMixinGetData && ((IMixinGetData) bolt).getInt() == 2)
                {
                    bolt.discard();
                    var fBolt = new EntityFalseLightning(ModEntities.FALSE_LIGHTNING, level);
                    fBolt.setPos(getEffectivePos().getX(), getEffectivePos().getY(), getEffectivePos().getZ());
                    if (!level.isClientSide)
                        level.addFreshEntity(fBolt);
                    addMana(getMaxMana());
                }
            } else
            {
                if (bolt instanceof IMixinGetData && ((IMixinGetData) bolt).getInt() == 1)
                {
                    level.explode(null, getEffectivePos().getX(), getEffectivePos().getY(), getEffectivePos().getZ(),
                            1.0F, Explosion.BlockInteraction.BREAK);
                }
            }
            sync();
        }
    }

    @Override
    public int getMaxMana()
    {
        return 16000;
    }

    @Override
    public int getColor()
    {
        return 0x0000CD;
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
