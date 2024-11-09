package modcore.cards.skill;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.cardmods.OneTurnRetainMod;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS;

public class YueYuTu extends AbstractB1Card {
    public static final String ID = "blackmythwukong:YueYuTu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/YueTu.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private boolean isPlayed;


    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YueYuTu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.selfRetain=true;
        this.baseDamage = 15;
        isPlayed=false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(baseDamage, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
    public void applyPowers()//实现主要的功能
    {
        //System.out.println("手牌里有没有日金乌"+AbstractDungeon.player.hand.contains(AbstractDungeon.player.hand.findCardById(RiJingWu.ID)));
        if (AbstractDungeon.player.hand.contains(AbstractDungeon.player.hand.findCardById(RiJingWu.ID)))
        {
            int handPos = AbstractDungeon.player.hand.group.indexOf(this);
            int RiJingWuPos = AbstractDungeon.player.hand.group.indexOf(AbstractDungeon.player.hand.findCardById(RiJingWu.ID));
            int start = Math.min(handPos, RiJingWuPos);
            int end = Math.max(handPos, RiJingWuPos);
            for (int i = start; i <= end; i++)
            {
                // 为二者之间所有的卡添加一次性的保留，如果出现中间的卡牌或二牌被丢弃等等情况，一次性的保留也不会消失（必须打出后才会消失，所以可能会产生良性bug ）
                AbstractCard card = AbstractDungeon.player.hand.group.get(i);
                if (!CardModifierManager.hasModifier(card,OneTurnRetainMod.ID))
                {
                    CardModifierManager.addModifier(card,new OneTurnRetainMod());
                }
            }
            if (handPos-RiJingWuPos<=1&&handPos-RiJingWuPos>=-1&&!isPlayed)
            {
                isPlayed=true;//isPlayed负责使这段语句只执行一次
                use(AbstractDungeon.player,null);
                addToBot(new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand,true));
                addToBot(new ExhaustSpecificCardAction(AbstractDungeon.player.hand.findCardById(RiJingWu.ID),AbstractDungeon.player.hand,true));
            }
        }
    }


    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return false;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
