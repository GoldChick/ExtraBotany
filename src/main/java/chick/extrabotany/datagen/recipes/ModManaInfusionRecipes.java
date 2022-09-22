package chick.extrabotany.datagen.recipes;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.libs.LibItemNames;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.crafting.StateIngredientHelper;
import vazkii.botania.common.helper.ItemNBTHelper;

import java.util.function.Consumer;

public class ModManaInfusionRecipes extends RecipeProvider
{
    public ModManaInfusionRecipes(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        consumer.accept(new ManaInfusion(idFor(LibItemNames.NIGHTMARE_FUEL), new ItemStack(ModItems.NIGHTMARE_FUEL.get()), Ingredient.of(Items.COAL), 1000));
        consumer.accept(new ManaInfusion(idFor(LibItemNames.LIVINGWOOD_SHORTBOW), new ItemStack(ModItems.LIVINGWOOD_SHORTBOW.get()), Ingredient.of(vazkii.botania.common.item.ModItems.livingwoodBow), 2000));
        consumer.accept(new ManaInfusion(idFor(LibItemNames.MANA_SHORTARROW), new ItemStack(ModItems.MANA_SHORTARROW.get()), Ingredient.of(ModItems.MANA_SHORTARROW.get()), 100));
        consumer.accept(new ManaInfusion(idFor(LibItemNames.FRIED_CHICKEN), new ItemStack(ModItems.FRIED_CHICKEN.get()), Ingredient.of(Items.COOKED_CHICKEN), 600));

        consumer.accept(ManaInfusion.dimension(idFor("enderpearl"), new ItemStack(Items.ENDER_PEARL), Ingredient.of(Items.DIAMOND), 20000));
        consumer.accept(ManaInfusion.dimension(idFor("shulker_shell"), new ItemStack(Items.SHULKER_SHELL), Ingredient.of(Items.DIAMOND_HORSE_ARMOR), 20000));
        consumer.accept(ManaInfusion.dimension(idFor("chorus_fruit"), new ItemStack(Items.CHORUS_FRUIT), Ingredient.of(Items.APPLE), 500));
        consumer.accept(ManaInfusion.dimension(idFor("end_stone"), new ItemStack(Items.END_STONE), Ingredient.of(Items.STONE), 500));
        consumer.accept(ManaInfusion.dimension(idFor("nether_rack"), new ItemStack(Items.NETHERRACK), Ingredient.of(Items.COBBLESTONE), 500));
        consumer.accept(ManaInfusion.dimension(idFor("soul_sand"), new ItemStack(Items.SOUL_SAND), Ingredient.of(Items.SAND), 500));
        consumer.accept(ManaInfusion.dimension(idFor("quartz_ore"), new ItemStack(Items.NETHER_QUARTZ_ORE), Ingredient.of(Items.IRON_ORE), 2000));
        consumer.accept(ManaInfusion.dimension(idFor("blaze_rod"), new ItemStack(Items.BLAZE_ROD, 2), Ingredient.of(Items.BLAZE_ROD), 20000));
        consumer.accept(ManaInfusion.dimension(idFor("totem_of_undying"), new ItemStack(Items.TOTEM_OF_UNDYING), Ingredient.of(Items.NETHER_STAR), 50000));
        consumer.accept(ManaInfusion.dimension(idFor("elytra"), new ItemStack(Items.ELYTRA), Ingredient.of(ModItems.THE_ORIGIN.get()), 50000));

    }

    private static ResourceLocation idFor(String s)
    {
        return new ResourceLocation(ExtraBotany.MODID, "mana_infusion/" + s);
    }

    //to show it is related to manapool(mana infusion)
    protected static class ManaInfusion implements FinishedRecipe
    {

        private static final StateIngredient CONJURATION = StateIngredientHelper.of(ModBlocks.conjurationCatalyst);
        private static final StateIngredient ALCHEMY = StateIngredientHelper.of(ModBlocks.alchemyCatalyst);
        private static final StateIngredient DIMENSION = StateIngredientHelper.of(chick.extrabotany.common.ModBlocks.DIMENSION_CATALYST.get());

        private final ResourceLocation id;
        private final Ingredient input;
        private final ItemStack output;
        private final int mana;
        private final String group;
        @Nullable
        private final StateIngredient catalyst;

        public ManaInfusion(ResourceLocation id, ItemStack output, Ingredient input, int mana)
        {
            this(id, output, input, mana, "", null);
        }

        public ManaInfusion(ResourceLocation id, ItemStack output, Ingredient input, int mana, String group, @Nullable StateIngredient catalyst)
        {
            this.id = id;
            this.input = input;
            this.output = output;
            this.mana = mana;
            this.group = group;
            this.catalyst = catalyst;
        }

        public static ManaInfusion conjuration(ResourceLocation id, ItemStack output, Ingredient input, int mana)
        {
            return new ManaInfusion(id, output, input, mana, "", CONJURATION);
        }

        public static ManaInfusion alchemy(ResourceLocation id, ItemStack output, Ingredient input, int mana)
        {
            return new ManaInfusion(id, output, input, mana, "", ALCHEMY);
        }

        public static ManaInfusion dimension(ResourceLocation id, ItemStack output, Ingredient input, int mana)
        {
            return new ManaInfusion(id, output, input, mana, "", DIMENSION);
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", input.toJson());
            json.add("output", ItemNBTHelper.serializeStack(output));
            json.addProperty("mana", mana);
            if (!group.isEmpty())
            {
                json.addProperty("group", group);
            }
            if (catalyst != null)
            {
                json.add("catalyst", catalyst.serialize());
            }
        }

        @Override
        public ResourceLocation getId()
        {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipeTypes.MANA_INFUSION_SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement()
        {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId()
        {
            return null;
        }
    }
}
