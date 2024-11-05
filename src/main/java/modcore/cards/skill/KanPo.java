package modcore.cards.skill;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.cards.other.MonsterDamage;
import modcore.powers.MonsterDamagePower;
import modcore.powers.PoZhanPower;

import static modcore.Characters.WuKong.Enums.BMW_CARD;
import static modcore.utils.PowerIDUtil.*;

public class KanPo extends AbstractB1Card{
    public static final String ID = "blackmythwukong:KanPo";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/KanPo.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public KanPo()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new MonsterDamage();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >=3&& m.getIntentBaseDmg() > 0)
        {

            addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",3));
            int damageTime=ReflectionHacks.getPrivate(m,AbstractMonster.class,"intentMultiAmt");
            if (damageTime<=0)
            {
                damageTime=1;
            }
            System.out.println("怪物IntentDmg伤害是: "+m.getIntentDmg()+",怪物的意图是 "+m.intent+",怪物造成intentMultiAmt "+ damageTime);
            addToBot(new MakeTempCardInHandAction(new MonsterDamage(m.getIntentDmg(), damageTime, getExtraPowerIDAndAdd()), false));
            printExtraPowerID();
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MonsterDamagePower(AbstractDungeon.player,getSourceExtraPowerID(),m.getIntentDmg(),damageTime)));
            printExtraPowerID();

            m.setMove((byte)-23, AbstractMonster.Intent.ATTACK, 0);
            m.createIntent();
        }
        else if(this.upgraded)
        {
            addToBot(new ApplyPowerAction(m, p, new PoZhanPower(m,1)));
        }
    }

    public void triggerOnGlowCheck()
    {
        if(AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >=3)
        {
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() > 0)
                {
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    break;
                }
            }
        }
        else
        {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

