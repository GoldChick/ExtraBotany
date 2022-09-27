package chick.extrabotany.common.optional;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.optional.tconstruct.TConCompat;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <b style=color:rgb(0,255,255)>copied from twilight forest</b><p>
 * I was having an issue where the game refused to load with the enum method and I couldnt figure it out, so I moved to a new method of registering compat
 * </p>It works the same as it used to, but all the content for each mod should be in its own class.
 **/

public abstract class EXBOTCompat
{
    public static final String TCON_ID = "tconstruct";
    public static final String TWILIGHT_FOREST = "twilightforest";

    public static HashMap<String, Class<? extends EXBOTCompat>> classes = new HashMap<>();
    public static Set<EXBOTCompat> modules = new HashSet<>();

    static
    {
        classes.put(TCON_ID, TConCompat.class);
    }

    protected EXBOTCompat(String modName)
    {
        this.modName = modName;
    }

    public static void preInitCompat()
    {
        for (Map.Entry<String, Class<? extends EXBOTCompat>> entry : classes.entrySet())
        {
            if (ModList.get().isLoaded(entry.getKey()))
            {
                try
                {
                    EXBOTCompat compat = entry.getValue().newInstance();
                    modules.add(compat);
                    compat.isActivated = compat.preInit();

                    if (compat.isActivated)
                    {
                        ExtraBotany.LOGGER.info("Loaded compatibility for mod {}.", compat.modName);
                    } else
                    {
                        ExtraBotany.LOGGER.warn("Couldn't activate compatibility for mod {}!", compat.modName);
                    }
                } catch (Exception e)
                {
                    ExtraBotany.LOGGER.error("Had a {} error loading {} compatibility in preInit!", e.getLocalizedMessage(), entry.getKey());
                    ExtraBotany.LOGGER.catching(e.fillInStackTrace());
                }
            } else
            {
                ExtraBotany.LOGGER.info("Skipped compatibility for mod {}.", entry.getKey());
            }
        }
    }

    public static void initCompat(FMLCommonSetupEvent event)
    {
        for (EXBOTCompat compat : modules)
        {
            if (compat.isActivated)
            {
                try
                {
                    compat.init(event);
                } catch (Exception e)
                {
                    compat.isActivated = false;
                    ExtraBotany.LOGGER.error("Had a {} error loading {} compatibility in init!", e.getLocalizedMessage(), compat.modName);
                    ExtraBotany.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }

    public static void initCompatItems(RegistryEvent.Register<Item> evt)
    {
        for (EXBOTCompat compat : modules)
        {
            if (compat.isActivated)
            {
                try
                {
                    compat.initItems(evt);
                } catch (Exception e)
                {
                    compat.isActivated = false;
                    ExtraBotany.LOGGER.error("Had a {} error loading {} compatibility in initializing items!", e.getLocalizedMessage(), compat.modName);
                    ExtraBotany.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }

    public static void postInitCompat()
    {
        for (EXBOTCompat compat : modules)
        {
            if (compat.isActivated)
            {
                try
                {
                    compat.postInit();
                } catch (Exception e)
                {
                    compat.isActivated = false;
                    ExtraBotany.LOGGER.error("Had a {} error loading {} compatibility in postInit!", e.getLocalizedMessage(), compat.modName);
                    ExtraBotany.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }

    public static void sendIMCs()
    {
        for (EXBOTCompat compat : modules)
        {
            if (compat.isActivated)
            {
                try
                {
                    compat.handleIMCs();
                } catch (Exception e)
                {
                    compat.isActivated = false;
                    ExtraBotany.LOGGER.error("Had a {} error loading {} compatibility in sending IMCs!", e.getLocalizedMessage(), compat.modName);
                    ExtraBotany.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }

    protected abstract boolean preInit();

    protected abstract void init(FMLCommonSetupEvent event);

    protected abstract void postInit();

    protected abstract void handleIMCs();

    protected abstract void initItems(RegistryEvent.Register<Item> evt);

    public final String modName;

    private boolean isActivated = false;
}
