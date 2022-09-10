package chick.extrabotany.common.base;

import chick.extrabotany.ExtraBotany;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * need to be check when you change Minecraft version
 */
public class ExtrabotanyReflectHelper
{
    public static <T, E> T getPrivateValue(Class<? super E> clazz, E instance, String fieldName)
    {
        try
        {
            return ObfuscationReflectionHelper.getPrivateValue(clazz, instance, fieldName);
        } catch (Exception e)
        {
            throw new IllegalStateException(ExtraBotany.MODID + "AdvancementHandler Error when read from class \"" + clazz + "\"! Maybe Field ID \"" + fieldName + "\" has been changed!");
        }
    }

    public static <T, E> void setPrivateValue(@NotNull final Class<? super T> clazz, @NotNull final T instance, @Nullable final E value, @NotNull final String fieldName)
    {
        try
        {
            ObfuscationReflectionHelper.setPrivateValue(clazz, instance, value, fieldName);
        } catch (Exception e)
        {
            throw new IllegalStateException(ExtraBotany.MODID + "AdvancementHandler Error when write into class \"" + clazz + "\"! Maybe Field ID \"" + fieldName + "\" has been changed!");
        }
    }
}
