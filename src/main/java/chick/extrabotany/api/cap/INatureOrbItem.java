package chick.extrabotany.api.cap;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * An item that has this capability can contain nature.
 */
public interface INatureOrbItem
{
    int getNature();

    int getMaxNature();

    void addNature(int x);

    boolean canReceiveNatureFromNatureAdder(BlockEntity adder);

    boolean canReceiveManaFromItem(ItemStack otherStack);

    boolean canExportManaToPool(BlockEntity pool);

    boolean canExportManaToItem(ItemStack otherStack);

    boolean isNoExport();
}
