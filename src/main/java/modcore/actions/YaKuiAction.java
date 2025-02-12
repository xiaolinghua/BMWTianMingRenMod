package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.powers.GunShiPower;

public class YaKuiAction extends AbstractGameAction
{
    public DamageInfo info;

    public YaKuiAction(AbstractMonster target, DamageInfo info)
    {
        this.target = target;
        this.info = info;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    @Override
    public void update()
    {
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            this.target.damage(this.info);
            if (!this.target.isDying && this.target.currentHealth > 0 && !this.target.halfDead && !this.target.hasPower("Minion"))
            {
                // Consume one blackmythwukong:棍势点
                if (AbstractDungeon.player.hasPower(GunShiPower.POWER_ID)&&AbstractDungeon.player.getPower(GunShiPower.POWER_ID).amount>=3)
                {
                    addToTop(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, GunShiPower.POWER_ID,3));
                    // Deal damage again
                    AbstractCard tmp = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).makeSameInstanceOf();
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.current_x = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).current_x;
                    tmp.current_y = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).current_y;
                    tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                    tmp.target_y = Settings.HEIGHT / 2.0F;
                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp,(AbstractMonster)target,1, true, true), true);
                }
            }
            this.isDone = true;
        }
        this.tickDuration();
    }
}