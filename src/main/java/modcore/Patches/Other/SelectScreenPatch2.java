 package modcore.Patches.Other;

 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
 import modcore.Characters.WuKong;

 @SpirePatch(clz = HandCardSelectScreen.class, method = "prep")
 public class SelectScreenPatch2
 {
       @SpirePrefixPatch
       public static SpireReturn SelectionPrePatch(HandCardSelectScreen reg)
       {
             if (AbstractDungeon.player.chosenClass == WuKong.Enums.TianMingRen)
             {
                   SelectScreenPatch.handClone = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                   for (AbstractCard c : AbstractDungeon.player.hand.group)
                   {
                         SelectScreenPatch.handClone.addToBottom(c);
                   }
             }
             return SpireReturn.Continue();
       }
 }