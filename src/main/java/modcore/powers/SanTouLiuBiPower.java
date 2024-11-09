package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import modcore.B1Mod;


public class SanTouLiuBiPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:SanTouLiuBiPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int firstFenShen = 0;

    public SanTouLiuBiPower(AbstractCreature owner, int Amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/AnShenFaPower84.png";
        String path48 = "B1ModResources/images/powers/AnShenFaPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public void atStartOfTurn()
    {
       this.firstFenShen = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (card.hasTag(B1Mod.FENSHEN)&&!card.purgeOnUse && this.amount > 0 &&this.firstFenShen < this.amount)
        {
            System.out.println("this.firstFenShen为------"+this.firstFenShen);
            this.firstFenShen++;
            flash();
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp,null, card.energyOnUse, true, true), true);
        }
    }


    // 能力在更新时如何修改描述
    public void updateDescription()
    {
        if (this.amount == 1)
        {
        this.description = String.format(DESCRIPTIONS[0]);
        } else
        {
          this.description =String.format(DESCRIPTIONS[1]) + this.amount + String.format(DESCRIPTIONS[2]);
        }
    }
}

