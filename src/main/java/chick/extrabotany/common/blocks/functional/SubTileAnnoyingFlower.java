package chick.extrabotany.common.blocks.functional;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.IFluidBlock;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block.IPetalApothecary;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;
import vazkii.botania.common.block.tile.TileAltar;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SubTileAnnoyingFlower extends TileEntityFunctionalFlower
{
    private static final int COST = 300;
    private static final int RANGE = 3;
    private static final String TAG_TIME = "times";
    int times = 0;

    public SubTileAnnoyingFlower(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.ANNOYINGFLOWER, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        if (redstoneSignal > 0)
            return;

        boolean hasWater = false;
        for (int x = -RANGE; x <= RANGE; x++)
        {
            for (int z = -RANGE; z <= RANGE; z++)
            {
                //TODO:现在只能识别花药台里面的水
                BlockPos posi = getEffectivePos().offset(x, 0, z);

                if (level.getBlockEntity(posi) instanceof TileAltar)
                {
                    TileAltar te = (TileAltar) level.getBlockEntity(posi);
                    hasWater = te.getFluid() == IPetalApothecary.State.WATER;
                    if (hasWater)
                        break;
                } else if (level.getFluidState(posi).getType() instanceof WaterFluid)
                {
                    hasWater = true;
                    break;
                }
            }
        }

        for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
        {
            if (item.getItem().getItem() == ModItems.FRIED_CHICKEN.get() && item.getItem().getCount() > 0)
            {
                item.getItem().shrink(1);
                times += 3;
            }
        }

        int cd = times > 0 ? (900 * 2 / 5) : 900;

        if (redstoneSignal == 0 && ticksExisted % cd == 0
                && getMana() >= COST && hasWater && !this.level.isClientSide)
        {
            Random rand = level.random;
            ItemStack stack;
            do
            {
                LootContext ctx = new LootContext.Builder((ServerLevel) level).create(LootContextParamSets.EMPTY);
                List<ItemStack> stacks = ((ServerLevel) level).getServer().getLootTables()
                        .get(BuiltInLootTables.FISHING).getRandomItems(ctx);
                if (times > 0)
                {
                    stacks = ((ServerLevel) level).getServer().getLootTables()
                            .get(BuiltInLootTables.FISHING_TREASURE).getRandomItems(ctx);
                    times--;
                }
                if (stacks.isEmpty())
                {
                    return;
                } else
                {
                    Collections.shuffle(stacks);
                    stack = stacks.get(0);
                }
            } while (stack.isEmpty());

            int bound = RANGE * 2 + 1;
            ItemEntity entity = new ItemEntity(level, getEffectivePos().getX() - RANGE + rand.nextInt(bound), getEffectivePos().getY() + 2, getEffectivePos().getZ() - RANGE + rand.nextInt(bound), stack);
            entity.setDeltaMovement(Vec3.ZERO);

            if (!level.isClientSide)
                level.addFreshEntity(entity);
            addMana(-COST);
            sync();
        }
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putInt(TAG_TIME, times);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        times = cmp.getInt(TAG_TIME);
    }

    @Override
    public int getMaxMana()
    {
        return 1000;
    }

    @Override
    public int getColor()
    {
        return 0x000000;
    }

    @Override
    public boolean acceptsRedstone()
    {
        return true;
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
}
