package modcore.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import modcore.powers.GunShiPower;

import java.util.Random;

// 继承CustomRelic
public class ChanShiWan extends CustomRelic
{
    // 图片路径
    public static final String ID = "blackmythwukong:ChanShiWan";
    private static final String IMG_PATH = "B1ModResources/images/relics/ChanShiWan.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private final String[] dialog={"一粒金丹一因果，我命由天不由我",
            "内炼成丹，外炼成法",
            "妙哉妙哉，世间珍奇不过如此",
            "阴阳生五行，五行孕一性",
            "天地万物皆炉鼎，唯有上品得我心",
            "查天地动静之机，探日月盈虚之妙，丹可成矣",
            "天地为炉鼎，你我皆丹药",
            "吃药，吃药，若论出身，更不公道",
            "想走近道的人多了，才有了炼药术",
            "金丹虽成，仍为外道，需谨慎处置",
            "金丹易炼，天命难改哟",
            "当饭吃哩？ "};

    public ChanShiWan()
    {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ChanShiWan();
    }


    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(p, p, new GunShiPower(p, 3), 3));
        this.grayscale = true;
    }
    public void onUsePotion()
    {
        Random rand = new Random();
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&rand.nextBoolean())
        {

            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, dialog[rand.nextInt(dialog.length)], 1.2F, 2F));
        }
    }
    public void justEnteredRoom(AbstractRoom room)
    {
        this.grayscale = false;
    }
}