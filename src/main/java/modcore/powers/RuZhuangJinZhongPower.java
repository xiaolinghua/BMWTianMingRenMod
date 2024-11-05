package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class RuZhuangJinZhongPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:RuZhuangJinZhongPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature lastAttacker = null;
    // 引入一个布尔变量来跟踪是否已经播放了对话动作针对当前攻击者
    private boolean hasSpokenToCurrentAttacker = false;
    public RuZhuangJinZhongPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount =Amount;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/RuZhuangJinZhongPower84.png";
        String path48 = "B1ModResources/images/powers/RuZhuangJinZhongPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public void atEndOfRound()
    {
        if (this.amount == 0)
        {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        else
        {
            addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }
    /*
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount <= 0 && info.output > 0)
        {
            flash();
            addToTop(new ApplyPowerAction(info.owner,this.owner,new PoZhanPower(info.owner,1)));
            if (!hasSpoken)
            {
                addToBot(new TalkAction(info.owner, "好个铜头", 1.2F, 2F));
            }
        }
        return damageAmount;
    }

     */
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount <= 0 && info.output > 0)
        {
            flash();
            addToBot(new ApplyPowerAction(info.owner, this.owner, new PoZhanPower(info.owner, 1)));
            // 检查攻击者是否已更改
            if (lastAttacker != info.owner) {
                // 攻击者已更改，重置已播放标志
                hasSpokenToCurrentAttacker = false;
                lastAttacker = info.owner; // 更新上一个攻击者的ID（或引用）
            }
            // 如果尚未对当前攻击者播放对话动作，则播放并设置标志
            if (!hasSpokenToCurrentAttacker) {
                addToBot(new TalkAction(info.owner, "好个铜头", 1.2F, 2F));
                hasSpokenToCurrentAttacker = true; // 设置标志为已播放
            }
        }
        // 注意：不需要在这里重置lastAttacker或hasSpokenToCurrentAttacker，
        // 因为它们会在下一次攻击时根据条件自动更新。
        return damageAmount;
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
    }
}
