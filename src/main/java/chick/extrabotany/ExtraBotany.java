package chick.extrabotany;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.Registration;
import chick.extrabotany.common.base.ConfigHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExtraBotany.MODID)
public class ExtraBotany
{
    public static final String MODID = "extrabotany";//mod name

    public static final String TAB_NAME = "extrabotany";//Name in creative inventury

    public static final Logger LOGGER = LogUtils.getLogger();

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
        Registration.initRegistration();
        //create config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
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
