package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.actions.TuiChunAction;
import modcore.cards.other.PreviewJinChi;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class TuiChunJinChi extends AbstractB1Card {
    public static final String ID = "blackmythwukong:TuiChunJinChi";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/TuiChun.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private boolean TuiChun=true;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public TuiChunJinChi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.baseBlock =9;
        this.baseDamage=10;
        this.cardsToPreview = new PreviewJinChi();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (TuiChun)
        {
            this.type=CardType.SKILL;
            addToBot(new GainBlockAction(p, p, this.baseBlock));
            addToBot(new TuiChunAction(1, m));
            TuiChun=false;
            this.textureImg="B1ModResources/images/cards/JinChi.png";
            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];

            this.cost=0;
        }
        else
        {
            this.type=CardType.ATTACK;
            addToBot(new DamageAction(m, new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL)));
            TuiChun=true;
            this.textureImg="B1ModResources/images/cards/TuiChun.png";
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            this.name = CARD_STRINGS.NAME;
            this.cost=1;
        }
        this.initializeDescription();
        initializeTitle();
        if (this.textureImg != null)
        {
            loadCardImage(this.textureImg);
        }
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeDamage(5);
            this.cardsToPreview.upgrade();
        }
    }
}
