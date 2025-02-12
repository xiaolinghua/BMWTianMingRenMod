package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import modcore.Patches.AbstractB1Card;
import modcore.cards.other.QianYao;
import modcore.powers.GunShiPower;
import modcore.relics.ShenBenYou;
import modcore.utils.GunShiUtil;
import modcore.utils.SfxUtil;

public class ZhongGun extends AbstractB1Card {
    public static final String ID = "blackmythwukong:ZhongGun";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/ZhongGun.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION+ CARD_STRINGS.EXTENDED_DESCRIPTION[1]; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private boolean ifEnergy;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:2Xu"}, true, 1.0F, 0.1F, 0.9F);

    public ZhongGun()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 10;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        sfxUtil.playSFX();
        boolean ifAddPower = AbstractDungeon.player.hasPower("blackmythwukong:GunShi") && AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >= 9;
        addToBot(new RemoveSpecificPowerAction(p, p, GunShiPower.POWER_ID));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new MakeTempCardInHandAction(new QianYao()));
        if (ifEnergy&&AbstractDungeon.player.hasRelic(ShenBenYou.ID))
        {
            AbstractDungeon.player.getRelic(ShenBenYou.ID).flash();
            addToBot(new GainEnergyAction(1));
        }
        if (ifAddPower)
        {
            addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,GunShiUtil.getPowerAmount()),GunShiUtil.getPowerAmount()));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount>= 6)
        {
            return true;
        }
        else
        {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }
    public void applyPowers()
    {
        if (AbstractDungeon.player.hasRelic(ShenBenYou.ID))
        {
            ifEnergy=true;
        }
        this.baseMagicNumber=GunShiUtil.getPowerAmount();
        if (AbstractDungeon.player.hasPower(GunShiPower.POWER_ID))
        {
            switch (AbstractDungeon.player.getPower(GunShiPower.POWER_ID).amount)
            {
                case 6:
                case 7:
                case 8:
                    this.baseDamage = GunShiUtil.getErDouZhongGunDamage();
                    this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
                    this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[3]+ CARD_STRINGS.EXTENDED_DESCRIPTION[7]+2+ CARD_STRINGS.EXTENDED_DESCRIPTION[8];
                    break;
                case 9:
                case 10:
                case 11:
                    this.baseDamage = GunShiUtil.getSanDouZhongGunDamage();
                    this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[5];
                    this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[3]+ CARD_STRINGS.EXTENDED_DESCRIPTION[7]+3+ CARD_STRINGS.EXTENDED_DESCRIPTION[8]+CARD_STRINGS.EXTENDED_DESCRIPTION[9];
                    break;
                case 12:
                    this.baseDamage = GunShiUtil.getSiDouZhongGunDamage();
                    this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[6];
                    this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[3]+ CARD_STRINGS.EXTENDED_DESCRIPTION[7]+4+ CARD_STRINGS.EXTENDED_DESCRIPTION[8]+CARD_STRINGS.EXTENDED_DESCRIPTION[9];
                    break;
                default:
                    this.baseDamage = 0;
                    break;
            }
            super.applyPowers();
        }
        if (ifEnergy)
        {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        }
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
        initializeTitle();
    }
    @Override
    public void upgrade() {

    }
    public AbstractCard makeCopy()
    {
        return new ZhongGun();
    }
}