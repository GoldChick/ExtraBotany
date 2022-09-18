package chick.extrabotany.api.cap;

import static vazkii.botania.common.block.subtile.generating.SubTileHydroangeas.TAG_PASSIVE_DECAY_TICKS;

/**
 * <b>CAPABILITY</b> for passive flower<p>
 * have a default Implement<p>
 * init when forge client inits<p>
 * (<b>chick.extrabotany.forge.CapabilityInit</b>)
 */
public interface IPassiveFlowerCap
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
    default int getDecayTicks()
    {
        return 72000;
    }

    void serenitianWorks();

    void decay();

}
