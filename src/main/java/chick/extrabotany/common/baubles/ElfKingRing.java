package chick.extrabotany.common.baubles;

import chick.extrabotany.common.baubles.relic.AbstractMultiUpgradedBauble;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.xplat.IXplatAbstractions;


public class ElfKingRing extends AbstractMultiUpgradedBauble
{
    public ElfKingRing(Properties props)
    {
        super(props);
    }

    @Override
    public int getMaxAmount()
    {
        return 2;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getEquippedAttributeModifiers(stack));
        ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, new AttributeModifier(getBaubleUUID(stack), "Ring modifier", 0.25, AttributeModifier.Operation.ADDITION));
        return ret;
    }

    @Override
    public int canAddBauble(ItemStack base, ItemStack bauble)
    {
        if (IXplatAbstractions.INSTANCE.findRelic(bauble) != null)
        {
            return -1;
        }
        return super.canAddBauble(base, bauble);
    }
}
