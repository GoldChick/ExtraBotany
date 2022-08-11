package chick.extrabotany.common.blocks.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
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
    private static final int RANGE = 5;

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
            if (getMana() == 0)
            {
                addMana(getMaxMana());
            } else
            {
                level.explode(null, getEffectivePos().getX(), getEffectivePos().getY(), getEffectivePos().getZ(), 3.0F, Explosion.BlockInteraction.BREAK);
            }
            bolt.discard();
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
