package modcore.Patches.minion;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(cls="com.megacrit.cardcrawl.actions.GameActionManager", method="getNextAction")
public class B1PreMonsterTurnPatch {
    @SpireInsertPatch(rloc = 192,localvars={"m"})
    public static SpireReturn<Void> Insert(GameActionManager __instance, AbstractMonster m)
    {
        if (m.id.equals("blackmythwukong:KuiLei")||m.id.equals("blackmythwukong:JinBi")||m.id.equals("blackmythwukong:ShuangShuang")) {
            System.out.println("---------------------跳过了 " + m.name);
            __instance.monsterQueue.remove(0);
            return SpireReturn.Return();
        }
        else
        {
            return SpireReturn.Continue();
        }
    }
}