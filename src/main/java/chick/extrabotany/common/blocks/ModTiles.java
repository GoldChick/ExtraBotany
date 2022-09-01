package chick.extrabotany.common.blocks;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.blocks.tile.TileDimensionCatalyst;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ModTiles
{
    private static final Map<ResourceLocation, BlockEntityType<?>> ALL = new HashMap<>();

    public static final BlockEntityType<TileDimensionCatalyst> DIMENSION_CATALYST = type(new ResourceLocation(ExtraBotany.MODID, ModBlocks.DIMENSION_CATALYST.get().getDescriptionId()), TileDimensionCatalyst::new, ModBlocks.DIMENSION_CATALYST.get());

    private static <T extends BlockEntity> BlockEntityType<T> type(ResourceLocation id, BiFunction<BlockPos, BlockState, T> func, Block... blocks)
    {
        var ret = IXplatAbstractions.INSTANCE.createBlockEntityType(func, blocks);
        var old = ALL.put(id, ret);
        if (old != null)
        {
            throw new IllegalArgumentException("Duplicate id " + id);
        }
        return ret;
    }

    public static void registerTiles(BiConsumer<BlockEntityType<?>, ResourceLocation> r)
    {
        for (var e : ALL.entrySet())
        {
            r.accept(e.getValue(), e.getKey());
        }
    }
}
