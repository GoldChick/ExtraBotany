package chick.extrabotany.common.entities;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.tools.weapons.TrueShadowKatana;
import chick.extrabotany.common.tools.weapons.TrueTerraBlade;
import chick.extrabotany.common.tools.weapons.TrueThunStarCaller;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class EGOGoal extends Goal
{
    private final EntityEGO entityEGO;

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
        entityEGO.setItemInHand(InteractionHand.MAIN_HAND, getWeaponItemStack());
        entityEGO.setAggressive(true);
    }

    @Override
    public void stop()
    {
        super.stop();
    }

    @Override
    public void tick()
    {
        super.tick();
        if (entityEGO.getAttackDelay() <= 0 && tryAttack())
        {
            entityEGO.setAttackDelay((int) (60 - entityEGO.getStage() * 10 + 15 * Math.random()));
        }
        if (entityEGO.getTpDelay() <= 0)
        {
            tryTp();
            entityEGO.setAttackDelay(entityEGO.getAttackDelay() - 5);
        }
    }

    public boolean tryAttack()
    {
        changeWeaponBefore();
        entityEGO.setItemInHand(InteractionHand.MAIN_HAND, getWeaponItemStack());
        if (!entityEGO.level.isClientSide)
        {
            if (entityEGO.getPlayersAround().isEmpty())
                return false;
            entityEGO.swing(InteractionHand.MAIN_HAND);
            Entity target = entityEGO.getPlayersAround().get(0);
            //TODO weapon type
            switch (entityEGO.getWeaponType())
            {
                case 0 -> ((TrueShadowKatana) ModItems.TRUE_SHADOW_KATANA.get()).attack(entityEGO, target, 3, 0.9D, 2F);
                case 1 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 3, 0.9D, 2F);
                case 2 -> ((TrueThunStarCaller) ModItems.TRUE_THUNSTAR_CALLER.get()).attack(entityEGO, target, 1, 1D, 2F);
                case 3 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 1, 1.1D, 2F);
                case 4 -> ((TrueTerraBlade) ModItems.TRUE_TERRA_BLADE.get()).attack(entityEGO, target, 4, 1.1D, 2F);
            }
        }
        if (entityEGO.getWeaponType() > 0)
            changeWeaponAfter();
        return true;
    }

    public void changeWeaponBefore()
    {
        if (entityEGO.getStage() > 1)
        {
            for (var player : entityEGO.getPlayersAround())
            {
                if (player.position().distanceTo(entityEGO.position()) <= 6)
                {
                    entityEGO.setWeaponType(2);
                    break;
                }
            }
        }
    }

    public void changeWeaponAfter()
    {
        if (entityEGO.getStage() > 1)
        {
            Random random = new Random();
            entityEGO.setWeaponType(random.nextInt(2));
        }
    }

    public void tryTp()
    {
        entityEGO.teleportRandomly();
        entityEGO.setTpDelay(100 - entityEGO.getStage() * 10);
    }

    public ItemStack getWeaponItemStack()
    {
        return switch (entityEGO.getWeaponType())
                {
                    default -> ItemStack.EMPTY;
                    case 0 -> new ItemStack(ModItems.TRUE_SHADOW_KATANA.get());
                    case 1 -> new ItemStack(ModItems.TRUE_TERRA_BLADE.get());
                    case 2 -> new ItemStack(ModItems.TRUE_THUNSTAR_CALLER.get());
                    case 3 -> new ItemStack(ModItems.INFLUX_WAVER.get());
                    case 4 -> new ItemStack(ModItems.FIRST_FRACTAL.get());
                };
    }
}