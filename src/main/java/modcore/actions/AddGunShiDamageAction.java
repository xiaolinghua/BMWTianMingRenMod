package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import modcore.utils.GunShiUtil;

public class AddGunShiDamageAction extends AbstractGameAction
{
    private int amount;

    public AddGunShiDamageAction(int amount) {
        this.amount=amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public void update() {
        GunShiUtil.increaseGunShiDamage(this.amount);
        this.isDone = true;
    }
}