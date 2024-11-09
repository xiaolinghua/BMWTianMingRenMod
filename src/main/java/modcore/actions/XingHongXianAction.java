package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class XingHongXianAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractGameAction.AttackEffect effect;
    private final int attackTimes;

    public XingHongXianAction(AbstractCard card, AbstractGameAction.AttackEffect effect,int attackTimes) {
        this.card = card;
        this.effect = effect;
        this.attackTimes=attackTimes;
    }

    public XingHongXianAction(AbstractCard card) {
        this(card, AttackEffect.NONE,3);
    }

    public void update()
    {
        AbstractCreature m = null;
        for (int i = 0; i < this.attackTimes; i++)
        {
            this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (this.target != null)
            {
                this.card.calculateCardDamage((AbstractMonster) this.target);
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
                if (m != this.target)
                {
                    addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, card.block));
                    m = this.target;
                }
            }
        }

        this.isDone = true;
    }
}
