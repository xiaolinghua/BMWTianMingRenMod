//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.powers.DingShenPower;

public class DingShenAction extends AbstractGameAction {
    public DingShenAction(AbstractMonster target, AbstractCreature source) {
        this(target, source, 1);
    }

    public DingShenAction(AbstractMonster target, AbstractCreature source, int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        this.actionType = ActionType.DEBUFF;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new DingShenPower((AbstractMonster)this.target, this.amount), this.amount));
        }
        this.tickDuration();
    }
}
