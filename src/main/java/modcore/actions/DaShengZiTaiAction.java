package modcore.actions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import modcore.B1Mod;
import modcore.shows.MyEmptyOrbSlot;
import modcore.utils.GunShiDamageCalculate;


public class DaShengZiTaiAction extends AbstractGameAction
{
    AbstractPlayer p = AbstractDungeon.player;
    public DaShengZiTaiAction()
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }
    public void update()
    {
        {
            System.out.println("-----------------------------------------------球已创建完");
            GunShiDamageCalculate.setGunShiMax(13);
            GunShiDamageCalculate.doubleGunShiDamage();
            B1Mod.MyOrblist.add(new MyEmptyOrbSlot());
            B1Mod.MyOrblist.get(3).setSlot(4);
        }
        this.isDone = true;
        tickDuration();
    }
}


