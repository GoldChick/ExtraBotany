package chick.extrabotany.common.crafting;

import chick.extrabotany.common.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class DupeRuneRecipe extends CustomRecipe
{
    public static final SimpleRecipeSerializer<DupeRuneRecipe> SERIALIZER = new SimpleRecipeSerializer<>(DupeRuneRecipe::new);

    private static final List<Item> ELEMENT_RUNES = Arrays.asList(vazkii.botania.common.item.ModItems.runeFire,
            vazkii.botania.common.item.ModItems.runeAir,
            vazkii.botania.common.item.ModItems.runeWater,
            vazkii.botania.common.item.ModItems.runeEarth,
            vazkii.botania.common.item.ModItems.runeSpring,
            vazkii.botania.common.item.ModItems.runeSummer,
            vazkii.botania.common.item.ModItems.runeAutumn,
            vazkii.botania.common.item.ModItems.runeWinter
    );
    private static final List<Item> SIN_RUNES = Arrays.asList(vazkii.botania.common.item.ModItems.runeWrath,
            vazkii.botania.common.item.ModItems.runeMana,
            vazkii.botania.common.item.ModItems.runeEnvy,
            vazkii.botania.common.item.ModItems.runeGluttony,
            vazkii.botania.common.item.ModItems.runeGreed,
            vazkii.botania.common.item.ModItems.runeLust,
            vazkii.botania.common.item.ModItems.runePride,
            vazkii.botania.common.item.ModItems.runeSloth
    );

    public DupeRuneRecipe(ResourceLocation idIn)
    {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        boolean findRune = false;
        boolean findNormalRune = false;
        int slot = -1;

        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            var stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == ModItems.ELEMENT_RUNE.get() || stack.getItem() == ModItems.SIN_RUNE.get())
            {
                if (slot < 0)
                {
                    slot = i;
                } else
                {
                    return false;
                }
            }
        }
        if (slot > 0)
        {
            for (int i = 0; i < inv.getContainerSize(); i++)
            {
                var stack = inv.getItem(i);
                if (!stack.isEmpty())
                {
                    if ((stack.getItem() == ModItems.ELEMENT_RUNE.get() || stack.getItem() == ModItems.SIN_RUNE.get()) && !findRune)
                    {
                        findRune = true;
                    } else if (checkRune(inv.getItem(slot).getItem(), stack.getItem()) && !findNormalRune)
                    {
                        findNormalRune = true;
                    } else
                    {
                        return false;
                    }
                }
            }
        }
        return findRune && findNormalRune;
    }

    private boolean checkRune(Item baseRune, Item normalRune)
    {
        var list = baseRune == ModItems.ELEMENT_RUNE.get() ? ELEMENT_RUNES : SIN_RUNES;

        for (var rune : list)
            if (rune == normalRune)
                return true;

        return false;
    }

    @NotNull
    @Override
    public ItemStack assemble(CraftingContainer inv)
    {
        ItemStack runeStack = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() != ModItems.ELEMENT_RUNE.get() && stack.getItem() != ModItems.SIN_RUNE.get())
            {
                runeStack = stack;
                break;
            }
        }
        return new ItemStack(runeStack.getItem(), 2);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }
}
