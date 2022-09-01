package chick.extrabotany.common;

import chick.extrabotany.ExtraBotany;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

public class ModSounds
{
    private static final List<SoundEvent> EVENTS = new ArrayList<>();

    public static final SoundEvent swordland = makeSoundEvent("music.ego");
    public static final SoundEvent salvation = makeSoundEvent("music.herrscher");
    public static final SoundEvent chouka = makeSoundEvent("sound.chouka");

    private static SoundEvent makeSoundEvent(String name)
    {
        SoundEvent event = new SoundEvent(new ResourceLocation(ExtraBotany.MODID, name));
        EVENTS.add(event);
        return event;
    }

    public static void init(BiConsumer<SoundEvent, ResourceLocation> r)
    {
        for (SoundEvent event : EVENTS)
        {
            r.accept(event, event.getLocation());
        }
    }
    private ModSounds()
    {
    }
}
