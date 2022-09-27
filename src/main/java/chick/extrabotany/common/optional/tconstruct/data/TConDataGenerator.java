package chick.extrabotany.common.optional.tconstruct.data;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.libs.LibItemNames;
import chick.extrabotany.common.optional.EXBOTCompat;
import chick.extrabotany.common.optional.tconstruct.TConCompat;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class TConDataGenerator
{
    public static class TConFluidTagGenerator extends FluidTagsProvider
    {

        public TConFluidTagGenerator(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper)
        {
            super(generator, ExtraBotany.MODID, existingFileHelper);
        }

        @Override
        protected void addTags()
        {
            tagAll(TConCompat.MOLTEN_SHADOWIUM);
            tagAll(TConCompat.MOLTEN_PHOTONIUM);

            // tagLocal(TConCompat.FIERY_ESSENCE);

            this.tag(TinkerTags.Fluids.METAL_TOOLTIPS)
                    .addTag(TConCompat.MOLTEN_SHADOWIUM.getForgeTag())
                    .addTag(TConCompat.MOLTEN_PHOTONIUM.getForgeTag());

            this.tag(TinkerTags.Fluids.CHEAP_METAL_SPILLING)
                    .addTag(TConCompat.MOLTEN_SHADOWIUM.getForgeTag())
                    .addTag(TConCompat.MOLTEN_PHOTONIUM.getForgeTag());
        }

        private void tagLocal(FluidObject<?> fluid)
        {
            tag(fluid.getLocalTag())
                    .addOptional(fluid.getStill().getRegistryName())
                    .addOptional(fluid.getFlowing().getRegistryName());
        }

        private void tagAll(FluidObject<?> fluid)
        {
            tagLocal(fluid);
            tag(fluid.getForgeTag()).addOptionalTag(fluid.getLocalTag().location());
        }
    }


    public static class EXBOTMaterialDatagen extends AbstractMaterialDataProvider
    {
        public EXBOTMaterialDatagen(DataGenerator gen)
        {
            super(gen);
        }

        @Override
        protected void addMaterials()
        {
            addMaterial(TConCompat.NIGHTMARE, 2, ORDER_WEAPON, true);
            addMaterial(TConCompat.SPIRIT, 2, ORDER_WEAPON, true);
            addMaterial(TConCompat.SHADOWIUM, 3, ORDER_WEAPON, false);
            addMaterial(TConCompat.PHOTONIUM, 3, ORDER_WEAPON, false);
            addMaterial(TConCompat.ORICHALCOS, 4, ORDER_WEAPON, false);
        }

        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Materials";
        }
    }

    public static class EXBOTMaterialRecipes extends BaseRecipeProvider implements IMaterialRecipeHelper
    {

        public EXBOTMaterialRecipes(DataGenerator generator)
        {
            super(generator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
        {
            materialMeltingCasting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.SHADOWIUM, TConCompat.MOLTEN_SHADOWIUM, true, "tools/materials/");
            materialMeltingCasting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.PHOTONIUM, TConCompat.MOLTEN_SHADOWIUM, true, "tools/materials/");

            metalMaterialRecipe(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.SHADOWIUM, "tools/materials/", LibItemNames.SHADOW_INGOT, false);
            metalMaterialRecipe(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.PHOTONIUM, "tools/materials/", LibItemNames.PHOTON_INGOT, false);

            materialRecipe(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.NIGHTMARE, Ingredient.of(ModItems.NIGHTMARE_FUEL.get()), 1, 1, "tools/materials/" + LibItemNames.NIGHTMARE_FUEL);
            materialRecipe(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.SPIRIT, Ingredient.of(ModItems.SPIRIT_FRAG.get()), 1, 1, "tools/materials/" + LibItemNames.SPIRIT_FUEL);
        }


        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Material Recipes";
        }
    }

    public static class EXBOTMaterialRenders extends AbstractMaterialRenderInfoProvider
    {

        public EXBOTMaterialRenders(DataGenerator gen, @Nullable AbstractMaterialSpriteProvider materialSprites)
        {
            super(gen, materialSprites);
        }

        @Override
        protected void addMaterialRenderInfo()
        {
            buildRenderInfo(TConCompat.ORICHALCOS).fallbacks("metal");

            buildRenderInfo(TConCompat.SHADOWIUM).fallbacks("metal");
            buildRenderInfo(TConCompat.PHOTONIUM).luminosity(15).fallbacks("metal");

            buildRenderInfo(TConCompat.NIGHTMARE);
            buildRenderInfo(TConCompat.SPIRIT);
        }

        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Material Renders";
        }
    }

    public static class EXBOTMaterialSprites extends AbstractMaterialSpriteProvider
    {

        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Material Sprites";
        }

        @Override
        protected void addAllMaterials()
        {
            buildMaterial(TConCompat.PHOTONIUM)
                    .meleeHarvest()
                    .colorMapper(GreyToColorMapping.builder()
                            .addARGB(63, 0xFFFFD83A)
                            .addARGB(102, 0xFFDA7600)
                            .addARGB(140, 0xFF0B0507)
                            .addARGB(178, 0xFF191313)
                            .addARGB(216, 0xFF3A2424)
                            .addARGB(255, 0xFF413333)
                            .build());

            buildMaterial(TConCompat.SHADOWIUM)
                    .meleeHarvest()
                    .colorMapper(GreyToColorMapping.builderFromBlack()
                            .addARGB(63, 0xFF505E4C)
                            .addARGB(102, 0xFF5D7667)
                            .addARGB(140, 0xFF81887D)
                            .addARGB(178, 0xFFAFBAA1)
                            .addARGB(216, 0xFFC4D6AE)
                            .addARGB(255, 0xFFF9FFD6)
                            .build());

            buildMaterial(TConCompat.NIGHTMARE)
                    .statType(HeadMaterialStats.ID)
                    .colorMapper(GreyToColorMapping.builderFromBlack()
                            .addARGB(63, 0xFF0C1708)
                            .addARGB(102, 0x172911)
                            .addARGB(140, 0xFF1B4B4E)
                            .addARGB(178, 0xFF254B29)
                            .addARGB(216, 0xFF325D25)
                            .addARGB(255, 0xFF575E1C)
                            .build());

            buildMaterial(TConCompat.SPIRIT)
                    .meleeHarvest()
                    .colorMapper(GreyToColorMapping.builderFromBlack()
                            .addARGB(63, 0xFF081608)
                            .addARGB(102, 0xFF1A2B11)
                            .addARGB(140, 0xFF27401D)
                            .addARGB(178, 0xFF345E27)
                            .addARGB(216, 0xFF569243)
                            .addARGB(255, 0xFF8CB05A)
                            .build());
        }
    }

    public static class EXBOTMaterialStats extends AbstractMaterialStatsDataProvider
    {

        public EXBOTMaterialStats(DataGenerator gen, AbstractMaterialDataProvider materials)
        {
            super(gen, materials);
        }

        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Material Stats";
        }

        @Override
        protected void addMaterialStats()
        {
            addMaterialStats(TConCompat.ORICHALCOS,
                    new HeadMaterialStats(1145, 14.0F, Tiers.NETHERITE, 2.5F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);


            addMaterialStats(TConCompat.PHOTONIUM,
                    new HeadMaterialStats(720, 6.5F, Tiers.DIAMOND, 3.0F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);

            addMaterialStats(TConCompat.SHADOWIUM,
                    new HeadMaterialStats(900, 6.0F, Tiers.NETHERITE, 2.5F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);

            addMaterialStats(TConCompat.NIGHTMARE,
                    new HeadMaterialStats(460, 9.0F, Tiers.STONE, 2.0F));

            addMaterialStats(TConCompat.SPIRIT,
                    new HeadMaterialStats(180, 6.0F, Tiers.IRON, 2.5F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);
        }
    }

    public static class EXBOTMaterialTraits extends AbstractMaterialTraitDataProvider
    {

        public EXBOTMaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials)
        {
            super(gen, materials);
        }

        @Override
        protected void addMaterialTraits()
        {
            addDefaultTraits(TConCompat.ORICHALCOS, TinkerModifiers.experienced, TinkerModifiers.axeScrape, TinkerModifiers.autosmelt, TinkerModifiers.fiery);

            addDefaultTraits(TConCompat.PHOTONIUM, TinkerModifiers.axeScrape, TinkerModifiers.axeScrape, TinkerModifiers.autosmelt, TinkerModifiers.fiery);
            addDefaultTraits(TConCompat.SHADOWIUM, TinkerModifiers.axeScrape, TinkerModifiers.axeScrape);

            addDefaultTraits(TConCompat.NIGHTMARE, TinkerModifiers.axeScrape, TinkerModifiers.axeScrape);
            addDefaultTraits(TConCompat.SPIRIT, TinkerModifiers.axeScrape, TinkerModifiers.axeScrape);
        }

        @Override
        public @NotNull String getName()
        {
            return "Extrabotany Traits";
        }
    }

    public static class EXBOTSmelteryRecipes extends BaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper
    {

        public EXBOTSmelteryRecipes(DataGenerator generator)
        {
            super(generator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
        {
            // this.metalTagCasting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.MOLTEN_KNIGHTMETAL, "knightmetal", "smeltery/casting/metal/", true);
            // this.metalMelting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.MOLTEN_KNIGHTMETAL.get(), "knightmetal", false, "smeltery/melting/metal", false);
            // this.metalTagCasting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.MOLTEN_FIERY, "fiery", "smeltery/casting/metal/", true);
            // this.metalMelting(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), TConCompat.MOLTEN_FIERY.get(), "fiery", false, "smeltery/melting/metal", false);

            //  MeltingFuelBuilder.fuel(new FluidStack(TConCompat.FIERY_ESSENCE.get(), 50), 300)
            //          .save(withCondition(consumer, new ModLoadedCondition(EXBOTCompat.TCON_ID)), modResource("smeltery/melting/fuel/fiery_essence"));
        }

        @Override
        public String getModId()
        {
            return ExtraBotany.MODID;
        }

        @Override
        public String getName()
        {
            return "Extrabotany Smeltery Recipes";
        }
    }
}
