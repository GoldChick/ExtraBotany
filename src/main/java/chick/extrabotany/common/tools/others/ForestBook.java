package chick.extrabotany.common.tools.others;

import chick.extrabotany.common.base.DamageHandler;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class ForestBook extends Item
{
    public ForestBook(Properties prop)
    {
        super(prop);
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        if (ctx.getPlayer() != null && !ctx.getLevel().isClientSide && ctx.getPlayer().getHealth() > 0)
        {
            //TODO应该有一个buff标记，以免二次使用
            if (ctx.getPlayer().getAbsorptionAmount() < 10F)
            {
                ctx.getPlayer().hurt(DamageSource.MAGIC.bypassArmor().bypassInvul().bypassMagic(), 10.0F);
                ctx.getPlayer().setAbsorptionAmount(ctx.getPlayer().getAbsorptionAmount() + 10.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }
}
