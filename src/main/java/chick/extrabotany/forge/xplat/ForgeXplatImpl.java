package chick.extrabotany.forge.xplat;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.api.cap.ExtraBotanyCapabilities;
import chick.extrabotany.api.cap.IPassiveFlowerCap;
import chick.extrabotany.xplat.IXplatAbstractions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.IManaItem;

public class ForgeXplatImpl implements IXplatAbstractions
{
    @Override
    public boolean isForge()
    {
        return true;
    }

    @Override
    public boolean isModLoaded(String modId)
    {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment()
    {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isPhysicalClient()
    {
        return FMLLoader.getDist() == Dist.CLIENT;
    }

    @Override
    public String getExtrabotanyVersion()
    {
        return ModList.get().getModContainerById(ExtraBotany.MODID).get().getModInfo().getVersion().toString();
    }

    @Nullable
    @Override
    public IPassiveFlowerCap findPassiveFlower(BlockEntity blockEntity)
    {
        return blockEntity.getCapability(ExtraBotanyCapabilities.PASSIVE_FLOWER).orElse(null);
    }

    @Nullable
    @Override
    public IManaItem findNatureOrb(ItemStack stack)
    {
        return stack.getCapability(ExtraBotanyCapabilities.NATURE_ORB).orElse(null);
    }

}
