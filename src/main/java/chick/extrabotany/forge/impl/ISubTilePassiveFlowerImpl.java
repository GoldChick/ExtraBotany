package chick.extrabotany.forge.impl;

import chick.extrabotany.api.block.ISubTilePassiveFlower;
import chick.extrabotany.common.base.ConfigHandler;
import chick.extrabotany.common.base.ExtrabotanyReflectHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
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
            return ExtrabotanyReflectHelper.getPrivateValue(SubTileHydroangeas.class, hydroangeas, "passiveDecayTicks");
        }
        return passiveTicks;
    }

    @Override
    public void setPassiveTicks(int x)
    {
        if (flower instanceof SubTileHydroangeas hydroangeas)
        {
            ExtrabotanyReflectHelper.setPrivateValue(SubTileHydroangeas.class, hydroangeas, 0, "passiveDecayTicks");
        }
        passiveTicks = x;
    }

    @Override
    public void addPassiveTicks()
    {
        setPassiveTicks(getPassiveTicks() + 1);
        if (getPassiveTicks() > getDecayTicks())
        {
            decay();
        }
    }

    @Override
    public int getDecayTicks()
    {
        return 72000;
    }

    @Override
    public boolean isNoDrop()
    {
        return !(flower instanceof SubTileHydroangeas);
    }

    @Override
    public void serenitianWorks()
    {
        Vec3 offset = flower.getLevel().getBlockState(flower.getEffectivePos()).getOffset(flower.getLevel(), flower.getEffectivePos());

        if (Minecraft.getInstance().level != null && ConfigHandler.CLIENT.serenitianParticle.get())
        {
            Minecraft.getInstance().level.addParticle(ParticleTypes.ANGRY_VILLAGER,
                    flower.getBlockPos().getX() + offset.x + Math.random() * 0.2,
                    flower.getBlockPos().getY() + offset.y + 0.7,
                    flower.getBlockPos().getZ() + offset.z + Math.random() * 0.2,
                    0.0D, 0.0D, 0.0D);
        }
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
