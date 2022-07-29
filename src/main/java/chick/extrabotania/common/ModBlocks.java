package chick.extrabotania.common;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static chick.extrabotania.ExtraBotania.MODID;

public class ModBlocks
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //item for block item
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static DeferredRegister<Item> GetItems()
    {
        return ITEMS;
    }
    public static DeferredRegister<Block> GetBlocks()
    {
        return BLOCKS;
    }
}
