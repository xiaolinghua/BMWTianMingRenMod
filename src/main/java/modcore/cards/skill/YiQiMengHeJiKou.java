package modcore.cards.skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import modcore.Patches.AbstractB1Card;
import modcore.potion.QingTianHuLu;

import java.util.Objects;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class YiQiMengHeJiKou extends AbstractB1Card {
    public static final String ID = "blackmythwukong:YiQiMengHeJiKou";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/YiQIMengHe.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YiQiMengHeJiKou() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber=9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int maxhealth=magicNumber;
        for (AbstractPotion potion : p.potions)
        {
            if (potion instanceof PotionSlot|| (Objects.equals(potion.ID, QingTianHuLu.ID)&&!potion.canUse()))
            {
                maxhealth -= magicNumber/3;
            }
            else
            {
                potion.use(p);
                if (!Objects.equals(potion.ID, QingTianHuLu.ID))
                {
                    AbstractDungeon.topPanel.destroyPotion(potion.slot);
                }
            }
        }
        if (maxhealth > 0)
        {
            AbstractDungeon.player.increaseMaxHp(maxhealth, true);
        }
    }
    public void applyPowers()
    {
        int maxhealth=magicNumber;
        for (AbstractPotion potion : AbstractDungeon.player.potions)
        {
            if (potion instanceof PotionSlot|| (Objects.equals(potion.ID, QingTianHuLu.ID)&&!potion.canUse()))
            {
                maxhealth -= magicNumber/3;
            }
        }
        if (maxhealth < 0)
        {
            maxhealth=0;
        }
        if (!this.upgraded)
        {
            this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1]+maxhealth+CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[1]+maxhealth+CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        }
        this.initializeDescription();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        super.canUse(p,m);
        for (AbstractPotion potion : p.potions)
        {
            if (!(potion instanceof PotionSlot)&&!(Objects.equals(potion.ID, QingTianHuLu.ID))||(Objects.equals(potion.ID, QingTianHuLu.ID)&&potion.canUse()))
            {
                return true;
            }
        }
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(3);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
