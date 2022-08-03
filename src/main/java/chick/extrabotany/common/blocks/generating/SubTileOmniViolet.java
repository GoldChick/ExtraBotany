package chick.extrabotany.common.blocks.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.handler.ModSounds;
import vazkii.botania.common.helper.DelayHelper;

public class SubTileOmniViolet extends TileEntityGeneratingFlower
{
    public static final float MANA_PER_TICK = 32F;
    private static final String TAG_BURN_TIME = "burnTime";
    private static final int RANGE = 3;
    private static final int START_BURN_EVENT = 0;


    private int burnTime = 0;

    public SubTileOmniViolet(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.OMNIVIOLET, pos, state);
    }

    private float getPower(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos)
    {
        return world.getBlockState(pos).getEnchantPowerBonus(world, pos);
    }

    private float getPower()
    {
        float power = 0F;
        for (int k = -1; k <= 1; ++k)
        {
            for (int l = -1; l <= 1; ++l)
            {
                if ((k != 0 || l != 0) && getLevel().isEmptyBlock(getEffectivePos().offset(l, 0, k)) && getLevel().isEmptyBlock(getEffectivePos().offset(l, 1, k)))
                {
                    power += getPower(getLevel(), getEffectivePos().offset(l * 2, 0, k * 2));
                    power += getPower(getLevel(), getEffectivePos().offset(l * 2, 1, k * 2));

                    if (l != 0 && k != 0)
                    {
                        power += getPower(getLevel(), getEffectivePos().offset(l * 2, 0, k));
                        power += getPower(getLevel(), getEffectivePos().offset(l * 2, 1, k));
                        power += getPower(getLevel(), getEffectivePos().offset(l, 0, k * 2));
                        power += getPower(getLevel(), getEffectivePos().offset(l, 1, k * 2));
                    }
                }
            }
        }
        return power;
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();

        float buff = 1 + getPower() * 0.05F;

        if (burnTime > 0)
        {
            burnTime--;
        }
        if (getLevel().isClientSide)
        {
            if (burnTime > 0 && getLevel().random.nextInt(10) == 0)
            {
                emitParticle(ParticleTypes.FLAME, 0.4 + Math.random() * 0.2, 0.7, 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
            }
            return;
        } else
        {
            if (burnTime > 0)
            {
                addMana((int) (MANA_PER_TICK * Math.min(4F, buff)));
            }
        }

        if (isValidBinding())
        {
            if (burnTime == 0)
            {
                if (getMana() < getMaxMana())
                {
                    for (ItemEntity item : getLevel().getEntitiesOfClass(ItemEntity.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
                    {
                        if (DelayHelper.canInteractWith(this, item))
                        {
                            ItemStack stack = item.getItem();
                            if (stack.getItem().hasCraftingRemainingItem())
                            {
                                continue;
                            }

                            int burnTime = getBurnTime(stack);
                            if (burnTime > 0 && stack.getCount() > 0)
                            {
                                stack.shrink(1);
                                getLevel().playSound(null, getEffectivePos(), ModSounds.endoflame, SoundSource.BLOCKS, 1F, 1F);
                                getLevel().blockEvent(getBlockPos(), getBlockState().getBlock(), START_BURN_EVENT, item.getId());
                                sync();

                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean triggerEvent(int event, int param)
    {
        if (event == START_BURN_EVENT)
        {
            Entity e = getLevel().getEntity(param);
            if (e != null)
            {
                e.level.addParticle(ParticleTypes.LARGE_SMOKE, e.getX(), e.getY() + 0.1, e.getZ(), 0.0D, 0.0D, 0.0D);
                e.level.addParticle(ParticleTypes.FLAME, e.getX(), e.getY(), e.getZ(), 0.0D, 0.0D, 0.0D);
            }
            return true;
        } else
        {
            return super.triggerEvent(event, param);
        }
    }

    @Override
    public int getMaxMana()
    {
        return 1500;
    }

    @Override
    public int getColor()
    {
        return 0xEE82EE;
    }

    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);

        cmp.putInt(TAG_BURN_TIME, burnTime);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);

        burnTime = cmp.getInt(TAG_BURN_TIME);
    }

    private int getBurnTime(ItemStack stack)
    {
        // || Block.byItem(stack.getItem()) instanceof BlockSpreader
        if (stack.isEmpty())
        {
            return 0;
        } else
        {
            return burnTime = (stack.getItem() == Items.BOOK) ? 51 : (stack.getItem() == Items.WRITTEN_BOOK) ? 71 : 0;
        }
    }

}
