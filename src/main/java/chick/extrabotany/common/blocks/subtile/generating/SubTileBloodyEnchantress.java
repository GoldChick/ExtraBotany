package chick.extrabotany.common.blocks.subtile.generating;

import chick.extrabotany.common.ModEffects;
import chick.extrabotany.common.base.AdvancementHandler;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;

public class SubTileBloodyEnchantress extends TileEntityGeneratingFlower
{
    private static final int RANGE = 1;
    private static final String TAG_CD = "cd";

    private static final int COOLDOWN_EVENT = 0;

    private int cdTime = 0;

    public SubTileBloodyEnchantress(BlockPos pos, BlockState state)
    {
        super(ModSubtiles.BLOODYENCHANTRESS, pos, state);
    }

    @Override
    public void tickFlower()
    {
        super.tickFlower();
        if (cdTime > 0)
            cdTime--;

        int ampAll = 0;
        for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
        {
            if (!living.isRemoved())
            {
                int amp = living.hasEffect(ModEffects.BLOOD_TEMPTATION.get()) ? living.getEffect(ModEffects.BLOOD_TEMPTATION.get()).getAmplifier() : 0;
                ampAll += amp;
            }
        }
        if (ampAll > 35)
            return;

        if (isValidBinding())
        {
            if (cdTime == 0 && getMana() < getMaxMana())
            {
                for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class, new AABB(getEffectivePos().offset(-RANGE, -RANGE, -RANGE), getEffectivePos().offset(RANGE + 1, RANGE + 1, RANGE + 1))))
                {
                    if (!living.isRemoved())
                    {
                        int amp = living.hasEffect(ModEffects.BLOOD_TEMPTATION.get()) ? living.getEffect(ModEffects.BLOOD_TEMPTATION.get()).getAmplifier() : 0;
                        if (amp > 4 && Math.random() > 0.5F)
                            continue;
                        if (amp < 10)
                        {
                            addMana((int) (25F * 20F * (1F - 0.04F * amp - 0.02F * ampAll)));
                        } else
                            break;
                        int lv = 0;
                        if (living.hasEffect(ModEffects.BLOOD_TEMPTATION.get()))
                        {
                            lv = living.getEffect(ModEffects.BLOOD_TEMPTATION.get()).getAmplifier() + 1;
                        }
                        living.addEffect(new MobEffectInstance(ModEffects.BLOOD_TEMPTATION.get(), 8 * 20, lv));
                        if (living instanceof ServerPlayer)
                        {
                            AdvancementHandler.INSTANCE.grantAdvancement((ServerPlayer) living, LibAdvancementNames.BLOODY_ENCHANTRESS_USE);
                        }
                        living.hurt(DamageSource.MAGIC.bypassArmor().bypassMagic().bypassInvul(), 4F);
                        living.hurt(DamageSource.MAGIC, 0.01F);
                        cdTime += 20;
                    }
                }
            }
        }
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp)
    {
        super.writeToPacketNBT(cmp);
        cmp.putInt(TAG_CD, cdTime);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp)
    {
        super.readFromPacketNBT(cmp);
        cdTime = cmp.getInt(TAG_CD);
    }

    @Override
    public boolean triggerEvent(int event, int param)
    {
        if (event == COOLDOWN_EVENT)
        {
            Entity e = level.getEntity(param);
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
        return 800;
    }

    @Override
    public int getColor()
    {
        return 0x8B0000;
    }

    @Nullable
    @Override
    public RadiusDescriptor getRadius()
    {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }
    //TODO:血腥妖姬
}
