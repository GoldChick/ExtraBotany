package chick.extrabotany.xplat;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.cap.INatureOrbItem;
import chick.extrabotany.api.cap.IPassiveFlowerCap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import vazkii.botania.api.mana.IManaItem;

import javax.annotation.Nullable;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface IXplatAbstractions
{
    // Yes, this forms a loop by default. Each loader overrides their own to break the loop
    default boolean isFabric()
    {
        return !isForge();
    }

    default boolean isForge()
    {
        return !isFabric();
    }

    boolean isModLoaded(String modId);

    boolean isDevEnvironment();

    boolean isPhysicalClient();

    String getExtrabotanyVersion();

    @Nullable
    IPassiveFlowerCap findPassiveFlower(BlockEntity blockEntity);

    @Nullable
    INatureOrbItem findNatureOrbItem(ItemStack stack);

    IXplatAbstractions INSTANCE = find();

    private static IXplatAbstractions find()
    {
        var providers = ServiceLoader.load(IXplatAbstractions.class).stream().toList();
        if (providers.size() != 1)
        {
            var names = providers.stream().map(p -> p.type().getName()).collect(Collectors.joining(",", "[", "]"));
            throw new IllegalStateException("Extrabotany Error:There should be exactly one IXplatAbstractions implementation on the classpath. Found: " + names);
        } else
        {
            var provider = providers.get(0);
            ExtraBotany.LOGGER.debug("Instantiating xplat impl: " + provider.type().getName());
            return provider.get();
        }
    }
}
