package modcore.Patches.minion;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ExplosivePower;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @SpirePatch(clz = ExplosivePower.class, method = "duringTurn", paramtypez = {})
/*    */ public class ExplosivePowerKillMinionPatch
        /*    */ {
    /*    */   @SpirePrefixPatch
    /*    */   public static void ExplosivePowerKillMinionPatch(ExplosivePower __instance) {
        /* 24 */     if (__instance.amount == 1 && !__instance.owner.isDying) {
            /* 25 */       boolean allMinion = true;
            /* 26 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                /* 27 */         if ((!m.isDeadOrEscaped() && m.currentHealth > 0 &&
                        /*    */
                        /* 29 */           !m.hasPower("Minion") &&
                        /* 30 */           !m.equals(__instance.owner)) || m.id.equals("AwakenedOne")) {
                    /*    */
                    /* 32 */           allMinion = false;
                    /*    */           break;
                    /*    */         }
                /*    */       }
            /* 36 */       if (allMinion)
                /* 37 */         for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    /* 38 */           if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("AwakenedOne")) {
                        /* 39 */             AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                        /* 40 */             AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
                        /*    */           }
                    /*    */         }
            /*    */     }
        /*    */   }
    /*    */ }