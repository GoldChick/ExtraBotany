package chick.extrabotany.datagen.recipes;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.data.recipes.TerraPlateProvider;

import java.util.function.Consumer;


public class ModTerraRecipes extends TerraPlateProvider
{
    public ModTerraRecipes(DataGenerator gen)
    {
        super(gen);
    }
    @Override
    public String getName()
    {
        return "extrabotany terra plate recipes";
    }

    @Override
    public void registerRecipes(Consumer<net.minecraft.data.recipes.FinishedRecipe> consumer)
    {
        var endAir = Ingredient.of(vazkii.botania.common.item.ModItems.enderAirBottle);
        var dragonStone = Ingredient.of(vazkii.botania.common.item.ModItems.dragonstone);
        var phantomMem = Ingredient.of(Items.PHANTOM_MEMBRANE);

        consumer.accept(new FinishedRecipe(idFor("aerialite"), TilePool.MAX_MANA / 2, new ItemStack(ModItems.AERIALITE_INGOT.get()), endAir, dragonStone, phantomMem));
        consumer.accept(new FinishedRecipe(idFor("the_universe"), TilePool.MAX_MANA, new ItemStack(ModItems.THE_UNIVERSE.get()),Ingredient.of(ModItems.THE_CHAOS.get()),Ingredient.of(ModItems.THE_ORIGIN.get()),Ingredient.of(ModItems.THE_END.get())));
    }

    private static ResourceLocation idFor(String s)
    {
        return new ResourceLocation(ExtraBotany.MODID, "terra_plate/" + s);
    }
}
