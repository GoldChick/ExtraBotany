package chick.extrabotany.common.baubles;

import chick.extrabotany.api.item.IItemWithRightClick;
import chick.extrabotany.common.baubles.cosmetic.Cosmetic;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.recipe.IPureDaisyRecipe;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

import javax.annotation.Nullable;
import java.util.List;

public class PureDaisyPendant extends Cosmetic implements IItemWithRightClick
{
    public PureDaisyPendant(Properties props)
    {
        super(Variant.PureDaisyPendant, props.durability(64));
        MinecraftForge.EVENT_BUS.addListener(this::rightClickBlock);
    }

    public static final String TAG_USE = "use_count";
    public static final String TAG_CD = "cool_down";

    @Override
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock evt)
    {
        var stack = EquipmentHandler.findOrEmpty(this, evt.getPlayer());
        if (!stack.isEmpty() && evt.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
        {
            evt.getPlayer().swing(InteractionHand.MAIN_HAND);
            useOn(new UseOnContext(evt.getPlayer().getLevel(), evt.getPlayer(), InteractionHand.MAIN_HAND, stack, evt.getHitVec()));
        }
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext ctx)
    {
        var level = ctx.getLevel();
        var pos = ctx.getClickedPos();
        var blockState = level.getBlockState(pos);

        if (!blockState.isAir())
        {
            var player = ctx.getPlayer();
            var stack = ctx.getItemInHand();

            level.getProfiler().push("findRecipe");
            IPureDaisyRecipe recipe = findRecipe(level, pos);
            level.getProfiler().pop();

            if (recipe != null && getCD(stack) == 0)
            {
                if (recipe.set(level, pos, null) && ManaItemHandler.instance().requestManaExactForTool(stack, player, 50, true))
                {
                    level.blockEvent(pos, recipe.getOutputState().getBlock(), 0, 0);
                }
                level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(recipe.getOutputState()));
                setCount(stack, getCount(stack) + 1);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(ctx.getHand()));
                setCD(stack, getCount(stack));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Nullable
    private IPureDaisyRecipe findRecipe(Level level, BlockPos coords)
    {
        BlockState state = level.getBlockState(coords);

        for (Recipe<?> recipe : ModRecipeTypes.getRecipes(level, ModRecipeTypes.PURE_DAISY_TYPE).values())
        {
            if (recipe instanceof IPureDaisyRecipe daisyRecipe && daisyRecipe.matches(level, coords, null, state))
            {
                return daisyRecipe;
            }
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(new TextComponent("Cooldown:").withStyle(ChatFormatting.GOLD)
                .append(new TextComponent(String.valueOf(getCD(stack))))
                .append(new TextComponent(" ticks").withStyle(ChatFormatting.GOLD))
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_)
    {
        super.inventoryTick(stack, p_41405_, p_41406_, p_41407_, p_41408_);
        if (getCD(stack) > 0)
            setCD(stack, getCD(stack) - 1);
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity)
    {
        super.onWornTick(stack, entity);
        if (entity.getLevel().isDay() && entity.getLevel().dayTime() % 120 == 0)
            entity.heal(1F);
    }

    //矿物词典，不过现在似乎并没有什么用了？
    /*
    private boolean isOreDict(ItemStack stack, String entry)
    {
        if (stack.isEmpty())
            return false;

        for (ItemStack ostack : OreDictionary.getOres(entry, false))
        {
            if (OreDictionary.itemMatches(ostack, stack, false))
            {
                return true;
            }
        }

        return false;
    }


 */

    public void setCD(ItemStack stack, int i)
    {
        ItemNBTHelper.setInt(stack, TAG_CD, i);
    }

    public int getCD(ItemStack stack)
    {
        return ItemNBTHelper.getInt(stack, TAG_CD, 0);
    }

    public void setCount(ItemStack stack, int i)
    {
        ItemNBTHelper.setInt(stack, TAG_USE, i);
    }

    public int getCount(ItemStack stack)
    {
        return ItemNBTHelper.getInt(stack, TAG_USE, 0);
    }

    private ItemStack renderStack;

/*
    @Override
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        renderStack = stack;
        if (type == RenderType.BODY)
        {
            Helper.rotateIfSneaking(player);
            boolean armor = !player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
            GlStateManager.rotate(180F, 1F, 0F, 0F);
            GlStateManager.translate(-0.3F, -0.57F, armor ? 0.2F : 0.15F);
            GlStateManager.scale(0.6F, 0.6F, 0.6F);

            TextureAtlasSprite gemIcon = MiscellaneousIcons.INSTANCE.puredaisyPendantIcon;
            float f = gemIcon.getMinU();
            float f1 = gemIcon.getMaxU();
            float f2 = gemIcon.getMinV();
            float f3 = gemIcon.getMaxV();
            IconHelper.renderIconIn3D(Tessellator.getInstance(), f1, f2, f, f3, gemIcon.getIconWidth(), gemIcon.getIconHeight(), 1F / 32F);
        }
    }
    */

}
