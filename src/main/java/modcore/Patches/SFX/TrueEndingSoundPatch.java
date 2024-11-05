package modcore.Patches.SFX;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "playBossStinger"
)
public class TrueEndingSoundPatch {

    public static SpireReturn<Void> Prefix() {
        if (AbstractDungeon.id.equals("TheEnding") && "tianmingren".equals(AbstractDungeon.player.name)) {
            System.out.println("音乐patch启动 ");
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}