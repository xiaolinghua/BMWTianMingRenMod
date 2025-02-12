package modcore.cards.attack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import modcore.Patches.AbstractB1Card;
import modcore.cards.other.PreviewWuLiYun;
import modcore.powers.HanDongPower;
import modcore.utils.SfxUtil;

import static modcore.Characters.WuKong.Enums.BMW_CARD;

public class YunLiWu extends AbstractB1Card {
    public static final String ID = "blackmythwukong:YunLiWu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    // private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "B1ModResources/images/cards/YunLiWuWuLiYun.png";
    private static final int COST = 0;
    // private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = BMW_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    public static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:WuLiYun1", "B1:WuLiYun2", "B1:WuLiYun3"}, false, 0.5F, 0.1F, 0.1F);
    private boolean isYunLiWu;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public YunLiWu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 30;
        this.magicNumber = this.baseMagicNumber = 7;
        isYunLiWu=false;
        this.cardsToPreview = new PreviewWuLiYun();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (!isYunLiWu)
        {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(
                            m,
                            new DamageInfo(
                                    p,
                                    damage,
                                    DamageInfo.DamageType.NORMAL
                            )
                    )
            );
        }
        else
        {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                addToBot(new ApplyPowerAction(mo, p, new HanDongPower(mo, magicNumber), magicNumber));
            }
        }
    }
    public void update()
    {
        super.update();
        if (AbstractDungeon.player != null &&AbstractDungeon.currMapNode!=null&&(AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hand.contains(this) && this.isHoveredInHand(this.drawScale)&& AbstractDungeon.player.hand.size() > 1) {
            int thisIndex = AbstractDungeon.player.hand.group.indexOf(this);
            int randomIndex = AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.hand.size() - 1);
            AbstractCard temp = AbstractDungeon.player.hand.group.get(randomIndex);
            AbstractDungeon.player.hand.group.set(randomIndex, this);
            AbstractDungeon.player.hand.group.set(thisIndex, temp);
            isYunLiWu = !isYunLiWu;
            sfxUtil.playSFX();
            configureNameAndImage(isYunLiWu);
            AbstractDungeon.player.hand.refreshHandLayout();
        }
    }
    private void configureNameAndImage(boolean isYunLiWu)
    {
        if (isYunLiWu)
        {
            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[2];
            this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            this.textureImg="B1ModResources/images/cards/YunLIWu.png";
        }
        else
        {
            this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            this.rawDescription = DESCRIPTION;
            this.textureImg="B1ModResources/images/cards/WuLiYun.png";
        }
        this.initializeDescription();
        initializeTitle();
        if (this.textureImg != null)
        {
            loadCardImage(this.textureImg);
        }
    }
    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(7);
            this.upgradeMagicNumber(3);
            this.cardsToPreview.upgrade();
        }
    }
}