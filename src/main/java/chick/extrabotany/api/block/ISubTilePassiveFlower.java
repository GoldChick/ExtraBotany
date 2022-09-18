package chick.extrabotany.api.block;

import net.minecraftforge.event.TickEvent;

import static vazkii.botania.common.block.subtile.generating.SubTileHydroangeas.TAG_PASSIVE_DECAY_TICKS;

/**
 * <b>CAPABILITY</b> for passive flower<p>
 * have a default Implement<p>
 * you need to store passive ticks in block nbt
 */
public interface ISubTilePassiveFlower
{
    /**
     * get string to store it in the block nbt
     * default is "passiveDecayTicks"
     */
    default String getTagPassiveTicks()
    {
        return TAG_PASSIVE_DECAY_TICKS;
    }

    /**
     * get ticks it has since last Serenitian in Extrabotany works
     */
    int getPassiveTicks();

    /**
     * change passive ticks (usually to zero)
     */
    void setPassiveTicks(int x);

    /**
     * add 1 to passiveTicks
     * use it when necessary
     */
    void addPassiveTicks();

    /**
     * just as its name (usually 72000 ticks, that is, 1 hour)
     */
    int getDecayTicks();

    /**
     * does the flower block have drops
     */
    boolean isNoDrop();

    void serenitianWorks();

    void decay();

    default boolean isHydroangeas()
    {
        return false;
    }

    default void tickFlower(TickEvent event)
    {

    }
}
