package chick.extrabotany.common.entities;

import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.weapons.TrueShadowKatana;
import chick.extrabotany.common.tools.weapons.TrueTerraBlade;
import chick.extrabotany.common.tools.weapons.TrueThunStarCaller;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class EntityEGOMinion extends Monster
{
    public EntityEGO summoner;

    private static final List<BlockPos> SPAWN_LOCATIONS = ImmutableList.of(
            new BlockPos(6, 1, 6),
            new BlockPos(6, 1, -6),
            new BlockPos(-6, 1, 6),
            new BlockPos(-6, 1, -6)
    );

    private static final String TAG_TYPE = "type";

    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityEGOMinion.class, EntityDataSerializers.INT);

    private int attackCooldown = 0;
    private int tp = 0;

    public EntityEGOMinion(EntityType<EntityEGOMinion> type, Level level)
    {
        super(type, level);
    }

    public EntityEGOMinion(Level level)
    {
        super(ModEntities.EGO_MINION, level);
    }

    @Override
    public void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(4,new HurtByTargetGoal(this, Player.class));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 64.0F));
    }

    @Override
    public void aiStep()
    {
        super.aiStep();

        if (!level.isClientSide)
        {
            clearPotions(this);
            if (level.getDifficulty() == Difficulty.PEACEFUL || summoner == null || summoner.isRemoved())
            {
                discard();
            }
        }

        float RANGE = 16F;
        AABB axis = new AABB(position().add(-RANGE, -RANGE, -RANGE), position().add(RANGE + 1, RANGE + 1, RANGE + 1));
        if (getTarget() == null || !(getTarget() instanceof Player))
        {
            List<Player> players = level.getEntitiesOfClass(Player.class, axis);
            if (players.size() > 0)
            {
                setTarget(players.get(0));
            }
        }

        if (getTarget() != null)
        {
            Vec3 lookVec = getTarget().getLookAngle().multiply(1, 0, 1);

            double playerRot = Math.toRadians(getTarget().getYRot() + 90);
            if (lookVec.x == 0 && lookVec.z == 0)
            {
                lookVec = new Vec3(Math.cos(playerRot), 0, Math.sin(playerRot));
            }

            lookVec = lookVec.normalize().multiply(3.5F, 0, 3.5F);

            if (this.getHealth() <= this.getMaxHealth() * 0.5F)
            {
                lookVec = lookVec.multiply(-2F, 0, -2F);
                if (tickCount % 40 == 0)
                    this.heal(2F);
            }

            lookVec = lookVec.yRot((float) (Math.PI / 2F * getMinionType() + Math.floor((double) tickCount / 100) * Math.PI / 4F));

            Vec3 targetPos = getTarget().position().add(lookVec);

            if (!level.isClientSide)
            {
                if (this.position().distanceTo(targetPos) >= 0.5F)
                {
                    this.getMoveControl().setWantedPosition(targetPos.x, targetPos.y, targetPos.z, 0.7F);
                    //dont know if it works
                    //this.getMoveHelper().setMoveTo(targetPos.x, targetPos.y, targetPos.z, 0.7F);
                    tp++;
                } else
                {
                    tp = 0;
                }

                if (tp >= 60)
                {
                    this.setPos(targetPos.x, targetPos.y, targetPos.z);
                    tp = 0;
                }
            }
        }

        if (this.attackCooldown > 0)
            this.attackCooldown--;

        if (this.attackCooldown == 0)
        {
            this.setItemInHand(InteractionHand.MAIN_HAND,getWeapon());
            if (tryAttack())
                this.attackCooldown = 90 + level.random.nextInt(40);
        }

    }


    public boolean tryAttack()
    {
        if (getTarget() == null)
            return false;

        this.swing(InteractionHand.MAIN_HAND);
        if (!level.isClientSide)
        {
            switch (getMinionType())
            {
                case 0 -> ((TrueShadowKatana) ModItems.TRUE_SHADOW_KATANA.get()).attack(this, getTarget());
                case 1 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(this, getTarget());
                case 2 -> ((TrueThunStarCaller) ModItems.TRUE_THUNSTAR_CALLER.get()).attack(this, getTarget());
                case 3 -> ((TrueTerraBlade) ModItems.INFLUX_WAVER.get()).attack(this, getTarget());
            }
        }
        return true;
    }

    public void clearPotions(LivingEntity player)
    {
        List<MobEffect> potionsToRemove = player.getActiveEffects().stream()
                .map(MobEffectInstance::getEffect)
                .filter(effect -> !effect.isBeneficial())
                .distinct().toList();

        potionsToRemove.forEach(potion ->
        {
            player.removeEffect(potion);
            ((ServerLevel) player.level).getChunkSource().broadcastAndSend(player, new ClientboundRemoveMobEffectPacket(player.getId(), potion));
        });
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        Entity e = source.getDirectEntity();
        if (e instanceof Player && isTruePlayer(e))
        {
            float RANGE = 8F;
            AABB axis = new AABB(position().add(-RANGE, -RANGE, -RANGE)
                    , position().add(RANGE + 1, RANGE + 1, RANGE + 1));
            List<EntityEGOMinion> minions = level.getEntitiesOfClass(EntityEGOMinion.class, axis);
            float resistance = Math.min(0.6F, minions.size() * 0.15F);
            int cap = 20;
            return super.hurt(source, Math.min(cap, amount * (1F - resistance)));
        }
        return false;
    }

    private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");

    public static boolean isTruePlayer(Entity e)
    {
        if (!(e instanceof Player player))
        {
            return false;
        }

        String name = player.getName().getString();
        return !(player instanceof FakePlayer || FAKE_PLAYER_PATTERN.matcher(name).matches());
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(TYPE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp)
    {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_TYPE, getMinionType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp)
    {
        super.readAdditionalSaveData(cmp);
        setMinionType(cmp.getInt(TAG_TYPE));
    }

    public static void spawn(EntityEGO summoner, Level level, BlockPos pos, float health)
    {
        List<String> names = new ArrayList<>();

        //This is for Contributors for the mod
        //it is diabled caz nobody has done it lol

        //(ContributorListHandler.contributorsMap.keySet());
        //Collections.shuffle(names);
        names.add("ExtraMeteorP");
        names.add("Vazkii");
        names.add("Notch");
        names.add("LexManos");
        names.add("Gold_Chick");
        Collections.shuffle(names);
        if (!level.isClientSide)
        {
            int type = 0;
            for (BlockPos spawnpos : SPAWN_LOCATIONS)
            {
                EntityEGOMinion minion = new EntityEGOMinion(level);
                minion.summoner = summoner;
                BlockPos mpos = pos.offset(spawnpos.getX(), spawnpos.getY(), spawnpos.getZ());
                minion.setPos(mpos.getX(), mpos.getY(), mpos.getZ());
                minion.setCustomName(new TextComponent(names.get(type)));
                minion.setMinionType(type++);
                minion.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
                minion.getAttribute(Attributes.ARMOR).setBaseValue(10);
                minion.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(minion.blockPosition()), MobSpawnType.EVENT, null, null);
                level.addFreshEntity(minion);
            }
        }
    }

    public ItemStack getWeapon()
    {
        return switch (getMinionType())
                {
                    case 0 -> new ItemStack(ModItems.TRUE_SHADOW_KATANA.get());
                    case 1 -> new ItemStack(ModItems.TRUE_TERRA_BLADE.get());
                    case 2 -> new ItemStack(ModItems.TRUE_THUNSTAR_CALLER.get());
                    case 3 -> new ItemStack(ModItems.INFLUX_WAVER.get());
                    default -> new ItemStack(Items.WOODEN_SWORD);//TODO:王者圣剑
                };
    }

    public int getMinionType()
    {
        return entityData.get(TYPE);
    }

    public void setMinionType(int i)
    {
        entityData.set(TYPE, i);
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
