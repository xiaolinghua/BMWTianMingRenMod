package modcore.potion;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomPotion;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import modcore.powers.GunShiPower;

public class QingTianHuLu extends CustomPotion  implements CustomSavable<Boolean>
{
    public static final String ID ="blackmythwukong:QingTianHuLu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    private boolean isUsed;
    public QingTianHuLu()
    {
        super(NAME, ID, PotionRarity.RARE, PotionSize.CARD, PotionColor.SMOKE);
        this.potency = getPotency();
        ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("B1ModResources/images/potion/QingTianHuLu.png"));
        ReflectionHacks.setPrivate(this, AbstractPotion.class, "outlineImg", new Texture("B1ModResources/images/potion/QingTian_outLine.png"));
        ReflectionHacks.setPrivate(this, AbstractPotion.class, "liquidImg", new Texture("B1ModResources/images/potion/none.png"));
        ReflectionHacks.setPrivate(this, AbstractPotion.class, "hybridImg", new Texture("B1ModResources/images/potion/none.png"));
        ReflectionHacks.setPrivate(this, AbstractPotion.class, "spotsImg", new Texture("B1ModResources/images/potion/none.png"));
        this.description = DESCRIPTIONS[0];
        this.isThrown = false;
        isUsed=false;
        this.tips.add(new PowerTip(this.name, this.description));
    }
    public void use(AbstractCreature target)
    {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&& !isUsed)
        {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GunShiPower(AbstractDungeon.player, this.potency), this.potency));
            ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("B1ModResources/images/potion/QingTianHuLu_gray.png"));
            isUsed=true;
        }
        else
        {
            ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("B1ModResources/images/potion/QingTianHuLu.png"));
            isUsed=false;
        }
    }
    public boolean canUse()
    {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
        {
            return !isUsed;
        }
        else
        {
            return false;
        }
    }

    public AbstractPotion makeCopy() {
        return new QingTianHuLu();
    }
    public int getPotency(int potency) {
        return 1;
    }

    @Override
    public Boolean onSave()
    {
        return isUsed;
    }

    @Override
    public void onLoad(Boolean aBoolean)
    {
        isUsed=aBoolean;
    }
}

