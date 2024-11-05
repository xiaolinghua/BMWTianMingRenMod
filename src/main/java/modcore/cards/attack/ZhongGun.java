package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.powers.GunShiPower;
import modcore.relics.ShenBenYou;
import modcore.utils.GunShiDamageCalculate;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class ZhongGun extends AbstractB1Card {
    public static final String ID = "blackmythwukong:ZhongGun";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/ZhongGun.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION+ CARD_STRINGS.EXTENDED_DESCRIPTION[1]; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private boolean ifEnergy;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:2Xu"}, true, 1.0F, 0.1F, 0.9F);

    public ZhongGun()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 10;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        sfxUtil.playSFX();
        addToBot(new RemoveSpecificPowerAction(p, p, GunShiPower.POWER_ID));
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(baseDamage, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (ifEnergy&&AbstractDungeon.player.hasRelic(ShenBenYou.ID))
        {
            AbstractDungeon.player.getRelic(ShenBenYou.ID).flash();
            addToBot(new GainEnergyAction(1));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount>=GunShiDamageCalculate.getGunShiMax())
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
        this.baseDamage= GunShiDamageCalculate.getGunShiDamage();
        super.applyPowers();
        if (AbstractDungeon.player.hasRelic(ShenBenYou.ID))
        {
            ifEnergy=true;
        }
        if (ifEnergy&&GunShiDamageCalculate.getGunShiMax()==10)
        {
            this.rawDescription = CARD_STRINGS.DESCRIPTION+ CARD_STRINGS.EXTENDED_DESCRIPTION[2]+ CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            this.initializeDescription();
        }else if (!ifEnergy&&GunShiDamageCalculate.getGunShiMax()==13)
        {
            setDisplayRarity(CardRarity.RARE);
            this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
            this.rawDescription =CARD_STRINGS.EXTENDED_DESCRIPTION[3]+ CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            this.initializeDescription();
            initializeTitle();
        }
        else if (ifEnergy&&GunShiDamageCalculate.getGunShiMax()==13)
        {
            setDisplayRarity(CardRarity.RARE);
            this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
            this.rawDescription =CARD_STRINGS.EXTENDED_DESCRIPTION[3]+ CARD_STRINGS.EXTENDED_DESCRIPTION[2]+ CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            this.initializeDescription();
            initializeTitle();
        }

    }
    public void triggerOnGlowCheck()
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >= GunShiDamageCalculate.getGunShiMax())
        {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else
        {
            this.glowColor =AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void upgrade() {

    }
    public AbstractCard makeCopy()
    {
        return new ZhongGun();
    }
}