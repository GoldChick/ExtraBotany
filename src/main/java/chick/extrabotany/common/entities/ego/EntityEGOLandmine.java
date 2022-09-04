package chick.extrabotany.common.entities.ego;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.handler.ModSounds;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityEGOLandmine extends Entity
{
    public EntityEGO summoner;

    private static final String TAG_TYPE = "type";

    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityEGOLandmine.class, EntityDataSerializers.INT);

    public EntityEGOLandmine(EntityType<EntityEGOLandmine> type, Level level)
    {
        super(type, level);
    }

    public EntityEGOLandmine(Level level)
    {
        super(ModEntities.EGO_LANDMINE, level);
    }

    public static void spawnLandmine(int wave, Level world, BlockPos source, EntityEGO ego)
    {
        Vec3 vecSource = Vec3.atCenterOf(source);
        Vec3 unit = new Vec3(2, 0, 0);
        if (!world.isClientSide)
        {
            switch (wave)
            {
                case 0 ->
                {

                    for (int i = 0; i < 8; i++)
                    {
                        unit = unit.yRot((float) (Math.PI / 4F * i));
                        for (int j = 0; j < 8; j++)
                        {
                            Vec3 end = vecSource.add(unit.multiply(j + 1, j + 1, j + 1));
                            int k = j % 4 == 0 ? 2 : 0;
                            EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                            landmine.summoner = ego;
                            landmine.setPos(end.x, end.y, end.z);
                            landmine.setLandmineType(k);
                            world.addFreshEntity(landmine);
                        }
                    }
                }
                case 1 ->
                {
                    for (int i = 0; i < 5; i++)
                    {

                        for (int j = 0; j < 16; j++)
                        {
                            Vec3 u = unit.add(new Vec3(3, 0, 0).multiply(i, 0, 0));
                            u = u.yRot((float) (Math.PI / 8F * j));
                            Vec3 end = vecSource.add(u);
                            int k = i % 3;
                            EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                            landmine.summoner = ego;
                            landmine.setPos(end.x, end.y, end.z);
                            landmine.setLandmineType(k);
                            world.addFreshEntity(landmine);
                        }

                    }
                }
                case 2 ->
                {
                    for (int i = 0; i < 72; i++)
                    {
                        double p = i * Math.PI / 12F;
                        double r = 1 + 1 * p;
                        double x = r * Math.cos(p);
                        double z = r * Math.sin(p);
                        double y = vecSource.y;
                        int k = i % 5 == 0 ? 2 : 0;
                        EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                        landmine.summoner = ego;
                        landmine.setPos(vecSource.x + x, y, vecSource.z + z);
                        landmine.setLandmineType(k);
                        world.addFreshEntity(landmine);
                    }
                }
                case 3 ->
                {
                    for (int i = 0; i < 80; i++)
                    {
                        double p = i * Math.PI / 80F;
                        double r = 24 * Math.sin(5F * p);
                        double x = r * Math.cos(p);
                        double z = r * Math.sin(p);
                        double y = vecSource.y;
                        int k = i % 4 == 0 ? 2 : 0;
                        EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                        landmine.summoner = ego;
                        landmine.setPos(vecSource.x + x, y, vecSource.z + z);
                        landmine.setLandmineType(k);
                        world.addFreshEntity(landmine);
                    }
                }
                case 4 ->
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 16; j++)
                        {
                            Vec3 u = unit.multiply(3, 0, 3);
                            u = u.yRot((float) (Math.PI / 8F * j));
                            Vec3 end = vecSource.add(unit.multiply(6, 0, 6).yRot((float) (Math.PI / 4 * i))).add(u);
                            int k = i % 3;
                            EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                            landmine.summoner = ego;
                            landmine.setPos(end.x, end.y, end.z);
                            landmine.setLandmineType(k);
                            world.addFreshEntity(landmine);
                        }
                    }
                }
                case 5 ->
                {
                    for (int i = 0; i < 6; i++)
                    {
                        Vec3 mp = vecSource.add(unit.multiply(5, 0, 5).yRot((float) (Math.PI * 2 / 6F * i)));
                        EntityEGOLandmine mid = new EntityEGOLandmine(world);
                        mid.summoner = ego;
                        mid.setPos(mp.x, mp.y, mp.z);
                        mid.setLandmineType(0);
                        world.addFreshEntity(mid);
                        for (int j = 0; j < 16; j++)
                        {
                            Vec3 u = unit.multiply(2, 0, 2).yRot((float) (Math.PI / 8F * j));
                            Vec3 end = mp.add(u);
                            EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                            landmine.summoner = ego;
                            landmine.setPos(end.x, end.y, end.z);
                            landmine.setLandmineType(2);
                            world.addFreshEntity(landmine);
                        }
                    }
                }
                case 6 ->
                {
                    for (int i = 0; i < 72; i++)
                    {
                        Vec3 mp = vecSource.add(unit.multiply(7, 0, 7).yRot((float) (Math.PI * 2 / 72F * i)));
                        EntityEGOLandmine mid = new EntityEGOLandmine(world);
                        mid.summoner = ego;
                        mid.setPos(mp.x, mp.y, mp.z);
                        mid.setLandmineType(2);
                        world.addFreshEntity(mid);
                        if (i % 5 == 0)
                        {
                            for (int j = 0; j < 12; j++)
                            {
                                Vec3 u = unit.multiply(4, 0, 4).yRot((float) (Math.PI / 6F * j));
                                Vec3 end = mp.add(u);
                                EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                                landmine.summoner = ego;
                                landmine.setPos(end.x, end.y, end.z);
                                landmine.setLandmineType(i % 2);
                                world.addFreshEntity(landmine);
                            }
                        }
                    }
                }
                case 7 ->
                {
                    for (int i = 0; i < 6; i++)
                    {
                        for (int l1 = 0; l1 < 11; l1++)
                        {
                            Vec3 mp = vecSource.add(unit.multiply(l1, 0, l1).yRot((float) (Math.PI * 2 / 6F * i)));
                            EntityEGOLandmine mid = new EntityEGOLandmine(world);
                            mid.summoner = ego;
                            mid.setPos(mp.x, mp.y, mp.z);
                            mid.setLandmineType(1);
                            world.addFreshEntity(mid);
                            if (l1 == 5)
                            {
                                for (int j = 0; j < 6; j++)
                                {
                                    for (int l2 = 0; l2 < 7; l2++)
                                    {
                                        Vec3 end = mp.add(unit.multiply(l2 * 0.6F, 0, l2 * 0.6F).yRot((float) (Math.PI * 2 / 6F * j + Math.PI / 6F)));
                                        EntityEGOLandmine landmine = new EntityEGOLandmine(world);
                                        landmine.summoner = ego;
                                        landmine.setPos(end.x, end.y, end.z);
                                        landmine.setLandmineType(j % 3);
                                        world.addFreshEntity(landmine);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void defineSynchedData()
    {
        entityData.define(TYPE, 0);
    }

    @Override
    public void tick()
    {
        setDeltaMovement(Vec3.ZERO);
        super.tick();

        //getWidth
        float range = getBbWidth() / 2;
        float r = 0F;
        float g = 0F;
        float b = 0F;

        switch (getLandmineType())
        {
            case 0 -> b = 1F;
            case 1 -> g = 1F;
            case 2 -> r = 1F;
        }

        if (tickCount % 2 == 0)
            for (int i = 0; i < 2; i++)
            {
                WispParticleData data = WispParticleData.wisp(0.4F, r, g, b, (float) 1);
                level.addParticle(data, getX() - range + Math.random() * range * 2, getY(), getZ() - range + Math.random() * range * 2, 0, - -0.015F, 0);
            }

        if (tickCount >= 50)
        {
            level.playSound(null, getX(), getY(), getZ(), ModSounds.gaiaTrap, SoundSource.NEUTRAL, 0.3F, 1F);

            float m = 0.35F;
            for (int i = 0; i < 4; i++)
            {
                WispParticleData data = WispParticleData.wisp(0.5F, r, g, b);
                level.addParticle(data, getX(), getY() + 1, getZ(), (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m);
            }

            if (!level.isClientSide)
            {
                List<Player> players = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(0, 12, 0));
                for (Player player : players)
                {
                    DamageHandler.INSTANCE.doDamage(player, DamageSource.indirectMagic(this, summoner), 5F,  0);
                    switch (getLandmineType())
                    {
                        default ->
                        {
                            player.hurt(DamageSource.indirectMagic(this, summoner), 10F);
                            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 25, 0));
                            MobEffectInstance wither = new MobEffectInstance(MobEffects.WITHER, 120, 2);
                            wither.getCurativeItems().clear();
                            player.addEffect(wither);
                        }
                        case 1 ->
                        {
                            if (!player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty())
                                player.drop(player.getItemBySlot(EquipmentSlot.MAINHAND), true);
                            if (!player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty())
                                player.drop(player.getItemBySlot(EquipmentSlot.OFFHAND), true);
                            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                            player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                        }
                        case 2 ->
                                DamageHandler.INSTANCE.doDamage(player, DamageSource.indirectMagic(this, summoner), 20F,  0);
                    }
                }
            }
            discard();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp)
    {
        setLandmineType(cmp.getInt(TAG_TYPE));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp)
    {
        cmp.putInt(TAG_TYPE, getLandmineType());
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public int getLandmineType()
    {
        return entityData.get(TYPE);
    }

    public void setLandmineType(int i)
    {
        entityData.set(TYPE, i);
    }

}
