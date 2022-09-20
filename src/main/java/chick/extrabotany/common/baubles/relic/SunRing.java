package chick.extrabotany.common.baubles.relic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import vazkii.botania.common.item.ModItems;

import java.util.Arrays;
import java.util.List;


public class SunRing extends AbstractMultiUpgradedRelic
{
    public SunRing(Properties props)
    {
        super(props);
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity player)
    {
        super.onWornTick(stack, player);

        /*
        //ModItems.bloodPendant;
        this.addBauble(stack, ModItems.waterRing);
        this.addBauble(stack, ModItems.miningRing);
        this.addBauble(stack, ModItems.auraRingGreater);
        this.addBauble(stack, ModItems.swapRing);
        this.addBauble(stack, chick.extrabotany.common.ModItems.DEATH_RING.get());
        this.addBauble(stack, chick.extrabotany.common.ModItems.FROST_RING.get());
        // ((ItemManaDriveRing) manadrivering).onWornTick(stack, player);

         */
    }

    @Override
    public List<TagKey<Item>> getTagKeys()
    {
        //return List.of(ItemTags.create(new ResourceLocation("curios", "ring")));
        return Arrays.asList(
                ItemTags.create(new ResourceLocation("curios", "ring")),
                ItemTags.create(new ResourceLocation("curios", "necklace"))
        );
    }
}
