//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class TuiChunAction extends AbstractGameAction
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int amount;
    private final AbstractMonster targetMonster;

    public TuiChunAction(int amount, AbstractMonster m)
    {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.amount = amount;
        this.targetMonster = m;
    }

    public void update()
    {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0)
        {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlurPower(AbstractDungeon.player, this.amount), this.amount));
        } else
        {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }

        this.isDone = true;
    }

    static
    {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
        TEXT = uiStrings.TEXT;
    }
}
