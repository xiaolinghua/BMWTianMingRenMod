package modcore.cards.other;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.cardmods.exhaustCardMod;

import java.util.Objects;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class MonsterDamage extends AbstractB1Card {
    public static final String ID = "blackmythwukong:MonsterDamage";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/MonsterDamage.png";
    private static final int COST = -2;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    int damageAmount;
    int damageTime;
    private final int extraPowerID;
    private String powerID;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public MonsterDamage(int damageAmount,int damageTime,int extraPowerID)
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damageAmount=damageAmount;
        this.damageTime=damageTime;
        this.extraPowerID=extraPowerID;
        this.damage = this.baseDamage = 13;
        CardModifierManager.addModifier(this, new exhaustCardMod());
        configureDamage();
    }
    public MonsterDamage()
    {
        this(0,0,0);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return false;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        if (Objects.equals(c.cardID, "blackmythwukong:ZhongGun"))
        {
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player,"blackmythwukong:MonsterDamagePower"+extraPowerID));
            addToBot(new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand));
        }
    }
    public void triggerOnExhaust()
    {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player,"blackmythwukong:MonsterDamagePower"+extraPowerID));
    }
    private void configureDamage() {
        this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0]+this.damageAmount+CARD_STRINGS.EXTENDED_DESCRIPTION[1]+this.damageTime+CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }
    public AbstractCard makeCopy()
    {
        return new MonsterDamage(this.damageAmount,damageTime,this.extraPowerID);
    }
    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();

        }
    }

}
