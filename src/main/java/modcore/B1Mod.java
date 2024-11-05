package modcore;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import modcore.Characters.WuKong;
import modcore.actions.CreateSlot;
import modcore.cards.attack.*;
import modcore.cards.power.*;
import modcore.cards.skill.*;
import modcore.monsters.KuiLei;
import modcore.potion.JinDan;
import modcore.potion.QingTianHuLu;
import modcore.relics.*;
import modcore.shows.showsOrb;
import modcore.utils.GunShiDamageCalculate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static basemod.BaseMod.removeRelic;
import static com.megacrit.cardcrawl.core.Settings.language;
import static modcore.Characters.WuKong.Enums.BMW_CARD;
import static modcore.Characters.WuKong.Enums.TianMingRen;
import static modcore.utils.PowerIDUtil.resetExtraPowerID;

@SpireInitializer
public class B1Mod implements EditCardsSubscriber, SaveLoadSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber,StartGameSubscriber, OnStartBattleSubscriber, AddAudioSubscriber,PostInitializeSubscriber
{
    private static final String MY_CHARACTER_BUTTON = "B1ModResources/images/charSelect/wukongButton.png";
    private static final String MY_CHARACTER_PORTRAIT = "B1ModResources/images/charSelect/WuKong.jpg";
    private static final String BG_ATTACK_512 = "B1ModResources/images/512/cardsmall.png";
    private static final String BG_POWER_512 = "B1ModResources/images/512/cardsmall.png";
    private static final String BG_SKILL_512 = "B1ModResources/images/512/cardsmall.png";
    private static final String small_orb = "B1ModResources/images/512/smallorb.png";
    private static final String BG_ATTACK_1024 = "B1ModResources/images/1024/cardbig.png";
    private static final String BG_POWER_1024 = "B1ModResources/images/1024/cardbig.png";
    private static final String BG_SKILL_1024 = "B1ModResources/images/1024/cardbig.png";
    private static final String big_orb = "B1ModResources/images/1024/eorb.png";
    private static final String energy_orb = "B1ModResources/images/512/512orb.png";
    public static boolean SL=false;
    public static SpireConfig config = null;
    @SpireEnum
    public static AbstractCard.CardTags FENSHEN;
    public static AbstractCard.CardTags QieShouJi;
    public static ArrayList<showsOrb> MyOrblist = new ArrayList<>();

    public static final Color B1_COLOR = new Color(196.0F / 230.0F, 145.0F / 179.0F, 69.0F / 90.0F, 1.0F);

