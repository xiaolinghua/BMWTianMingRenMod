package modcore.cards.attack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import modcore.Patches.AbstractB1Card;
import modcore.actions.DrawToDiscardAction;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class FengChuanHua extends AbstractB1Card {
    public static final String ID = "blackmythwukong:FengChuanHua";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/FengChuanHua.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public FengChuanHua()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 0;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractMonster strongestMonster = null;
        for (AbstractMonster m2 : AbstractDungeon.getMonsters().monsters)
        {
            if (!m2.isDeadOrEscaped())
            {
                if (strongestMonster == null || m.currentHealth > strongestMonster.currentHealth)
                {
                    strongestMonster = m2;
                }
            }
        }
        newapplyPowers();
        addToBot(new DamageAction(strongestMonster, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "blackmythwukong:GunShi"));
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), false)));
        addToBot(new DrawToDiscardAction(this, AbstractDungeon.player.hand));
    }
    public void triggerWhenDrawn()
    {
        AbstractMonster strongestMonster = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
        {
            if (!m.isDeadOrEscaped())
            {
                if (strongestMonster == null || m.currentHealth > strongestMonster.currentHealth)
                {
                    strongestMonster = m;
                }
            }
        }
        newapplyPowers();
        addToBot(new DamageAction(strongestMonster, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "blackmythwukong:GunShi"));
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), false)));
        addToBot(new DrawToDiscardAction(this, AbstractDungeon.player.hand));
    }
    public void newapplyPowers()
    {

        if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
        {
            if (!this.upgraded)
            {
                this.baseDamage = AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount * magicNumber;
            }
            else
            {
                this.baseDamage = AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount * magicNumber+5;
            }

        }else
        {
            if (GameActionManager.turn == 1 && AbstractDungeon.player.hasRelic("blackmythwukong:ChanShiWan"))
            {

                if (!this.upgraded)
                {
                    this.baseDamage = 3 * magicNumber;
                }
                else
                {
                    this.baseDamage = 3 * magicNumber + 5;
                }

            }
            else
            {
                if (!this.upgraded)
                {
                    this.baseDamage = 0;
                }
                else
                {
                    this.baseDamage = 5;
                }
            }
        }
        super.applyPowers();
        System.out.println("applyPowers凤传花的伤害"+this.baseDamage);
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