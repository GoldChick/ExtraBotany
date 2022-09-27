package chick.extrabotany.api.cap;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import vazkii.botania.api.mana.IManaItem;

/**
 * it has nothing now lol
 */
public class EXBOTCapabilities
{
    public static final Capability<IPassiveFlowerCap> PASSIVE_FLOWER = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<INatureOrbItem> NATURE_ORB = CapabilityManager.get(new CapabilityToken<>() {});

}
