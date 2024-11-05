package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class TongTouPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:TongTouPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TongTouPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/TonfTou84.png";
        String path48 = "B1ModResources/images/powers/TonfTou32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type)
    {
        if (damage > 0F&&damage<=this.amount) {
            damage = 0F;
        }
        return damage;
    }

    public void wasHPLost(DamageInfo info, int damageAmount)
    {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0)
        {
           flash();
           addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "blackmythwukong:TongTouPower"));
        }
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0])+ this.amount+String.format(DESCRIPTIONS[1])+ this.amount+String.format(DESCRIPTIONS[2]);
    }
}
