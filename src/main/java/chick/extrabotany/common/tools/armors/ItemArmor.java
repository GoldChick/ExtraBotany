package chick.extrabotany.common.tools.armors;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.helper.ItemNBTHelper;

public abstract class ItemArmor extends ArmorItem implements IPhantomInkable
{
    private static final String TAG_PHANTOM_INK = "phantomInk";
    private final String armorTexture;
    public ItemArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties, String armorTexture)
    {
        super(material, slot, properties);
        this.armorTexture = armorTexture;
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return hasPhantomInk(stack) ? LibResources.MODEL_INVISIBLE_ARMOR : armorTexture;
    }
    @Override
    public boolean hasPhantomInk(ItemStack stack)
    {
        return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
    }

    @Override
    public void setPhantomInk(ItemStack stack, boolean ink)
    {
        ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, ink);
    }
}
