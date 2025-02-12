package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FindStrongestMonsterAndAttackAction extends AbstractGameAction
{
    public DamageInfo info;

    public FindStrongestMonsterAndAttackAction(DamageInfo info) {
        this.info = info;
    }

    @Override
    public void update()
    {
        AbstractMonster strongestMonster = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
        {
            if (!m.isDeadOrEscaped())
            {
                if (strongestMonster == null || m.currentHealth > strongestMonster.currentHealth)
                {
                    strongestMonster = m;
                }
            }
        }
        addToTop(new DamageAction(strongestMonster, info));
        this.isDone = true;
    }
}
