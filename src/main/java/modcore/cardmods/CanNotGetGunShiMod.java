package modcore.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class CanNotGetGunShiMod extends AbstractCardModifier {
    public static final String ID = "blackmythwukong:CanNotGetGunShiMod";
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public String identifier(AbstractCard card) {
        return ID;
    }


    public AbstractCardModifier makeCopy()
    {
        return new CanNotGetGunShiMod();
    }


    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[0] + rawDescription;
    }


    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }
}