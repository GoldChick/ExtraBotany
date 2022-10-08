package chick.extrabotany.common.handler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import chick.extrabotany.api.craft.IStonesiaRecipe;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.handler.OrechidManager;
import vazkii.botania.xplat.IXplatAbstractions;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chick.extrabotany.ExtraBotany.prefixRl;


public class StonesiaManager implements ResourceManagerReloadListener
{
    private static final Map<RecipeType<? extends IStonesiaRecipe>, ListMultimap<StateIngredient, ? extends IStonesiaRecipe>> DATA = new HashMap<>();

    public static void registerListener()
    {
        IXplatAbstractions.INSTANCE.registerReloadListener(PackType.SERVER_DATA, prefixRl("stonesia"), new StonesiaManager());
    }

    @Override
    public void onResourceManagerReload(@Nonnull ResourceManager manager)
    {
        DATA.clear();
    }

    public static ListMultimap<StateIngredient, ? extends IStonesiaRecipe> getFor(RecipeManager manager, RecipeType<? extends IStonesiaRecipe> type)
    {
        return DATA.computeIfAbsent(type, t ->
        {
            ListMultimap<StateIngredient, IStonesiaRecipe> map = ArrayListMultimap.create();
            for (var recipe : manager.getAllRecipesFor(t))
            {
                map.put(recipe.getInput(), recipe);
            }
            //this is sort

            //for (var list : map.asMap().values())
            {
               // ((List<IStonesiaRecipe>) list).sort(Comparator.comparingInt(IStonesiaRecipe::getWeight));
            }
            return map;
        });
    }
}
