package chick.extrabotany.common.lens;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.recipe.IManaInfusionRecipe;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.item.lens.Lens;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LenMana extends Lens
{
    private int MAX_MANA = 10000;

    public LenMana(int maxMana)
    {
        MAX_MANA = maxMana;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props)
    {
        props.maxMana = MAX_MANA;
        props.motionModifier *= 0.5F;
        props.manaLossPerTick *= 2F;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack)
    {
        var entity = burst.entity();
        if (entity.level.isClientSide)
            return;

        int mana = burst.getMana();
        var state = entity.level.getBlockState(burst.getBurstSourceBlockPos().offset(0, -1, 0));

        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);

        List<ItemEntity> entities = entity.level.getEntitiesOfClass(ItemEntity.class, axis);

        if (!burst.isFake())
            for (ItemEntity items : entities)
            {

                ItemStack itemstack = items.getItem();
                IManaInfusionRecipe recipe = getMatchingRecipe(entity.level, itemstack, state);
                if (recipe != null)
                {
                    int manaToConsume = recipe.getManaToConsume();
                    if (mana >= manaToConsume)
                    {
                        burst.setMana(mana - manaToConsume);
                        itemstack.shrink(1);

                        ItemStack output = recipe.getRecipeOutput(itemstack).copy();

                        ItemEntity outputItem = new ItemEntity(entity.level, items.getX(), items.getY() + 0.5, items.getZ() + 0.5, output);

                        outputItem.setPickUpDelay(50);
                        entity.level.addFreshEntity(outputItem);
                    }
                }
            }
    }

    public static List<IManaInfusionRecipe> manaInfusionRecipes(Level world)
    {
        return ModRecipeTypes.getRecipes(world, ModRecipeTypes.MANA_INFUSION_TYPE).values().stream()
                .filter(r -> r instanceof IManaInfusionRecipe)
                .map(r -> (IManaInfusionRecipe) r)
                .collect(Collectors.toList());
    }

    public IManaInfusionRecipe getMatchingRecipe(Level world, @Nonnull ItemStack stack, @Nonnull BlockState state)
    {
        List<IManaInfusionRecipe> matchingNonCatRecipes = new ArrayList<>();
        List<IManaInfusionRecipe> matchingCatRecipes = new ArrayList<>();

        for (IManaInfusionRecipe recipe : manaInfusionRecipes(world))
        {
            if (recipe.matches(stack))
            {
                if (recipe.getRecipeCatalyst() == null)
                {
                    matchingNonCatRecipes.add(recipe);
                } else if (recipe.getRecipeCatalyst().test(state))
                {
                    matchingCatRecipes.add(recipe);
                }
            }
        }

        // Recipes with matching catalyst take priority above recipes with no catalyst specified
        return !matchingCatRecipes.isEmpty() ? matchingCatRecipes.get(0) : !matchingNonCatRecipes.isEmpty() ? matchingNonCatRecipes.get(0) : null;
    }
}
