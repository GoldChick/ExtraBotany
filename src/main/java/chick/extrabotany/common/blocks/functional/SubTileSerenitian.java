package chick.extrabotany.common.blocks.functional;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.SubTileDecay;
import chick.extrabotany.common.blocks.SubTilePassive;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.common.block.subtile.generating.SubTileFluidGenerator;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;

public class SubTileSerenitian extends TileEntityFunctionalFlower
{
    private static final int RANGE = 5;

    public SubTileSerenitian(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.SERENITIAN, pos, state);
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
                if (tile instanceof SubTileDecay)
                {
                    SubTileDecay passive = (SubTileDecay) tile;
                    if (passive.getPassiveTicks() >= 100)
                    {
                        passive.setPassiveTicks(0);
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
