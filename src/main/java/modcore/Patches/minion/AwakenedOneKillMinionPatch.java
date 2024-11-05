package modcore.Patches.minion;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
/*    */
/*    */ @SpirePatch(clz = AwakenedOne.class, method = "die", paramtypez = {})
/*    */ public class AwakenedOneKillMinionPatch
        /*    */ {
    /*    */   @SpirePrefixPatch
    /*    */   public static void AwakenedOneKillMinionPatch(AbstractMonster __instance) {
        /* 18 */     if (!(AbstractDungeon.getCurrRoom()).cannotLose)
            /* 19 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                /* 20 */         if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("Cultist")) {
                    /* 21 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                    /* 22 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
                    /*    */         }
                /*    */       }
        /*    */   }
    /*    */ }
