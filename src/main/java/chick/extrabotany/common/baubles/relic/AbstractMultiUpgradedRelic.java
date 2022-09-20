package chick.extrabotany.common.baubles.relic;

import chick.extrabotany.api.advancement.IAdvancementRequirement;
import chick.extrabotany.common.libs.LibAdvancementNames;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractMultiUpgradedRelic extends ItemBauble implements IAdvancementRequirement
{
    private static final String TAG_BAUBLE_NBT = "tag_bauble_nbt_";

    private static final int MAX_AMOUNT = 6;

    public AbstractMultiUpgradedRelic(Properties props)
    {
        super(props);
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack)
    {
        return Rarity.EPIC;
    }

    public boolean addBauble(ItemStack base, Item bauble)
    {
        return addBauble(base, new ItemStack(bauble));
    }

    public boolean addBauble(ItemStack base, ItemStack bauble)
    {
        int check = canAddBauble(base, bauble);
        if (check >= 0)
        {
            var base_cmp = ItemNBTHelper.getCompound(base, TAG_BAUBLE_NBT, false);

            base_cmp.put(TAG_BAUBLE_NBT + check, bauble.save(new CompoundTag()));

            ItemNBTHelper.setCompound(base, TAG_BAUBLE_NBT, base_cmp);

            return true;
        }
        return false;
    }

    /**
     * @return 0 - <b style="color:rgb(0,255,255)">(MAX_AMOUNT-1)</b>(default 6-1=5) represents SUCCESS<p>
     * -1 represents FAILED
     */
    public int canAddBauble(ItemStack base, ItemStack bauble)
    {
        if (bauble.getTags().anyMatch(b -> getTagKeys().contains(b)))
        {
            var base_cmp = ItemNBTHelper.getCompound(base, TAG_BAUBLE_NBT, false);

            if (base_cmp.size() < MAX_AMOUNT)
            {
                for (int i = 0; i < MAX_AMOUNT; i++)
                {
                    if (base_cmp.getCompound(TAG_BAUBLE_NBT + i).isEmpty())
                    {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public List<ItemStack> getBaubles(ItemStack stack)
    {
        var base_cmp = ItemNBTHelper.getCompound(stack, TAG_BAUBLE_NBT, false);
        List<ItemStack> rings = new ArrayList<>();
        for (int i = 0; i < base_cmp.size(); i++)
        {
            var ring = ItemStack.of(base_cmp.getCompound(TAG_BAUBLE_NBT + i));

            if (!ring.isEmpty())
            {
                rings.add(ring);
            } else
            {
                base_cmp.remove(TAG_BAUBLE_NBT + i);
                var player = Minecraft.getInstance().player;
                if (player != null)
                {
                    //TODO: 删除失效的item 缺少文字
                    player.sendMessage(new TextComponent("A Bauble Has Been Removed From Sun Ring").withStyle(ChatFormatting.GOLD), Util.NIL_UUID);
                }
            }
        }
        return rings;
    }

    public void removeLastBauble(ItemStack stack)
    {
        removeBauble(stack, getBaublesAmount(stack) - 1);
    }

    public void removeBauble(ItemStack stack, int index)
    {
        var base_cmp = ItemNBTHelper.getCompound(stack, TAG_BAUBLE_NBT, false);
        base_cmp.remove(TAG_BAUBLE_NBT + index);
    }

    /**
     * just return amount<p>
     * do not generate ItemStack
     *
     * @param stack base_bauble
     */
    public int getBaublesAmount(ItemStack stack)
    {
        int amount = 0;
        var base_cmp = ItemNBTHelper.getCompound(stack, TAG_BAUBLE_NBT, false);
        for (int i = 0; i < MAX_AMOUNT; i++)
        {
            if (!base_cmp.getCompound(TAG_BAUBLE_NBT + i).isEmpty())
            {
                ++amount;
            }
        }
        return amount;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, level, tooltip, flags);
        for (var bauble : getBaubles(stack))
        {
            tooltip.add(((MutableComponent) bauble.getItem().getName(stack)).withStyle(bauble.getRarity().color));
        }
    }

    @Override
    public void onWornTick(ItemStack base, LivingEntity player)
    {
        super.onWornTick(base, player);
        for (var item : getBaubles(base))
        {
            if (item.getItem() instanceof ItemBauble bauble)
            {
                bauble.onWornTick(item, player);
            }
        }
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity)
    {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack)
    {
        var map = HashMultimap.create(super.getEquippedAttributeModifiers(stack));
        for (var item : getBaubles(stack))
        {
            if (item.getItem() instanceof ItemBauble bauble)
            {
                map.putAll(bauble.getEquippedAttributeModifiers(item));
            }
        }
        return super.getEquippedAttributeModifiers(stack);
    }

    public List<TagKey<Item>> getTagKeys()
    {
        return new ArrayList<>();
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
