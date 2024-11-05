package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class JinZiFaAction extends AbstractGameAction
{
    private DamageInfo info;

    public JinZiFaAction() {
        this.actionType = ActionType.WAIT;
        this.attackEffect = AttackEffect.FIRE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update()
    {
        AbstractPlayer p=AbstractDungeon.player;
        int count = 0;
        for (int i = 0; i < p.drawPile.group.size(); )
        {
            AbstractCard c = p.drawPile.group.get(i);
            if (c.type != AbstractCard.CardType.ATTACK)
            {
                p.drawPile.moveToExhaustPile(c);
                count++;
                continue;
            }
            i++;
        }
        for (int i = 0; i < p.discardPile.group.size(); )
        {
            AbstractCard c = p.discardPile.group.get(i);
            if (c.type != AbstractCard.CardType.ATTACK)
            {
                p.discardPile.moveToExhaustPile(c);
                count++;
                continue;
            }
            i++;
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group)
        {
            if (c.type != AbstractCard.CardType.ATTACK)
            {
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                count++;
            }
        }
        addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, count), count));
        this.isDone = true;
    }
}