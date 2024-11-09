package modcore.cards.skill;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.B1Mod;
import modcore.Patches.AbstractB1Card;
import modcore.actions.SpawnFenShenAction;
import modcore.monsters.KuiLei;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class ShenWaiShenFa extends AbstractB1Card {
    public static final String ID = "blackmythwukong:ShenWaiShenFa";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/ShenWaiShen.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:shenWaiShen"}, true, 1.0F, 0.1F, 0.9F);

    public ShenWaiShenFa()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(B1Mod.FENSHEN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        sfxUtil.playSFX();
        float x = 0.0F;
        float y = 0.0F;
        KuiLei kuiLei = new KuiLei(x, y);
        addToBot(new SpawnFenShenAction(kuiLei,null));
    }

    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.isInnate = true;
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
