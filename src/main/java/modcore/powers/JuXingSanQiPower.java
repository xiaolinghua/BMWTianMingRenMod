package modcore.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import modcore.Characters.WuKong;


public class JuXingSanQiPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:JuXingSanQiPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public JuXingSanQiPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/JuXinShanQi84.png";
        String path48 = "B1ModResources/images/powers/JuXinShanQi32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述

        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
        if (this.amount == 0)
        {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        else
        {
            addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL)
        {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }
    public void onRemove()
    {
        WuKong player= (WuKong) AbstractDungeon.player;
        player.ReloadImage();
    }
    // 能力在更新时如何修改描述
    public void updateDescription()
    {

        this.description = String.format(DESCRIPTIONS[0])+ this.amount+String.format(DESCRIPTIONS[1]);
    }
}
