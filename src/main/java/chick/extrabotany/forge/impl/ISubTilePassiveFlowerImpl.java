package chick.extrabotany.forge.impl;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.block.ISubTilePassiveFlower;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;

/**
 * default passive flower capability
 * just use it if you don't want any change
 */
public class ISubTilePassiveFlowerImpl implements ISubTilePassiveFlower
{
    private final TileEntitySpecialFlower flower;
    private int passiveTicks;

    public ISubTilePassiveFlowerImpl(BlockEntity blockEntity)
    {
        if (blockEntity instanceof TileEntitySpecialFlower flower)
        {
            this.flower = flower;
        } else
        {
            throw new IllegalStateException("Extrabotany Error! Not a Special Flower is attached to Capability ISubTilePassiveFlower!");
        }
    }

    @Override
    public int getPassiveTicks()
    {
        if (flower instanceof SubTileHydroangeas hydroangeas)
        {
            return ObfuscationReflectionHelper.getPrivateValue(SubTileHydroangeas.class, hydroangeas, "passiveDecayTicks");
        }
        return passiveTicks;
    }

    @Override
    public void setPassiveTicks(int x)
    {
        ExtraBotany.LOGGER.debug("sb250 impl tick=" + getPassiveTicks());
        ExtraBotany.LOGGER.debug("sb250 tag tick=" + flower.saveWithFullMetadata().getInt(getTagPassiveTicks()));

        if (flower instanceof SubTileHydroangeas hydroangeas)
        {
            ObfuscationReflectionHelper.setPrivateValue(SubTileHydroangeas.class, hydroangeas, 0, "passiveDecayTicks");
        }

        passiveTicks = x;

        if (getPassiveTicks() > getDecayTicks())
        {
            decay();
        }
    }

    @Override
    public void addPassiveTicks()
    {
        setPassiveTicks(getPassiveTicks() + 1);
    }

    @Override
    public int getDecayTicks()
    {
        return 200;
    }

    @Override
    public boolean isNoDrop()
    {
        return !(flower instanceof SubTileHydroangeas);
    }

    @Override
    public void serenitianWorks()
    {
        flower.emitParticle(ParticleTypes.ANGRY_VILLAGER, Math.random() * 0.2, 0.7, Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void decay()
    {
        var level = flower.getLevel();
        var blockpos = flower.getBlockPos();
        if (level != null)
        {
            level.destroyBlock(blockpos, false);
            if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(level, blockpos))
            {
                level.setBlockAndUpdate(blockpos, Blocks.DEAD_BUSH.defaultBlockState());
            }
        }
    }
}
