package chick.extrabotany.api;

import chick.extrabotany.api.item.mana.IManaRulerItem;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ExtraBotanyForgeCapabilities
{
    public static Capability<IManaRulerItem> MANA_RULER = CapabilityManager.get(new CapabilityToken<>()
    {
    });
}
