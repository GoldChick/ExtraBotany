package chick.extrabotany.common.entities;

import chick.extrabotany.common.ModEntities;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewItem;

import javax.annotation.Nonnull;
import java.util.List;


public class EntitySplashGrenade extends ThrowableItemProjectile
{
    private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(EntitySplashGrenade.class, EntityDataSerializers.ITEM_STACK);

    public EntitySplashGrenade(EntityType<? extends ThrowableItemProjectile> type, Level level)
    {
        super(type, level);
    }

    public EntitySplashGrenade(Level level, LivingEntity player)
    {
        super(ModEntities.SPLASH_GRENADE, level);
        setOwner(player);
    }

    @Override
    public void tick()
    {
        if (!level.isClientSide && ((getOwner() == null) || getOwner().isRemoved()))
        {
            discard();
            return;
        }
        super.tick();
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult)
    {
        onImpact();
    }

    public void onImpact()
    {
        if (getPotion().getItem() instanceof IBrewItem bi)
        {
            Brew brew = bi.getBrew(getPotion());
            double range = 5;
            AABB bounds = new AABB(getX() - range, getY() - range, getZ() - range,
                    getX() + range, getY() + range, getZ() + range);
            List<LivingEntity> entitiess;
            entitiess = level.getEntitiesOfClass(LivingEntity.class, bounds);
            for (LivingEntity living2 : entitiess)
            {
                if (!(living2 instanceof Player))
                    living2.hurt(DamageSource.MAGIC, 10F);
                for (var effect : brew.getPotionEffects(getPotion()))
                {
                    MobEffectInstance newEffect = new MobEffectInstance(effect.getEffect(), (int) ((float) effect.getDuration() * 0.6F), effect.getAmplifier(), true, true);
                    if (!(living2 instanceof Player) && !effect.getEffect().isBeneficial())
                    {
                        if (effect.getEffect().isInstantenous())
                        {
                            effect.getEffect().applyInstantenousEffect(living2, living2, living2, newEffect.getAmplifier(), 1F);
                        } else
                        {
                            living2.addEffect(newEffect);
                        }
                    } else if (living2 instanceof Player && effect.getEffect().isBeneficial())
                    {
                        if (effect.getEffect().isInstantenous())
                        {
                            effect.getEffect().applyInstantenousEffect(living2, living2, living2, newEffect.getAmplifier(), 1F);
                        } else
                        {
                            living2.addEffect(newEffect);
                        }
                    }
                }
            }
            for (var effect : brew.getPotionEffects(getPotion()))
            {
                int i = effect.getEffect().isInstantenous() ? 2007 : 2002;
                this.level.levelEvent(i, this.blockPosition(), brew.getColor(getPotion()));
            }
        }
        if (!level.isClientSide)
            this.discard();
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(ITEM, ItemStack.EMPTY);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp)
    {
        super.addAdditionalSaveData(cmp);
        ItemStack itemstack = this.getPotion();
        if (!itemstack.isEmpty())
        {
            cmp.put("Potion", itemstack.save(new CompoundTag()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp)
    {
        super.readAdditionalSaveData(cmp);
        ItemStack itemstack = ItemStack.of(cmp.getCompound("Potion"));
        this.setItem(itemstack);
    }

    public void setItem(ItemStack stack)
    {
        this.getEntityData().set(ITEM, Util.make(stack.copy(), (itemStack) -> itemStack.setCount(1)));
    }

    @Override
    protected float getGravity()
    {
        return 0.02F;
    }

    @Override
    protected Item getDefaultItem()
    {
        return getPotion().getItem();
    }

    public ItemStack getPotion()
    {
        return this.getEntityData().get(ITEM);
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getItem()
    {
        return getPotion();
    }
}
