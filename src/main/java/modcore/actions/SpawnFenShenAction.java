package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.utils.ScreenPartition;

public class SpawnFenShenAction extends AbstractGameAction {
    public AbstractPlayer p;
    public AbstractMonster m1;
    public AbstractMonster m2;
    public SpawnFenShenAction(AbstractMonster m1,AbstractMonster m2)
    {
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.m1 = m1;
        if (m2!=null)
        {
            this.m2=m2;
        }
    }
    public void update() {
        this.m1.usePreBattleAction();
        if (m2!=null)
        {
            this.m2.usePreBattleAction();
            ScreenPartition.assignSequentialPosition(m1, m2);
            addToTop(new SpawnMonsterAction(this.m1, true, 0));
            addToTop(new SpawnMonsterAction(this.m2, true, 0));
        }
        else
        {
            ScreenPartition.assignSequentialPosition(m1, null);
            addToTop(new SpawnMonsterAction(this.m1, true, 0));
        }
        this.isDone = true;
    }}
