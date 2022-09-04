package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.advancement.IAdvancementRequirement;
import chick.extrabotany.common.base.DamageHandler;
import chick.extrabotany.common.libs.LibAdvancementNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.handler.ModSounds;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.RelicImpl;

import java.util.List;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class Excaliber extends SwordRelicBase implements ILensEffect, ICustomDamageItem, IAdvancementRequirement
{
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final String TAG_HOME_ID = "homeID";

    public Excaliber(Properties prop)
    {
        super(Tiers.NETHERITE, 8, -2f, prop);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND)
        {
            ret.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Excaliber modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return ret;
    }

    @Override
    public int getManaPerDamage()
    {
        return 160;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime)
    {
        if (player instanceof Player)
        {
            if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this)
            {
                EntityManaBurst burst = getBurst((Player) player, player.getItemInHand(InteractionHand.MAIN_HAND));
                player.level.addFreshEntity(burst);
                ToolCommons.damageItemIfPossible(player.getItemInHand(InteractionHand.MAIN_HAND), 1, player, getManaPerDamage());
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.terraBlade,
                        SoundSource.PLAYERS, 0.4F, 1.4F);
            }
        }
    }

    public static EntityManaBurst getBurst(Player player, ItemStack stack)
    {
        EntityManaBurst burst = new EntityManaBurst(player);

        float motionModifier = 9F;
        burst.setColor(0xFFFF20);
        burst.setMana(160);
        burst.setStartingMana(160);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(4F);
        burst.setGravity(0F);
        burst.setDeltaMovement(burst.getDeltaMovement().scale(motionModifier));

        ItemStack lens = stack.copy();
        ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getName().getString());
        burst.setSourceLens(lens);
        return burst;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props, Level level)
    {
    }

    @Override
    public boolean collideBurst(IManaBurst burst, HitResult pos, boolean isManaBlock, boolean shouldKill, ItemStack stack)
    {
        return shouldKill;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack)
    {
        ThrowableProjectile entity = burst.entity();
        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
        Entity thrower = entity.getOwner();

        int homeID = ItemNBTHelper.getInt(stack, TAG_HOME_ID, -1);
        if (homeID == -1)
        {
            AABB axis1 = new AABB(entity.getX() - 5F, entity.getY() - 5F, entity.getZ() - 5F,
                    entity.xOld + 5F, entity.yOld + 5F, entity.zOld + 5F);
            List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis1);
            for (LivingEntity living : entities)
            {
                if (!(living instanceof Mob) || living.hurtTime != 0)
                    continue;
                homeID = living.getId();
                ItemNBTHelper.setInt(stack, TAG_HOME_ID, homeID);
                break;
            }
        }

        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, axis);
        if (homeID != -1)
        {
            Entity home = entity.level.getEntity(homeID);
            if (home != null)
            {
                Vec3 vecEntity = VecHelper.fromEntityCenter(home);
                Vec3 vecThis = VecHelper.fromEntityCenter(entity);
                Vec3 vecMotion = vecEntity.subtract(vecThis);
                Vec3 currMotion = entity.getDeltaMovement();

                vecMotion.normalize().scale(currMotion.length());
                entity.setDeltaMovement(vecMotion.x, vecMotion.y, vecMotion.z);
            }
        }

        for (LivingEntity living : entities)
        {
            if (living instanceof Player && (living.getName().getString().equals(ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, ""))))
                continue;

            if (!living.isRemoved())
            {
                int cost = getManaPerDamage() / 3;
                int mana = burst.getMana();
                if (mana >= cost)
                {
                    burst.setMana(mana - cost);
                    float damage = BotaniaAPI.instance().getTerrasteelItemTier().getAttackDamageBonus() + 3F;
                    if (!burst.isFake() && !entity.level.isClientSide)
                    {
                        DamageHandler.INSTANCE.doDamage(living, burst.entity(), thrower, damage,  0);
                        entity.discard();
                        break;
                    }
                }
            }
        }
    }

    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID, "challenge/excaliber"));
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack)
    {
        return true;
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
