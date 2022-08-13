package chick.extrabotany.forge.mixin.client;

import chick.extrabotany.common.tools.armors.ItemArmor;
import chick.extrabotany.forge.client.model.armor.ArmorModels;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(ItemArmor.class)
public abstract class MixinArmorModel extends Item
{
    private MixinArmorModel(Properties props)
    {
        super(props);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties()
        {
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel)
            {
                return ArmorModels.get(stack);
            }
        });
    }
}
