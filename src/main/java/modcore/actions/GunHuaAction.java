package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class GunHuaAction extends AbstractGameAction
{
    public DamageInfo info;

    public GunHuaAction(AbstractMonster target, DamageInfo info) {
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.info = info;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        {
            this.tickDuration();

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY, false));
                System.out.println(info+"-------------------------------");
                this.target.damage(this.info);//可能有问题，目标有格挡时不触发
                if (this.target.lastDamageTaken > 0) {
                    this.addToTop(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, -this.target.lastDamageTaken), -this.target.lastDamageTaken));
                    if (this.target.hb != null) {
                        this.addToTop(new ApplyPowerAction(this.target, this.source, new GainStrengthPower(this.target, this.target.lastDamageTaken), this.target.lastDamageTaken));
                        this.addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
                    }
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    this.addToTop(new WaitAction(0.1F));
                }
            this.isDone=true;
        }

    }

}
