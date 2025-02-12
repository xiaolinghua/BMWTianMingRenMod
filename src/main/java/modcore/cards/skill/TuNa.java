package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
        this.magicNumber = this.baseMagicNumber=2;
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
            addToBot(new GainEnergyAction(baseMagicNumber-count));
        }
        else
        {
            addToBot(new GainEnergyAction(baseMagicNumber));
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
            int EnergyCount = baseMagicNumber-count;
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0]+count+CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            for (int i = 0; i < EnergyCount; i++)
            {
                this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[2];
            }
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[3];
        }
        else
        {
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0]+0+CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            for (int i = 0; i < baseMagicNumber; i++)
            {
                this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[2];
            }
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[3];
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
