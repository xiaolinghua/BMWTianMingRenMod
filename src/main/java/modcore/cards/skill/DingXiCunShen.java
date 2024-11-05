package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class DingXiCunShen extends AbstractB1Card {
    public static final String ID = "blackmythwukong:DingXiCunShen";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/DingXiCunShen.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public DingXiCunShen()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
        {
            int amount=AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount;
                if (amount >= 3 && amount < 6)
                {
                    addToBot(new GainEnergyAction(1));
                    addToBot(new DrawCardAction(p, 1));
                    addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",3));
                }
                else if (amount >= 6 && amount < 9)
                {
                    addToBot(new GainEnergyAction(2));
                    addToBot(new DrawCardAction(p, 2));
                    addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",6));
                }
                else if (amount >= 9&&amount < 12)
                {
                    addToBot(new GainEnergyAction(3));
                    addToBot(new DrawCardAction(p, 3));
                    addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",9));
                }
                else if (amount >= 12)
                {
                    addToBot(new GainEnergyAction(4));
                    addToBot(new DrawCardAction(p, 4));
                    addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",12));
                }
        }
    }
    public void triggerOnGlowCheck()
    {
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >=3)
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
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain=true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

