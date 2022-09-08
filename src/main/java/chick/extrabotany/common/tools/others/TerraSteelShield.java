package chick.extrabotany.common.tools.others;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import vazkii.botania.common.lib.ModTags;

public class TerraSteelShield extends ElementSteelShield
{
    public TerraSteelShield(Properties prop)
    {
        super(prop);
    }

    @Override
    public void onShieldBlock(ShieldBlockEvent event)
    {
        super.onShieldBlock(event);

        DamageSource source = event.getDamageSource();
        LivingEntity user = event.getEntityLiving();
        float damage = event.getBlockedDamage();

        var entity = source.getDirectEntity();
        if (entity instanceof LivingEntity living)
        {
            living.hurt(user instanceof Player p ? DamageSource.playerAttack(p) : DamageSource.mobAttack(user), damage);
        }
        //TODO:如果不是直接伤害，是否应该反弹？
    }

    @Override
    public boolean isValidRepairItem(ItemStack item, ItemStack material)
    {
        return material.is(ModTags.Items.INGOTS_TERRASTEEL);
    }
}
