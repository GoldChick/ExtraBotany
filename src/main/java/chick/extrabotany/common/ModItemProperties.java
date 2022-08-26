package chick.extrabotany.common;

import chick.extrabotany.common.tools.weapons.LivingWoodCrossBow;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.common.item.equipment.tool.bow.ItemLivingwoodBow;
import vazkii.botania.network.TriConsumer;

@OnlyIn(Dist.CLIENT)
public class ModItemProperties
{
    public static void init(TriConsumer<ItemLike, ResourceLocation, ClampedItemPropertyFunction> consumer)
    {
        ClampedItemPropertyFunction bow_using = (stack, worldIn, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        ClampedItemPropertyFunction bow_pull = (stack, worldIn, entity, seed) ->
        {
            ItemLivingwoodBow item = ((ItemLivingwoodBow) stack.getItem());
            return (entity != null && entity.getUseItem() == stack)
                    ? (stack.getUseDuration() - entity.getUseItemRemainingTicks()) * item.chargeVelocityMultiplier() / 20.0F : 0.0F;
        };
        consumer.accept(ModItems.FAILNAUGHT.get(), new ResourceLocation("pulling"), bow_using);
        consumer.accept(ModItems.FAILNAUGHT.get(), new ResourceLocation("pull"), bow_pull);

        ClampedItemPropertyFunction crossbow_using = (stack, worldIn, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        ClampedItemPropertyFunction crossbow_pull = (stack, worldIn, entity, seed) ->
        {
            LivingWoodCrossBow item = (LivingWoodCrossBow) stack.getItem();
            return (entity != null && !CrossbowItem.isCharged(stack)) ?  ((float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) * item.chargeVelocityMultiplier()) / (float) CrossbowItem.getChargeDuration(stack) : 0.0F;
        };
        ClampedItemPropertyFunction charged = (stack, worldIn, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        ClampedItemPropertyFunction firework = (stack, worldIn, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;

        consumer.accept(ModItems.LIVINGWOOD_CROSSBOW.get(), new ResourceLocation("pulling"), crossbow_using);
        consumer.accept(ModItems.LIVINGWOOD_CROSSBOW.get(), new ResourceLocation("pull"), crossbow_pull);
        consumer.accept(ModItems.LIVINGWOOD_CROSSBOW.get(), new ResourceLocation("charged"), charged);
        consumer.accept(ModItems.LIVINGWOOD_CROSSBOW.get(), new ResourceLocation("firework"), firework);
    }
}
