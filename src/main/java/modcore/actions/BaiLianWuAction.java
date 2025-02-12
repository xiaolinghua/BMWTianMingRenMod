package modcore.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import modcore.cardmods.FrozenCardMod;

import java.util.ArrayList;

public class BaiLianWuAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractPlayer p;
    private final boolean canPickZero;
    private final ArrayList<AbstractCard> cannotModifier = new ArrayList<>();
    public BaiLianWuAction(int amount,boolean canPickZero) {
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        // 检查是否是初始持续时间
        if (this.duration == this.startDuration) {

            // 遍历手牌，将非技能牌添加到不能被修改的列表中
            this.p.hand.group.stream().filter(card -> card.type != AbstractCard.CardType.SKILL).forEach(this.cannotModifier::add);
            // 如果手牌中没有技能牌，结束动作
            if (this.cannotModifier.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            // 如果手牌中技能牌少于amount，直接将其附魔为冻结卡牌
            if (this.p.hand.group.size() - this.cannotModifier.size() <= this.amount) {
                for (AbstractCard c : this.p.hand.group)
                {
                    if (c.type == AbstractCard.CardType.SKILL)
                    {
                        CardModifierManager.addModifier(c, new FrozenCardMod());
                        c.superFlash();
                        c.applyPowers();

                    }
                }
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotModifier);
            // 打开手牌选择屏幕
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, this.canPickZero);
            this.tickDuration();
            return;
        }
        // 检查手牌选择屏幕是否已检索卡牌
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            // 遍历选择的卡牌并��加冻结卡牌修饰
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
            {
                CardModifierManager.addModifier(c, new FrozenCardMod());
                c.superFlash();
                c.applyPowers();
                this.p.hand.addToTop(c);
            }
            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        // 更新动作持续时间
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("BaiLianWuAction");
        TEXT = uiStrings.TEXT;
    }
    private void returnCards()
    {
        for (AbstractCard c : this.cannotModifier)
        {
            this.p.hand.addToTop(c);
        }
        this.p.hand.refreshHandLayout();
    }
}