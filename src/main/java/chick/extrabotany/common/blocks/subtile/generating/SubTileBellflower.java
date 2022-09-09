package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.api.block.ISubTilePassiveFlower;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public class SubTileBellflower extends TileEntityGeneratingFlower
{
    private static final int RANGE = 3;
    private final ISubTilePassiveFlower flower;

    public SubTileBellflower(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.BELLFLOWER, pos, state);
        flower = IXplatAbstractions.INSTANCE.findPassiveFlower(this);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        int baseGen = 10;
        int baseY = 90;
        int y = this.getEffectivePos().getY();
        float times = 3F;
        for (int xoff = -3; xoff <= 3 && times > 0; xoff++)
        {
            for (int zoff = -3; zoff <= 3 && times > 0; zoff++)
            {
                for (int yoff = 0; yoff <= 3 && times > 0; yoff++)
                {
                    if (xoff == 0 && zoff == 0)
                        break;
                    if (!level.isEmptyBlock(getBlockPos().offset(xoff, yoff, zoff)))
                    {
                        times -= 1 / Math.pow(new Vec3(xoff, yoff, zoff).distanceTo(Vec3.ZERO), 2);
                        if (level.getBlockEntity(getBlockPos().offset(xoff, yoff, zoff)) instanceof SubTileBellflower)
                            times -= 1 / (new Vec3(xoff, yoff, zoff).distanceTo(Vec3.ZERO));
                    }
                }
            }
        }
        if (times < 0)
        {
            times = 0;
        }
        if (this.level.canSeeSky(this.getEffectivePos()) && y > baseY)
        {
            flower.addPassiveTicks();
            int rain = this.level.isRaining() ? 10 : 0;
            if (flower.getPassiveTicks() % 10 == 0)
            {
                addMana((baseGen + rain) * (int) (0.3F * (float) y / (float) baseY * times));
            }
            sync();
        }
    }

    @Override
    public int getMaxMana()
    {
        return 300;
    }

    @Override
    public int getColor()
    {
        return 0xFFFF99;
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putInt(flower.getTagPassiveTicks(), flower.getPassiveTicks());
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        flower.setPassiveTicks(cmp.getInt(flower.getTagPassiveTicks()));
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
