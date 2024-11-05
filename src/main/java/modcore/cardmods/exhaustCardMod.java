 package modcore.cardmods;

 import basemod.abstracts.AbstractCardModifier;
 import basemod.helpers.CardModifierManager;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.UIStrings;

 public class exhaustCardMod extends AbstractCardModifier {
       public static final String ID = "blackmythwukong:exhaustCardMod";
       public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

       public String identifier(AbstractCard card) {
             return ID;
           }


       public AbstractCardModifier makeCopy() {
             return new exhaustCardMod();
           }


       public String modifyDescription(String rawDescription, AbstractCard card) {
             return uiStrings.TEXT[0] + rawDescription;
           }


       public boolean shouldApply(AbstractCard card) {
             return !CardModifierManager.hasModifier(card, ID);
           }
     }


