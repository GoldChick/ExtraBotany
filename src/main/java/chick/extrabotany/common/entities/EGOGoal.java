package chick.extrabotany.common.entities;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.weapons.TrueShadowKatana;
import chick.extrabotany.common.tools.weapons.TrueTerraBlade;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


//TODO:这个不能用，要重写
public class EGOGoal extends Goal
{
    private final EntityEGO entityEGO;
    public int attackDelay;

    public EGOGoal(EntityEGO entityEGO)
    {
        this.entityEGO = entityEGO;
    }

    @Override
    public boolean canUse()
    {
        return entityEGO != null;
    }

    @Override
    public void start()
    {
        super.start();
        entityEGO.setItemInHand(InteractionHand.MAIN_HAND, getWeapon());
        attackDelay = 100;
        entityEGO.setAggressive(true);
    }

    @Override
    public void stop()
    {
        super.stop();
        //entityEGO.setAggressive(false);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (--attackDelay <= 0)
        {
            if (tryAttack())
            {
                attackDelay = (int) (60 - entityEGO.getStage() * 10 + 15 * Math.random());
            }
        }
    }

    public boolean tryAttack()
    {
        entityEGO.setItemInHand(InteractionHand.MAIN_HAND, getWeapon());
        //TODO:挥手动作不能显示

        if (!entityEGO.level.isClientSide)
        {
            if (entityEGO.getPlayersAround().isEmpty())
                return false;
            Entity target = entityEGO.getPlayersAround().get(0);
            //TODO weapon type
            switch (entityEGO.getWeaponType())
            {
                default ->
                        ((TrueShadowKatana) ModItems.TRUE_SHADOW_KATANA.get()).attack(entityEGO, target, 3, 0.8D, 1F);
                case 1 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 3, 1.1D, 1F);
                case 2 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 2, 1.1D, 1F);
                // ((ItemInfluxWaver) ModItems.influxwaver).attack(this, target);
                case 3 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 1, 1.1D, 1F);
                // ((ItemStarWrath) ModItems.starwrath).attack(this, target);
                case 4 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 4, 1.1D, 1F);
                // ((ItemFirstFractal) ModItems.firstfractal).attack(this, target);
            }
        }
        return true;
    }
    public boolean tryTp()
    {
        return true;
    }

    //TODO: 不同阶段切换武器
    public ItemStack getWeapon()
    {
        return switch (entityEGO.getWeaponType())
                {
                    default -> new ItemStack(ModItems.TRUE_SHADOW_KATANA.get());
                    case 1 -> new ItemStack(ModItems.TRUE_TERRA_BLADE.get());
                    case 2 -> new ItemStack(Items.WOODEN_SWORD);
                    //     return new ItemStack(ModItems.influxwaver);
                    case 3 -> new ItemStack(Items.WOODEN_SWORD);
                    //     return new ItemStack(ModItems.starwrath);
                    case 4 -> new ItemStack(Items.WOODEN_SWORD);
                    //     return new ItemStack(ModItems.firstfractal);
                };
    }
}