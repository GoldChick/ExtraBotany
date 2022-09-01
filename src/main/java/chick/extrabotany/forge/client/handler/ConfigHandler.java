package chick.extrabotany.forge.client.handler;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHandler
{
    public static class Client
    {

        public final ForgeConfigSpec.BooleanValue disablelogInfo;

        public Client(ForgeConfigSpec.Builder builder)
        {
            builder.push("client");
            disablelogInfo = builder
                    .comment("Whether to disable the spam in the logs. Default is false.")
                    .define("disableLogSpam", false);
            builder.pop();
        }

    }

    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static
    {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static class Common
    {

        public final ForgeConfigSpec.BooleanValue disableDisarm;
        public final ForgeConfigSpec.BooleanValue doProjectileBreakBlock;
        public final ForgeConfigSpec.DoubleValue gaiaDamageTimes;

        public Common(ForgeConfigSpec.Builder builder)
        {
            //TODO:为什么要禁用缴械捏noob
            builder.push("common");
            disableDisarm = builder
                    .comment("Whether to disable the Ego's disarm. Default is false(Not Recommend).")
                    .define("disableDisarm", false);

            doProjectileBreakBlock = builder
                    .comment("Whether Relic projectiles break blocks. Default is false.")
                    .define("doProjectileBreakBlock", false);

            gaiaDamageTimes = builder
                    .comment("How many times damage will EGO(Gaia III) do when it use weapons.")
                    .defineInRange("gaiaDamageTimes", 2.0D, 0D, 10D);
            builder.pop();
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static
    {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
