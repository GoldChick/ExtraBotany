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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
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

/**
 * abstract class for baubles that can be crafted with others to obtain their abilities<p>
 * note that the base bauble will only be able to get the <b style=color:rgb(0,255,255)>onWornTick()</b> of others
 */
public abstract class AbstractMultiUpgradedBauble extends ItemBauble implements IAdvancementRequirement
{
    private static final String TAG_BAUBLE_NBT = "tag_bauble_nbt_";

    public AbstractMultiUpgradedBauble(Properties props)
    {
        super(props);
    }

    public int getMaxAmount()
    {
        return 8;
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack)
    {
        return Rarity.EPIC;
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
     * @return 0 - <b style="color:rgb(0,255,255)">(MAX_AMOUNT-1)</b> represents SUCCESS<p>
     * -1 represents FAILED
     */
    public int canAddBauble(ItemStack base, ItemStack bauble)
    {
        if (bauble.getTags().anyMatch(b -> getTagKeys().contains(b)))
        {
            var base_cmp = ItemNBTHelper.getCompound(base, TAG_BAUBLE_NBT, false);

            if (base_cmp.size() < getMaxAmount())
            {
                for (int i = 0; i < getMaxAmount(); i++)
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

    /**
     * remove bauble from index
     *
     * @param index start from ZERO to MAX_AMOUNT-1<p>
     */
    public void removeBauble(ItemStack base, int index)
    {
        index = Mth.clamp(index, 0, getBaublesAmount(base) - 1);
        var base_cmp = ItemNBTHelper.getCompound(base, TAG_BAUBLE_NBT, false);
        int j = 0;
        for (int i = 0; i < getMaxAmount(); i++)
        {
            var cmp = base_cmp.getCompound(TAG_BAUBLE_NBT + i);
            if (!cmp.isEmpty())
            {
                if (j == index)
                {
                    base_cmp.remove(TAG_BAUBLE_NBT + i);
                    break;
                }
                j++;
            }
        }
    }

    /**
     * just return amount<p>
     * do not generate ItemStack
     *
     * @param base base_bauble
     */
    public int getBaublesAmount(ItemStack base)
    {
        int amount = 0;
        var base_cmp = ItemNBTHelper.getCompound(base, TAG_BAUBLE_NBT, false);
        for (int i = 0; i < getMaxAmount(); i++)
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

    /**
     * default is <b style=color:rgb(0,255,255)>curios:ring</b><p>
     * you can override it if you want to add or change
     */
    public List<TagKey<Item>> getTagKeys()
    {
        return List.of(ItemTags.create(new ResourceLocation("curios", "ring")));
    }

    @Override
    public String getAdvancementName()
    {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
