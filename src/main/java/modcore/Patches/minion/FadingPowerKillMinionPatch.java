package modcore.Patches.minion;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @SpirePatch(clz = FadingPower.class, method = "duringTurn", paramtypez = {})
/*    */ public class FadingPowerKillMinionPatch
        /*    */ {
    /*    */   @SpirePrefixPatch
    /*    */   public static void ExplosivePowerKillMinionPatch(FadingPower __instance) {
        /* 23 */     if (__instance.amount == 1 && !__instance.owner.isDying) {
            /* 24 */       boolean allMinion = true;
            /* 25 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                /* 26 */         if ((!m.isDeadOrEscaped() && m.currentHealth > 0 &&
                        /*    */
                        /* 28 */           !m.hasPower("Minion") &&
                        /* 29 */           !m.equals(__instance.owner)) || m.id.equals("AwakenedOne")) {
                    /*    */
                    /* 31 */           allMinion = false;
                    /*    */           break;
                    /*    */         }
                /*    */       }
            /* 35 */       if (allMinion)
                /* 36 */         for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    /* 37 */           if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("AwakenedOne")) {
                        /* 38 */             AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                        /* 39 */             AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
                        /*    */           }
                    /*    */         }
            /*    */     }
        /*    */   }
    /*    */ }
