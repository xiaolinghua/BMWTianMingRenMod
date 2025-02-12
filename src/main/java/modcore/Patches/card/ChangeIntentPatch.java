package modcore.Patches.card;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeIntentPatch {

    private static final Logger logger = LogManager.getLogger(ChangeIntentPatch.class);

    private static final Map<AbstractMonster, EnemyMoveInfo> moveRecord = new HashMap<>();
    private static final Map<AbstractMonster, String> moveNameRecord = new HashMap<>();

    public static void changeIntentAttack(AbstractMonster m, int damage) {
        if (m.intent != null) {
            if (!moveRecord.containsKey(m))
            {
                moveRecord.put(m, ReflectionHacks.getPrivate(m,AbstractMonster.class,  "move"));
                moveNameRecord.put(m, m.moveName);
            }
            m.setMove(m.nextMove, AbstractMonster.Intent.ATTACK, damage);
            m.createIntent();
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class PreEnemyTakeTurnPatch {

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"m"}
        )
        public static SpireReturn<Void> Insert(GameActionManager __instance, AbstractMonster m) {
            if (moveRecord.containsKey(m)) {
                Integer damage = ReflectionHacks.getPrivate(m,AbstractMonster.class, "intentDmg");
                if (damage == null) {
                    logger.error("Failed to get intent damage");
                    damage = 0;
                }
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(m, "ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5f));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(m, damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

                m.applyTurnPowers();
                String moveName = moveNameRecord.get(m);
                EnemyMoveInfo move = moveRecord.get(m);
                m.setMove(moveName, move.nextMove, move.intent, move.baseDamage, move.multiplier, move.isMultiDamage);
                moveRecord.remove(m);
                moveNameRecord.remove(m);
                __instance.monsterQueue.remove(0);
                if (__instance.monsterQueue.isEmpty()) {
                    __instance.addToBottom(new WaitAction(1.5F));
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "takeTurn");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

}