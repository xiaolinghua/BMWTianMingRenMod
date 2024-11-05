package modcore.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import modcore.B1Mod;
import modcore.actions.OrbAddAmountAction;
import modcore.actions.OrbCleanAmountAction;
import modcore.cards.attack.ZhongGun;
import modcore.utils.GunShiDamageCalculate;


public class GunShiPower extends AbstractPower// implements InvisiblePower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:GunShi");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean giveZhongGun;

    public GunShiPower(AbstractCreature owner, int Amount)
    {
        giveZhongGun=false;
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        String path128 = "B1ModResources/images/powers/GunShi84.png";
        String path48 = "B1ModResources/images/powers/GunShi32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        if (this.amount >= 999)
        {
            this.amount = 999;
        }
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
        AddOrbAmount(stackAmount);
        if (this.amount >= GunShiDamageCalculate.getGunShiMax())
        {
            this.amount=GunShiDamageCalculate.getGunShiMax();
            if (!giveZhongGun)
            {
                this.flash();
                giveZhongGun = true;
                addToBot(new MakeTempCardInHandAction(new ZhongGun(), false));
                if (AbstractDungeon.player.hasPower("blackmythwukong:BeiLiWanPower"))
                {
                    AbstractPower BeiLiWan = AbstractDungeon.player.getPower("blackmythwukong:BeiLiWanPower");
                    BeiLiWan.flash();
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, BeiLiWan.amount), BeiLiWan.amount));
                }
            }
        }
        if (this.amount > 0&&this.amount<GunShiDamageCalculate.getGunShiMax())
        {
            giveZhongGun=false;
        }
        if (this.amount <= 0)
        {
            giveZhongGun=false;
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "blackmythwukong:GunShi"));
            addToBot(new OrbCleanAmountAction(true));
        }
    }
    public void reducePower(int reduceAmount)
    {
        super.reducePower(reduceAmount);
        AddOrbAmount(-reduceAmount);
        giveZhongGun=false;
    }

    public void onInitialApplication()
    {
        addToBot(new OrbAddAmountAction(this.amount));
        if (this.amount >= GunShiDamageCalculate.getGunShiMax())
        {
            this.amount=GunShiDamageCalculate.getGunShiMax();
            if (!giveZhongGun)
            {
                this.flash();
                giveZhongGun = true;
                addToBot(new MakeTempCardInHandAction(new ZhongGun(), false));
                if (AbstractDungeon.player.hasPower("blackmythwukong:BeiLiWanPower"))
                {
                    AbstractPower BeiLiWan = AbstractDungeon.player.getPower("blackmythwukong:BeiLiWanPower");
                    BeiLiWan.flash();
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, BeiLiWan.amount), BeiLiWan.amount));
                }
            }
        }
    }
    public void onRemove()
    {
        addToBot(new OrbCleanAmountAction(true));
        for (AbstractCard c:AbstractDungeon.player.discardPile.group)
        {
            if (c.hasTag(B1Mod.QieShouJi))
            {
                System.out.println("----------------------------------切手技");
                addToBot(new DiscardToHandAction(c));
            }
        }
    }
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {

    }
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {

    }
    public void AddOrbAmount(int amount)
    {
        addToBot(new OrbAddAmountAction(amount));
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0])+GunShiDamageCalculate.getGunShiMax()+String.format(DESCRIPTIONS[1])+GunShiDamageCalculate.getGunShiDamage()+String.format(DESCRIPTIONS[2]);
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.description = String.format(DESCRIPTIONS[0])+GunShiDamageCalculate.getGunShiMax()+String.format(DESCRIPTIONS[1])+GunShiDamageCalculate.getGunShiDamage()+String.format(DESCRIPTIONS[2]);
    }
}
