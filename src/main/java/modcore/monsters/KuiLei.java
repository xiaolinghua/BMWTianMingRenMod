package modcore.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.MonsterStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import modcore.powers.HaoMaoPower;
import modcore.powers.KuiLeiShuPower;
import modcore.powers.PoZhanPower;

public class KuiLei
           extends AbstractMonster {
       public static final String ID = "blackmythwukong:KuiLei";
       private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);

      public static final String NAME = monsterStrings.NAME;

    public KuiLei(float x, float y) {
             super(NAME, ID, 10, 0.0F, -24.0F, 130.0F, 200.0F, null, x, y);
             setHp(10, 10);
             this.damage.add(new DamageInfo((AbstractCreature)this, 0));
             //this.img=new Texture("ExampleModResources/images/monsters/fensheng.png");
           loadAnimation("B1ModResources/images/monsters/kuilei/skeleton.atlas",
                   "B1ModResources/images/monsters/kuilei/skeleton.json", 1.0F);
           AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
           e.setTime(e.getEndTime() * MathUtils.random());
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
           addToBot(new ReducePowerAction(AbstractDungeon.player, this,"blackmythwukong:HaoMaoPower",1));
       }
     }
