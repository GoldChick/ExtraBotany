package chick.extrabotany.api.block;

import net.minecraftforge.event.TickEvent;

/**
 * capability for passive flower
 * you need to store passive ticks in block nbt
 */
public interface ISubTilePassiveFlower
{
    /**
     * get string to store it in the block nbt
     * default is "passive_ticks"
     */
    default String getTagPassiveTicks()
    {
        return "passive_ticks";
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
