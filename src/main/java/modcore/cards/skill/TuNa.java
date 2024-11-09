package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class TuNa extends AbstractB1Card {
    public static final String ID = "blackmythwukong:TuNa";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/TuNa.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public TuNa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int count = this.baseMagicNumber;
        for (AbstractMonster m2 : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            if (!m2.isDeadOrEscaped())
            {
                count--;
            }
        }
        if (count>0)
        {
            addToBot(new DrawCardAction(p, count));
        }
    }
    public void applyPowers()
    {
        int count = this.baseMagicNumber;
        for (AbstractMonster m2 : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            if (!m2.isDeadOrEscaped())
            {
                count--;
            }
        }
        if (count>0)
        {
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1]+count+CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }

        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(1);
        }
    }
}
