package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawToDiscardAction extends AbstractGameAction
{
    private AbstractCard targetCard;
    private CardGroup group;
    private float startingDuration;

    public DrawToDiscardAction(AbstractCard targetCard, CardGroup group, boolean isFast) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.EXHAUST;
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }
    public DrawToDiscardAction(AbstractCard targetCard, CardGroup group) {
        this(targetCard, group, false);
    }

    public void update() {
        if (this.duration == this.startingDuration && this.group.contains(this.targetCard)) {
            this.group.moveToDiscardPile(this.targetCard);
            CardCrawlGame.dungeon.checkForPactAchievement();
        }

        this.tickDuration();
    }
}