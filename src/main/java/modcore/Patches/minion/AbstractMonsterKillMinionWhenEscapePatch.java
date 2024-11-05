package modcore.Patches.minion;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */
/*    */
/*    */
/*    */
/*    */ @SpirePatch(clz = AbstractMonster.class, method = "escape", paramtypez = {})
/*    */ public class AbstractMonsterKillMinionWhenEscapePatch
        /*    */ {
    /*    */   @SpirePostfixPatch
    /*    */   public static void AbstractMonsterKillMinionWhenDiePatch(AbstractMonster __instance) {
        /* 20 */     boolean allMinion = true;
        /* 21 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            /* 22 */       if ((!m.isDeadOrEscaped() && m.currentHealth > 0 && !m.hasPower("Minion")) || m.id.equals("AwakenedOne")) {
                /* 23 */         allMinion = false;
                /*    */         break;
                /*    */       }
            /*    */     }
        /* 27 */     if (allMinion)
            /* 28 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                /* 29 */         if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("AwakenedOne")) {
                    /* 30 */           AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
                    /* 31 */           AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
                    /*    */         }
                /*    */       }
        /*    */   }
    /*    */ }
