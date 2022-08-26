package chick.extrabotany.api.patchouli;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ManyCraftingProcessor implements IComponentProcessor
{
    private boolean shapeless = true;
    private boolean hasCustomHeading;

    private List<ItemStack> slotStack = new ArrayList<>();
    private List<ItemStack> result = new ArrayList<>();

    @Override
    public void setup(IVariableProvider variables)
    {
        var slotRl = variables.get("slot").asStream().map(IVariable::asString).toList();
        var resultRl = variables.get("result").asStream().map(IVariable::asString).toList().get(0);
        var nums = variables.get("nums").asStream().map(IVariable::asString).map(Integer::valueOf).toList();

        for (int i = 0; i < 9 && i < slotRl.size(); i++)
        {
            if (slotRl.get(i).equals("null"))
            {
                slotStack.add(ItemStack.EMPTY);
            } else
            {
                slotStack.add(new ItemStack(getItemByResourceLocation(new ResourceLocation(slotRl.get(i))), nums.get(i)));
            }
        }

        result.add(new ItemStack(getItemByResourceLocation(new ResourceLocation(resultRl)), nums.get(nums.size() - 1)));
        this.hasCustomHeading = variables.has("heading");
    }

    @NotNull
    public static Item getItemByResourceLocation(ResourceLocation id)
    {
        var list = Registry.ITEM.stream().filter(b -> b.getRegistryName().equals(id)).toList();
        if (!list.isEmpty())
        {
            return list.get(0);
        }
        throw new RuntimeException("Extrabotany: Patchouli many_crafting didn't find valid item!");
    }

    @Override
    public IVariable process(String key)
    {
        if (!slotStack.isEmpty())
        {
            if (key.equals("heading"))
            {
                if (!hasCustomHeading)
                {
                    return IVariable.from(result.get(0).getHoverName());
                }
                return null;
            }
            if (key.startsWith("input"))
            {
                int index = Integer.parseInt(key.substring(5)) - 1;
                var stack = index + 1 <= slotStack.size() ? slotStack.get(index) : ItemStack.EMPTY;
                return IVariable.wrapList(Stream.of(stack).map(IVariable::from).toList());
            }
            if (key.equals("output"))
            {
                return IVariable.wrapList(result.stream().map(IVariable::from).toList());
            }
            if (key.equals("shapeless"))
            {
                return IVariable.wrap(shapeless);
            }
        }
        return null;
    }
}
