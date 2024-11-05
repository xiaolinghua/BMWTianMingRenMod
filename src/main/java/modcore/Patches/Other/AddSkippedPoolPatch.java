package modcore.Patches.Other;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import modcore.utils.SkippedCardPool;

import java.util.ArrayList;

public class AddSkippedPoolPatch
{
    @SpirePatch(clz = CardRewardScreen.class, method = "open")
    public static class OpenPostfixPatch
    {
        @SpirePrefixPatch
        public static void Open(CardRewardScreen __instance,ArrayList<AbstractCard> cards)
        {
            SkippedCardPool.AddSkippedCard(cards);
        }
    }
    @SpirePatch(clz = CardRewardScreen.class, method = "acquireCard")
    public static class acquireCardPostfixPatch
    {
        @SpirePrefixPatch
        public static void acquireCard(CardRewardScreen __instance,AbstractCard hoveredCard)
        {
            SkippedCardPool.RemoveSkippedCard(hoveredCard);
        }
    }
}
