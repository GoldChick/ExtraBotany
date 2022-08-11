package chick.extrabotany.common.brews;

import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.brews.ItemBrewBase;


public class Cocktail extends ItemBrewBase
{
    public Cocktail(Properties properties)
    {
        super(properties, 8, 20, () -> ModItems.EMPTY_BOTTLE.get(), 1.3F, 0);
    }
}
