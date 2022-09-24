package chick.extrabotany.datagen.lang;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModEffects;
import chick.extrabotany.common.ModEntities;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
import chick.extrabotany.common.libs.LibAdvancementNames;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;


public class ZH_CNLanguageProvider extends LanguageProvider
{
    public ZH_CNLanguageProvider(DataGenerator gen, String locale)
    {
        super(gen, ExtraBotany.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup." + ExtraBotany.TAB_NAME, "额外植物学");
        add(ModEffects.BLOOD_TEMPTATION.get(), "血之诱惑");
        add(ModEffects.DIVINE_JUSTICE.get(), "神圣裁决");
        add(ModEffects.VEGETABLE.get(), "绿意盎然");
        add(ModEffects.TIME_LOCK.get(), "时空锁定");
        add(ModEffects.REMEMBER.get(), "铭记");
        add(ModEffects.BALANCE.get(), "平衡");

        add(ModItems.NIGHTMARE_FUEL.get(), "梦魇燃料");
        add(ModItems.SPIRIT_FUEL.get(), "精神燃料");
        add(ModItems.SPIRIT_FRAG.get(), "精神碎片");
        add(ModItems.SHADOW_INGOT.get(), "暗影锭");
        add(ModItems.PHOTON_INGOT.get(), "光子锭");
        add(ModItems.GILDED_POTATO.get(), "镀金服务器");
        add(ModItems.GILDED_MASHED_POTATO.get(), "镀金土豆泥");

        add(ModItems.MANA_READER.get(), "魔力读取器");
        add(ModItems.ROD_OF_DISCORD.get(), "不谐传送杖");
        add(ModItems.MINI_TORNADO_ROD.get(), "天空短杖");
        add(ModItems.THE_CHAOS.get(), "混沌物质");
        add(ModItems.THE_ORIGIN.get(), "起源物质");
        add(ModItems.THE_END.get(), "终末物质");
        add(ModItems.THE_UNIVERSE.get(), "宇宙之心");
        add(ModItems.FRIED_CHICKEN.get(), "香香鸡");

        add(ModBlocks.DIMENSION_CATALYST.get(), "次元催化剂");
        add(ModBlocks.POWER_FRAME.get(), "力量框架");
        add(ModBlocks.BLOCK_SHADOWIUM.get(), "暗影块");
        add(ModBlocks.BLOCK_PHOTONIUM.get(), "光子块");
        add(ModBlocks.BLOCK_ORICHALCOS.get(), "奥里哈钢块");

        add(ModItems.ORICHALCOS.get(), "奥利哈刚锭");
        add(ModItems.AERIALITE_INGOT.get(), "天空锭");
        add(ModItems.TICKET.get(), "寄给自己的邀请函");
        add(ModItems.GOLD_CLOTH.get(), "莱茵河的黄金");
        add(ModItems.LEN_SMELT.get(), "魔力透镜：冶炼");
        add(ModItems.LEN_SMELT.get().getDescriptionId() + ".short", "冶炼");
        add(ModItems.LEN_POTION.get(), "魔力透镜：药水：%s");
        add(ModItems.LEN_POTION.get().getDescriptionId() + ".short", "药水");
        add(ModItems.LEN_TRACE.get(), "魔力透镜：追踪");
        add(ModItems.LEN_TRACE.get().getDescriptionId() + ".short", "追踪");
        add(ModItems.LEN_MANA.get(), "魔力透镜：魔力");
        add(ModItems.LEN_MANA.get().getDescriptionId() + ".short", "魔力");
        add(ModItems.LEN_SUPERCONDUCTOR.get(), "魔力透镜：超导");
        add(ModItems.LEN_SUPERCONDUCTOR.get().getDescriptionId() + ".short", "超导");

        add(ModItems.TREASURE_BOX.get(), "潘多拉魔盒");
        add(ModItems.REWARD_BAG_A.get(), "奖励袋Eins");
        add(ModItems.REWARD_BAG_B.get(), "奖励袋Zwei");
        add(ModItems.REWARD_BAG_C.get(), "奖励袋Drei");
        add(ModItems.REWARD_BAG_D.get(), "奖励袋Vier");
        add(ModItems.UNIVERSAL_PETAL.get(), "彩虹花瓣");
        add(ModItems.ELEMENT_RUNE.get(), "元灵符文");
        add(ModItems.SIN_RUNE.get(), "大罪符文");

        add(ModItems.GENESIS_CRYSTAL.get(), "创世结晶");
        add(ModItems.PRIMO_GEM.get(), "原石");
        add(ModItems.ACQUAINT_FATE.get(), "相遇之缘");
        add(ModItems.INTERTWINED_FATE.get(), "纠缠之缘");

        add(ModItems.FOX_EAR.get(), "樱樱耳");
        add(ModItems.FOX_MASK.get(), "附有记忆的面具");
        add(ModItems.PYLON.get(), "模拟人生");
        add(ModItems.SUPER_CROWN.get(), "超级王冠");
        add(ModItems.THUG_LIFE.get(), "THUG LIFE");
        add(ModItems.BLACK_GLASSES.get(), "黑框眼镜");
        add(ModItems.RED_SCARF.get(), "红领巾");
        add(ModItems.STONE_MASK.get(), "石鬼面");

        add(ModItems.PEACE_AMULET.get(), "和平友好之证");
        add(ModItems.DEATH_RING.get(), "诅咒指环");
        add(ModItems.FROST_RING.get(), "霜冻之星");
        add(ModItems.POWER_GLOVE.get(), "强力手套");
        add(ModItems.JINGWEI_FEATHER.get(), "精卫之羽");
        add(ModItems.AERO_STONE.get(), "风之符石");
        add(ModItems.AQUA_STONE.get(), "水之符石");
        add(ModItems.EARTH_STONE.get(), "地之符石");
        add(ModItems.IGNITE_STONE.get(), "火之符石");
        add(ModItems.SUPREME_AERO_STONE.get(), "至高风之符石");
        add(ModItems.SUPREME_AQUA_STONE.get(), "至高水之符石");
        add(ModItems.SUPREME_EARTH_STONE.get(), "至高地之符石");
        add(ModItems.SUPREME_IGNITE_STONE.get(), "至高火之符石");
        add(ModItems.ELF_KING_RING.get(), "精灵王指环");
        add(ModItems.SAGES_MANA_RING.get(), "贤者魔力指环");
        add(ModItems.POTATO_CHIP.get(), "薯片");
        add(ModItems.SUN_RING.get(), "圣阳尊戒");
        add(ModItems.MOON_PENDANT.get(), "蚀月之心");
        add(ModItems.NATURE_ORB.get(), "自然蕴息宝珠");
        add("extrabotany.nature_orb", "自然之息： %s / %s");
        add("extrabotany.nature_orb_effect1", "魔力的加护");
        add("extrabotany.nature_orb_effect2", "再生的加护");
        add("extrabotany.nature_orb_effect3", "森罗的加护");
        add(ModItems.CORE_GOD.get(), "律者核心");
        add("extrabotany.wings0", "翅膀：律者");
        add("extrabotany.wings1", "翅膀：芙兰朵露");
        add("extrabotany.wings2", "翅膀：吉米");
        add("extrabotany.wings3", "翅膀：蒸汽朋克");

        add(ModItems.MANASTEEL_SHIELD.get(), "魔力钢盾牌");
        add(ModItems.ELEMENT_SHIELD.get(), "源质钢盾牌");
        add(ModItems.DIRT_SHIELD.get(), "魔力泥土盾牌");
        add(ModItems.FOREST_BOOK.get(), "森林书");
        add(ModItems.SHADOW_KATANA.get(), "影刃");
        add(ModItems.LIVINGWOOD_CROSSBOW.get(), "活木弩");
        add(ModItems.LIVINGWOOD_SHORTBOW.get(), "活木短弓");
        add(ModItems.MANA_SHORTARROW.get(), "魔力短箭");
        add(ModItems.TRUE_SHADOW_KATANA.get(), "真·影刃");
        add(ModItems.TRUE_TERRA_BLADE.get(), "真·泰拉之刃");
        add(ModItems.TRUE_THUNSTAR_CALLER.get(), "真·星雷者");
        add(ModItems.INFLUX_WAVER.get(), "波涌之刃");
        add(ModItems.FIRST_FRACTAL.get(), "最初分型");
        add(ModItems.FAILNAUGHT.get(), "百中弓");
        add(ModItems.EXCALIBER.get(), "王者圣剑");


        add(ModItems.SHADOW_WARRIOR_HELM.get(), "暗影武士头盔");
        add(ModItems.SHADOW_WARRIOR_CHEST.get(), "暗影武士胸甲");
        add(ModItems.SHADOW_WARRIOR_LEGS.get(), "暗影武士护腿");
        add(ModItems.SHADOW_WARRIOR_BOOTS.get(), "暗影武士靴子");
        add(ModItems.GOBLINSLAYER_HELM.get(), "哥布林杀手头盔");
        add(ModItems.GOBLINSLAYER_CHEST.get(), "哥布林杀手胸甲");
        add(ModItems.GOBLINSLAYER_LEGS.get(), "哥布林杀手护腿");
        add(ModItems.GOBLINSLAYER_BOOTS.get(), "哥布林杀手靴子");
        add(ModItems.MIKU_HELM.get(), "星空歌姬头饰");
        add(ModItems.MIKU_CHEST.get(), "星空歌姬服");
        add(ModItems.MIKU_LEGS.get(), "星空歌姬裙甲");
        add(ModItems.MIKU_BOOTS.get(), "星空歌姬鞋子");
        add(ModItems.MAID_HELM.get(), "昴星团战斗女仆头饰");
        add(ModItems.MAID_CHEST.get(), "昴星团战斗女仆服");
        add(ModItems.MAID_LEGS.get(), "昴星团战斗女仆裙甲");
        add(ModItems.MAID_BOOTS.get(), "昴星团战斗女仆鞋子");
        add(ModItems.HERO_MEDAL.get(), "英雄勋章");
        add(ModItems.EMPTY_BOTTLE.get(), "魔法玻璃空瓶");
        add(ModItems.MANA_DRINK.get(), "魔力鸡尾酒");
        add(ModItems.COCK_TAIL.get(), "装有%s (%s)秘制鸡尾酒");
        add("item.extrabotany.infinitewine", "装有%s (%s)无限之酒");
        add(ModItems.SPLASH_GRENADE.get(), "装有%s 圣水手雷");

        add("extrabotany.brew.revolution", "革命");
        add("extrabotany.brew.allmighty", "全能");
        add("extrabotany.brew.shell", "龟壳");
        add("extrabotany.brew.deadpool", "死灵");
        add("extrabotany.brew.floating", "漂浮");
        add("extrabotanymisc.inventoryUnfeasible", "你的背包里包含违禁物品，请再次检查你的背包！");
        add("extrabotanymisc.unlegalPlayercount", "在场玩家数大于召唤时的玩家数，这是不符合规则的！");
        add("extrabotanymisc.description", "你无法使用该物品直到你完成进度 <%s>。");
        langFlower();
        langAdvancement();
        langPantchouli();
        otherLangs();
        add(ModEntities.EGO, "本我");
        add(ModEntities.EGO_LANDMINE, "本我地雷");
        add(ModEntities.EGO_MINION, "本我仆从");
        add(ModEntities.AURAFIRE, "火焰");
        add(ModEntities.SPLASH_GRENADE, "圣水手雷");
        add(ModEntities.FALSE_LIGHTNING, "仿真闪电");
        add(ModEntities.TRUE_SHADOW_KATANA, "真·影刃");
        add(ModEntities.TRUE_TERRA_BLADE, "真·泰拉之刃");
        add(ModEntities.INFLUX_WAVER, "波涌之刃");
        add(ModEntities.MAGIC_ARROW, "魔法箭矢");
        add(ModEntities.PHANTOM_SWORD, "天顶剑");
    }

    private void langFlower()
    {
        add(ModSubtiles.sunshinelily, "日耀百合");
        add(ModSubtiles.sunshinelilyFloating, "浮空日耀百合");
        add(ModSubtiles.sunshinelily.getDescriptionId() + ".reference", "愿光芒能治愈并指引你");
        add(ModSubtiles.moonlightlily, "月光百合");
        add(ModSubtiles.moonlightlilyFloating, "浮空月光百合");
        add(ModSubtiles.moonlightlily.getDescriptionId() + ".reference", "愿你能找到所有失去的东西");
        add(ModSubtiles.omniviolet, "全知瑾");
        add(ModSubtiles.omnivioletFloating, "浮空全知瑾");
        add(ModSubtiles.omniviolet.getDescriptionId() + ".reference", "我知万物");
        add(ModSubtiles.geminiorchid, "双子兰");
        add(ModSubtiles.geminiorchidFloating, "浮空双子兰");
        add(ModSubtiles.geminiorchid.getDescriptionId() + ".reference", "为什么乌鸦像写字台？");
        add(ModSubtiles.bellflower, "风铃草");
        add(ModSubtiles.bellflowerFloating, "浮空风铃草");
        add(ModSubtiles.bellflower.getDescriptionId() + ".reference", "迷失的风");
        add(ModSubtiles.reikarorchid, "雷卡兰");
        add(ModSubtiles.reikarorchidFloating, "浮空雷卡兰");
        add(ModSubtiles.reikarorchid.getDescriptionId() + ".reference", "游戏崩溃");
        add(ModSubtiles.bloodyenchantress, "鲜血妖姬");
        add(ModSubtiles.bloodyenchantressFloating, "浮空鲜血妖姬");
        add(ModSubtiles.bloodyenchantress.getDescriptionId() + ".reference", "用鲜血和暗影缔结契约吧");
        add(ModSubtiles.edelweiss, "雪绒花");
        add(ModSubtiles.edelweissFloating, "浮空雪绒花");
        add(ModSubtiles.edelweiss.getDescriptionId() + ".reference", "你想堆个雪人吗");
        add(ModSubtiles.tinkleflower, "叮当舞花");
        add(ModSubtiles.tinkleflowerFloating, "浮空叮当舞花");
        add(ModSubtiles.tinkleflower.getDescriptionId() + ".reference", "接下来是我的回合");

        add(ModSubtiles.serenitian, "永寂龙胆");
        add(ModSubtiles.serenitianFloating, "浮空永寂龙胆");
        add(ModSubtiles.serenitian.getDescriptionId() + ".reference", "无念，断绝");
        add(ModSubtiles.annoying, "神烦花");
        add(ModSubtiles.annoyingFloating, "浮空神烦花");
        add(ModSubtiles.annoying.getDescriptionId() + ".reference", "摸了");

    }

    private void langAdvancement()
    {
        prefixAdvancement(LibAdvancementNames.ROOT, "欢迎来到世界", "不要错过今天，去过好每一天");
        prefixAdvancement(LibAdvancementNames.NIGHTMARE_FUEL_EAT, "Deep Dark Fantasy", "食用一个梦魇燃料(这真的能吃吗)");
        prefixAdvancement(LibAdvancementNames.THE_CHAOS_CRAFT, "一点都不混沌的物质", "合成混沌物质");
        prefixAdvancement(LibAdvancementNames.EGO_DEFEAT, "KiLLER LADY", "击败本我");

        prefixAdvancement(LibAdvancementNames.MANA_READER_CRAFT, "Satisfaction", "获得魔力读取器");
        prefixAdvancement(LibAdvancementNames.SPIRIT_FRAG, "PONPONPON", "获得精神碎片");
        prefixAdvancement(LibAdvancementNames.SHADOW_WARRIOR, "Crazy ∞ nighT", "装备一套暗影武士");
        prefixAdvancement(LibAdvancementNames.GOBLIN_SLAYER, "Befall", "装备一套哥布林杀手");
        prefixAdvancement(LibAdvancementNames.MIKU, "Cat's Dance", "装备一套星空歌姬");
        prefixAdvancement(LibAdvancementNames.BLOODY_ENCHANTRESS_USE, "Sweet Devil", "将自己沐浴在血海中");
        prefixAdvancement(LibAdvancementNames.TINKLE_FLOWER_USE, "Project Diva desu", "在叮当舞花旁跳舞");


        prefixAdvancement(LibAdvancementNames.MAID, "Drug Of Gold", "装备一套昴星团战斗女仆");
        prefixAdvancement(LibAdvancementNames.SHOOTING_GUARDIAN, "CONNECT", "装备一套银翼护卫");
        prefixAdvancement(LibAdvancementNames.ROD_OF_DISCORD_CRAFT, "不是Discord", "合成不谐传送杖");
        prefixAdvancement(LibAdvancementNames.POWER_FRAME_CRAFT, "Letter Song", "获得力量框架");
        prefixAdvancement(LibAdvancementNames.NATURE_ORB_CRAFT, "雨夢楼", "获得自然蕴息宝珠");


        prefixAdvancement(LibAdvancementNames.FAILNAUGHT_CRAFT, "from Y to Y", "合成百中弓");
        prefixAdvancement(LibAdvancementNames.EXCALIBER_CRAFT, "ReAct", "合成王者圣剑");
        prefixAdvancement(LibAdvancementNames.FIRST_FRACTAL_CRAFT, "Infinity +1 Sword", "合成最初分型");
        prefixAdvancement(LibAdvancementNames.THE_UNIVERSE_CRAFT, "Gears of Love", "合成宇宙之心");
        prefixAdvancement(LibAdvancementNames.SAGES_MANA_RING_CRAFT, "COLOR", "合成贤者魔力指环");
        prefixAdvancement(LibAdvancementNames.CORE_GOD_CRAFT, "All Hail The Queen", "获得律者核心");
        prefixAdvancement(LibAdvancementNames.SUN_RING_CRAFT, "Promise", "获得圣阳尊戒");
        prefixAdvancement(LibAdvancementNames.MOON_PENDANT_CRAFT, "Crystalline", "获得蚀月之心");
    }

    private void prefixAdvancement(String name, String title, String text)
    {
        add("advancement.extrabotany:" + name + ".title", title);
        add("advancement.extrabotany:" + name + ".desc", text);
    }

    private void langPantchouli()
    {
        add("extrabotany.category.extrabotany", "额外的知识");
        add("extrabotany.entry.header", "欢迎来到世界");
        add("extrabotany.page.header0", "欢迎来到魔力至上主义的世界。您是一位具有极大潜力的人，我在您的身上看到了未来。我相信您正渴求着更多的知识，而我正是来改变您，向您传授知识的人。<br>1.18.2版本由Gold_Chick移植");
        add("extrabotany.page.header1", "每当您解锁新的进度时，书中的知识都会增加。我在此可以给您一个提示，食用梦魇燃料可以获得精神燃料。");
        add("extrabotany.entry.nightmarefuel", "梦魇燃料");
        add("extrabotany.page.nightmarefuel0", "$(item)梦魇燃料$(0)是噩梦的集合体，食用后会遭遇不幸，但也可能是美梦的开始。精灵们可能也会对这种东西感兴趣。（警告：瞬间伤害III会造成24点伤害！）");
        add("extrabotany.page.nightmarefuel1", "大脑在颤抖。");
        add("extrabotany.entry.spiritfuel", "精神燃料");
        add("extrabotany.page.spiritfuel0", "$(item)精神燃料$(0)是一种非常重要的材料。它有两种获取方式，一种是食用$(item)梦魇燃料$(0)，另一种是通过精灵交易。精灵门可以将精神燃料精炼为$(item)精神碎片$(0)。");
        add("extrabotany.page.spiritfuel1", "精神燃料");
        add("extrabotany.page.spiritfuel2", "精神碎片");
        add("extrabotany.entry.shadowium", "暗影锭");
        add("extrabotany.page.shadowium0", "以镀金土豆泥作为媒介，将梦魇燃料和源质钢锭结合得到的全新材料$(item)暗影锭$(0)。相比源质钢锭，暗影锭拥有着更大的潜能。");
        add("extrabotany.page.shadowium1", "无人生还。");
        add("extrabotany.page.shadowium2", "");
        add("extrabotany.page.shadowium3", "可以用它制作$(item)暗影武士套$(0)，这套套装能在夜晚为装备者提供极高的加成。也可以用它来制作$(item)影刃$(0)，可以传送到敌人的背后。");
        add("extrabotany.page.shadowium4", "Silent Death。");
        add("extrabotany.page.shadowium5", "暗影武士头盔");
        add("extrabotany.page.shadowium6", "暗影武士护甲");
        add("extrabotany.page.shadowium7", "暗影武士护腿");
        add("extrabotany.page.shadowium8", "暗影武士靴子");
        add("extrabotany.entry.photonium", "光子锭");
        add("extrabotany.page.photonium0", "精神燃料与源质钢锭的完美结合。$(item)光子锭$(0)是一种优秀的材料，它是暗影锭的对立面。");
        add("extrabotany.page.photonium1", "三块一个，十块三个。");
        add("extrabotany.page.photonium2", "");
        add("extrabotany.page.photonium3", "它可以用于制作$(item)哥布林杀手套$(0)，这套装备可以在白天提供极强的再生和生命提升能力。");
        add("extrabotany.page.photonium4", "哥布林杀手头盔");
        add("extrabotany.page.photonium5", "哥布林杀手胸甲");
        add("extrabotany.page.photonium6", "哥布林杀手护腿");
        add("extrabotany.page.photonium7", "哥布林杀手靴子");
        add("extrabotany.entry.gildedpotato", "镀金土豆");
        add("extrabotany.page.gildedpotato0", "镀金了的土豆，可能在未来会有大用处。");
        add("extrabotany.page.gildedpotato1", "奇迹和魔法都是存在的");
        add("extrabotany.page.gildedpotato2", "镀金土豆做成的土豆泥，食用后会获得意想不到的效果。");
        add("extrabotany.page.gildedpotato3", "金色传说");
        add("extrabotany.entry.manareader", "魔力读取器");
        add("extrabotany.page.manareader0", "手持魔力读取器右键一个魔力池，可以查看其中含有多少mana。对于比较小的数字不是很准确。");
        add("extrabotany.page.manareader1", "让我看看！");
        add("extrabotany.armorset.mana.desc", "全套提供魔力工具及法杖消耗魔力减免 %s");
        add("extrabotany.armorset.magic_protection.desc", "全套提供对魔法伤害 %s 减免");
        add("extrabotany.armorset.miku.desc", "超强魔力亲和");
        add("extrabotany.armorset.maid.desc0", "空手怪力");
        add("extrabotany.armorset.maid.desc1", "再生增强");
        add("extrabotany.armorset.maid.desc2", "魔力亲和");
        add("extrabotany.armorset.maid.desc3", "解除负面效果");
        add("extrabotany.armorset.shadowwarrior.desc0", "黑夜给了我黑色的眼睛，");
        add("extrabotany.armorset.shadowwarrior.desc1", "我却用它寻找光明。");
        add("extrabotany.armorset.goblinslayer.desc0", "我不拯救世界，只管杀哥布林。");
        add("extrabotany.armorset.goblinslayer.desc1", "想象力也是武器，没有想象力的人会先死。");
        add("extrabotany.armorset.goblinslayer.desc2", "在白天具有更强的力量");
        add("extrabotany.armorset.shootingguardian.desc0", "快速拉弓");
        add("extrabotany.armorset.shootingguardian.desc1", "攻击附带穿甲和吸血");
        add("extrabotany.armorset.shootingguardian.desc2", "伤害抵抗");
        add("extrabotany.armorset.shootingguardian.desc3", "生命恢复大幅减弱");
        add("extrabotany.armorset.shadowwarrior.name", "暗影武士");
        add("extrabotany.armorset.goblinslayer.name", "哥布林杀手");
        add("extrabotany.armorset.miku.name", "星空歌姬");
        add("extrabotany.armorset.maid.name", "昴星团战斗女仆");
        add("extrabotany.armorset.shootingguardian.name", "银翼射手");
        add("extrabotany.entry.elementstone", "元素符石");
        add("extrabotany.page.elementstone0", "将元素符文炼制成对应的符石。$(item)风之符石$(0)可以增加佩戴者的移速，$(item)地之符石$(0)可以提高佩戴者的护甲，$(item)水之符石$(0)可以减少佩戴者的魔力消耗，$(item)火之符石$(0)可以提升佩戴者的攻击力。");
        add("extrabotany.page.elementstone1", "风之符石");
        add("extrabotany.page.elementstone2", "地之符石");
        add("extrabotany.page.elementstone3", "水之符石");
        add("extrabotany.page.elementstone4", "火之符石");
        add("extrabotany.entry.supreme_elementstone", "至高符石（未完成）");
        add("extrabotany.page.supreme_elementstone0", "元素符石已经十分强大了。但是还有一种方法能从元素中取得更强大的力量。不过，这也导致了一些缺点，同时也加剧了不同元素之间的冲突。比如说，如果其中一个是至高级别的符石，那么你将无法同时使用$(item)风之符石$(0)和$(item)地之符石$(0)，或者$(item)火之符石$(0)和$(item)水之符石$(0)。");
        add("extrabotany.page.supreme_elementstone1", "Aero Stone");
        add("extrabotany.page.supreme_elementstone2", "Earth Stone");
        add("extrabotany.page.supreme_elementstone3", "Aqua Stone");
        add("extrabotany.page.supreme_elementstone4", "Ignis Stone");
        add("extrabotany.entry.deathring", "诅咒指环");
        add("extrabotany.page.deathring0", "$(item)诅咒指环$(0)能消耗少量的&4魔力$(0)使小范围内所视的生物获得凋零和不幸的效果。");
        add("extrabotany.page.deathring1", "力量本身并不可怕，可怕的是它的主人。");
        add("extrabotany.entry.advancedmaterial", "高等形式");
        add("extrabotany.page.advancedmaterial0", "借助$(item)精神碎片$(0)的力量将两种材料结合构成的高等形式，这类材料拥有着更强的属性与潜力。");
        add("extrabotany.page.advancedmaterial1", "混沌物质");
        add("extrabotany.page.advancedmaterial2", "起源物质");
        add("extrabotany.page.advancedmaterial3", "终末物质");
        add("extrabotany.page.advancedmaterial4", "宇宙物质");
        add("extrabotany.entry.brew", "全新酿造类型");
        add("extrabotany.page.brew0", "拿着魔法玻璃空瓶对着魔力池右键可以获得魔力鸡尾酒，饮用魔力鸡尾酒可以获得多种正面buff。");
        add("extrabotany.page.brew1", "");
        add("extrabotany.page.brew2", "比起普通的酿造能够多饮用两次，并且药水的持续时间也要来得更长。只需要将装有所需酿造的烧瓶和魔力鸡尾酒合成即可得到对应的秘制鸡尾酒。 ");
        add("extrabotany.page.brew3", "将装有所需酿造的秘制鸡尾酒和爆裂紫颂果合成即可得到对应的圣水手雷。圣水手雷爆炸后会给予大范围生物较高的伤害，给予玩家其所含的正面效果而给予其他生物负面效果。 <br>可以说是几乎完美的酿造，但为什么是一颗手雷呢?");
        add("extrabotany.page.brew4", "能够大幅提升挖掘效率，但是需要透支运气。");
        add("extrabotany.page.brew5", "拥有超强的防御力，但行动却也变得像乌龟一样慢。");
        add("extrabotany.page.brew6", "获得超强的力量，但是代价是什么?");
        add("extrabotany.page.brew7", "几乎所有的正面效果!但只能维持一小会。");
        add("extrabotany.page.brew8", "能够让人漂浮起来的药水，或许对敌人使用会更好?");
        add("extrabotany.entry.powerglove", "强力手套");
        add("extrabotany.page.powerglove0", "装备$(item)强力手套$(0)后，摁住鼠标左键会自动以最高攻速进行攻击。");
        add("extrabotany.page.powerglove1", "");
        add("extrabotany.entry.rodofdiscord", "不谐传送杖");
        add("extrabotany.page.rodofdiscord0", "$(item)不谐传送杖$(0)可以消耗魔力传送到准心所指的位置，但每次使用后会使使用者进入反胃状态。在冷却期间继续使用会消耗使用者的生命值。");
        add("extrabotany.page.rodofdiscord1", "");
        add("extrabotany.entry.jingweifeather", "精卫之羽");
        add("extrabotany.page.jingweifeather0", "装备$(item)精卫之羽$(0)后，每次空手攻击都可以发射出一个火球，造成伤害的时候回复1点吸收值，吸收值最多叠加到10点。");
        add("extrabotany.page.jingweifeather1", "");
        add("extrabotany.entry.frostring", "霜冻之星");
        add("extrabotany.page.frostring0", "$(item)霜冻之星$(0)能让装备者行走时冻结脚下大范围的水和岩浆，并会使小范围内装备者所视的生物大幅减速。");
        add("extrabotany.page.frostring1", "我是天灾的冰冻之心。");
        add("extrabotany.entry.sages_mana_ring", "贤者魔力指环");
        add("extrabotany.page.sages_mana_ring0", "$(item)贤者魔力指环$(0)可以存储几乎无限的$(thing)魔力$(0)。");
        add("extrabotany.page.sages_mana_ring1", "蛋糕是个谎言。");
        add("extrabotany.foxmaskinfo0", "与君相别离，不知何日是归期，我如朝露转瞬晞");
        add("extrabotany.foxmaskinfo1", "如今面具和花散里分开了");
        add("extrabotany.foxmaskinfo2", "派蒙也不会叫她面具巫女小姐了");
        add("extrabotany.entry.lens", "全新魔力透镜");
        add("extrabotany.page.lens0", "$(item)魔力透镜$(0)能够模拟魔力池，使魔力脉冲触碰的物品接受魔力灌注。如果在发射器下方放置炼金催化器或炼造催化器也会有对应的效果。<br>魔力透镜会提高每次魔力脉冲携带的魔力量。");
        add("extrabotany.page.lens1", "魔力");
        add("extrabotany.page.lens2", "$(item)冲击透镜$(0)会使魔力脉冲推动所有接触到的生物，强制它们跟随着魔力脉冲移动。");
        add("extrabotany.page.lens3", "冲击");
        add("extrabotany.page.lens4", "$(item)追踪透镜$(0)会使魔力脉冲锁定其3格以内的生物并持续追踪，直到其中一方消失。");
        add("extrabotany.page.lens5", "追踪");
        add("extrabotany.page.lens6", "$(item)冶炼透镜$(0)会冶炼魔力脉冲所触碰到的方块，掉落冶炼成品，这会消耗少量魔力。");
        add("extrabotany.page.lens7", "冶炼");
        add("extrabotany.page.lens8", "$(item)药水透镜$(0)会使魔力脉冲所触碰到的第一个生物获得对应的药水效果。<br>需要将透镜和对应的烧瓶酿造合成来决定其效果。");
        add("extrabotany.page.lens9", "药水");
        add("extrabotany.page.lens10", "$(item)超导透镜$(0)会增加魔力脉冲携带的魔力、魔力流失的速率、魔力脉冲的速度，同时会对玩家和其他生物造成大量伤害。注意：$(thing)不会降低防御。$(0)(不会做)");
        add("extrabotany.entry.dimension_catalyst", "次元催化器");
        add("extrabotany.page.dimension_catalyst0", "$(item)次元催化器$(0)连接了主世界与其他两个次元，它可以消耗$(thing)魔力$(0)来将主世界的资源转化为其他次元内的资源。");
        add("extrabotany.page.dimension_catalyst1", "");
        add("extrabotany.entry.peace_amulet", "和平友好之证");
        add("extrabotany.page.peace_amulet0", "装备$(item)和平友好之证$(0)后，本Mod内的所有装备不再会对其他玩家和非敌对生物造成影响。");
        add("extrabotany.page.peace_amulet1", "");
        add("extrabotany.entry.ego", "本我 (盖亚守护者III)");
        add("extrabotany.page.ego0", "可以消耗自然蕴息宝珠中储存的自然之息来召唤本我，这一过程需要消耗20w自然之息；也可以使用邀请函来召唤。挑战本我需要遵守其规则之力，首先玩家不能携带、持有、装备任何非植物魔法或原版或额外植物学的物品，否则会被缴械在地上。");
        add("extrabotany.page.ego1", "其次参与战斗的玩家数不能超过召唤时的玩家数，否则本我会离开。本我诸武精通，在第一阶段会使用$(item)真泰拉之刃$(0)或$(item)真影刃$(0)。在进入第二阶段时会进入一段时间的无敌，期间会连续释放地雷阵，挑战者需要及时规避。不同颜色的地雷会有不同的效果。");
        add("extrabotany.page.ego2", "战地");
        add("extrabotany.page.ego3", "邀请函");
        add("extrabotany.page.ego4", "第二阶段的本我会使用$(item)波涌之刃$(0)或$(item)狂星之怒$(0),同时每累积一定传送次数会再次召唤地雷阵。当血量低于一定值时会进入第三阶段，无敌、回复生命的同时会召唤四个仆从攻击挑战者。仆从分别使用四种武器，提前击败仆从可以提前结束本我的无敌状态。");
        add("extrabotany.page.ego5", "本我在第三阶段只会使用$(item)最初分型$(0)对玩家进行攻击，其能力值在这一阶段会达到峰值。击败本我可以得到$(item)潘多拉魔盒$(0)，打开它可以获得丰富的奖励，包含一个$(item)英雄勋章$(0)。");
        add("extrabotany.page.ego6", "至高教义");
        add("extrabotany.entry.gold_cloth", "莱茵河的黄金");
        add("extrabotany.page.gold_cloth0", "$(item)莱茵河的黄金$(0)是一种优秀的材料。除此之外，它可以用来修改遗物的绑定，只需要将它和遗物合成，就能使遗物恢复成未绑定的状态。");
        add("extrabotany.page.gold_cloth1", "");
        add("extrabotany.page.gold_cloth2", "泰拉钢套的改进版，拥有超强的再生能力和防御力，并且能清除负面效果，同时能使装备者获得空手怪力。");
        add("extrabotany.page.gold_cloth3", "昴星团战斗女仆头饰");
        add("extrabotany.page.gold_cloth4", "昴星团战斗女仆服");
        add("extrabotany.page.gold_cloth5", "昴星团战斗女仆裙甲");
        add("extrabotany.page.gold_cloth6", "昴星团战斗女仆靴子");
        add("extrabotany.entry.miku", "星空歌姬套");
        add("extrabotany.page.miku0", "魔钢套的改进版，拥有着极强的魔力亲和力，可以让装备者大幅减少其魔力消耗。");
        add("extrabotany.page.miku1", "星空歌姬头饰");
        add("extrabotany.page.miku2", "星空歌姬服");
        add("extrabotany.page.miku3", "星空歌姬裙甲");
        add("extrabotany.page.miku4", "星空歌姬鞋子");
        add("extrabotany.entry.aerialite", "天空锭");
        add("extrabotany.page.aerialite0", "将天空的力量与大量的魔力注入到$(item)龙石$(0)中得到的高级合金，拥有着媲美$(item)泰拉钢$(0)的优秀属性。");
        add("extrabotany.page.aerialite1", "");
        add("extrabotany.page.aerialite2", "由天空锭制成的护甲，拥有着优秀的保护能力。当穿齐一套时，可以赋予玩家极高的机动性、攻击力和吸血能力，并使玩家的拉弓速度翻倍。但玩家的生命恢复能力会受到限制。");
        add("extrabotany.page.aerialite3", "银翼护卫头盔");
        add("extrabotany.page.aerialite4", "银翼护卫胸甲");
        add("extrabotany.page.aerialite5", "银翼护卫护腿");
        add("extrabotany.page.aerialite6", "银翼护卫靴子");
        add("extrabotany.entry.life_essence", "盖亚之魂材料");
        add("extrabotany.page.life_essence0", "在一些材料中混入$(item)盖亚之魂$(0)可以让他们不分彼此。比如，$(item)彩虹花瓣$(0)可以直接代替其他花瓣参加合成；$(item)元灵符文$(0)、$(item)大罪符文$(0)可以通过和其他符文合成的方式转变为其他符文。不过，这可能会导致合成冲突。");
        add("extrabotany.page.life_essence1", "One For All");
        add("extrabotany.page.life_essence2", "两种符文和合成所需的八种符文中的任意一个合成，可以得到两个该符文。");
        add("extrabotany.entry.genesis_crystal", "创世结晶");
        add("extrabotany.page.genesis_crystal0", "借助大量本我的掉落物，可以从异空间内获得一些有用的物品。");
        add("extrabotany.page.genesis_crystal1", "使用创世结晶获取原石。");
        add("extrabotany.page.genesis_crystal2", "合成纠缠之缘。");
        add("extrabotany.page.genesis_crystal3", "合成相遇之缘。");
        add("extrabotany.page.genesis_crystal4", "祈愿池");
        add("extrabotany.page.genesis_crystal5", "使用$(item)纠缠之缘￥(0)或者$(item)相遇之缘$(0)右击$(item)次元催化剂$(0)开始祈愿。");
        add("extrabotany.entry.livingwood_crossbow", "活木弩");
        add("extrabotany.page.livingwood_crossbow0", "仿照活木弓的方式，可以用$(item)活木$(0)、$(item)魔力钢锭$(0)和$(item)蕴魔丝线(0)制造更加耐用的活木弩。此外，它也可以用魔力修复。");
        add("extrabotany.page.livingwood_crossbow1", "它能把飞机打下来么？");
        add("extrabotany.entry.true_swords", "真·剑");
        add("extrabotany.page.true_swords0", "经过研究，$(item)盖亚之魂$(0)和其他材料能够解放你所拥有的一些武器的真正力量。因此，这些武器在盖亚的手中也将更加强大。");
        add("extrabotany.page.true_thunstar_caller", "$(item)召雷者和召星者$(0)的升级版。每次挥动会从天上导引出闪电。虽然距离真正的闪电尚有不足，但用于攻击已经足够。当击中着火的敌人时会因为超载的原因增加伤害。");
        add("extrabotany.page.true_terra_blade", "$(item)泰拉之刃$(0)的升级版。每次挥动都会发射可以贯穿敌人的剑气。");
        add("extrabotany.page.true_shadow_katana", "$(item)影刃$(0)的升级版。每次挥动都会召唤两把真影刃自动索敌并追击敌人。");
        add("extrabotany.page.influx_waver", "每次挥动所发射的剑气会在命中敌人后在其附近重新出现并追击敌人。");
        add("extrabotany.entry.failnaught", "百中弓");
        add("extrabotany.page.failnaught0", "$(item)百中弓$(0)可以消耗魔力发射出一支能够贯穿敌人的箭矢，这支箭能对周围较大范围敌人造成伤害。蓄力时间越久，箭矢的伤害就越高，飞行距离也越长。");
        add("extrabotany.page.failnaught1", "百发百中。");
        add("extrabotany.entry.buddhistrelics", "源初造物丨虚空万藏");
        add("extrabotany.page.buddhistrelics0", "$(item)源初造物丨虚空万藏$(0)可以近乎完美地模拟几种遗物的形态，使用者可以通过ctrl键来切换形态，但维持其他形态需要持续消耗魔力，一旦魔力不足就会恢复原形。各形态的数据都是独立储存的。");
        add("extrabotany.entry.excaliber", "王者圣剑");
        add("extrabotany.page.excaliber0", "$(item)王者圣剑$(0)可以消耗魔力发射金色的魔力光束，魔力光束可以追踪敌人并造成物理穿甲伤害。");
        add("extrabotany.page.excaliber1", "");
        add("extrabotany.entry.mastermanaring", "大师魔力之戒");
        add("extrabotany.page.mastermanaring0", "$(item)大师魔力之戒$(0)能够储存近乎无限的魔力。");
        add("extrabotany.page.mastermanaring1", "蛋糕是个谎言。");
        add("extrabotany.entry.manasteel_shield", "魔力盾牌");
        add("extrabotany.page.manasteel_shield0", "普通的盾牌实在是太容易损坏了。使用$(item)魔力钢锭$(0)代替$(item)铁锭$(0)可能会更好一些。");
        add("extrabotany.page.manasteel_shield1", "化方为圆。");
        add("extrabotany.page.manasteel_shield2", "在制作材料中加入$(item)泥土(0)会让盾牌和大地的亲和能力更强，右击地面可以迅速创建一堵土墙。不幸的是，这让盾牌的耐久显著降低了，并且失去了普通的防御能力。");
        add("extrabotany.page.manasteel_shield3", "它会掉渣吗。");
        add("extrabotany.entry.forest_book", "森林书");
        add("extrabotany.page.forest_book0", "将$(item)金苹果$(0)和自然的力量灌注进书本可以制作$(item)森林书$(0)。右击会受到6点$(thing)真实伤害$(0)并获得12点$(thing)生命吸收$(0)和5秒钟的$(thing)铭记$(0)效果。5秒后消耗最多6颗金心回复最多6生命值。");
        add("extrabotany.page.forest_book1", "“你好”，“谢谢”，最后是“再见”。");
        add("extrabotany.entry.puredaisy_pendant", "白雏菊项链");
        add("extrabotany.page.puredaisy_pendant0", "手持$(item)白雏菊项链$(0)右击方块与$(item)白雏菊$(0)效果相同，但这一过程需要消耗少量魔力，并且随着使用次数的增加，冷却时间也会变长。即使采用某些方法修复它的耐久也是如此。");
        add("extrabotany.page.puredaisy_pendant1", "My beauty is in position.");
        add("extrabotany.entry.potato_chip", "薯片");
        add("extrabotany.page.potato_chip0", "$(item)薯片$(0)拥有着和不死图腾类似的效果。装备后，在受到致死伤害时会激活，消耗魔力获得多种增益并回复生命至5点。这一效果有一定的冷却时间，并且会消耗大量的魔力。");
        add("extrabotany.page.potato_chip1", "");
        add("extrabotany.entry.elf_king_ring", "精灵王指环");
        add("extrabotany.page.elf_king_ring0", "$(item)精灵王指环$(0)是一种奇特的戒指，只要将它与别的戒指合成，就可能获得这种戒指的特殊能力。$(thing)储存魔力$(0)等能力不能正常作用于$(item)精灵王指环$(0)。$(item)精灵王指环$(0)可以额外获取总共两个戒指的能力，有些能力可以叠加作用。$(br)对$(thing)遗物$(0)不生效。$(br)另外，在工作台中可以消去$(item)精灵王指环$(0)的能力。");
        add("extrabotany.page.elf_king_ring1", "");
        add("extrabotany.entry.power_frame","力量框架");
        add("extrabotany.page.power_frame0","将魔力物品或者$(item)自然蕴息宝珠$(0)放入$(item)力量框架$(0)中，力量框架会抽取上方魔力池的魔力来为其充能。");
        add("extrabotany.page.power_frame1","");
        add("extrabotany.entry.nature_orb","自然蕴息宝珠");
        add("extrabotany.page.nature_orb0","$(item)自然蕴息宝珠$(0)可以储存自然之息，利用自然的力量来守护携带者。魔力的加护会提供魔力，再生的加护会加快生命恢复，森罗的加护会清除负面状态。你需要搭建一个多方块结构来为自然蕴息宝珠充能，结构中魔力池中的魔力可以加快这一进度。");
        add("extrabotany.page.nature_orb1","不是气血宝珠。");
        add("extrabotany.page.nature_orb2","");
        add("extrabotany.entry.sun_ring", "圣阳尊戒");
        add("extrabotany.page.sun_ring0", "$(item)圣阳尊戒$(0)是$(item)精灵王指环$(0)的升级版，能够获取八个戒指的能力，包括遗物。然而，$(item)圣阳尊戒$(0)仍然不能获得$(thing)储存魔力$(0)等能力。");
        add("extrabotany.page.sun_ring1", "渡火者的醒悟");
        add("extrabotany.entry.moon_pendant", "蚀月之心");
        add("extrabotany.page.moon_pendant0", "$(item)蚀月之心$(0)和$(item)圣阳尊戒$(0)具有类似的力量，不同之处只是在于$(item)蚀月之心$(0)是一种项链，并且只能获取六个项链的能力。");
        add("extrabotany.page.moon_pendant1", "破冰踏雪的回音");


        add("extrabotany.entry.mini_item", "缩小工具");
        add("extrabotany.page.mini_item0", "像$(thing)缩小花$(0)那样，可以使用$(thing)魔力$(0)缩小一些工具，从而达到不可思议的效果。$(br)$(br)比如$(item)活木短弓$(0)无需拉弓，左键即可射出箭矢。然而，这仍然会消耗箭矢，并且会造成低一些的伤害和高一点的击退。或许应该考虑更好一点的弓材料才对。$(br)这种方式射出的箭矢与水具有较强的亲和性，并且具有一定的穿透能力。");
        add("extrabotany.page.mini_item1", "");
        add("extrabotany.page.mini_item2", "通过甩动各种$(thing)短弓$(0)扔出箭矢是可行的，那么为什么不用手代替这个过程呢？将普通的$(item)箭$(0)转化成$(item)魔力短箭$(0)可以让玩家方便地右击扔出。不过，它不能再被弓使用，无法再携带药水效果，扔出的箭矢也将失去魔力。$(br)这种方式扔出的箭矢与水具有较强的亲和性，并且具有一定的穿透能力。");
        add("extrabotany.page.mini_item3", "");
        add("extrabotany.page.mini_item4", "$(item)天空法杖$(0)飞跃的高度有时候大大超过了我们所需，$(item)天空短杖$(0)就解决了这个问题。在这个过程中灌注的魔力也让$(item)天空短杖$(0)能够安装一些配件。");
        add("extrabotany.page.mini_item5", "");

        add("extrabotany.page.mini_item114", "$(item)水晶短弓$(0)比$(item)活木短弓$(0)更加强大，同时也可以用$(thing)魔力$(0)产生箭矢，不过需要消耗的$(thing)魔力$(0）相比$(item)水晶弓$(0)更多一点。");

        add("extrabotany.entry.elementium_shield", "高级魔力盾牌");
        add("extrabotany.page.elementium_shield0", "使用更好的材料制作的盾牌具有更加特殊的能力。这些盾牌均能够格挡一些$(thing)遗物$(0)产生的弹射物。然而，这些盾牌普遍具有较低的耐久，难以持续使用。");
        add("extrabotany.page.elementium_shield1", "$(item)源质钢盾牌$(0)成功格挡伤害后会暂时提升盾牌本身的属性。");
        add("extrabotany.page.elementium_shield2", "$(item)混沌盾牌$(0)成功格挡伤害后会对周围的生物（包括使用者）造成一定爆炸伤害。");
        add("extrabotany.page.elementium_shield3", "$(item)泰拉钢盾牌$(0)会反弹部分成功格挡的近战伤害。");
        add("extrabotany.page.elementium_shield4", "$(item)天空盾牌$(0)成功格挡伤害后会暂时让使用者变得更加轻盈。");
        pantchouliFlower();
    }

    private void pantchouliFlower()
    {
        add("extrabotany.entry.sunshine_lily", "日耀百合");
        add("extrabotany.page.sunshine_lily0", "拥有能将阳光转化为$(thing)魔力$(0)的能力，但产能效率并不可观。会在3个minecraft日后枯萎。");
        add("extrabotany.page.sunshine_lily1", "光与热。");
        add("extrabotany.entry.moonlight_lily", "月光百合");
        add("extrabotany.page.moonlight_lily0", "拥有能在夜晚产生$(thing)魔力$(0)的能力，但产能效率并不可观。会在3个minecraft日后枯萎。");
        add("extrabotany.page.moonlight_lily1", "愚者的播种。");
        add("extrabotany.entry.omni_violet", "全知瑾");
        add("extrabotany.page.omni_violet0", "知识就是力量，$(item)全知瑾$(0)能够将书本或成书转化为$(thing)魔力$(0)。 如果你在其周围按附魔台方式摆放上书架或者能够替代书架的方块的话能够起到催化剂的效果，增加其产魔效率。书架离得太近或太远均不会产生效果。魔力产出最多达到原来的四倍。");
        add("extrabotany.page.omni_violet1", "阿克夏记录。");
        add("extrabotany.entry.gemini_orchid", "双子兰");
        add("extrabotany.page.gemini_orchid0", "$(item)双子兰$(0)相当于一台温差发电机。假如你在其周围8格内一格倒入岩浆，一格倒入水，它就会开始产出$(thing)魔力$(0)。8格内的最大温差越大，$(thing)魔力$(0)产出也就越快。<br>注意，这是一朵被动产能花，会在3个minecraft日后枯萎。");
        add("extrabotany.page.gemini_orchid1", "我就是力量的化身!");
        add("extrabotany.entry.bell_flower", "风铃草");
        add("extrabotany.page.bell_flower0", "$(item)风铃草$(0)能将风能转化为$(thing)魔力$(0)，需要摆放在露天的高处，海拔越高效率越高，直到y=319。周围的方块或者$(item)风铃草$(0)会降低$(item)风铃草$(0)的效率。会在3个minecraft日后枯萎。");
        add("extrabotany.page.bell_flower1", "只有风暴才能击倒大树。");
        add("extrabotany.entry.reikarorchid", "雷卡兰");
        add("extrabotany.page.reikarorchid0", "$(item)雷卡兰$(0)在无魔力时可以将闪电的巨大能量转化为$(thing)魔力$(0)，每当有闪电劈在其附近时，就会立刻产出巨大$(thing)魔力$(0)，雷卡兰本身也具有一定的雷电吸引能力。若$(item)雷卡兰$(0)中储存有$(thing)魔力$(0)，则无法吸收闪电并且会引发爆炸。");
        add("extrabotany.page.reikarorchid1", "就决定是你了!");
        add("extrabotany.entry.bloodyenchantress", "鲜血妖姬");
        add("extrabotany.page.bloodyenchantress0", "$(item)鲜血妖姬$(0)能够将生物的生命值转化为$(thing)魔力$(0)，一旦有生物在其附近就会开始$(thing)魔力$(0)的转化，效率较为可观。需要注意的是通过这种方式献祭的生物不会有任何掉落。");
        add("extrabotany.page.bloodyenchantress1", "每次噬咬都会给对方添加血之诱惑效果并不断叠加，每层效果都会降低花的产魔，并且周围生物血之诱惑效果等级总和超过一定值时会停止工作。");
        add("extrabotany.page.bloodyenchantress2", "死亡是唯一的解药。");
        add("extrabotany.entry.tinkle_flower", "叮当舞花");
        add("extrabotany.page.tinkle_flower0", "$(item)叮当舞花$(0)可以将玩家的动能变为魔力。玩家在其附近移动时会产出$(thing)魔力$(0)，但也会加速玩家的饱食度消耗。");
        add("extrabotany.page.tinkle_flower1", "叮咚，我来找你了哦");
        add("extrabotany.entry.edelweiss", "雪绒花");
        add("extrabotany.page.edelweiss0", "$(item)雪绒花$(0)可以通过吞噬雪傀儡来产出$(thing)魔力$(0)。虽然$(thing)魔力$(0)产出是瞬时的，但也需要一小会的冷却。");
        add("extrabotany.page.edelweiss1", "If you are reading this, you can read.");


        add("extrabotany.entry.serenitian", "永寂龙胆");
        add("extrabotany.page.serenitian0", "$(item)永寂龙胆$(0)可以使周围同一平面上的被动产能花停止枯萎的进程，并使进程归零。");
        add("extrabotany.page.serenitian1", "事事皆虚空");
        add("extrabotany.entry.annoying_flower", "神烦花");
        add("extrabotany.page.annoying_flower0", "在$(item)神烦花$(0)周围摆放上一个带水的花药台，它就可以消耗$(thing)魔力$(0)来自动钓鱼。如果给它投喂香香鸡的话，它接下来几次的工作效率会得到大幅提高并有更高概率钓到稀有物品。");
        add("extrabotany.page.annoying_flower1", "光与热。");
        add("extrabotany.page.annoying_flower2", "浸泡过$(thing)魔力$(0)的香香鸡能够回复更多的饱食度。");
        add("extrabotany.page.annoying_flower3", "毕竟我也不是什么恶魔。");
    }

    private void otherLangs()
    {
        add("extrabotany.wish.is_active", "有玩家正在使用这个祈愿池！！");
    }
}
