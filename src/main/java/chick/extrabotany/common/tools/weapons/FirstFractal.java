package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.api.advancement.IAdvancementRequirement;
import chick.extrabotany.common.entities.projectile.relic_projectile.EntityPhantomSword;
import chick.extrabotany.common.libs.LibAdvancementNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import static vazkii.botania.common.item.equipment.bauble.ItemBauble.getBaubleUUID;

public class FirstFractal extends SwordRelicBase implements IAdvancementRequirement
{
    public FirstFractal(Properties prop)
    {
        super(Tiers.NETHERITE, 10, -1.6F, prop);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND)
        {
            ret.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Fractal modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return ret;
    }

    @Override
    public @NotNull Rarity getRarity(ItemStack stack)
    {
        return Rarity.EPIC;
    }

    @Override
    public int getManaPerDamage()
    {
        return 0;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime)
    {
        BlockPos targetpos = BlockPos.ZERO;
        if (target == null)
        {
            boolean haveEntity = false;
            for (int i = 1; i <= 13; i++)
            {
                var list = player.level.getEntitiesOfClass(LivingEntity.class,
                        new AABB(raytraceFromEntity(player, 5 * i, true).getBlockPos().offset(0, 1, 0)).inflate(3));
                list.remove(player);
                if (!list.isEmpty())
                {
                    targetpos = list.get(0).blockPosition().offset(0, 1, 0);
                    haveEntity = true;
                    break;
                }
            }
            if (!haveEntity)
            {
                targetpos = raytraceFromEntity(player, 64D, true).getBlockPos().offset(0, 1, 0);
            }
        } else
        {
            targetpos = new BlockPos(target.position()).offset(0, 1, 0);
        }
        double range = 5D;
        double j = -Math.PI + 2 * Math.PI * Math.random();
        double k;
        double x, y, z;
        for (int i = 0; i < 3; i++)
        {
            EntityPhantomSword sword = new EntityPhantomSword(player.level, player, targetpos, damageTime);
            sword.setDelay(5 + 5 * i);
            k = 0.12F * Math.PI * Math.random() + 0.28F * Math.PI;
            x = targetpos.getX() + range * Math.sin(k) * Math.cos(j);
            y = targetpos.getY() + range * Math.cos(k);
            z = targetpos.getZ() + range * Math.sin(k) * Math.sin(j);
            j += 2 * Math.PI * Math.random() * 0.08F + 2 * Math.PI * 0.17F;
            sword.setPos(x, y, z);
            sword.faceTarget(1.05F);

            sword.setDeltaMovement(sword.getDeltaMovement().scale(speedTime).scale(0.4D));

            player.level.addFreshEntity(sword);

        }
        EntityPhantomSword sword2 = new EntityPhantomSword(player.level, player, targetpos, damageTime);
        k = 0.12F * Math.PI * Math.random() + 0.28F * Math.PI;
        x = targetpos.getX() + range * Math.sin(k) * Math.cos(j);
        y = targetpos.getY() + range * Math.cos(k);
        z = targetpos.getZ() + range * Math.sin(k) * Math.sin(j);
        sword2.setPos(x, y, z);
        sword2.faceTarget(1.05F);
        sword2.setVariety(9);
        sword2.setDeltaMovement(sword2.getDeltaMovement().scale(speedTime).scale(0.6D));
        player.level.addFreshEntity(sword2);
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
