package modcore.cards.power;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import modcore.Patches.AbstractB1Card;
import modcore.actions.TaDeShanWaiQingShanAction;
import modcore.potion.JinDan;
import modcore.utils.SfxUtil;

import java.util.Iterator;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class TaDeShanWaiQingShan extends AbstractB1Card {
    public static final String ID = "blackmythwukong:TaDeShanWaiQingShan";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源

    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/simei.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static boolean isCanUse;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:ChouHouZi"}, true, 1.0F, 0.3F, 0.2F);

    public TaDeShanWaiQingShan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        sfxUtil.playSFX();
        addToBot(new TaDeShanWaiQingShanAction());
        addToBot(new ObtainPotionAction(new  JinDan()));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        super.canUse(p,m);
        int index = 0;
        for(Iterator<AbstractPotion> var3 = p.potions.iterator(); var3.hasNext(); ++index) {
            AbstractPotion potion = var3.next();

            if (potion instanceof PotionSlot) {
                break;
            }
        }
        if (index < p.potionSlots) {
            isCanUse=true;
            return true;
        } else {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            AbstractDungeon.topPanel.flashRed();
            isCanUse=false;
            return false;
        }
    }
    public void triggerOnGlowCheck()
    {
        if(isCanUse)
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
            this.upgradeBaseCost(0);
        }
    }
}