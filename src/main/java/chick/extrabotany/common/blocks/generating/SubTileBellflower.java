package chick.extrabotany.common.blocks.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.SubTilePassive;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileBellflower extends SubTilePassive
{
    private static final int RANGE = 2;

    public SubTileBellflower(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.BELLFLOWER, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        int baseGen = 10;
        int baseY = 90;
        int y = this.getEffectivePos().getY();

        if (this.level.canSeeSky(this.getEffectivePos()) && y > baseY)
        {
            //TODO:风铃草 枯萎以及进一步修改
            int rain = this.level.isRaining() ? 3 : 0;
            int gen = (baseGen + rain) * y / baseY;
            if (this.ticksExisted % 10 == 0)
            {
                addMana(gen);
                sync();
            }
            passiveDecayTicks++;
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
        cmp.putInt("bellI", passiveDecayTicks);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        passiveDecayTicks = cmp.getInt("bellI");
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
