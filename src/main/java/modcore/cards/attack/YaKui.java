package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class YaKui extends AbstractB1Card {
    public static final String ID = "blackmythwukong:YaKui";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/YaKui.png";
    private static final int COST = 0;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:ATTACK1", "B1:ATTACK2"}, true, 1.0F, 0.1F, 0.5F);
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YaKui()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        sfxUtil.playSFX();
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
        {
            int amount=AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount;
            if (amount >= 3 && amount < 6)
            {
                addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",3));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            }
            else if (amount >= 6 && amount < 9)
            {
                addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",6));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            }
            else if (amount >= 9&& amount < 12)
            {
                addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",9));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            }
            else if (amount >= 12)
            {
                addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",12));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            }
        }

    }
    public void applyPowers()
    {
        int orbNum=0;
        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
        {
            orbNum=AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount/3;

        }
        this.magicNumber = this.baseMagicNumber=orbNum;
        super.applyPowers();
        this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
}