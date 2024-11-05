package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class MonsterDamagePower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:MonsterDamagePower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damageAmount;
    private int damageTime;

    public MonsterDamagePower(AbstractCreature owner,int extraPowerID, int damageAmount,int damageTime)
    {
        this.name = NAME;
        this.ID = POWER_ID + extraPowerID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.damageAmount=damageAmount;
        this.damageTime=damageTime;
        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/MonsterDamagePower84.png";
        String path48 = "B1ModResources/images/powers/MonsterDamagePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        if (isPlayer)
        {
            for (int i=0;i<damageTime;i++)
            {
                addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.damageAmount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            }
        }
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
    // 能力在更新时如何修改描述
    public void updateDescription()
    {
        this.description = String.format(DESCRIPTIONS[0])+ damageAmount+String.format(DESCRIPTIONS[1])+damageTime+String.format(DESCRIPTIONS[2]);
    }
}
