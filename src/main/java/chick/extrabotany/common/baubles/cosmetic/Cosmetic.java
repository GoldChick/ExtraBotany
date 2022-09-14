package chick.extrabotany.common.baubles.cosmetic;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.client.render.AccessoryRenderRegistry;
import vazkii.botania.client.render.AccessoryRenderer;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.proxy.IProxy;

import java.util.List;

public class Cosmetic extends ItemBauble
{
    public enum Variant
    {
        Pylon(), FoxEar(), FoxMask(), SuperCrown(), RedScarf(1), BlackGlasses(), StoneMask(), ThugLife(),
        PureDaisyPendant(1);

        /*
         * 0=head 1=body
         */

        private final int slot;

        Variant(int slot)
        {
            this.slot = slot;
        }

        Variant()
        {
            this.slot = 0;
        }
    }

    private final Variant variant;
    public static final int SUBTYPES = Variant.values().length;

    public Cosmetic(Variant variant, Properties props)
    {
        super(props);
        this.variant = variant;
        IProxy.INSTANCE.runOnClient(() -> () -> AccessoryRenderRegistry.register(this, new Cosmetic.Renderer()));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags)
    {
        var variant = ((Cosmetic) stack.getItem()).variant;
        if (variant == Variant.FoxMask)
        {
            tooltip.add(new TranslatableComponent("extrabotany.foxmaskinfo0").withStyle(ChatFormatting.ITALIC));
            tooltip.add(new TranslatableComponent("extrabotany.foxmaskinfo1").withStyle(ChatFormatting.ITALIC));
            tooltip.add(new TranslatableComponent("extrabotany.foxmaskinfo2").withStyle(ChatFormatting.ITALIC));
        }
    }

    public static class Renderer implements AccessoryRenderer
    {
        @Override
        public void doRender(HumanoidModel<?> bipedModel, ItemStack stack, LivingEntity living, PoseStack ms, MultiBufferSource buffers, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
        {
            var variant = ((Cosmetic) stack.getItem()).variant;
            switch (variant.slot)
            {
                default ->
                {
                    //default(0 or something else): head
                    bipedModel.head.translateAndRotate(ms);
                }
                case 1 ->
                {
                    bipedModel.body.translateAndRotate(ms);
                }
                case 2 ->
                {
                    //it will come soon
                }
            }
            //dont know whats it for (?
            ms.pushPose();
            switch (variant)
            {
                case FoxEar ->
                {
                    ms.translate(0, -0.8, -0.1F);
                    ms.scale(0.8F, -0.8F, -0.8F);
                }
                case FoxMask ->
                {
                    ms.translate(0.02F, -0.3, -0.3);
                    ms.scale(0.66F, -0.65F, -0.65F);
                }
                case SuperCrown ->
                {
                    ms.translate(0, -0.7, -0.1F);
                    ms.scale(0.65F, -0.65F, -0.65F);
                }
                case BlackGlasses ->
                {
                    ms.translate(0, -0.2, -0.3);
                    ms.scale(0.55F, 0.55F, -0.55F);
                }
                case RedScarf ->
                {
                    ms.translate(0, 0.16, -0.15);
                    ms.scale(0.55F, -0.55F, -0.55F);
                }
                case StoneMask ->
                {
                    ms.translate(0F, -0.3, -0.3);
                    ms.scale(0.65F, -0.65F, -0.65F);
                }
                case ThugLife ->
                {
                    ms.translate(0, -0.2, -0.3);
                    ms.scale(0.7F, -0.7F, -0.7F);
                }
                case PureDaisyPendant ->
                {
                    //this is, maybe same?
                    ms.translate(0, 0.16, -0.15);
                    ms.scale(0.55F, -0.55F, -0.55F);
                }
                default ->
                {
                    //default is pylon
                    ms.translate(0, -0.8, -0.1F);
                    ms.scale(0.5F, -0.5F, -0.5F);
                }
            }
            renderItem(stack, living, ms, buffers, light);
        }
    }

    public static void renderItem(ItemStack stack, LivingEntity living, PoseStack ms, MultiBufferSource buffers, int light)
    {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.NONE,
                light, OverlayTexture.NO_OVERLAY, ms, buffers, living.getId());
        ms.popPose();
    }
}
