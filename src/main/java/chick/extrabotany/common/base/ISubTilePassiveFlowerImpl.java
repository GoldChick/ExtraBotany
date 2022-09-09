package chick.extrabotany.common.base;

import chick.extrabotany.api.block.ISubTilePassiveFlower;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ISubTilePassiveFlowerImpl implements ISubTilePassiveFlower
{
    private int passiveTicks;
    private BlockEntityType<?> blockEntityType;

    public ISubTilePassiveFlowerImpl(BlockEntityType<?> blockEntityType)
    {
        this.blockEntityType = blockEntityType;
    }

    @Override
    public int getPassiveTicks()
    {
        return passiveTicks;
    }

    @Override
    public void setPassiveTicks(int x)
    {
        passiveTicks = x;
    }

    @Override
    public int getMaxPassiveTicks()
    {
        return 72000;
    }

    @Override
    public boolean isNoDrop()
    {
        return false;
    }
}
