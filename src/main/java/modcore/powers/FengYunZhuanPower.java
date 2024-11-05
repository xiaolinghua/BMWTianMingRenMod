package modcore.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;


public class FengYunZhuanPower extends AbstractPower
{
    // 能力的ID
    public static final String POWER_ID = ("blackmythwukong:FengYunZhuanPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FengYunZhuanPower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        loadRegion("swivel");
        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
           if (Objects.equals(card.cardID, "blackmythwukong:JiangHaiFan"))
           {
               flash();
               addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
           }
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
    }
}
