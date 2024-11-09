package modcore.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class OneTurnRetainMod extends AbstractCardModifier {
    public static final String ID = "blackmythwukong:OneTurnRetainMod";
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public String identifier(AbstractCard card) {
        return ID;
    }
    public AbstractCardModifier makeCopy() {
        return new OneTurnRetainMod();
    }
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[0] + rawDescription;
    }
    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }
    public void onRemove(AbstractCard card) {
        card.selfRetain = false;
    }
    public boolean shouldApply(AbstractCard card) {
        return !card.selfRetain;
    }
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }
}