package chick.extrabotany.forge;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.*;
import chick.extrabotany.common.baubles.relic.CoreGod;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.blocks.ModTiles;
import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import chick.extrabotany.common.brews.ModBrews;
import chick.extrabotany.network.NetworkHandler;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = ExtraBotany.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class ForgeCommonInitializer
{
    //@SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent evt)
    {
        registerEvents();

        PatchouliAPI.get().registerMultiblock(Registry.BLOCK.getKey(ModBlocks.DIMENSION_CATALYST.get()), TileDimensionCatalyst.MULTIBLOCK.get());
    }

    private static void registerEvents()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addGenericListener(BlockEntity.class, CommonCapabilityInit::attachBlockEntityCaps);
        bus.addGenericListener(ItemStack.class, CommonCapabilityInit::attachItemStackCaps);
        NetworkHandler.registerMessage();

        bus.addListener((PlayerEvent.PlayerLoggedOutEvent e) -> CoreGod.playerLoggedOut((ServerPlayer) e.getPlayer()));
     }

    public static void registryInit()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.getBlocks().register(bus);
        ModBlocks.getItems().register(bus);

        ModItems.getItems().register(bus);

        ModEffects.getEffects().register(bus);
        //potions and brews
        ModBrews.registerBrews();

        //tiles
        bind(ForgeRegistries.BLOCK_ENTITIES, ModTiles::registerTiles);
        //flowers(subtiles)
        bind(ForgeRegistries.BLOCKS, ModSubtiles::registerBlocks);
        bind(ForgeRegistries.ITEMS, ModSubtiles::registerItemBlocks);
        bind(ForgeRegistries.BLOCK_ENTITIES, ModSubtiles::registerTEs);
        //entities
        bind(ForgeRegistries.ENTITIES, ModEntities::registerEntities);
        bus.addListener((EntityAttributeCreationEvent e) -> ModEntities.registerAttributes((type, builder) -> e.put(type, builder.build())));
        //recipe
        bind(ForgeRegistries.RECIPE_SERIALIZERS, ModItems::registerRecipeSerializers);
        //sounds
        bind(ForgeRegistries.SOUND_EVENTS, ModSounds::init);

        // Anything that touches vanilla registries needs to happen during *a* registry event
        // So just use a random one

        //bus.addGenericListener(Block.class, (RegistryEvent.Register<Block> e) -> ModLootModifiers.init());
    }

    public static <T extends IForgeRegistryEntry<T>> void bind(IForgeRegistry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source)
    {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(registry.getRegistrySuperType(),
                (RegistryEvent.Register<T> event) ->
                {
                    IForgeRegistry<T> forgeRegistry = event.getRegistry();
                    source.accept((t, rl) ->
                    {
                        t.setRegistryName(rl);
                        forgeRegistry.register(t);
                    });
                });
    }
}
