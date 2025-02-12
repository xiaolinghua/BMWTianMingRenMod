package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import modcore.utils.GunShiUtil;


public class BeiLiWanAction extends AbstractGameAction
{
    AbstractPlayer p = AbstractDungeon.player;
    private int addamount;
    public BeiLiWanAction(int addamount)
    {
        this.addamount = addamount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }
    public void update()
    {
        {
            GunShiUtil.addPowerAmount(addamount);
        }
        this.isDone = true;
        tickDuration();
    }
}