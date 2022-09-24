package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.lib.LibMisc;
import vazkii.botania.data.util.ModelWithOverrides;
import vazkii.botania.data.util.OverrideHolder;
import vazkii.botania.mixin.AccessorTextureSlot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class ModItemOverrideModels implements DataProvider
{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final TextureSlot LAYER1 = AccessorTextureSlot.make("layer1");
    private static final ModelTemplate GENERATED_1 = new ModelTemplate(Optional.of(new ResourceLocation("item/generated")), Optional.empty(), TextureSlot.LAYER0, LAYER1);
    private static final ModelWithOverrides GENERATED_OVERRIDES_1 = new ModelWithOverrides(new ResourceLocation("item/generated"), TextureSlot.LAYER0, LAYER1);
    private static final ModelWithOverrides HANDHELD_OVERRIDES = new ModelWithOverrides(new ResourceLocation("item/handheld"), TextureSlot.LAYER0);

    private final DataGenerator generator;

    public ModItemOverrideModels(DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run(HashCache cache)
    {
        Set<Item> items = Registry.ITEM.stream().filter(i -> ExtraBotany.MODID.equals(Registry.ITEM.getKey(i).getNamespace()))
                .collect(Collectors.toSet());
        Map<ResourceLocation, Supplier<JsonElement>> map = new HashMap<>();
        //registerItemBlocks(takeAll(items, i -> i instanceof BlockItem).stream().map(i -> (BlockItem) i).collect(Collectors.toSet()), map::put);
        registerItemOverrides(items, map::put);
        //registerItems(items, map::put);

        for (Map.Entry<ResourceLocation, Supplier<JsonElement>> e : map.entrySet())
        {
            ResourceLocation id = e.getKey();
            Path out = generator.getOutputFolder().resolve("assets/" + id.getNamespace() + "/models/" + id.getPath() + ".json");
            try
            {
                DataProvider.save(GSON, cache, e.getValue().get(), out);
            } catch (IOException ex)
            {
                BotaniaAPI.LOGGER.error("Failed to generate {}", out, ex);
            }
        }
    }

    @NotNull
    @Override
    public String getName()
    {
        return "extrabotany some model provider";
    }

    private static void registerItemOverrides(Set<Item> items, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer)
    {
        OverrideHolder flaskOverrides = new OverrideHolder();
        for (int i = 1; i <= 7; i++)
        {
            ResourceLocation overrideModel = ModelLocationUtils.getModelLocation(chick.extrabotany.common.ModItems.COCK_TAIL.get(), "_" + i);
            GENERATED_1.create(overrideModel,
                    TextureMapping.layer0(chick.extrabotany.common.ModItems.EMPTY_BOTTLE.get()).put(LAYER1, overrideModel),
                    consumer);

            flaskOverrides.add(overrideModel, Pair.of(new ResourceLocation("custom_model_data"), (double) i));
        }
        GENERATED_OVERRIDES_1.create(ModelLocationUtils.getModelLocation(chick.extrabotany.common.ModItems.COCK_TAIL.get()),
                TextureMapping.layer0(chick.extrabotany.common.ModItems.EMPTY_BOTTLE.get()).put(LAYER1, TextureMapping.getItemTexture(chick.extrabotany.common.ModItems.COCK_TAIL.get(), "_0")),
                flaskOverrides,
                consumer);
        items.remove(chick.extrabotany.common.ModItems.COCK_TAIL.get());

        singleHandheldSuffixOverride(chick.extrabotany.common.ModItems.MINI_TORNADO_ROD.get(), "_active", new ResourceLocation(LibMisc.MOD_ID,"active"), 1.0, consumer);

    }
    private static void singleHandheldOverride(Item item, ResourceLocation overrideModel, ResourceLocation predicate, double value, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        ModelTemplates.FLAT_HANDHELD_ITEM.create(overrideModel, TextureMapping.layer0(overrideModel), consumer);
        HANDHELD_OVERRIDES.create(ModelLocationUtils.getModelLocation(item),
                TextureMapping.layer0(item),
                new OverrideHolder()
                        .add(overrideModel, Pair.of(predicate, value)),
                consumer);
    }
    private static void singleHandheldSuffixOverride(Item item, String suffix, ResourceLocation predicate, double value, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        singleHandheldOverride(item, ModelLocationUtils.getModelLocation(item, suffix), predicate, value, consumer);
    }

}
