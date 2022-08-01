package chick.extrabotany.common.bauble;

import chick.extrabotany.common.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vazkii.botania.api.mana.ManaDiscountEvent;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class SupremeAquaStone extends ItemBauble
{
    public SupremeAquaStone(Properties props)
    {
        super(props);
    }

    @SubscribeEvent
    public void manaDiscount(ManaDiscountEvent event)
    {
        Player player = event.getEntityPlayer();
        if (!EquipmentHandler.findOrEmpty(this, player).isEmpty())
        {
            event.setDiscount(event.getDiscount() + 0.3F);
        }
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.SUPREME_IGNIS_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.IGNIS_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.AQUA_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ModItems.FOUR_IN_ONE_STONE.get(), entity).isEmpty()
                ;
    }


}
