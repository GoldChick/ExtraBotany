package chick.extrabotany;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.base.ConfigHandler;
import chick.extrabotany.common.optional.EXBOTCompat;
import chick.extrabotany.forge.ForgeCommonInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExtraBotany.MODID)
public class ExtraBotany
{
    public static final String MODID = "extrabotany";//mod name

    public static final String TAB_NAME = "extrabotany";//Name in creative inventury

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final CreativeModeTab ITEM_GROUP = (new CreativeModeTab(TAB_NAME)
    {
        @Override
        public boolean showTitle()
        {
            return super.showTitle();
        }

        @Override
        public boolean hasSearchBar()
        {
            return true;
        }

        @Override
        public @NotNull ItemStack makeIcon()
        {
            return new ItemStack(ModItems.PYLON.get());
        }
    }).hideTitle().setBackgroundImage(new ResourceLocation("textures/gui/container/creative_inventory/tab_extrabotany.png"));

    public ExtraBotany()
    {
        //register
        ForgeCommonInitializer.registryInit();
        //setup
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ForgeCommonInitializer::commonSetup);

        //create config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);

        if (ConfigHandler.COMMON.doCompat.get())
        {
            try
            {
                EXBOTCompat.preInitCompat();
            } catch (Exception e)
            {
                ConfigHandler.COMMON.doCompat.set(false);
                LOGGER.error("Had an error loading preInit compatibility!");
                LOGGER.catching(e.fillInStackTrace());
            }
        } else
        {
            LOGGER.warn("Skipping compatibility!");
        }
    }

    @SubscribeEvent
    public static void initCompat(FMLCommonSetupEvent evt)
    {
        if (ConfigHandler.COMMON.doCompat.get())
        {
            try
            {
                EXBOTCompat.initCompat(evt);
            } catch (Exception e)
            {
                ConfigHandler.COMMON.doCompat.set(false);
                LOGGER.error("Had an error loading init compatibility!");
                LOGGER.catching(e.fillInStackTrace());
            }
            try
            {
                EXBOTCompat.postInitCompat();
            } catch (Exception e)
            {
                ConfigHandler.COMMON.doCompat.set(false);
                LOGGER.error("Had an error loading postInit compatibility!");
                LOGGER.catching(e.fillInStackTrace());
            }
        }
    }


    public static String prefix(String string)
    {
        return MODID + ":" + string;
    }

    public static ResourceLocation prefixRl(String string)
    {
        return new ResourceLocation(MODID, string);
    }
}
