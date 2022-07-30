package chick.extrabotany.datagen.recipes;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
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
        consumer.accept(new ManaInfusion(idFor("ironingot"), new ItemStack(Items.IRON_INGOT), Ingredient.of(Items.OBSIDIAN), 10000));
        consumer.accept(ManaInfusion.conjuration(idFor("obsidian_dupe"), new ItemStack(Items.OBSIDIAN, 2), Ingredient.of(Items.OBSIDIAN), 10000));
        consumer.accept(ManaInfusion.test(idFor("test"), new ItemStack(Items.OBSIDIAN, 64), Ingredient.of(Items.OBSIDIAN), 10000));

        consumer.accept(new ManaInfusion(idFor("nightmarefuel"), new ItemStack(ModItems.NIGHTMAREFUEL_PROP.get()), Ingredient.of(Items.COAL), 100));
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
        private static final StateIngredient TEST = StateIngredientHelper.of(chick.extrabotany.common.ModBlocks.YLG_ORE.get());
        //private static final StateIngredient DIMENSION = StateIngredientHelper.of(com.meteor.extrabotany.common.blocks.ModBlocks.dimensioncatalyst);

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
        public static ManaInfusion test(ResourceLocation id, ItemStack output, Ingredient input, int mana)
        {
            return new ManaInfusion(id, output, input, mana, "", TEST);
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
