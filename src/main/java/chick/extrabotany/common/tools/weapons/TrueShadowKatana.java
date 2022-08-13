package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.common.entities.projectile.EntityTrueShadowKatanaProjectile;
import chick.extrabotany.network.DamageHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.helper.VecHelper;

import java.util.List;

public class TrueShadowKatana extends ItemSwordRelic
{
    public static final int MANA_PER_DAMAGE = 800;

    public TrueShadowKatana(Properties prop)
    {
        super(Tiers.DIAMOND, 5, -2F, prop);
    }

    public void attackEntity(LivingEntity player, Entity target)
    {
        Vec3 targetpos = Vec3.ZERO;

        float RANGE = 8F;
        AABB axis_ = new AABB(player.position().add(-RANGE, -RANGE, -RANGE)
                , player.position().add(RANGE + 1, RANGE + 1, RANGE + 1));

        List<LivingEntity> entities = player.level.getEntitiesOfClass(LivingEntity.class, axis_);
        List<LivingEntity> list = DamageHandler.INSTANCE.getFilteredEntities(entities, player);

        if (list.size() == 0)
        {
            var pos = raytraceFromEntity(player, 64F, true).getBlockPos();
            targetpos = target == null ? new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(0, 1, 0) : target.position().add(0, 1, 0);
        } else if (player instanceof Mob && ((Mob) player).getTarget() != null && entities.contains(((Mob) player).getTarget()))
        {
            targetpos = ((Mob) player).getTarget().position();
        } else if (player.getLastHurtMob() != null && entities.contains(player.getLastHurtMob()))
        {
            targetpos = player.getLastHurtMob().position();
        } else
        {
            for (LivingEntity living : entities)
            {
                if (living != player)
                {
                    targetpos = living.position();
                    break;
                }
            }
        }
        /**
 * 设置剑气数量
 */
        for (int i = 2; i < 3; i++)
        {
            Vec3 look = new Vec3(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z).multiply(1, 0, 1);

            double playerRot = Math.toRadians(player.yRotO + 90);
            if (look.x == 0 && look.z == 0)
            {
                look = new Vec3(Math.cos(playerRot), 0, Math.sin(playerRot));
            }

            look = look.normalize().scale(1.75);

            int div = i / 3;
            int mod = i % 3;

            Vec3 pl = look.add(VecHelper.fromEntityCenter(player)).add(0, 0.1, div * 0.1);

            Vec3 axis = look.normalize().cross(new Vec3(-1, 0, -1)).normalize();

            double rot = mod * Math.PI / 4 - Math.PI / 4;

            Vec3 axis1 = axis.scale(div * 2.5 + 2).lerp(look, rot);
            //TODO what does rotate change to?
            //whats this?
            //rotate(rot, look);
            if (axis1.y < 0)
            {
                axis1 = axis1.multiply(1, -1, 1);
            }

            Vec3 end = pl.add(axis1);

            EntityTrueShadowKatanaProjectile proj = new EntityTrueShadowKatanaProjectile(player.level, player);
            proj.setPos(end.x, end.y, end.z);
            proj.setTargetPos(targetpos);
            proj.faceTargetAccurately(0.75F);
            proj.setDeltaMovement(proj.getDeltaMovement().scale(2D));
            player.level.addFreshEntity(proj);
        }
    }

    @Override
    public void onLeftClick(Player player, Entity target)
    {
        if (!player.level.isClientSide
                && !player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
                && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == this
                && player.getAttackStrengthScale(0) == 1
                && ManaItemHandler.instance().requestManaExactForTool(player.getItemBySlot(EquipmentSlot.MAINHAND), player, MANA_PER_DAMAGE, true))
        {
            attackEntity(player, target);
        }
    }
}
