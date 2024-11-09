package modcore.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.Patches.AbstractB1Card;
import modcore.actions.DingShenAction;
import modcore.powers.GunShiPower;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class Dingshenfa extends AbstractB1Card {
    public static final String ID = "blackmythwukong:Dingshenfa";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/DingShenFa.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:ding1"}, true, 1.0F, 0.1F, 0.9F);

    public Dingshenfa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }
    public  Dingshenfa(boolean isupgrade) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.upgrade();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

        if(AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >=6)
        {
            System.out.println("玩家是否有遗物？"+AbstractDungeon.player.hasRelic("blackmythwukong:SheChangSi"));
            if(AbstractDungeon.player.hasRelic("blackmythwukong:SheChangSi"))
            {
                AbstractDungeon.player.getRelic("blackmythwukong:SheChangSi").flash();
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                {
                    sfxUtil.playSFX();
                    AbstractDungeon.actionManager.addToBottom(new DingShenAction(mo, AbstractDungeon.player));
                }
            }
            else
            {
                sfxUtil.playSFX();
                AbstractDungeon.actionManager.addToBottom(new DingShenAction(m, AbstractDungeon.player));
            }
                addToBot(new ReducePowerAction(p, p, "blackmythwukong:GunShi",6));
        }
        else
        {
            this.addToBot(new ApplyPowerAction(p, p, new GunShiPower(p, 3), 3));
        }
    }
    public void triggerOnGlowCheck()
    {
        if(AbstractDungeon.player.hasPower("blackmythwukong:GunShi")&&AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount >=6)
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
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
