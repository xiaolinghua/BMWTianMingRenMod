package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import modcore.utils.SkippedCardPool;

import static modcore.Characters.WuKong.Enums.TianMingRen_LIBRARY;

public class BieYouPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:BieYouPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BieYouPower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "B1ModResources/images/powers/Beiliwan84.png";
        String path48 = "B1ModResources/images/powers/Beiliwan32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        // 首次添加能力更新描述
        this.updateDescription();
    }
    public void atStartOfTurn()
    {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            this.flash();
/*
            for (Map.Entry<String, String> entry : UnlockTracker.seenPref.data.entrySet())
            {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }

 */
            for (AbstractCard card:CardLibrary.getCardList(TianMingRen_LIBRARY))
            {
                if (!UnlockTracker.isCardSeen(card.cardID))
                {
                    addToBot(new MakeTempCardInHandAction(card, true));
                    return;
                }
            }
            SkippedCardPool.ToString();
            AbstractCard c =SkippedCardPool.GetRandomCardFromPool().makeCopy();
            addToBot(new MakeTempCardInHandAction(c, true));
        }
    }


    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
    }
}

