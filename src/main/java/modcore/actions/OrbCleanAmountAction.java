package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import modcore.B1Mod;

public class OrbCleanAmountAction extends AbstractGameAction
{
    boolean ifcleanall;
    public OrbCleanAmountAction(boolean ifcleanall)
    {
        this.ifcleanall=ifcleanall;
    }
    public void update()
    {
        if (ifcleanall)
        {

            for (int i = 0; i < B1Mod.MyOrblist.size(); i++) {
                B1Mod.MyOrblist.get(i).exchange=0;
            }
        }
        else
        {
            int orbamount=0;
            for (int i = 0; i < B1Mod.MyOrblist.size(); i++)
            {
                if (B1Mod.MyOrblist.get(i).exchange!=3)
                {
                    orbamount+=B1Mod.MyOrblist.get(i).exchange;
                }
            }
            addToTop(new OrbCleanAndAddAction(orbamount));
        }
        for (int i = 0; i < B1Mod.MyOrblist.size(); i++) {
            B1Mod.MyOrblist.get(i).updateDescription();
        }
        this.isDone = true;
    }
}