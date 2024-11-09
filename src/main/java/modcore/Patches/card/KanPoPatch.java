package modcore.Patches.card;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class KanPoPatch {

    private static final Logger logger = LogManager.getLogger(KanPoPatch.class);

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"m"}
    )
    public static void Insert(AbstractMonster m) {
        if (m.nextMove == -23) {
            int damage = 0;
            AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(m, "ATTACK"));
            AbstractDungeon.actionManager.addToBottom(new RollMoveAction(m));
        }
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