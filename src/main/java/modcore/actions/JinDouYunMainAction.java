//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;

public class JinDouYunMainAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractPlayer p;
    public JinDouYunMainAction(AbstractCard card) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.card = card;
        p=AbstractDungeon.player;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards)
        {
            if (c.uuid == card.uuid)
            {
                break;
            }
            else
            {
                if (p.hand.size() == 9)
                {

                    addToTop(new ApplyPowerAction(p, p, new NoDrawPower(p)));
                    addToTop(new DrawCardAction(1));
                    break;
                }
                addToTop(new DrawCardAction(1,new JinDouYunMainAction(card)));
            }
        }

        this.isDone = true;
    }
}
