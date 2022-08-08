package chick.extrabotany;

import chick.extrabotany.common.Registration;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
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
    }
}