    public B1Mod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(BMW_CARD, B1_COLOR, B1_COLOR, B1_COLOR,
                B1_COLOR, B1_COLOR, B1_COLOR, B1_COLOR, BG_ATTACK_512,
                BG_SKILL_512, BG_POWER_512, energy_orb, BG_ATTACK_1024,
                BG_SKILL_1024, BG_POWER_1024, big_orb, small_orb
        );
    }

    public static void initialize() {
        new B1Mod();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new HuoYanJinJing());
        BaseMod.addCard(new GunHua());
        BaseMod.addCard(new LongJingHuMeng());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new TieBangDangTou());
        BaseMod.addCard(new ShenWaiShenFa());
        BaseMod.addCard(new FengYunZhuan());
        BaseMod.addCard(new JiaoZhen());
        BaseMod.addCard(new BeiLiWan());
        BaseMod.addCard(new Dingshenfa());
        BaseMod.addCard(new RuZhuangJinZhong());
        BaseMod.addCard(new AnShenFa());
        BaseMod.addCard(new JiangHaiFan());
        BaseMod.addCard(new RenFanTeng());
        BaseMod.addCard(new QuLaiKong());
        BaseMod.addCard(new BuPoBuLi());
        BaseMod.addCard(new CaiYuanGuangJin());
        BaseMod.addCard(new FengChuanHua());
        BaseMod.addCard(new JiuMingHaoMao());
        BaseMod.addCard(new LuanDianTianGong());
        BaseMod.addCard(new HuaXianWeiYi());
        BaseMod.addCard(new SanTouLiuBi());
        BaseMod.addCard(new TaDeShanWaiQingShan());
        BaseMod.addCard(new YiQiMengHeJiKou());
        BaseMod.addCard(new YouJiuChuChuShenXianFu());
        BaseMod.addCard(new TongTouTieBi());
        BaseMod.addCard(new XiaoGuaGua());
        BaseMod.addCard(new ChiYao());
        BaseMod.addCard(new DingXiCunShen());
        BaseMod.addCard(new TuiChunJinChi());
        //BaseMod.addCard(new PreviewJinChi());
        BaseMod.addCard(new PoGunShi());
        BaseMod.addCard(new BaJiaoShan());
        BaseMod.addCard(new XinYouYu());
        BaseMod.addCard(new TianDiQing());
        BaseMod.addCard(new JiaWeiChanShiWan());
        BaseMod.addCard(new GongShiPengPai());
        BaseMod.addCard(new ZhanGunShi());
        BaseMod.addCard(new ZhongGun());
        BaseMod.addCard(new BieYouShiJianWeiChengJian());
        BaseMod.addCard(new XuLi());
        BaseMod.addCard(new TuNa());
        //BaseMod.addCard(new MonsterDamage(5,5,0));
        BaseMod.addCard(new KanPo());
        BaseMod.addCard(new JuXingSanQi());
        BaseMod.addCard(new JinZiFa());
        BaseMod.addCard(new YaKui());
        BaseMod.addCard(new SheBuTou());
        //BaseMod.addCard(new BuYouJi());
        BaseMod.addCard(new BuYouTian());
        BaseMod.addCard(new JiaoGun());
        BaseMod.addCard(new ShanShen());
        BaseMod.addCard(new XingHongXian());
        BaseMod.addCard(new DaShengZiTai());
        BaseMod.addCard(new JinDouYun());
    }
    public void receiveAddAudio() {
        BaseMod.addAudio("B1:OPENING", "B1ModResources/audio/begin.mp3");
        BaseMod.addAudio("B1:ATTACK1", "B1ModResources/audio/attack1.mp3");
        BaseMod.addAudio("B1:ATTACK2", "B1ModResources/audio/attack2.mp3");
        BaseMod.addAudio("B1:BaJiao", "B1ModResources/audio/Bajiao.mp3");
        BaseMod.addAudio("B1:ChenWeiJian", "B1ModResources/audio/ChenWeiJian.mp3");
        BaseMod.addAudio("B1:jiuMingHaoMao", "B1ModResources/audio/jiuMingHaoMao.mp3");
        BaseMod.addAudio("B1:juXinShanQi", "B1ModResources/audio/juXinShanQi.mp3");
        BaseMod.addAudio("B1:tongTou", "B1ModResources/audio/tongTou.mp3");
        BaseMod.addAudio("B1:shenWaiShen", "B1ModResources/audio/shenWaiShen.mp3");
        BaseMod.addAudio("B1:End", "B1ModResources/audio/End.mp3");
        BaseMod.addAudio("B1:ChouHouZi", "B1ModResources/audio/ChouHouZi.mp3");
        BaseMod.addAudio("B1:shanShen", "B1ModResources/audio/shanShen.mp3");
        BaseMod.addAudio("B1:jingZiJue", "B1ModResources/audio/jingZiJue.mp3");
        BaseMod.addAudio("B1:ding1", "B1ModResources/audio/ding1.mp3");
        BaseMod.addAudio("B1:2Xu", "B1ModResources/audio/2Xu.mp3");
        BaseMod.addAudio("B1:DaShengYuYin1", "B1ModResources/audio/DaShengYuYin1.mp3");
        BaseMod.addAudio("B1:DaShengYuYin2", "B1ModResources/audio/DaShengYuYin2.mp3");
        BaseMod.addAudio("B1:DaShengYuYin3", "B1ModResources/audio/DaShengYuYin3.mp3");
    }

    @Override
    public void receivePostInitialize()
    {
        removeRelic(new StrangeSpoon());
    }

    public enum ConfigField
    {
        VOICE_DISABLED("VoiceDisabled");
        final String id;

        ConfigField(String val) {
            /*  66 */       this.id = val;
            /*     */     }
    }

    public static boolean getVoiceDisabled()
    {
        if (config == null) return false;
        return config.getBool(ConfigField.VOICE_DISABLED.id);
    }
    public void receiveEditPotions() {
        BaseMod.addPotion(JinDan.class,null,null,null,JinDan.ID);
        BaseMod.addPotion(QingTianHuLu.class,null,null,null,QingTianHuLu.ID);
    }
    @Override
    public void receiveEditCharacters()
    {
        // 向BaseMod注册人物
        BaseMod.addCharacter(new WuKong("tianmingren"), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, TianMingRen);
        receiveEditPotions();
    }
    private void initializeMonsters()
    {
        BaseMod.addMonster(KuiLei.ID, "KuiLei", () -> new MonsterGroup(new AbstractMonster[] {new KuiLei(0.0F, 0.0F)}));
    }
    @Override
    public void receiveEditRelics()
    {
        BaseMod.addRelicToCustomPool(new ChanShiWan(), BMW_CARD);
        BaseMod.addRelicToCustomPool(new SheChangSi(), BMW_CARD);
        BaseMod.addRelicToCustomPool(new LingTaiYaoMiao(), BMW_CARD);
        BaseMod.addRelicToCustomPool(new ShenBenYou(), BMW_CARD);
        BaseMod.addRelicToCustomPool(new YanKanXi(), BMW_CARD);
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "B1ModResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "B1ModResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "B1ModResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "B1ModResources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "B1ModResources/localization/" + lang + "/monsters.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, "B1ModResources/localization/" + lang + "/Orbs.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "B1ModResources/localization/" + lang + "/potion.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "B1ModResources/localization/" + lang + "/UIStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ZHS";
        }
        String json = Gdx.files.internal("B1ModResources/localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("blackmythwukong", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        MyOrblist.clear();
        if ("tianmingren".equals(AbstractDungeon.player.name))
        {
            AbstractDungeon.actionManager.addToBottom(new CreateSlot());
            GunShiDamageCalculate.resetGunShiMax();
            GunShiDamageCalculate.resetGunShiDamage();
            resetExtraPowerID();
        }
    }
    public void onLoadGame()
    {
        SL=true;
    }
    public void receiveStartGame() {
        MyOrblist.clear();
        SaveLoadSubscriber.super.receiveStartGame();
    }
}