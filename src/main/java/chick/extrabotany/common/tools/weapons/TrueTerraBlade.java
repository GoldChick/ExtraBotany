package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.projectile.relic_projectile.EntityTrueTerraBladeProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.RelicImpl;

public class TrueTerraBlade extends SwordRelicBase
{
    private static final int MANA_PER_DAMAGE = 800;

    public TrueTerraBlade(Properties prop)
    {
        super(Tiers.DIAMOND, 5, -2F, prop);
    }

    /**
     * @param times Max 3
     */
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime)
    {
        Vec3 targetpos = target == null ? raytraceFromEntity(player, 80F, true).getLocation().add(0, 1, 0) : target.position().add(0, 1, 0);

        for (int i = 0; i < times; i++)
        {
            EntityTrueTerraBladeProjectile proj = new EntityTrueTerraBladeProjectile(player.level, player, damageTime);
            proj.setPos(player.getX(), player.getY() + 1.1D + 1D * i, player.getZ());
            proj.setTargetPos(targetpos);
            proj.faceTargetAccurately(0.8F);
            proj.setDeltaMovement(proj.getDeltaMovement().scale(speedTime));
            player.level.addFreshEntity(proj);
        }
    }
    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID,"challenge/true_terra_blade"));
    }
    @Override
    public int getManaPerDamage()
    {
        return MANA_PER_DAMAGE;
    }
}
