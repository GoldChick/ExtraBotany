package chick.extrabotany.common.blocks.tile;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.baubles.NatureOrb;
import chick.extrabotany.common.blocks.ModTiles;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.xplat.IXplatAbstractions;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TilePowerFrame extends TileSimpleInventory
{
    public TilePowerFrame(BlockPos pos, BlockState state)
    {
        super(ModTiles.POWER_FRAME, pos, state);
    }

    public static final int TRANSFER_SPEED = 1000;

    protected static final String TAG_TRANSFERRING = "tag_transferring";
    protected boolean is_transferring;

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
            '0', chick.extrabotany.common.ModBlocks.POWER_FRAME.get(),
            'M', ModBlocks.manaPool));

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
        if (IXplatAbstractions.INSTANCE.findManaItem(stack) == null && stack.getItem() != ModItems.NATURE_ORB.get())
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

    @Override
    public void writePacketNBT(CompoundTag tag)
    {
        super.writePacketNBT(tag);
        tag.putBoolean(TAG_TRANSFERRING, is_transferring);
    }

    @Override
    public void readPacketNBT(CompoundTag tag)
    {
        super.readPacketNBT(tag);
        is_transferring = tag.getBoolean(TAG_TRANSFERRING);
    }

    public static void clientTick(Level level, BlockPos worldPosition, BlockState state, TilePowerFrame self)
    {
        if (self.is_transferring && MULTIBLOCK_ADV.get().validate(level, worldPosition) != null)
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

    public static void serverTick(Level level, BlockPos worldPosition, BlockState state, TilePowerFrame self)
    {
        int redstoneSignal = 0;
        for (Direction dir : Direction.values())
        {
            redstoneSignal = Math.max(redstoneSignal, level.getSignal(worldPosition.relative(dir), dir));
        }

        int speed = TRANSFER_SPEED;

        if (MULTIBLOCK_ADV.get().validate(level, worldPosition) != null)
        {
            speed *= 2;
        }
        ItemStack stack = self.getItemHandler().getItem(0);
        if (!stack.isEmpty())
        {
            var manaItem = IXplatAbstractions.INSTANCE.findManaItem(stack);

            int chargeModifier = 100;

            if (manaItem == null)
            {
                manaItem = chick.extrabotany.xplat.IXplatAbstractions.INSTANCE.findNatureOrb(stack);
                chargeModifier = 1;
            }

            if (manaItem != null)
            {
                if (level.getBlockEntity(worldPosition.offset(0, 1, 0)) instanceof TilePool p)
                {
                    int current;

                    if (redstoneSignal == 0)
                    {
                        int manaToGet = Math.min(speed, p.getCurrentMana());
                        int space = Math.max(0, manaItem.getMaxMana() - manaItem.getMana());
                        current = Math.min(space, manaToGet);
                    } else
                    {
                        int manaToGet = Math.min(speed, manaItem.getMana());
                        int space = Math.max(0, p.manaCap - p.getCurrentMana());
                        current = -Math.min(space, manaToGet);
                    }

                    p.receiveMana(-current * chargeModifier / 100);
                    manaItem.addMana(current * chargeModifier / 100);

                    if (current > 0)
                    {
                        self.setTransferring(true);
                    }
                }
            }
        }
    }

    public void setTransferring(boolean is_transferring)
    {
        setChanged();
        this.is_transferring = is_transferring;
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        if (level != null && !level.isClientSide)
        {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
    }

    public static class WandHud implements IWandHUD
    {
        private final TilePowerFrame frame;

        public WandHud(TilePowerFrame frame)
        {
            this.frame = frame;
        }

        @Override
        public void renderHUD(PoseStack ms, Minecraft mc)
        {
            String name = new ItemStack(frame.getBlockState().getBlock()).getHoverName().getString();

            int color = 0x1e90ff;

            var stack = frame.getItemHandler().getItem(0);

            int curr = 0;
            int max = 500000;

            var manaItem = IXplatAbstractions.INSTANCE.findManaItem(stack);
            if (manaItem != null)
            {
                curr = manaItem.getMana();
                max = manaItem.getMaxMana();
            } else if (stack.getItem() instanceof NatureOrb orb)
            {
                curr = orb.getXP(stack);
            }
            BotaniaAPIClient.instance().drawSimpleManaHUD(ms, color, curr, max, name);

            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        }
    }
}
