package chick.extrabotany.api;

import chick.extrabotany.api.block.ISubTilePassiveFlower;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

/**
 * it has nothing now lol
 */
public class ExtraBotanyForgeCapabilities
{
    public static final Capability<ISubTilePassiveFlower> PASSIVE_FLOWER = CapabilityManager.get(new CapabilityToken<>() {});

}
