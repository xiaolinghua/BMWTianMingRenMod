//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TaDeShanWaiQingShanAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public TaDeShanWaiQingShanAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        p=AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            this.CostAllCardsInGroup(p.hand);
            this.CostAllCardsInGroup(p.drawPile);
            this.CostAllCardsInGroup(p.discardPile);
            this.CostAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }
    }
    private void CostAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group)
        {
            if (c.costForTurn > 1)
            {
                c.costForTurn = 1;
                c.isCostModifiedForTurn = true;
            }
            if (c.cost > 1)
            {
                c.cost = 1;
                c.isCostModified = true;
            }
        }
    }
}
