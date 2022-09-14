package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.base.AdvancementHandler;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public class SubTileTinkle extends TileEntityGeneratingFlower
{
    public SubTileTinkle(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.TINKLEFLOWER, pos, state);
    }

    private static final int RANGE = 8;
    private static final String TAG_TIME = "time";
    private int time = 0;

    @Override
    public void tickFlower()
    {
        super.tickFlower();

        if (!level.isClientSide && level.getGameTime() % 20L == 0)
        {
            for (LivingEntity player : level.getEntitiesOfClass(LivingEntity.class, new AABB(getEffectivePos()).inflate(RANGE)))
            {
                double vel = player.getDeltaMovement().length();

                if (player instanceof Player p)
                {
                    p.sendMessage(new TextComponent("vel * 10 = " + (int) (vel * 10)), Util.NIL_UUID);
                }

                int time = Math.min((int) (vel * 10.0), 8);

                addMana(time * 3);
                if (player instanceof Player p)
                {
                    p.causeFoodExhaustion(0.02F);
                    AdvancementHandler.INSTANCE.grantAdvancement((ServerPlayer) p, LibAdvancementNames.TINKLE_FLOWER_USE);
                }
            }
        }
    }

    @Override
    public int getMaxMana()
    {
        return 1000;
    }

    @Override
    public int getColor()
    {
        return 0xCCFF00;
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
        cmp.putInt(TAG_TIME, time);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        time = cmp.getInt(TAG_TIME);
    }

    //TODO:叮当舞花
}
