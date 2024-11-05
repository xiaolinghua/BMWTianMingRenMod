package modcore.Patches.potion;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.SmokeBomb;
@SpirePatch(clz = SmokeBomb.class, method = "use")
public class SmokeBombPatch {
    @SpireInsertPatch(rloc = 2)
    public static SpireReturn<Void> Insert(SmokeBomb __instance)
    {
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.hasPower("BackAttack")) {
                return SpireReturn.Return();
            }
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                return SpireReturn.Return();
            }
        }
        return SpireReturn.Continue();
    }
}
