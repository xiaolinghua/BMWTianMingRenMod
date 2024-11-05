package modcore.Characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import modcore.cards.attack.HuoYanJinJing;
import modcore.cards.skill.Defend;
import modcore.cards.attack.Strike;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import java.util.ArrayList;

import modcore.cards.skill.ShanShen;
import modcore.relics.ChanShiWan;
import static modcore.Characters.WuKong.Enums.BMW_CARD;
import static modcore.Characters.WuKong.Enums.TianMingRen;
import modcore.B1Mod;
// 继承CustomPlayer类
public class WuKong extends CustomPlayer {
    // 火堆的人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = "B1ModResources/images/char/shoulder.png";
    // 火堆的人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = "B1ModResources/images/char/shoulder.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = "B1ModResources/images/char/die.png";

    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "B1ModResources/images/char/orb/layer1.png",
            "B1ModResources/images/char/orb/layer2.png",
            "B1ModResources/images/char/orb/layer3.png",
            "B1ModResources/images/char/orb/layer4.png",
            "B1ModResources/images/char/orb/layer5.png",
            "B1ModResources/images/char/orb/layer6.png",
            "B1ModResources/images/char/orb/layer1d.png",
            "B1ModResources/images/char/orb/layer2d.png",
            "B1ModResources/images/char/orb/layer3d.png",
            "B1ModResources/images/char/orb/layer4d.png",
            "B1ModResources/images/char/orb/layer5d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    // 人物的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("blackmythwukong:WuKong");

    public WuKong(String name) {
        super(name, TianMingRen,ORB_TEXTURES, "B1ModResources/images/char/orb/vfx.png", LAYER_SPEED, null, null);


        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);


        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                "B1ModResources/images/char/tianmingren.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 440.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );



        this.loadAnimation("B1ModResources/images/char/tianmingren.atlas", "B1ModResources/images/char/tianmingren.json", 2F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        // e.setTimeScale(1.2F);


    }

    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<4; x++) {
            retVal.add(Strike.ID);
        }
        for(int x = 0; x<4; x++) {
            retVal.add(Defend.ID);
        }
        retVal.add(ShanShen.ID);
        retVal.add("blackmythwukong:HuoYanJinJing");
        return retVal;
    }


    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ChanShiWan.ID); // 这里
        UnlockTracker.markRelicAsSeen("blackmythwukong:ChanShiWan");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 人物名字
                characterStrings.TEXT[0], // 人物介绍
                85, // 当前血量
                85, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    @Override
    public AbstractCard.CardColor getCardColor() {
        return BMW_CARD;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return new HuoYanJinJing();
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return B1Mod.B1_COLOR;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体，没必要修改
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.play("B1:OPENING");
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels()
    {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("B1ModResources/images/char/trueEnding.png", "B1:End"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }
    public void SwitchCharacterImage()
    {
        this.img =ImageMaster.loadImage("B1ModResources/images/char/skeleton.png");
        this.loadAnimation("B1ModResources/images/char/skeleton.atlas", "B1ModResources/images/char/skeleton37.json", 2F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }
    public void ReloadImage()
    {
        this.img =ImageMaster.loadImage("B1ModResources/images/char/tianmingren.png");
        this.loadAnimation("B1ModResources/images/char/tianmingren.atlas", "B1ModResources/images/char/tianmingren.json", 2F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }
    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例，照抄
    @Override
    public AbstractPlayer newInstance() {
        return new WuKong(this.name);
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return B1Mod.B1_COLOR;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return B1Mod.B1_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    // 为原版人物枚举、卡牌颜色枚举扩展的枚举，需要写，接下来要用
    // ***填在SpireEnum中的name需要一致***
    public static class Enums {
        // 注意此处是在 MyCharacter 类内部的静态嵌套类 Enums 中定义的新 PlayerClass 枚举值
        // 不可将该定义放在外部的 MyCharacter 类中，具体原因见《高级技巧 / 01 - Patch / SpireEnum》
        @SpireEnum
        public static PlayerClass TianMingRen;

        @SpireEnum(name = "EXAMPLE_GREEN")
        public static AbstractCard.CardColor BMW_CARD;

        @SpireEnum(name = "EXAMPLE_GREEN")
        public static CardLibrary.LibraryType TianMingRen_LIBRARY;
    }
}

