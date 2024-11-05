package modcore.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import modcore.cards.skill.Dingshenfa;

// 继承CustomRelic
public class SheChangSi extends CustomRelic
{
    // 图片路径
    public static final String ID = "blackmythwukong:SheChangSi";
    private static final String IMG_PATH = "B1ModResources/images/relics/SheChangSi.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public SheChangSi()
    {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SheChangSi();
    }
    public void onEquip() {
    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Dingshenfa(true), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
    UnlockTracker.markCardAsSeen("blackmythwukong:Dingshenfa");
    }
}