package chick.extrabotany.common.tools.weapons.ranged;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.entities.judah.EntityJudahOath;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.ICustomDamageItem;

import java.util.function.Consumer;

public class JudahOath extends Item implements ICustomDamageItem
{
    final int types = 3;

    public JudahOath(Properties prop)
    {
        super(prop);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> attrib = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND)
        {
            attrib.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 12, AttributeModifier.Operation.ADDITION));
            attrib.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.6, AttributeModifier.Operation.ADDITION));
        }
        return attrib;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken)
    {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (ManaItemHandler.instance().requestManaExactForTool(stack, player, 2500, true))
        {
            //TODO: entity
            if (!level.isClientSide)
            {
                EntityJudahOath judah = new EntityJudahOath(level, player);
                judah.setPos(player.position().add(0, 1, 0));
                judah.setRotation(Mth.wrapDegrees(-player.yRotO + 180));
                judah.shootFromRotation(player, player.xRotO, player.yRotO, 0F, 0.5F, 0F);

                judah.setDamage(7);
                judah.setType(EntityJudahOath.JudahType.byId(0));
                level.addFreshEntity(judah);
            }
            player.getCooldowns().addCooldown(this, 80);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

/*
    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "item." + LibItemsName.JUDAHOATHS[Math.min(types - 1, par1ItemStack.getItemDamage())];
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks)
    {
        if (isInCreativeTab(tab))
        {
            for (int i = 0; i < types; i++)
            {
                stacks.add(new ItemStack(this, 1, i));
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void onItemRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (type == RenderType.BODY)
        {
            Helper.rotateIfSneaking(player);
            boolean armor = !player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
            GlStateManager.rotate(180F, 1F, 0F, 0F);
            GlStateManager.translate(-1.0F, -1.23F, armor ? -0.23F : -0.18F);
            GlStateManager.scale(1.7F, 1.7F, 1.7F);
            TextureAtlasSprite gemIcon = MiscellaneousIcons.INSTANCE.judahIcons2[stack.getMetadata()];
            float f = gemIcon.getMinU();
            float f1 = gemIcon.getMaxU();
            float f2 = gemIcon.getMinV();
            float f3 = gemIcon.getMaxV();
            IconHelper.renderIconIn3D(Tessellator.getInstance(), f1, f2, f, f3, gemIcon.getIconWidth(),
                    gemIcon.getIconHeight(), 1F / 32F);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels()
    {
        for (int i = 0; i < LibItemsName.JUDAHOATHS.length; i++)
        {
            ModelLoader.setCustomModelResourceLocation(this, i,
                    new ModelResourceLocation(Reference.MOD_ID + ":" + LibItemsName.JUDAHOATHS[i], "inventory"));
        }
    }


 */

}
