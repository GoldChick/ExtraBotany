package chick.extrabotany.common.crafting;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.baubles.relic.AbstractMultiUpgradedRelic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class BaublesMultiUpgradeRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<BaublesMultiUpgradeRecipe> SERIALIZER = new SimpleRecipeSerializer<>(BaublesMultiUpgradeRecipe::new);

    public BaublesMultiUpgradeRecipe(ResourceLocation idIn)
    {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        ItemStack base = null;

        boolean findBauble = false;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof AbstractMultiUpgradedRelic)
                {

                    if (base != null)
                    {
                        return false;
                    }
                    base = stack;
                }
            }
        }
        if (base != null)
        {
            for (int i = 0; i < inv.getContainerSize(); i++)
            {
                ItemStack stack = inv.getItem(i);
                if (!stack.isEmpty())
                {
                    var item = stack.getItem();
                    if (!(item instanceof AbstractMultiUpgradedRelic) && item instanceof ItemBauble)
                    {
                        if (findBauble)
                        {
                            return false;
                        }
                        findBauble = ((AbstractMultiUpgradedRelic) base.getItem()).canAddBauble(base, stack) >= 0;
                    }
                }
            }
        }
        return base != null && findBauble;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer inv)
    {

        ItemStack out = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractMultiUpgradedRelic)
            {
                out = ItemStack.of(stack.save(new CompoundTag()));
                break;
            }
        }
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && !(stack.getItem() instanceof AbstractMultiUpgradedRelic))
            {
                ((AbstractMultiUpgradedRelic) out.getItem()).addBauble(out, stack);
                break;
            }
        }
        return out;
    }

    @Override
    public boolean canCraftInDimensions(int width, int length)
    {
        return width > 1 || length > 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
