package chick.extrabotany.forge.mixin.client;

import chick.extrabotany.common.blocks.SubTileDecay;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.common.block.subtile.generating.SubTileFluidGenerator;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;

@Mixin(SubTileHydroangeas.class)
public abstract class ForgeMixinHydroangeas extends SubTileFluidGenerator implements SubTileDecay
{
    //cause crash!
    @Shadow
    private int passiveDecayTicks;

    protected ForgeMixinHydroangeas(BlockEntityType<?> type, BlockPos pos, BlockState state, TagKey<Fluid> consumedFluid, int startBurnTime, int manaPerTick, int maxCooldown)
    {
        super(type, pos, state, consumedFluid, startBurnTime, manaPerTick, maxCooldown);
    }

    @Override
    public void setPassiveTicks(int x)
    {
        this.passiveDecayTicks = x;
    }
    @Override
    public int getPassiveTicks()
    {
        return passiveDecayTicks;
    }
}