package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import modcore.utils.GunShiUtil;

public class doubleGunShiDamageAction extends AbstractGameAction
{

    public doubleGunShiDamageAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        GunShiUtil.doubleGunShiDamage();
        this.isDone = true;
    }
}