 package modcore.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import modcore.B1Mod;
 import modcore.shows.MyEmptyOrbSlot;


 public class CreateSlot extends AbstractGameAction
 {
       AbstractPlayer p = AbstractDungeon.player;
       public CreateSlot()
       {
             this.duration = Settings.ACTION_DUR_FAST;
             this.actionType = AbstractGameAction.ActionType.BLOCK;
       }
       public void update()
       {
           {
               this.amount = 3;
               int max = 1; int i;
               for (i = 0; i < this.amount; i++)
               {
                   System.out.println("-----------------------------------------------球已创建完");
                   B1Mod.MyOrblist.add(new MyEmptyOrbSlot());
               }
               for (i = 0; i < B1Mod.MyOrblist.size(); i++)
               {
                   B1Mod.MyOrblist.get(i).setSlot(max);
                   max++;
               }
           }
           this.isDone = true;
           tickDuration();
       }
     }


