 package modcore.Patches.card;

 import basemod.helpers.CardModifierManager;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import modcore.cardmods.exhaustCardMod;

 public class B1EndOfTurnPatch
 {
     @SpirePatch(clz = AbstractRoom.class, method = "endTurn")
     public static class EndTurn
     {
         @SpirePrefixPatch
         public static void Prefix(AbstractRoom __instance) {
             if (CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                 AbstractPlayer p = AbstractDungeon.player;
                 for (CardGroup cardGroup : new CardGroup[] { p.hand, p.drawPile, p.discardPile }) {
                     for (AbstractCard q : cardGroup.group) {
                         if (CardModifierManager.hasModifier(q, exhaustCardMod.ID))
                             AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
                             {
                                 public void update() {
                                     this.isDone = true;
                                     cardGroup.moveToExhaustPile(q);
                                 }
                             });
                     }
                 }
             }
         }
     }
 }

