package chick.extrabotany.datagen;

import chick.extrabotany.ExtraBotany;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.block.Block;

import vazkii.botania.common.block.decor.BlockFloatingFlower;
import vazkii.botania.xplat.IClientXplatAbstractions;

import javax.annotation.Nonnull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FloatingFlowerModelProvider implements DataProvider
{
    private final DataGenerator generator;

    public FloatingFlowerModelProvider(DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run(HashCache cache) throws IOException
    {
        List<Tuple<String, JsonElement>> jsons = new ArrayList<>();
        for (Block b : Registry.BLOCK)
        {
            ResourceLocation id = Registry.BLOCK.getKey(b);
            if (ExtraBotany.MODID.equals(id.getNamespace()) && b instanceof BlockFloatingFlower)
            {
                String name = id.getPath();
                String nonFloat;
                if (name.endsWith("_floating_flower"))
                {
                    nonFloat = name.replace("_floating_flower", "_mystical_flower");
                } else
                {
                    nonFloat = name.replace("floating_", "");
                }

                JsonObject obj = new JsonObject();
                obj.addProperty("parent", "minecraft:block/block");
                obj.addProperty("loader", IClientXplatAbstractions.FLOATING_FLOWER_MODEL_LOADER_ID.toString());
                JsonObject flower = new JsonObject();
                flower.addProperty("parent", "extrabotany:" + "block/" + nonFloat);
                obj.add("flower", flower);
                jsons.add(new Tuple<>(name, obj));
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Tuple<String, JsonElement> pair : jsons)
        {
            Path blockPath = generator.getOutputFolder().resolve("assets/" + ExtraBotany.MODID + "/models/block/" + pair.getA() + ".json");
            Path itemPath = generator.getOutputFolder().resolve("assets/" + ExtraBotany.MODID + "/models/item/" + pair.getA() + ".json");
            DataProvider.save(gson, cache, pair.getB(), blockPath);
            DataProvider.save(gson, cache, pair.getB(), itemPath);
        }
    }

    @Nonnull
    @Override
    public String getName()
    {
        return "Botania floating flower models";
    }
}
