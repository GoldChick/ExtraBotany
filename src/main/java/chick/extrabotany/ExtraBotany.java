package chick.extrabotany;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.Registration;
import chick.extrabotany.forge.client.handler.ConfigHandler;
import com.mojang.logging.LogUtils;
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

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME)
    {
        @Override
        public @NotNull ItemStack makeIcon()
        {
            return new ItemStack(ModItems.PYLON.get());
        }
    };

    public ExtraBotany()
    {
        //register
        Registration.initRegistration();
        //create config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
    }
}
