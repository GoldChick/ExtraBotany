package chick.extrabotany.common.items;

import chick.extrabotany.api.BonusHelper;
import chick.extrabotany.api.WeightCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class RewardBag extends Item
{
    List<WeightCategory> categoryList;

    public RewardBag(Properties prop, List<WeightCategory> categoryList)
    {
        super(prop);
        this.categoryList = categoryList;
    }

    @NotNull
    @Override
    public InteractionResult useOn(@NotNull UseOnContext context)
    {
        var player = context.getPlayer();
        if (player != null)
        {
            var level = player.level;

            var reward = BonusHelper.rollItem(categoryList);

            if (!reward.isEmpty() && !level.isClientSide)
            {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
                player.drop(reward, true).setNoPickUpDelay();
                if (!player.isCreative())
                {
                    context.getItemInHand().shrink(1);
                }
            }
        }
        return InteractionResult.PASS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags)
    {
        super.appendHoverText(stack, level, list, flags);
        DecimalFormat df = new DecimalFormat("0.00%");
        int sum = BonusHelper.sum(categoryList);
        for (WeightCategory category : categoryList)
        {
            String percentage = df.format((float) category.getWeight() / sum);

            var stackname = new TranslatableComponent(category.getCategory().getDescriptionId()).getString();

            int count = category.getCategory().getCount();

            var color = (float) category.getWeight() / sum <= 0.01F ? ChatFormatting.GOLD : ChatFormatting.RESET;
            list.add(new TextComponent(String.format("%s x%d %s", stackname, count, percentage)).withStyle(color));
        }
    }
}
