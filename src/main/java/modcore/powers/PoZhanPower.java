package modcore.powers;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import modcore.cardmods.CanNotGetGunShiMod;

public class PoZhanPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:PoZhan");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PoZhanPower(AbstractCreature owner,int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/pozhan84.png";
        String path48 = "B1ModResources/images/powers/pozhan32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && CardModifierManager.hasModifier(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1), CanNotGetGunShiMod.ID))
            {
                return damageAmount;
            }
            this.flash();
            this.addToTop(new ApplyPowerAction(p, p, new GunShiPower(p, 1), 1));
        }
        return damageAmount;
    }
    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
        if (this.amount>1)
        {
            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new VulnerablePower(this.owner,stackAmount,false)));
            this.amount=1;
        }
    }
    public void onInitialApplication()
    {
        if (this.amount>1)
        {
            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new VulnerablePower(this.owner,this.amount-1,false)));
            this.amount=1;
        }
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
    }
}

