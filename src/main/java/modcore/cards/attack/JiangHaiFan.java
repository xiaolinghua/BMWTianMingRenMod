package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class JiangHaiFan extends AbstractB1Card {
    public static final String ID = "blackmythwukong:JiangHaiFan";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/JiangHaiFan.png";
    private static final int COST = 2;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public JiangHaiFan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 15;
    }
    @Override
    public boolean freeToPlay()
    {
        super.freeToPlay();
        if (AbstractDungeon.player!=null)
        {
            AbstractPlayer p = AbstractDungeon.player;
            return p.hasPower("blackmythwukong:FengYunZhuanPower");
        }
        else
            return super.freeToPlay();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(baseDamage, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "blackmythwukong:FengYunZhuanPower"));
    }
    public void triggerOnGlowCheck()
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:FengYunZhuanPower"))
        {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }else
        {
            this.glowColor =AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }
}