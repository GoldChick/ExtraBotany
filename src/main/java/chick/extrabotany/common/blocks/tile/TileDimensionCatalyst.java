package chick.extrabotany.common.blocks.tile;

import chick.extrabotany.api.BonusHelper;
import chick.extrabotany.api.WeightCategory;
import chick.extrabotany.common.ModSounds;
import chick.extrabotany.common.blocks.ModTiles;
import chick.extrabotany.common.entities.ego.EntityEGOBeam;
import com.google.common.base.Suppliers;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

public class TileDimensionCatalyst extends TileMod
{
    public static final Supplier<IMultiblock> MULTIBLOCK = Suppliers.memoize(() -> PatchouliAPI.get().makeMultiblock
            (
                    new String[][]{
                            {
                                    "_____",
                                    "_   _",
                                    "_ D _",
                                    "_   _",
                                    "_____"
                            },
                            {
                                    "_____",
                                    "_ G _",
                                    "_GLG_",
                                    "_ G _",
                                    "_____"
                            },
                            {
                                    "_____",
                                    "_ G _",
                                    "_GLG_",
                                    "_ G _",
                                    "_____"
                            },
                            {
                                    "WABAR",
                                    "AGGGA",
                                    "BGLGB",
                                    "AGGGA",
                                    "RABAW"
                            },
                            {
                                    "_____",
                                    "_BAB_",
                                    "_A0A_",
                                    "_BAB_",
                                    "_____"
                            }},
                    'A', ModBlocks.livingrock,
                    'B', ModBlocks.shimmerrock,
                    '0', ModBlocks.shimmerrock,
                    'W', ModBlocks.manaDiamondBlock,
                    'R', ModBlocks.dragonstoneBlock,
                    'G', Blocks.WATER,
                    'L', PatchouliAPI.get().predicateMatcher(ModFluffBlocks.livingrockWall, state -> state.getValue(BlockStateProperties.WATERLOGGED)),
                    'D', chick.extrabotany.common.ModBlocks.DIMENSION_CATALYST.get()
            ));

    public boolean isActive;

    private final String TAG_IS_ACTIVE = "is_active";

    public TileDimensionCatalyst(BlockPos pos, BlockState state)
    {
        super(ModTiles.DIMENSION_CATALYST, pos, state);
    }

    @Override
    public void writePacketNBT(CompoundTag cmp)
    {
        super.writePacketNBT(cmp);
        cmp.putBoolean(TAG_IS_ACTIVE, isActive);
    }

    @Override
    public void readPacketNBT(CompoundTag cmp)
    {
        super.readPacketNBT(cmp);
        isActive = cmp.getBoolean(TAG_IS_ACTIVE);
    }

    public boolean chouJiang(Player player, ItemStack stack, List<WeightCategory> categoryList, Map<WeightCategory, Float> chanceList)
    {
        if (!level.isClientSide)
        {
            if (hasValidPlatform())
            {
                if (!isActive)
                {
                    isActive = true;
                    //TODO:出金 当然里面没有放值钱东西，不可能出金的
                    List<WeightCategory> list;
                    if (stack.getCount() >= 10)
                    {
                        stack.shrink(10);
                        list = popResource(level, categoryList, 10);
                    } else
                    {
                        stack.shrink(1);
                        list = popResource(level, categoryList, 1);
                    }
                    BlockPos dropCoord = getBlockPos().above();

                    var color = BonusHelper.getItemRarity(chanceList, list);

                    Color targetColor = new Color(color.getColor() != null ? color.getColor() : 0x4169E1);

                    var beam = new EntityEGOBeam(level, false, list, targetColor, player);
                    beam.setPos(dropCoord.getX() + 0.5F, dropCoord.getY() - 4.3F, dropCoord.getZ() + 0.5F);
                    beam.summoner = null;
                    beam.setColor(1F, 1F, 1F);
                    level.addFreshEntity(beam);

                    level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.chouka, SoundSource.PLAYERS, 20F, 1F);
                } else
                {
                    player.sendMessage(new TextComponent("is active!").withStyle(ChatFormatting.RED), Util.NIL_UUID);

                }
            } else
            {
                player.sendMessage(new TextComponent("chouka failed!").withStyle(ChatFormatting.RED), Util.NIL_UUID);
            }
        }
        return true;
    }

    public static List<Vec3> VEC_OFFSET = Arrays.asList(
            new Vec3(2, -1, 0),
            new Vec3(-2, -1, 0),
            new Vec3(0, -1, 2),
            new Vec3(0, -1, -2),
            new Vec3(1, -1, 1),
            new Vec3(1, -1, -1),
            new Vec3(-1, -1, 1),
            new Vec3(-1, -1, -1),
            new Vec3(0.5F, 0, 0),
            new Vec3(-0.5F, 0, 0)
    );

    private List<WeightCategory> popResource(Level level, List<WeightCategory> categoryList, int count)
    {
        List<WeightCategory> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            list.add(BonusHelper.rollItem(categoryList));
        }
        return list;
    }

    private boolean hasValidPlatform()
    {
        return MULTIBLOCK.get().validate(level, getBlockPos().below(4)) != null;
    }

    public void setActive(boolean b)
    {
        this.isActive = b;
    }
}
