package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import vazkii.botania.data.BlockLootProvider;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static chick.extrabotany.forge.client.CapabilityInit.PASSIVE_FLOWER;
import static vazkii.botania.common.block.subtile.generating.SubTileHydroangeas.TAG_PASSIVE_DECAY_TICKS;

public class ModLootTables extends BlockLootProvider
{
    private final DataGenerator generator;
    private final Map<Block, Function<Block, LootTable.Builder>> functionTable = new HashMap<>();

    public ModLootTables(DataGenerator generator)
    {
        super(generator);
        this.generator = generator;

        // Empty
        //functionTable.put(vazkii.botania.common.block.ModBlocks.bifrost, BlockLootProvider::empty);

        // Redirects
        //functionTable.put(vazkii.botania.common.block.ModBlocks.enchantedSoil, b -> genRegular(Blocks.DIRT));

        // Special
        //functionTable.put(ModBlocks.tinyPotato, BlockLootProvider::genTinyPotato);

        // Flower NBT saving
        List<BlockEntityType<?>> types = new ArrayList<>();
        PASSIVE_FLOWER.get().forEach((type, func) ->
        {
            if (type.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
            {
                types.add(type);
            }
        });
        Registry.BLOCK.stream().filter(b -> b.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
                .forEach(block ->
                {
                    for (var type : types)
                    {
                        if (type.isValid(block.defaultBlockState()))
                        {
                            functionTable.put(block, b -> genCopyNbt(b, TAG_PASSIVE_DECAY_TICKS));
                        }
                    }
                });
    }

    private static final Function<Block, LootTable.Builder> SKIP = b ->
    {
        throw new RuntimeException("shouldn't be executed");
    };

    @Override
    public void run(HashCache cache) throws IOException
    {
        Map<ResourceLocation, LootTable.Builder> tables = new HashMap<>();

        Registry.BLOCK.stream().filter(b -> b.getRegistryName().getNamespace().equals(ExtraBotany.MODID))
                .forEach(block ->
                {
                    Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, BlockLootProvider::genRegular);
                    if (func != SKIP)
                    {
                        tables.put(block.getRegistryName(), func.apply(block));
                    }
                });

        for (Map.Entry<ResourceLocation, LootTable.Builder> e : tables.entrySet())
        {
            Path path = getPath(generator.getOutputFolder(), e.getKey());
            DataProvider.save(GSON, cache, LootTables.serialize(e.getValue().setParamSet(LootContextParamSets.BLOCK).build()), path);
        }
    }


    @Nonnull
    @Override
    public String getName()
    {
        return "extrabotany block loot tables";
    }
}