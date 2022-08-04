package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import vazkii.botania.common.entity.EntityDoppleganger;

import java.util.function.BiConsumer;

public class ModEntities
{
    public static final EntityType<EntityDoppleganger> DOPPLEGANGER = EntityType.Builder.of(EntityDoppleganger::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .fireImmune()
            .clientTrackingRange(10)
            .updateInterval(10)
            .build("extrabotany:doppleganger");

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> r)
    {
        r.accept(DOPPLEGANGER, new ResourceLocation(ExtraBotany.MODID, "doppleganger"));
    }
}
