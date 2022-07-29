package chick.extrabotania;

import chick.extrabotania.setup.ClientSetup;
import chick.extrabotania.common.Registration;
import chick.extrabotania.setup.ModSetup;
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
@Mod(ExtraBotania.MODID)
public class ExtraBotania
{
    public static final String MODID="extrabotania";//mod name
    public static final String TAB_NAME="extrabotania";//Name in creative inventury

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME)
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Items.DIAMOND);
        }
    };
    public ExtraBotania()
    {
        Registration.Init();
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::Init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> modbus.addListener(ClientSetup::Init));
    }
}
