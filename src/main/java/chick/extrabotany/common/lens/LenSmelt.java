package chick.extrabotany.common.lens;

import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;
import java.util.stream.Stream;

public class LenSmelt extends Lens
{
    @Override
    public boolean collideBurst(IManaBurst burst, HitResult rtr, boolean isManaBlock, boolean shouldKill, ItemStack stack)
    {
        var entity = burst.entity();
        var level = entity.level;

        if (level.isClientSide || rtr.getType() != HitResult.Type.BLOCK)
        {
            return false;
        }

        BlockPos collidePos = ((BlockHitResult) rtr).getBlockPos();
        BlockState state = level.getBlockState(collidePos);
        Block block = state.getBlock();

        ItemStack composite = ((ItemLens) stack.getItem()).getCompositeLens(stack);
        boolean warp = !composite.isEmpty() && composite.getItem() == ModItems.lensWarp;

        //int harvestLevel = ConfigHandler.COMMON.harvestLevelBore.get();
        var tile = level.getBlockEntity(collidePos);
        float hardness = state.getDestroySpeed(level, collidePos);

        //float hardness = block.getBlockHardness(level, collidePos);
        //int neededHarvestLevel = block.getHarvestLevel(state);
        int mana = burst.getMana();

        BlockPos source = burst.getBurstSourceBlockPos();
        if (!source.equals(collidePos)
                && !(tile instanceof IManaReceiver)
                //       && neededHarvestLevel <= harvestLevel
                && hardness != -1 && hardness < 50F
                && (burst.isFake() || mana >= 24))
        {
            if (!burst.hasAlreadyCollidedAt(collidePos))
            {
                if (!burst.isFake())
                {
                    Recipe<?> irecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(new ItemStack(block)), level).orElse(null);

                    if (irecipe != null)
                    {
                        level.removeBlock(collidePos, false);
                        //if (ConfigHandler.COMMON.blockBreakParticles.get())
                        {
                            level.levelEvent(2001, collidePos, Block.getId(state));
                        }
                        boolean offBounds = source.getY() < 0;
                        boolean doWarp = warp && !offBounds;
                        BlockPos dropCoord = doWarp ? source : collidePos;
                        Block.popResource(level, dropCoord, irecipe.getResultItem().copy());
                        burst.setMana(mana - 40);
                    }
                }
            }
            shouldKill = false;
        }
        return shouldKill;
    }

    private static List<ItemStack> stacks(Item... items)
    {
        return Stream.of(items).map(ItemStack::new).toList();
    }

    private static final List<List<ItemStack>> HARVEST_TOOLS_BY_LEVEL = List.of(
            stacks(Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_SHOVEL),
            stacks(Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_HOE, Items.STONE_SHOVEL),
            stacks(Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SHOVEL),
            stacks(Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_SHOVEL),
            stacks(Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SHOVEL)
    );

    public static boolean canHarvest(int harvestLevel, BlockState state)
    {
        return !getTool(harvestLevel, state).isEmpty();
    }

    public static ItemStack getHarvestToolStack(int harvestLevel, BlockState state)
    {
        return getTool(harvestLevel, state).copy();
    }

    private static ItemStack getTool(int harvestLevel, BlockState state)
    {
        if (!state.requiresCorrectToolForDrops())
        {
            return HARVEST_TOOLS_BY_LEVEL.get(0).get(0);
        }

        int idx = Math.min(harvestLevel, HARVEST_TOOLS_BY_LEVEL.size() - 1);
        for (var tool : HARVEST_TOOLS_BY_LEVEL.get(idx))
        {
            if (tool.isCorrectToolForDrops(state))
            {
                return tool;
            }
        }

        return ItemStack.EMPTY;
    }
}
