package chick.extrabotany.common.items;

import chick.extrabotany.api.BonusHelper;
import chick.extrabotany.api.WeightCategory;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Fates extends Item
{
    public static final List<WeightCategory> categoryListIntertwined = Arrays.asList(
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaSteel, 4), 15),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaPearl, 4), 15),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaDiamond, 4), 15),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.elementium, 3), 11),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.pixieDust, 3), 11),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.dragonstone, 3), 11),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.manaPowder, 8), 10),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.terrasteel, 1), 9),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.lifeEssence, 4), 8),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.gaiaIngot, 1), 7),
            new WeightCategory(new ItemStack(ModItems.HERO_MEDAL.get(), 1), 2),

            new WeightCategory(new ItemStack(Items.COAL, 6), 40),
            new WeightCategory(new ItemStack(Items.IRON_INGOT, 4), 36),
            new WeightCategory(new ItemStack(Items.GOLD_INGOT, 4), 24),
            new WeightCategory(new ItemStack(Items.REDSTONE, 8), 22),
            new WeightCategory(new ItemStack(Items.ENDER_PEARL, 4), 20),
            new WeightCategory(new ItemStack(Items.DIAMOND, 1), 18),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.blackerLotus, 2), 16),
            new WeightCategory(new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed, 1), 12)

            //TODO: 虚空万藏

            //new WeightCategory(new ItemStack(ModItems.BUDDHISTRELICS.get()), 1)
    );
    public static final List<WeightCategory> categoryListAcquaint = Arrays.asList(
            new WeightCategory(new ItemStack(ModItems.UNIVERSAL_PETAL.get(), 4), 10),
            new WeightCategory(new ItemStack(ModItems.UNIVERSAL_PETAL.get(), 8), 10),
            new WeightCategory(new ItemStack(ModItems.UNIVERSAL_PETAL.get(), 6), 30),

            new WeightCategory(new ItemStack(ModItems.ELEMENT_RUNE.get(), 2), 80),
            new WeightCategory(new ItemStack(ModItems.SIN_RUNE.get(), 1), 20),
            new WeightCategory(new ItemStack(ModItems.HERO_MEDAL.get(), 1), 2)
    );
    private final List<WeightCategory> categoryList;
    private final Map<WeightCategory, Float> chanceMap;

    public enum FateType
    {
        Intertwined,
        Acquaint
    }

    public Fates(Properties prop, FateType type)
    {
        super(prop);
        categoryList = type == FateType.Intertwined ? categoryListIntertwined : categoryListAcquaint;
        chanceMap = BonusHelper.getItemChance(categoryList);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        var blockentity = ctx.getLevel().getBlockEntity(ctx.getClickedPos());
        if (blockentity instanceof TileDimensionCatalyst dimensionCatalyst)
        {
            return dimensionCatalyst.wish(ctx.getPlayer(), ctx.getItemInHand(), categoryList, chanceMap)
                    ? InteractionResult.SUCCESS
                    : InteractionResult.FAIL;
        }
        return InteractionResult.FAIL;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags)
    {
        super.appendHoverText(stack, level, list, flags);
        DecimalFormat df = new DecimalFormat("0.00%");
        for (var category : categoryList)
        {
            var percentage = df.format(chanceMap.getOrDefault(category, 0F).floatValue());

            var stackname = new TranslatableComponent(category.getCategory().getDescriptionId()).getString();

            var count = category.getCategory().getCount();

            var color = BonusHelper.getItemRarity(chanceMap, List.of(category));

            list.add(new TextComponent(String.format("%s x%d %s", stackname, count, percentage)).withStyle(color));
        }
    }
}
