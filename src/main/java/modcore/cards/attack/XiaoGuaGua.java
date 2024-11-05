package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import modcore.Patches.AbstractB1Card;
import modcore.powers.TongTouPower;

import java.util.Random;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class XiaoGuaGua extends AbstractB1Card {
    public static final String ID = "blackmythwukong:XiaoGuaGua";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/xiaoguagua.png";
    private static final int COST = 1;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public XiaoGuaGua()
    {
        this(0);
    }
    public XiaoGuaGua(int timesUpgraded)
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.upgraded = (timesUpgraded > 0);
        this.timesUpgraded = timesUpgraded;
        configureNameAndImage();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Random rand = new Random();
        if (rand.nextBoolean())
        {

            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "呱~", 1.2F, 2F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        if (timesUpgraded>=1)
        {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        }
        if (timesUpgraded>=2)
        {
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false),2));
        }
        if (timesUpgraded>=3)
        {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 2, false),2));
        }
        if (timesUpgraded>=4)
        {
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p,5), 5, AbstractGameAction.AttackEffect.POISON));
        }
        if (timesUpgraded>=5)
        {
            addToBot(new ApplyPowerAction(p, p, new VigorPower(p, 5), 5));
        }
        if (timesUpgraded>=6)
        {
            addToBot(new ApplyPowerAction(p, p, new TongTouPower(p, 5), 5));
        }

    }
    public void upgrade()
    {
        this.timesUpgraded++;
        this.upgraded = true;
        configureNameAndImage();

    }
    private void configureNameAndImage() {
        switch (timesUpgraded)
        {
            case 0:this.textureImg="B1ModResources/images/cards/xiaoguagua.png";

                    break;
            case 1: this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6];
                    this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
                this.textureImg="B1ModResources/images/cards/boligelang.png";
                    break;
            case 2:this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6]+CARD_STRINGS.EXTENDED_DESCRIPTION[7];
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
                this.textureImg="B1ModResources/images/cards/langligebo.png";
                break;
            case 3:this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6]+CARD_STRINGS.EXTENDED_DESCRIPTION[7]+CARD_STRINGS.EXTENDED_DESCRIPTION[8];
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[2];
                this.textureImg="B1ModResources/images/cards/langligelang.png";
                break;
            case 4:this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6]+CARD_STRINGS.EXTENDED_DESCRIPTION[7]+CARD_STRINGS.EXTENDED_DESCRIPTION[8]+CARD_STRINGS.EXTENDED_DESCRIPTION[9];
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[3];
                this.textureImg="B1ModResources/images/cards/boligebo.png";
                break;
            case 5:this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6]+CARD_STRINGS.EXTENDED_DESCRIPTION[7]+CARD_STRINGS.EXTENDED_DESCRIPTION[8]+CARD_STRINGS.EXTENDED_DESCRIPTION[9]+CARD_STRINGS.EXTENDED_DESCRIPTION[10];
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
                this.textureImg="B1ModResources/images/cards/bolanglang.png";
                break;
            case 6:this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[6]+CARD_STRINGS.EXTENDED_DESCRIPTION[7]+CARD_STRINGS.EXTENDED_DESCRIPTION[8]+CARD_STRINGS.EXTENDED_DESCRIPTION[9]+CARD_STRINGS.EXTENDED_DESCRIPTION[10]+CARD_STRINGS.EXTENDED_DESCRIPTION[11];
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[5];
                this.textureImg="B1ModResources/images/cards/langbobo.png";
                setDisplayRarity(CardRarity.RARE);
                break;
            default: break;
        }
        this.initializeDescription();
        initializeTitle();
        if (this.textureImg != null)
        {
            loadCardImage(this.textureImg);
        }
    }
    public boolean canUpgrade()
    {
        return this.timesUpgraded < 6;
    }
    public AbstractCard makeCopy()
    {
        return new XiaoGuaGua();
    }
}