package chick.extrabotany.common.blocks.tile;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.baubles.NatureOrb;
import chick.extrabotany.common.blocks.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.block.IWandable;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.xplat.IXplatAbstractions;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TilePowerFrame extends TileSimpleInventory implements IWandable
{
    public TilePowerFrame(BlockPos pos, BlockState state)
    {
        super(ModTiles.POWER_FRAME, pos, state);
    }

    public static final int TRANSFER_SPEED = 1000;

    private static final String[][] PATTERN_ADV = new String[][]{
            {
                    "P_____P",
                    "_______",
                    "_______",
                    "_______",
                    "_______",
                    "_______",
                    "P_____P"
            },
            {
                    "M_____M",
                    "_______",
                    "_______",
                    "___0___",
                    "_______",
                    "_______",
                    "M_____M"
            }
    };

    public static final Supplier<IMultiblock> MULTIBLOCK_ADV = (() -> PatchouliAPI.get().makeMultiblock(
            PATTERN_ADV,
            'P', ModBlocks.naturaPylon,
            '0', ModBlocks.dragonstoneBlock,
            'M', ModBlocks.manaPool));
    //TODO:方块替换

    public static final BlockPos[] POOL_LOCATIONS = {
            new BlockPos(3, 0, 3), new BlockPos(-3, 0, 3), new BlockPos(3, 0, -3), new BlockPos(-3, 0, -3)
    };

    @Override
    protected SimpleContainer createItemHandler()
    {
        return new SimpleContainer(1)
        {
            @Override
            public int getMaxStackSize()
            {
                return 1;
            }

            @Override
            public boolean canPlaceItem(int slot, @NotNull ItemStack stack)
            {
                return stack.getItem() instanceof IManaItem || stack.getItem() == ModItems.NATURE_ORB.get();
            }
        };
    }

    public boolean addItem(@Nullable Player player, ItemStack stack, @Nullable InteractionHand hand)
    {
        if (!(stack.getItem() instanceof IManaItem) && stack.getItem() != ModItems.NATURE_ORB.get())
        {
            return false;
        }
        boolean did = false;

        if (getItemHandler().getItem(0).isEmpty())
        {
            did = true;
            ItemStack stackToAdd = stack.copy();
            stackToAdd.setCount(1);
            getItemHandler().setItem(0, stackToAdd);

            if (player == null || !player.isCreative())
            {
                stack.shrink(1);
            }
        }

        if (did)
        {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }

        return true;
    }

    public static void clientTick(Level level, BlockPos worldPosition, BlockState state, TilePowerFrame self)
    {

    }

    public static void serverTick(Level level, BlockPos worldPosition, BlockState state, TilePowerFrame self)
    {

        int redstoneSignal = 0;
        for (Direction dir : Direction.values())
        {
            redstoneSignal = Math.max(redstoneSignal, level.getSignal(worldPosition.relative(dir), dir));
        }

        boolean transfering = false;
        int ritual = 0;

        if (MULTIBLOCK_ADV.get().validate(level, worldPosition) != null)
        {
            ritual = 1;
        }

        int speed = TRANSFER_SPEED * (1 + ritual);

        ItemStack stack = self.getItemHandler().getItem(0);
        if (!stack.isEmpty())
        {
            var item = IXplatAbstractions.INSTANCE.findManaItem(stack);
            if (item != null)
            {
                if (level.getBlockEntity(worldPosition.offset(0, 1, 0)) instanceof TilePool p)
                {
                    if (redstoneSignal == 0)
                    {
                        int manaToGet = Math.min(speed, p.getCurrentMana());
                        int space = Math.max(0, item.getMaxMana() - item.getMana());
                        int current = Math.min(space, manaToGet);
                        if (!level.isClientSide)
                        {
                            p.receiveMana(-current);
                            item.addMana(current);
                        }
                        if (current > 0)
                            transfering = true;
                    } else
                    {
                        int manaToGet = Math.min(speed, item.getMana());
                        int space = Math.max(0, p.manaCap - p.getCurrentMana());
                        int current = Math.min(space, manaToGet);
                        if (!level.isClientSide)
                        {
                            p.receiveMana(current);
                            item.addMana(-current);
                        }
                        if (current > 0)
                            transfering = true;
                    }

                }
            } else if (stack.getItem() instanceof NatureOrb orb && ritual > 0)
            {
                //nature orb充能
                int xp = (int) Math.pow(4, ritual);
                if (!level.isClientSide)
                    orb.addXP(stack, xp);
                if (orb.getXP(stack) < orb.getMaxXP())
                    transfering = true;

                for (BlockPos offset : POOL_LOCATIONS)
                {
                    BlockEntity tile = level.getBlockEntity(worldPosition.offset(offset));
                    if (tile instanceof TilePool pool)
                    {
                        if (pool.getCurrentMana() >= 10)
                        {
                            pool.receiveMana(-10);
                            orb.addXP(stack, 2);
                        }
                    }
                }
            }
        }


        if (level.isClientSide && ritual >= 1 && transfering)
        {
            Vec3 pos = Vec3.atCenterOf(worldPosition.offset(0, 0.5, 0));
            for (BlockPos arr : POOL_LOCATIONS)
            {
                Vec3 pylonPos = new Vec3(worldPosition.getX() + arr.getX(), worldPosition.getY() + arr.getY() + 1.2F, worldPosition.getZ() + arr.getZ());
                double worldTime = ClientTickHandler.ticksInGame;
                worldTime /= 5;

                float rad = 0.75F + (float) Math.random() * 0.05F;
                double xp = pylonPos.x + 0.5 + Math.cos(worldTime) * rad;
                double zp = pylonPos.z + 0.5 + Math.sin(worldTime) * rad;

                Vec3 partPos = new Vec3(xp, pylonPos.y, zp);
                Vec3 mot = pos.subtract(partPos).scale(0.04);

                float r = (float) Math.random() * 0.3F;
                float g = 0.75F + (float) Math.random() * 0.2F;
                float b = (float) Math.random() * 0.3F;

                WispParticleData data = WispParticleData.wisp(0.25F + (float) Math.random() * 0.1F, r, g, b, 1);
                level.addParticle(data, partPos.x, partPos.y, partPos.z, 0, -(-0.075F - (float) Math.random() * 0.015F), 0);
                WispParticleData data1 = WispParticleData.wisp(0.4F, r, g, b);
                level.addParticle(data1, partPos.x, partPos.y, partPos.z, (float) mot.x, (float) mot.y, (float) mot.z);
            }
        }
    }

    @Nonnull
    @Override
    public AABB getRenderBoundingBox()
    {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public boolean onUsedByWand(@Nullable Player player, ItemStack stack, Direction side)
    {
        return false;
    }
}
