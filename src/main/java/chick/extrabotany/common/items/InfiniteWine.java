package chick.extrabotany.common.items;

import chick.extrabotany.common.brews.ItemBrewBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.ICustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.UUID;
import java.util.function.Consumer;

import static chick.extrabotany.common.ModItems.*;

public class InfiniteWine extends ItemBrewBase implements IRelic, ICustomDamageItem
{
    private static final String TAG_SOULBIND_UUID = "soulbindUUID";
    private static final int MANA_PER_DAMAGE = 12000;

    public InfiniteWine(Properties builder)
    {
        super(builder, 12, 18, () -> EMPTY_BOTTLE.get(), 1.5F, 1);
    }

    @Override
    public void bindToUUID(UUID uuid)
    {
        ItemNBTHelper.setString(getBaseStack(), TAG_SOULBIND_UUID, uuid.toString());
    }

    @Nullable
    @Override
    public UUID getSoulbindUUID()
    {
        if (ItemNBTHelper.verifyExistance(getBaseStack(), TAG_SOULBIND_UUID))
        {
            try
            {
                return UUID.fromString(ItemNBTHelper.getString(getBaseStack(), TAG_SOULBIND_UUID, ""));
            } catch (IllegalArgumentException ex)
            { // Bad UUID in tag
                ItemNBTHelper.removeEntry(getBaseStack(), TAG_SOULBIND_UUID);
            }
        }
        return null;
    }

    @Override
    public void tickBinding(Player player)
    {

    }

    @Override
    public boolean shouldDamageWrongPlayer()
    {
        return true;
    }

    @Override
    public boolean isRightPlayer(Player player)
    {
        return player.getUUID().equals(getSoulbindUUID());
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, MANA_PER_DAMAGE);
    }
}
