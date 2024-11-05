package modcore.cards.attack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.cardmods.CanNotGetGunShiMod;
import modcore.powers.GunShiPower;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class JiaoGun extends AbstractB1Card {
    public static final String ID = "blackmythwukong:JiaoGun";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/JiaoGun.png";
    private static final int COST = 0;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:ATTACK1", "B1:ATTACK2"}, true, 1.0F, 0.1F, 0.5F);
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public JiaoGun() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 3;
        CardModifierManager.addModifier(this, new CanNotGetGunShiMod());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        sfxUtil.playSFX();
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        if (AbstractDungeon.player.hasPower(GunShiPower.POWER_ID))
        {
            addToBot(new ReducePowerAction(p, p, GunShiPower.POWER_ID,1));
            addToBot(new MakeTempCardInHandAction(new JiaoGun(), 1));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        if (AbstractDungeon.player.hasPower(GunShiPower.POWER_ID))
        {
            return true;
        }
        else
        {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }
    public void triggerOnGlowCheck()
    {
        if (AbstractDungeon.player.hasPower(GunShiPower.POWER_ID))
        {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }
}