package modcore.cards.other;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

public class HouYao extends AbstractB1Card {
    public static final String ID = "blackmythwukong:HouYao";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/BuYouJi.png";
    private static final int COST = 1;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public HouYao()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }
    public AbstractCard makeCopy()
    {
        return new HouYao();
    }
    public void triggerOnExhaust()
    {
        addToTop(new DrawCardAction(AbstractDungeon.player, 1));
    }
    @Override
    public void upgrade()
    {

    }
    public boolean canUpgrade() {
        return false;
    }
}
