package modcore.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import modcore.powers.HanDongPower;

public class FrozenCardMod extends AbstractCardModifier
{
    public static final String ID = "blackmythwukong:FrozenCardMod";
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public String identifier(AbstractCard card) {
        return ID;
    }


    public AbstractCardModifier makeCopy()
    {
        return new FrozenCardMod();
    }


    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[0] + rawDescription;
    }


    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action)
    {
        // 给予随机敌人两层寒冻
        AbstractCreature randomEnemy = AbstractDungeon.getMonsters().getRandomMonster(true);
        addToBot(new ApplyPowerAction(randomEnemy, AbstractDungeon.player, new HanDongPower(randomEnemy, 2), 2));
    }

}