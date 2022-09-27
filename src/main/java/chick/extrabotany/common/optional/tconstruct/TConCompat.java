package chick.extrabotany.common.optional.tconstruct;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.libs.LibItemNames;
import chick.extrabotany.common.optional.EXBOTCompat;
import chick.extrabotany.common.optional.tconstruct.data.TConDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;

public class TConCompat extends EXBOTCompat
{
    protected TConCompat()
    {
        super("Tinkers Construct");
    }

    @Override
    protected boolean preInit()
    {
        return true;
    }

    @Override
    protected void init(FMLCommonSetupEvent event)
    {

    }

    @Override
    protected void postInit()
    {

    }

    @Override
    protected void handleIMCs()
    {

    }

    @Override
    protected void initItems(RegistryEvent.Register<Item> evt)
    {

    }

    public static void tConDatagen(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();


        generator.addProvider(new TConDataGenerator.TConFluidTagGenerator(generator, helper));

        generator.addProvider(new TConDataGenerator.EXBOTMaterialRecipes(generator));
        generator.addProvider(new TConDataGenerator.EXBOTSmelteryRecipes(generator));

        AbstractMaterialSpriteProvider sprites = new TConDataGenerator.EXBOTMaterialSprites();
        generator.addProvider(new TConDataGenerator.EXBOTMaterialRenders(generator, sprites));

        AbstractMaterialDataProvider materials = new TConDataGenerator.EXBOTMaterialDatagen(generator);
        generator.addProvider(materials);
        generator.addProvider(new TConDataGenerator.EXBOTMaterialStats(generator, materials));
        generator.addProvider(new TConDataGenerator.EXBOTMaterialTraits(generator, materials));
    }

    //REGISTERED in ForgeCommonInitializer
    //fluids
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(ExtraBotany.MODID);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_SHADOWIUM = FLUIDS.register("molten_" + LibItemNames.SHADOW_INGOT, ModelFluidAttributes.builder().temperature(1000).luminosity(0).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_PHOTONIUM = FLUIDS.register("molten_" + LibItemNames.PHOTON_INGOT, ModelFluidAttributes.builder().temperature(1000).luminosity(15).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);

    //modifiers
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(ExtraBotany.MODID);

    //materials
    public static final MaterialId NIGHTMARE = id(LibItemNames.NIGHTMARE_FUEL);
    public static final MaterialId SPIRIT = id(LibItemNames.SPIRIT_FUEL);
    public static final MaterialId SHADOWIUM = id(LibItemNames.SHADOW_INGOT);
    public static final MaterialId PHOTONIUM = id(LibItemNames.PHOTON_INGOT);
    public static final MaterialId ORICHALCOS = id(LibItemNames.ORICHALCOS);


    /*

//Modifiers
    public static final StaticModifier<PrecipitateModifier> PRECIPITATE = MODIFIERS.register("precipitate", PrecipitateModifier::new);
    public static final StaticModifier<StalwartModifier> STALWART = MODIFIERS.register("stalwart", StalwartModifier::new);
    public static final StaticModifier<SuperheatModifier> SUPERHEAT = MODIFIERS.register("superheat", SuperheatModifier::new);
    public static final StaticModifier<SynergyModifier> SYNERGY = MODIFIERS.register("synergy", SynergyModifier::new);
    public static final StaticModifier<TwilitModifier> TWILIT = MODIFIERS.register("twilit", TwilitModifier::new);



    /**
     * Creates a new material ID
     *
     * @param name ID name
     * @return Material ID object
     */
    private static MaterialId id(String name)
    {
        return new MaterialId(ExtraBotany.MODID, name);
    }
}
