package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class RenFanTeng extends AbstractB1Card {
    public static final String ID = "blackmythwukong:RenFanTeng";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/RenFanTeng.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public RenFanTeng() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock =10;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi") )
        {
            if (AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount%2==0)
            {
                addToBot(new DrawCardAction(p, this.magicNumber));
            }
            else addToBot(new GainBlockAction(p, p, this.block));
            return;
        }
        addToBot(new DrawCardAction(p, this.magicNumber));
    }
    public void applyPowers()
    {

        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi") )
        {
            if (AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount%2==0)
            {
                this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            }
            else this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1];
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
            this.upgradeBlock(3);
            this.upgradeMagicNumber(1);
        }
    }
}
