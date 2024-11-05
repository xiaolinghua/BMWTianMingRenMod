 package modcore.Patches.minion;

 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
 import com.megacrit.cardcrawl.actions.common.SuicideAction;
 import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;

 @SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = {})
 public class AbstractMonsterKillMinionWhenDiePatch
 {
       @SpirePrefixPatch
       public static void AbstractMonsterKillMinionWhenDiePatch(AbstractMonster __instance)
       {
             boolean allMinion = true;
             for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters)
             {
                   if ((!m.isDeadOrEscaped() && m.currentHealth > 0 && !m.hasPower("Minion")) || m.id.equals("AwakenedOne"))
                   {
                         allMinion = false;
                         break;
                   }
             }
             if (allMinion)
                   for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                   {
                         if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("AwakenedOne"))
                         {
                               AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
                               AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
                         }
                   }
       }
 }

