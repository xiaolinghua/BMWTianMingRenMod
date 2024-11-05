package modcore.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

// 继承CustomRelic
public class YanKanXi extends CustomRelic
{
    // 图片路径
    public static final String ID = "blackmythwukong:YanKanXi";
    private static final String IMG_PATH = "B1ModResources/images/relics/YanKanXi.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public YanKanXi()
    {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new YanKanXi();
    }
    public void onPlayerEndTurn()
    {
        int count = 0;
        for (AbstractMonster m2 : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            if (!m2.isDeadOrEscaped())
            {
                count++;
            }
        }
        flash();
        addToBot(new GainBlockAction(AbstractDungeon.player, null, count));
    }
}