package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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

public class YouJiuChuChuShenXianFu extends AbstractB1Card {
    public static final String ID = "blackmythwukong:YouJiuChuChuShenXianFu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/YouJiuChuChu.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YouJiuChuChuShenXianFu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 20;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (AbstractPotion potion : p.potions)
        {
            if (!(potion instanceof PotionSlot)&&!(Objects.equals(potion.ID, QingTianHuLu.ID))||(Objects.equals(potion.ID, QingTianHuLu.ID)&&potion.canUse()))
            {
                potion.use(p);
                if (!Objects.equals(potion.ID, QingTianHuLu.ID))
                {
                    AbstractDungeon.topPanel.destroyPotion(potion.slot);
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            }
        }
    }
    public void applyPowers()
    {
        int Ecost=0;
        for (AbstractPotion potion : AbstractDungeon.player.potions)
        {
            if (!(potion instanceof PotionSlot)&&!(Objects.equals(potion.ID, QingTianHuLu.ID))||(Objects.equals(potion.ID, QingTianHuLu.ID)&&potion.canUse()))
            {
                Ecost++;
            }
        }
        this.costForTurn=Ecost;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }
}
