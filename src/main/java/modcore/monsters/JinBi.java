package modcore.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import modcore.powers.HaoMaoPower;
import modcore.powers.KuiLeiShuPower;
import modcore.powers.PoZhanPower;

public class JinBi extends AbstractMonster {
    public static final String ID = "blackmythwukong:JinBi";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);

    public static final String NAME = monsterStrings.NAME;

    public JinBi(float x, float y) {
        super(NAME, ID, 10, 0.0F, -24.0F, 130.0F, 200.0F, null, x, y);
        setHp(4, 4);
        this.damage.add(new DamageInfo(this, 0));
        this.img=new Texture("B1ModResources/images/monsters/JinBi.png");
    }
    public void usePreBattleAction() {
        addToBot(new ApplyPowerAction(this, this, new PoZhanPower(this,1)));
        addToBot(new ApplyPowerAction(this, this, new KuiLeiShuPower(this)));
        addToBot(new ApplyPowerAction(this, this, new MinionPower(this)));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new HaoMaoPower(AbstractDungeon.player,1)));
    }
    public void takeTurn() {
        if (this.nextMove == 1)
        {
            AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
        }
    }
    protected void getMove(int num) {
        setMove((byte)1, Intent.STUN, this.damage.get(0).base);
    }


    public void die()
    {
        super.die();
        for (int i = 0; i < 20; i++)
        {
            AbstractDungeon.effectList.add(new GainPennyEffect(this.hb.cX, this.hb.cY));
        }
        AbstractDungeon.player.gainGold(20);
        addToBot(new ReducePowerAction(AbstractDungeon.player, this,"blackmythwukong:HaoMaoPower",1));
    }
}
