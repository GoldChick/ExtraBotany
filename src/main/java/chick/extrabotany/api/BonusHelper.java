package chick.extrabotany.api;

import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

public class BonusHelper
{
    public static int sum(List<WeightCategory> categorys)
    {
        int weightSum = 0;
        for (WeightCategory wc : categorys)
        {
            weightSum += wc.getWeight();
        }
        return weightSum;
    }

    public static ItemStack rollItem(List<WeightCategory> weightcategory)
    {
        int weightSum = sum(weightcategory);
        Random random = new Random();
        int n = random.nextInt(weightSum);

        int m = 0;

        for (var wc : weightcategory)
        {
            if (m <= n && n < m + wc.getWeight())
            {
                return wc.getCategory();
            }
            m += wc.getWeight();
        }
        return ItemStack.EMPTY;
    }
}
