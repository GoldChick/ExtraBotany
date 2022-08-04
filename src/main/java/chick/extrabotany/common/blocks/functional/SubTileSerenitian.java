package chick.extrabotany.common.blocks.functional;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.SubTilePassive;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;
public class SubTileSerenitian extends TileEntityFunctionalFlower
{
    private static final int RANGE = 5;

    protected SubTileSerenitian(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public SubTileSerenitian(BlockPos pos, BlockState state)
    {
        this(ModSubtiles.SERENITIAN, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();

        if (redstoneSignal > 0)
            return;
        for (int dx = -RANGE; dx <= RANGE; dx++)
            for (int dz = -RANGE; dz <= RANGE; dz++)
            {
                BlockPos pos = getEffectivePos().offset(dx, 0, dz);
                var tile = level.getBlockEntity(pos);
                if (tile instanceof SubTilePassive)
                {
                    SubTilePassive passive = ((SubTilePassive) tile);
                    if (passive.passiveDecayTicks >= 2400)
                    {
                        if (getLevel().isClientSide)
                        {
                            emitParticle(ParticleTypes.ANGRY_VILLAGER, dx + Math.random() * 0.2, 0.7, dz + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
                        }
                        else
                        {
                            level.playSound(null, getEffectivePos().getX(), getEffectivePos().getY(), getEffectivePos().getZ(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1F, 1F);
                            passive.passiveDecayTicks = 0;
                        }
                    }
                }
            }
    }

    @Override
    public boolean acceptsRedstone()
    {
        return true;
    }

    @Override
    public int getColor()
    {
        return 0x000000;
    }

    @Override
    public int getMaxMana()
    {
        return 1;
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }


}
