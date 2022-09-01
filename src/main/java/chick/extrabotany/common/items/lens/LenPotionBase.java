package chick.extrabotany.common.items.lens;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

import javax.annotation.Nonnull;

import java.util.List;
import java.util.Map;


public class LenPotionBase extends ItemLens implements IBrewItem
{
    private static final String TAG_BREW_KEY = "brewKey";

    public LenPotionBase(Properties builder, Lens lens, int props)
    {
        super(builder, lens, props);
    }

    @Override
    public Brew getBrew(ItemStack brew)
    {
        String key = ItemNBTHelper.getString(brew, TAG_BREW_KEY, "");
        return BotaniaAPI.instance().getBrewRegistry().get(ResourceLocation.tryParse(key));
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack)
    {
        return new TranslatableComponent(getDescriptionId(), new TranslatableComponent(getBrew(stack).getTranslationKey(stack)));
    }

    @OnlyIn(Dist.CLIENT)
    public void addPotionTooltip(List<MobEffectInstance> list, List<Component> lores, float durationFactor)
    {
        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();
        if (list.isEmpty())
        {
            lores.add((new TranslatableComponent("effect.none")).withStyle(ChatFormatting.GRAY));
        } else
        {
            for (MobEffectInstance effectinstance : list)
            {
                MutableComponent iformattabletextcomponent = new TranslatableComponent(effectinstance.getDescriptionId());
                MobEffect effect = effectinstance.getEffect();
                Map<Attribute, AttributeModifier> map = effect.getAttributeModifiers();
                if (!map.isEmpty())
                {
                    for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet())
                    {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierValue(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                        list1.add(new Pair<>(entry.getKey(), attributemodifier1));
                    }
                }

                if (effectinstance.getAmplifier() > 0)
                {
                    iformattabletextcomponent = new TranslatableComponent("potion.withAmplifier", iformattabletextcomponent, new TranslatableComponent("potion.potency." + (effectinstance.getAmplifier())));
                }

                if (effectinstance.getDuration() > 20)
                {
                    iformattabletextcomponent = new TranslatableComponent("potion.withDuration", iformattabletextcomponent, MobEffectUtil.formatDuration(new MobEffectInstance(effectinstance.getEffect(), (effectinstance.getDuration() / 3)), durationFactor));
                }

                lores.add(iformattabletextcomponent.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        }

        if (!list1.isEmpty())
        {
            lores.add(TextComponent.EMPTY);
            lores.add((new TranslatableComponent("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : list1)
            {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL)
                {
                    d1 = attributemodifier2.getAmount();
                } else
                {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D)
                {
                    lores.add((new TranslatableComponent("attribute.modifier.plus." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslatableComponent(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
                } else if (d0 < 0.0D)
                {
                    d1 = d1 * -1.0D;
                    lores.add((new TranslatableComponent("attribute.modifier.take." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslatableComponent(pair.getFirst().getDescriptionId()))).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flags)
    {
        addPotionTooltip(getBrew(stack).getPotionEffects(stack), list, 1);
    }
    /*
    public static void setBrew(ItemStack stack, @Nullable Brew brew)
    {
        ResourceLocation id;
        if (brew != null)
        {
            id = BotaniaAPI.instance().getBrewRegistry().getKey(brew);
        } else
        {
            id = prefix("fallback");
        }
        setBrew(stack, id);
    }

    public static void setBrew(ItemStack stack, ResourceLocation brew)
    {
        ItemNBTHelper.setString(stack, TAG_BREW_KEY, brew.toString());
    }
    */
}
