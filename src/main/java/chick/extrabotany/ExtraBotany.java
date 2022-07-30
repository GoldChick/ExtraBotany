package chick.extrabotany;

import chick.extrabotany.setup.ClientSetup;
import chick.extrabotany.common.Registration;
import chick.extrabotany.setup.ModSetup;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExtraBotany.MODID)
public class ExtraBotany
{
    public static final String MODID="extrabotany";//mod name
    public static final String TAB_NAME="extrabotany";//Name in creative inventury

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME)
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Items.DIAMOND);
        }
    };
    public ExtraBotany()
    {
        Registration.initRegistration();
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::Init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> modbus.addListener(ClientSetup::Init));
    }
}
