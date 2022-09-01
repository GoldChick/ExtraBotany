package chick.extrabotany.common.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class DivineJustice extends MobEffect
{
    public DivineJustice()
    {
        super(MobEffectCategory.HARMFUL, 0XFF7F50);
        addAttributeModifier(Attributes.MAX_HEALTH, "FB353E1C-4180-4865-B01B-BCCE9785ACA3", -0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified)
    {
        living.hurt(DamageSource.MAGIC.bypassArmor().bypassInvul().bypassMagic(), amplified * 2);
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return Lists.newArrayList();
    }
}
