package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import modcore.gameEffect.TrailEffect;


public class ShiZhiJingPoPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:ShiZhiJingPoPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ShiZhiJingPoPower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount =-1;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/RuZhuangJinZhongPower84.png";
        String path48 = "B1ModResources/images/powers/RuZhuangJinZhongPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        //if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount >= 0 && info.output > 0)
        this.flash();
        AbstractDungeon.effectList.add(new TrailEffect(this.owner.hb.cX, this.owner.hb.cY, 198.0F * Settings.xScale, 190.0F * Settings.yScale));
        addToBot(new GainEnergyAction(1));
        return damageAmount;
    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount)
    {
        damageAmount = 1;
        return damageAmount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
    }
}
