package chick.extrabotany.datagen.lang;

import chick.extrabotany.ExtraBotany;
import chick.extrabotany.common.ModBlocks;
import chick.extrabotany.common.ModItems;
import chick.extrabotany.common.blocks.ModSubtiles;
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
        add(ModBlocks.YLG_ORE.get(), "叶落归矿石");
        add(ModItems.RAW_YLG_CHUNK.get(), "Raw YeLuoGui Chunk");
        add(ModItems.YLG_INGOT.get(), "Legendary YeLuoGui Ingot");
        add(ModItems.OBSIDIAN_APPLE.get(), "黑曜石苹果");
        add(ModItems.SHADOW_INGOT.get(), "暗影锭");
        add(ModItems.PHOTON_INGOT.get(), "光子锭");
        add(ModItems.GILDED_POTATO.get(), "镀金服务器");
        add(ModItems.GILDED_MASHED_POTATO.get(), "镀金土豆泥");

        add(ModItems.MANA_READER.get(), "魔力读取器");
        add(ModItems.THE_CHAOS.get(), "混沌物质");
        add(ModItems.THE_ORIGIN.get(), "起源物质");
        add(ModItems.THE_END.get(), "终末物质");
        add(ModItems.THE_UNIVERSE.get(), "宇宙之心");

        add(ModItems.DEATH_RING.get(), "诅咒指环");
        add(ModItems.AERO_STONE.get(), "风之符石");
        add(ModItems.AQUA_STONE.get(), "水之符石");
        add(ModItems.EARTH_STONE.get(), "地之符石");
        add(ModItems.IGNIS_STONE.get(), "火之符石");
        add(ModItems.SUPREME_AERO_STONE.get(), "至高风之符石");
        add(ModItems.SUPREME_AQUA_STONE.get(), "至高水之符石");
        add(ModItems.SUPREME_EARTH_STONE.get(), "至高地之符石");
        add(ModItems.SUPREME_IGNIS_STONE.get(), "至高火之符石");

        add(ModItems.SHADOW_KATANA.get(), "影刃");
        add(ModItems.SHADOW_WARRIOR_HELM.get(), "暗影武士头盔");
        add(ModItems.SHADOW_WARRIOR_CHEST.get(), "暗影武士胸甲");
        add(ModItems.SHADOW_WARRIOR_LEGS.get(), "暗影武士护腿");
        add(ModItems.SHADOW_WARRIOR_BOOTS.get(), "暗影武士靴子");

        add(ModItems.GOBLINSLAYER_HELM.get(), "哥布林杀手头盔");
        add(ModItems.GOBLINSLAYER_CHEST.get(), "哥布林杀手胸甲");
        add(ModItems.GOBLINSLAYER_LEGS.get(), "哥布林杀手护腿");
        add(ModItems.GOBLINSLAYER_BOOTS.get(), "哥布林杀手靴子");

        langFlower();
        langAdvancement();
        langPantchouli();
    }

    private void langFlower()
    {
        add(ModSubtiles.sunshinelily, "日耀百合");
        add(ModSubtiles.sunshinelilyFloating, "浮空日耀百合");
        add(ModSubtiles.sunshinelily.getDescriptionId() + ".reference", "愿光芒能治愈并指引你");

        add(ModSubtiles.moonlightlily, "月光百合");
        add(ModSubtiles.moonlightlilyFloating, "浮空月光百合");
        add(ModSubtiles.moonlightlily.getDescriptionId() + ".reference", "愿你能找到所有失去的东西");
    }

    private void langAdvancement()
    {
        add("advancement.extrabotany:root.title","欢迎来到世界");
        add("advancement.extrabotany:root.desc","不要错过今天，去过好每一天");
        add("advancement.extrabotany:nightmarefuel_eat.title","Deep Dark Fantasy");
        add("advancement.extrabotany:nightmarefuel_eat.desc","食用一个梦魇燃料(这真的能吃吗)");
        add("advancement.extrabotany:manareader_craft.title","Satisfaction");
        add("advancement.extrabotany:manareader_craft.desc","获得魔力读取器");
        add("advancement.extrabotany:thechaos_craft.title","一点都不混沌的物质");
        add("advancement.extrabotany:thechaos_craft.desc","合成混沌物质");
    }
    private void langPantchouli()
    {
        add("extrabotany.category.extrabotany", "额外的知识");

        add("extrabotany.entry.header", "欢迎来到世界");
        add("extrabotany.page.header0", "欢迎来到魔力至上主义的世界。您是一位具有极大潜力的人，我在您的身上看到了未来。我相信您正渴求着更多的知识，而我正是来改变您，向您传授知识的人。<br>1.18.2版本由Gold_Chick移植");
        add("extrabotany.page.header1", "每当您解锁新的进度时，书中的知识都会增加。我在此可以给您一个提示，食用梦魇燃料可以获得精神燃料。");

        add("extrabotany.entry.nightmarefuel", "梦魇燃料");
        add("extrabotany.page.nightmarefuel0", "$(item)梦魇燃料$(0)是噩梦的集合体，食用后会遭遇不幸，但也可能是美梦的开始。");
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
        add("extrabotany.page.manareader0", "手持魔力读取器右键一个魔力池，可以查看其中含有多少mana。");
        add("extrabotany.page.manareader1", "让我看看！");

        add("extrabotany.armorset.miku.desc", "超强魔力亲和");
        add("extrabotany.armorset.maid.desc0", "空手怪力");
        add("extrabotany.armorset.maid.desc1", "再生增强");
        add("extrabotany.armorset.maid.desc2", "魔力亲和");
        add("extrabotany.armorset.shadowwarrior.desc0", "黑夜给了我黑色的眼睛，");
        add("extrabotany.armorset.shadowwarrior.desc1", "我却用它寻找光明。");
        add("extrabotany.armorset.goblinslayer.desc0", "我不拯救世界，只管杀哥布林。");
        add("extrabotany.armorset.goblinslayer.desc1", "想象力也是武器，没有想象力的人会先死。");
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

        add("extrabotany.entry.supreme_elementstone", "至高符石");
        add("extrabotany.page.supreme_elementstone0", "元素符石已经十分强大了。但是还有一种方法能从元素中取得更强大的力量。不过，这也导致了一些缺点，同时也加剧了不同元素之间的冲突。比如说，如果其中一个是至高级别的符石，那么你将无法同时使用$(item)风之符石$(0)和$(item)地之符石$(0)，或者$(item)火之符石$(0)和$(item)水之符石$(0)。");
        add("extrabotany.page.supreme_elementstone1", "Aero Stone");
        add("extrabotany.page.supreme_elementstone2", "Earth Stone");
        add("extrabotany.page.supreme_elementstone3", "Aqua Stone");
        add("extrabotany.page.supreme_elementstone4", "Ignis Stone");

        add("extrabotany.entry.deathring", "诅咒指环");
        add("extrabotany.page.deathring0", "$(item)诅咒指环$(0)能消耗少量的&4魔力$(0)使小范围内所视的生物获得凋零和不幸的效果。");
        add("extrabotany.page.deathring1", "力量本身并不可怕，可怕的是它的主人。");

        add("extrabotany.entry.advancedmaterial","高等形式");
        add("extrabotany.page.advancedmaterial0","借助$(item)精神碎片$(0)的力量将两种材料结合构成的高等形式，这类材料拥有着更强的属性与潜力。");
        add("extrabotany.page.advancedmaterial1","混沌物质");
        add("extrabotany.page.advancedmaterial2","起源物质");
        add("extrabotany.page.advancedmaterial3","终末物质");
        add("extrabotany.page.advancedmaterial4","宇宙物质");
        pantchouliFlower();
    }

    private void pantchouliFlower()
    {
        add("extrabotany.entry.sunshine_lily", "日耀百合");
        add("extrabotany.page.sunshine_lily0", "拥有能将阳光转化为$(thing)魔力$(0)的能力，但产能效率并不可观。会在3个minecraft日后枯萎。");
        add("extrabotany.page.sunshine_lily1", "光与热");

        add("extrabotany.entry.moonlight_lily", "月光百合");
        add("extrabotany.page.moonlight_lily0", "拥有能在夜晚产生$(thing)魔力$(0)的能力，但产能效率并不可观。会在3个minecraft日后枯萎。");
        add("extrabotany.page.moonlight_lily1", "愚者的播种");
    }
}
