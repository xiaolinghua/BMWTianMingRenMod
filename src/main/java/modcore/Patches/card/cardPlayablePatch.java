package modcore.Patches.card;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
@SpirePatch(clz = AbstractCard.class, method = "hasEnoughEnergy")
public class cardPlayablePatch {
    @SpireInsertPatch(rloc = 17)
    public static SpireReturn<Boolean> Insert(AbstractCard __instance)
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:XuLiPower") && __instance.type == AbstractCard.CardType.ATTACK)
        {
            __instance.cantUseMessage = "蓄力中,无法打出攻击牌 ";
            return SpireReturn.Return(false);
        }
        return SpireReturn.Continue();
    }
}
