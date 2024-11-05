package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.B1Mod;
import modcore.Patches.AbstractB1Card;
import modcore.actions.SpawnFenShenAction;
import modcore.monsters.JinBi;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class CaiYuanGuangJin extends AbstractB1Card {
    public static final String ID = "blackmythwukong:CaiYuanGuangJin";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/GuangZhi.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public CaiYuanGuangJin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(B1Mod.FENSHEN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new TalkAction(true, CARD_STRINGS.EXTENDED_DESCRIPTION[0], 1.0F, 2.0F));
        if (!this.upgraded)
        {
            JinBi jinBi=new JinBi(0,0);
            addToBot(new SpawnFenShenAction(jinBi,null));
        }
        else
        {
            JinBi jinBi1=new JinBi(0,0);
            JinBi jinBi2=new JinBi(0,0);
            addToBot(new SpawnFenShenAction(jinBi1,jinBi2));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
