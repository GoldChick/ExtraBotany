package chick.extrabotany.forge.mixin.client;

import chick.extrabotany.api.block.ISubTileDecay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.common.block.subtile.generating.SubTileFluidGenerator;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;

@Mixin(SubTileHydroangeas.class)
public abstract class MixinHydroangeas extends SubTileFluidGenerator implements ISubTileDecay
{
    //may cause error!
    @Shadow
    private int passiveDecayTicks;

    protected MixinHydroangeas(BlockEntityType<?> type, BlockPos pos, BlockState state, TagKey<Fluid> consumedFluid, int startBurnTime, int manaPerTick, int maxCooldown)
    {
        super(type, pos, state, consumedFluid, startBurnTime, manaPerTick, maxCooldown);
    }

    @Override
    public void setPassiveTicks(int x)
    {
        sync();
        this.passiveDecayTicks = x;
        if (x == 0)
        {
            emitParticle(ParticleTypes.ANGRY_VILLAGER, Math.random() * 0.2, 0.7, Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public int getPassiveTicks()
    {
        return passiveDecayTicks;
    }
}