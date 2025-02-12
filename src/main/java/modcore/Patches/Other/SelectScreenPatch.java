 package modcore.Patches.Other;

 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
 import modcore.Characters.WuKong;

 import java.util.Iterator;
@SpirePatch(clz = HandCardSelectScreen.class, method = "selectHoveredCard")
public class SelectScreenPatch
 {
     public static CardGroup handClone;
     public static CardGroup handCloneb;
     @SpirePostfixPatch
     public static void SelectionPostPatch(HandCardSelectScreen reg) {
         if (AbstractDungeon.player.chosenClass != WuKong.Enums.TianMingRen || InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed());
     }
     public static void ResetHand() {
         Iterator<AbstractCard> var1 = handClone.group.iterator();
         handCloneb = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
         while (var1.hasNext()) {
             AbstractCard c = var1.next();
             Iterator<AbstractCard> var3 = AbstractDungeon.player.hand.group.iterator();
             while (var3.hasNext()) {
                 AbstractCard d = var3.next();
                 if (c.equals(d)) {
                     handCloneb.addToBottom(c);
                     break;
                 }
             }
             AbstractDungeon.player.hand.removeCard(c);
         }
         Iterator<AbstractCard> var2 = handCloneb.group.iterator();
         while (var2.hasNext()) {
             AbstractCard c = var2.next();
             AbstractDungeon.player.hand.addToTop(c);
         }
         AbstractDungeon.player.hand.refreshHandLayout();
     }
 }

