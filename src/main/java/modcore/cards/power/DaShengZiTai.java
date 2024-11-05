package modcore.cards.power;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.actions.DaShengZiTaiAction;
import modcore.actions.doubleGunShiDamageAction;
import modcore.utils.GunShiDamageCalculate;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class DaShengZiTai extends AbstractB1Card {
    public static final String ID = "blackmythwukong:DaShengZiTai";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源

    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/Dasheng2.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:DaShengYuYin1","B1:DaShengYuYin2","B1:DaShengYuYin3"}, true, 1.0F, 0.3F, 0.2F);

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public DaShengZiTai() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        sfxUtil.playSFX();
        if (GunShiDamageCalculate.getGunShiMax()==10)
        {
            addToBot(new DaShengZiTaiAction());
        }
        else
        {
            addToBot(new doubleGunShiDamageAction());
        }
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(0);
        }
    }
}