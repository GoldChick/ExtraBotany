package chick.extrabotany.common.tools.weapons;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.Vec3;

public class TrueThunderCaller extends SwordRelicBase
{
    public TrueThunderCaller(Properties prop)
    {
        super(Tiers.DIAMOND, 5, -2, prop);
    }

    @Override
    public int getManaPerDamage()
    {
        return 500;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime,float damageTime)
    {
        Vec3 targetpos = target == null ? raytraceFromEntity(player, 64F, true).getLocation().add(0, 1, 0) : target.position().add(0, 1, 0);

        //EntityInfluxWaverProjectile proj = new EntityInfluxWaverProjectile(player.world, player);
        //proj.setPosition(player.getPosX(), player.getPosY()+1.1D, player.getPosZ());
        //proj.setTargetPos(targetpos);
        //proj.faceTargetAccurately(0.7F);
        //proj.setStrikeTimes(3);
        //player.world.addEntity(proj);
    }
}
