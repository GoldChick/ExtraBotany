package chick.extrabotany.common.crafting;

import chick.extrabotany.api.craft.IStonesiaRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.BiConsumer;

import static chick.extrabotany.ExtraBotany.prefixRl;

public class ModRecipeTypes
{
    public static final RecipeType<IStonesiaRecipe> STONESIA_TYPE = new ModRecipeType<>();

    public static void registerRecipeTypes(BiConsumer<RecipeSerializer<?>, ResourceLocation> r)
    {
        r.accept(CocktailRecipe.SERIALIZER, prefixRl("cocktail_upgrade"));
        r.accept(LenPotionRecipe.SERIALIZER, prefixRl("len_potion_bind"));
        r.accept(GrenadeRecipe.SERIALIZER, prefixRl("grenade_bind"));
        r.accept(DupeRuneRecipe.SERIALIZER, prefixRl("rune_dupe"));
        r.accept(GoldClothRecipe.SERIALIZER, prefixRl("gold_cloth_unbind"));
        r.accept(PrimoGemRecipe.SERIALIZER, prefixRl("primo_gem_buy_fate"));
        r.accept(ToolsMultiUpgradeRecipe.SERIALIZER, prefixRl("tools_multi_upgrade"));
        r.accept(BaublesMultiUpgradeRecipe.SERIALIZER, prefixRl("baubles_multi_upgrade"));
        r.accept(BaublesMultiRemoveRecipe.SERIALIZER, prefixRl("baubles_multi_remove"));


        Registry.register(Registry.RECIPE_TYPE, prefixRl(StonesiaRecipe.TYPE_ID), STONESIA_TYPE);
        r.accept(StonesiaRecipe.SERIALIZER, prefixRl(StonesiaRecipe.TYPE_ID));
    }

    private static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T>
    {
        @Override
        public String toString()
        {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

}
