package chick.extrabotany.api.block;

/**
 * capability for passive flower
 */
public interface ISubTilePassiveFlower
{
    /**
     * get ticks it has since last Serenitian in Extrabotany works
     */
    int getPassiveTicks();

    /**
     * change passive ticks (usually to zero)
     */
    void setPassiveTicks(int x);

    /**
     * just as its name (usually 72000 ticks, that is, 1 hour)
     */
    int getMaxPassiveTicks();

    /**
     * does the flower block have drops
     */
    boolean isNoDrop();
}
