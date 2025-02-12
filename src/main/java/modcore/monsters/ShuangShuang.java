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
import modcore.powers.HaoMaoPower;
import modcore.powers.ShiZhiJingPoPower;

public class ShuangShuang extends AbstractMonster {
    public static final String ID = "blackmythwukong:ShuangShuang";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);

    public static final String NAME = monsterStrings.NAME;

    public ShuangShuang(float x, float y) {
        super(NAME, ID, 5, 0.0F, -24.0F, 130.0F, 250.0F, null, x, y);
        setHp(5, 5);
        this.damage.add(new DamageInfo(this, 0));
        this.img=new Texture("B1ModResources/images/monsters/ShiShuangShuang250.png");
    }
    public void usePreBattleAction()
    {
        addToBot(new ApplyPowerAction(this, this, new MinionPower(this)));
        addToBot(new ApplyPowerAction(this, this, new ShiZhiJingPoPower(this)));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new HaoMaoPower(AbstractDungeon.player,1)));

    }
    public void takeTurn() {
        if (this.nextMove == 1)
        {
            AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
        }
    }
    protected void getMove(int num) {
        setMove((byte)1, Intent.NONE, this.damage.get(0).base);
    }


    public void die()
    {
        super.die();
        addToBot(new ReducePowerAction(AbstractDungeon.player, this,HaoMaoPower.POWER_ID,1));
    }
}
