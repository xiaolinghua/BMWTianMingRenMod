package modcore.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import modcore.cardmods.CanNotGetGunShiMod;
import modcore.powers.GunShiPower;

// 继承CustomRelic
public class YiJianYu extends CustomRelic
{
    // 图片路径
    public static final String ID = "blackmythwukong:YiJianYu";
    private static final String IMG_PATH = "B1ModResources/images/relics/ShenBenYou.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public YiJianYu()
    {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        System.out.println("target:" + target);
        if (target != AbstractDungeon.player && info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0)
        {
            if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && CardModifierManager.hasModifier(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1), CanNotGetGunShiMod.ID))
            {
                return;
            }
            flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GunShiPower(AbstractDungeon.player, 1), 1));
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (damageAmount > 0 && info.owner != null && info.owner != AbstractDungeon.player && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
        {
            flash();
            addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, GunShiPower.POWER_ID,1));
        }
        return damageAmount;
    }

    public AbstractRelic makeCopy()
    {
        return new YiJianYu();
    }

}