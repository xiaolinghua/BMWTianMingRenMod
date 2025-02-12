package modcore.cards.other;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS;

public class QianYao extends AbstractB1Card
{
    public static final String ID = "blackmythwukong:QianYao";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/BuYouJi.png";
    private static final int COST = -2;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private boolean ifExhaust = false;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public QianYao()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return false;
    }

    public AbstractCard makeCopy()
    {
        return new QianYao();
    }

    @Override
    public void upgrade()
    {

    }

    @Override
    public void applyPowers()
    {
        if (!ifExhaust)
        {
            // 获取当前卡牌在手牌中的位置
            int handPos = AbstractDungeon.player.hand.group.indexOf(this);
            // 如果当前卡牌前面有卡牌且该卡牌类型为STATUS，则将其移除
            if (handPos > 0 && AbstractDungeon.player.hand.group.get(handPos - 1).type == CardType.STATUS)
            {
                addToBot(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(handPos - 1), AbstractDungeon.player.hand));
                // 将当前卡牌移除
                addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
                AbstractDungeon.player.hand.refreshHandLayout();
                ifExhaust = true;
            }
            // 如果当前卡牌后面有卡牌且该卡牌类型为STATUS，则将其移除
            if (handPos < AbstractDungeon.player.hand.group.size() - 1 && AbstractDungeon.player.hand.group.get(handPos + 1).type == CardType.STATUS)
            {
                addToBot(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.group.get(handPos + 1), AbstractDungeon.player.hand));
                // 将当前卡牌移除
                addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
                AbstractDungeon.player.hand.refreshHandLayout();
                ifExhaust = true;
            }
        }
    }

    public boolean canUpgrade()
    {
        return false;
    }
}
