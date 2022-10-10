package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.crafting.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import chick.extrabotany.api.craft.IStonesiaRecipe;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.crafting.StateIngredientHelper;
import chick.extrabotany.common.handler.StonesiaManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO: to be test
public class SubTileStonesia extends TileEntityGeneratingFlower
{
    private static final int START_BURN_EVENT = 0;

    private static final BlockPos[] OFFSETS = {new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};
    private static final int Range = 1;
    private int burnTime, cooldown;

    public SubTileStonesia( BlockPos pos, BlockState state)
    {
        super(ModSubtiles.STONESIA, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
/*
        var pos = getEffectivePos();
        var x = pos.getX();
        var y = pos.getY();
        var z = pos.getZ();

        if (cooldown > 0)
        {
            cooldown--;
        }

        if (burnTime == 0)
        {
            if (getMana() < getMaxMana() && !level.isClientSide)
            {
                List<BlockPos> offsets = Arrays.asList(OFFSETS);
                Collections.shuffle(offsets);
                for (BlockPos offset : offsets)
                {
                    pos = getEffectivePos().offset(offset);

                    BlockState state = level.getBlockState(pos);

                    var recipe = getRecipe(state);
                    if (recipe != null)
                    {
                        if (cooldown == 0)
                        {
                            burnTime += recipe.getTime();
                            level.setBlockAndUpdate(pos, recipe.getOutputState());
                            cooldown = 40;
                        }
                        addMana(recipe.getMana());
                        sync();
                        level.playSound(null, getEffectivePos(), SoundEvents.GENERIC_DRINK, SoundSource.BLOCKS, 0.01F, 0.5F + (float) Math.random() * 0.5F);

                        break;
                    }
                }
            }
        } else
        {
            WispParticleData data = WispParticleData.wisp((float) Math.random() / 2F, 1, 1, 1);
            level.addParticle(data, x, y, z, 0, 0, 0);

            //Botania.proxy.wispFX(supertile.getPos().getX() + 0.55 + Math.random() * 0.2 - 0.1, supertile.getPos().getY() + 0.55 + Math.random() * 0.2 - 0.1, supertile.getPos().getZ() + 0.5, 0.05F, 0.05F, 0.7F, (float) Math.random() / 6, (float) -Math.random() / 60);
            burnTime--;
        }
        //  emitParticle(ParticleTypes.FLAME, 0.4 + Math.random() * 0.2, 0.7, 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
 */
    }

    private RecipeType<? extends IStonesiaRecipe> getRecipeType()
    {
        return ModRecipeTypes.STONESIA_TYPE;
    }

    @Nullable
    private IStonesiaRecipe getRecipe(BlockState state)
    {
        return StonesiaManager.getFor(getLevel().getRecipeManager(), getRecipeType())
                .get(StateIngredientHelper.of(state.getBlock()));
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
        return 10000;
    }

    @Override
    public int getColor()
    {
        return 0x778899;
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), Range);
    }
}
