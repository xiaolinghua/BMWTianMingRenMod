package modcore.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
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
import modcore.B1Mod;
import modcore.actions.OrbAddAmountAction;
import modcore.actions.OrbCleanAmountAction;
import modcore.cards.attack.ZhongGun;
import modcore.utils.GunShiUtil;
import modcore.utils.SfxUtil;

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
    public static SfxUtil sfxUtil2 = SfxUtil.createInstance(new String[]{"B1:2Dou"}, true, 1.0F, 0F, 1F);
    public static SfxUtil sfxUtil3 = SfxUtil.createInstance(new String[]{"B1:3Dou"}, true, 1.0F, 0F, 1F);
    private boolean canGetZhongGun;

    public GunShiPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        canGetZhongGun = true;
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
        /*
        if (this.amount % 3 == 0 && this.amount <= 12) {
            if (this.amount == 3) {
                sfxUtil2.playSFX();
            } else {
                sfxUtil3.playSFX();
            }
        }*/
        if (this.amount >= 6)
        {
            if (this.amount >= GunShiUtil.getGunShiMax())
            {
                this.amount = GunShiUtil.getGunShiMax();
            }
            if (!AbstractDungeon.player.hand.contains(AbstractDungeon.player.hand.findCardById(ZhongGun.ID)) && canGetZhongGun)
            {
                canGetZhongGun = false;
                this.flash();
                addToBot(new MakeTempCardInHandAction(new ZhongGun(), false));
            }
        }
        if (this.amount <= 0)
        {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "blackmythwukong:GunShi"));
            addToBot(new OrbCleanAmountAction(true));
        }
    }

    public void reducePower(int reduceAmount)
    {
        super.reducePower(reduceAmount);
        canGetZhongGun = true;
        GunShiUtil.setExhaustGunShiDianInOneTurn(reduceAmount / 3);
        System.out.println("本次消耗的棍势点是: " + reduceAmount / 3);
        AddOrbAmount(-reduceAmount);
        if (this.amount < 6)
        {
            for (AbstractCard c : AbstractDungeon.player.hand.group)
            {
                if (c.cardID.equals(ZhongGun.ID))
                {
                    addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
        }
    }

    public void onInitialApplication()
    {
        addToBot(new OrbAddAmountAction(this.amount));
        canGetZhongGun = true;
        /*
        if (this.amount % 3 == 0 && this.amount <= 12) {
            if (this.amount == 3) {
                sfxUtil2.playSFX();
            } else {
                sfxUtil3.playSFX();
            }
        }*/
        if (this.amount >= 6)
        {
            if (this.amount >= GunShiUtil.getGunShiMax())
            {
                this.amount = GunShiUtil.getGunShiMax();
            }
            if (!AbstractDungeon.player.hand.contains(AbstractDungeon.player.hand.findCardById(ZhongGun.ID)))
            {
                this.flash();
                addToBot(new MakeTempCardInHandAction(new ZhongGun(), false));
            }
        }
    }

    public void onRemove()
    {
        System.out.println("本次移除的棍势层数是: " + this.amount);
        GunShiUtil.setExhaustGunShiDianInOneTurn(this.amount / 3);
        addToBot(new OrbCleanAmountAction(true));
        canGetZhongGun = true;
        for (AbstractCard c : AbstractDungeon.player.hand.group)
        {
            if (c.cardID.equals(ZhongGun.ID))
            {
                addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group)
        {
            if (c.hasTag(B1Mod.QieShouJi))
            {
                System.out.println("----------------------------------切手技");
                addToBot(new DiscardToHandAction(c));
            }
            if (c.cardID.equals(ZhongGun.ID))
            {
                addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
        }
    }

    public void atStartOfTurn()
    {
        GunShiUtil.resetExhaustGunShiDianInOneTurn();
    }

    public void renderIcons(SpriteBatch sb, float x, float y, Color c)
    {

    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c)
    {

    }

    public void AddOrbAmount(int amount)
    {
        addToBot(new OrbAddAmountAction(amount));
    }

    // 能力在更新时如何修改描述
    public void updateDescription()
    {
        this.description = String.format(DESCRIPTIONS[0]) + GunShiUtil.getErDouZhongGunDamage() + String.format(DESCRIPTIONS[1]);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action)
    {
        this.description = String.format(DESCRIPTIONS[0]) + GunShiUtil.getErDouZhongGunDamage() + String.format(DESCRIPTIONS[1]);
    }
}
