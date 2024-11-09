package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import modcore.powers.GunShiPower;

public class BuPoBuLiAction extends AbstractGameAction
{
    private DamageInfo info;

    public BuPoBuLiAction(int magicNum) {
        this.amount=magicNum;
        this.actionType = ActionType.WAIT;
        this.attackEffect = AttackEffect.FIRE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();

        int i;
        for(i = 0; i < count; ++i) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new GunShiPower(AbstractDungeon.player, amount), amount));
        }
        for(i = 0; i < count; ++i) {
            if (Settings.FAST_MODE) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                this.addToTop(new ExhaustAction(1, true, true));
            }
        }

        this.isDone = true;
    }
}