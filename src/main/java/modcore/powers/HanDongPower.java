package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class HanDongPower extends AbstractPower// implements HealthBarRenderPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:HanDongPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private float timer=0F;
    public HanDongPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.DEBUFF;
        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;
        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/Beiliwan84.png";
        String path48 = "B1ModResources/images/powers/Beiliwan32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner)
        {
            this.flash();
            if (!this.owner.hasPower(WuFengNongXuePower.POWER_ID))
            {
                this.addToTop(new ReducePowerAction(this.owner, this.owner, this, 1));
            }
        }
        return damageAmount;
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL)
        {
            float reduction = Math.min(this.amount * 0.1F, 1.0F);
            return damage * (1.0F - reduction);
        }
        return damage;
    }
    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
        if (this.owner.state != null) {
            float timeScale = Math.max(1.0F - (this.amount * 0.1F), 0.0F);
            this.owner.state.setTimeScale(timeScale); // 根据层数调整动画速度
        }
    }
    public void onInitialApplication()
    {
        super.onInitialApplication();
        if (this.owner.state != null) {
            float timeScale = Math.max(1.0F - (this.amount * 0.1F), 0.0F);
            this.owner.state.setTimeScale(timeScale); // 根据层数调整动画速度
        }
    }
    // 能力在更新时如何修改描述
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0]+Math.min(this.amount,10)+DESCRIPTIONS[1];
    }
    /*
    public void update(int slot)
    {
        super.update(slot);
        timer-= Gdx.graphics.getDeltaTime();
        if (this.amount >= 5&&timer<0)
        {
            AbstractDungeon.effectList.add(new HanDongEffect(this.owner));
            timer=1.5F;
        }
    }

     */
}
