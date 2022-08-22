package chick.extrabotany.common.tools.weapons;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.projectile.EntityInfluxWaverProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.RelicImpl;

public class InfluxWaver extends SwordRelicBase
{
    public InfluxWaver(Properties prop)
    {
        super(Tiers.DIAMOND, 5, -2F, prop);
    }

    @Override
    public int getManaPerDamage()
    {
        return 500;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime)
    {
        Vec3 targetpos = target == null ? raytraceFromEntity(player, 64F, true).getLocation().add(0, 1, 0) : target.position().add(0, 1, 0);

        EntityInfluxWaverProjectile proj = new EntityInfluxWaverProjectile(player.level, player);
        proj.setPos(player.getX(), player.getY()+1.1D, player.getZ());
        proj.setTargetPos(targetpos);
        proj.faceTargetAccurately(0.7F);
        proj.setStrikeTimes(3);
        player.level.addFreshEntity(proj);
    }
    public static IRelic makeRelic(ItemStack stack)
    {
        return new RelicImpl(stack, new ResourceLocation(ExtraBotany.MODID,"challenge/influx_waver"));
    }
}
