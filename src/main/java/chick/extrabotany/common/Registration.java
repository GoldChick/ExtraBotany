package chick.extrabotany.common;

import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.brews.ModBrews;
import chick.extrabotany.common.tools.weapons.ShadowKatana;
import chick.extrabotany.forge.client.model.MiscellaneousIcons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Registration
{
    public static void initRegistration()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.GetBlocks().register(bus);
        ModBlocks.GetItems().register(bus);

        ModItems.GetItems().register(bus);

        //flowers
        bind(ForgeRegistries.BLOCKS, ModSubtiles::registerBlocks);
        bind(ForgeRegistries.ITEMS, ModSubtiles::registerItemBlocks);
        bind(ForgeRegistries.BLOCK_ENTITIES, ModSubtiles::registerTEs);
        //potions and brews
        ModBrews.registerBrews();
        //entities
        bind(ForgeRegistries.ENTITIES, ModEntities::registerEntities);
        bus.addListener((EntityAttributeCreationEvent e) -> ModEntities.registerAttributes((type, builder) -> e.put(type, builder.build())));
        //recipe
        bind(ForgeRegistries.RECIPE_SERIALIZERS, ModItems::registerRecipeSerializers);
        //sounds
        bind(ForgeRegistries.SOUND_EVENTS, ModSounds::init);
        initEvents();
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


    private static void initEvents()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener((PlayerInteractEvent.RightClickItem event) -> ShadowKatana.attackEntity(event.getPlayer(), event.getHand(), event.getItemStack()));
    }
}
