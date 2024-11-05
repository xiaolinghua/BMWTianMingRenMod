package modcore.Patches.card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.city.BanditPointy;

@SpirePatch(
        clz = BanditPointy.class,
        method = "takeTurn"
)
public class KanPoPointyPatch
{

    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(BanditPointy __instance) {
        if (__instance.nextMove == -23) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}