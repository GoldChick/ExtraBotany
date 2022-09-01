package chick.extrabotany.api;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static WeightCategory rollItem(List<WeightCategory> weightcategory)
    {
        int weightSum = sum(weightcategory);
        Random random = new Random();
        int n = random.nextInt(weightSum);

        int m = 0;

        for (var wc : weightcategory)
        {
            if (m <= n && n < m + wc.getWeight())
            {
                return wc;
            }
            m += wc.getWeight();
        }
        return new WeightCategory(ItemStack.EMPTY, 114);
    }

    public static ChatFormatting getItemRarity(Map<WeightCategory, Float> chanceMap, List<WeightCategory> categories)
    {
        ChatFormatting color = ChatFormatting.RESET;
        for (var category : categories)
        {
            float p = chanceMap.getOrDefault(category, 0F);
            if (p <= 0.01F)
            {
                return ChatFormatting.GOLD;
            } else if (p <= 0.03F)
            {
                color = ChatFormatting.LIGHT_PURPLE;
            }
        }
        return color;
    }

    public static Map<WeightCategory, Float> getItemChance(List<WeightCategory> categoryList)
    {
        Map<WeightCategory, Float> map = new HashMap<>();
        int sum = sum(categoryList);
        for (var category : categoryList)
        {
            map.put(category, (float) category.getWeight() / sum);
        }
        return map;
    }
}
