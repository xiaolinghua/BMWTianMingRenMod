 package modcore.potion;

 import basemod.ReflectionHacks;
 import basemod.abstracts.CustomPotion;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.localization.PotionStrings;
 import com.megacrit.cardcrawl.potions.AbstractPotion;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;

 public class JinDan extends CustomPotion {
     public static final String ID ="blackmythwukong:JinDan";
     private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
     public static final String NAME = potionStrings.NAME;
     public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
     public JinDan()
     {
         super(NAME, ID, PotionRarity.PLACEHOLDER, PotionSize.T, PotionColor.SMOKE);
         this.description = DESCRIPTIONS[0];
         ReflectionHacks.setPrivate(this, AbstractPotion.class, "containerImg", new Texture("B1ModResources/images/potion/JinDan.png"));
         ReflectionHacks.setPrivate(this, AbstractPotion.class, "outlineImg", new Texture("B1ModResources/images/potion/jindan_out.png"));
         ReflectionHacks.setPrivate(this, AbstractPotion.class, "liquidImg", new Texture("B1ModResources/images/potion/none.png"));
         ReflectionHacks.setPrivate(this, AbstractPotion.class, "hybridImg", new Texture("B1ModResources/images/potion/none.png"));
         ReflectionHacks.setPrivate(this, AbstractPotion.class, "spotsImg", new Texture("B1ModResources/images/potion/none.png"));
         this.isThrown = false;
         this.tips.add(new PowerTip(this.name, this.description));
     }
     public void use(AbstractCreature target)
     {
         if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
         {
             addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player,10));
         }
         else {
             AbstractDungeon.player.damage(new DamageInfo(null,10, DamageInfo.DamageType.HP_LOSS));
         }
     }
     public boolean canUse()
     {
         if (AbstractDungeon.actionManager.turnHasEnded &&
                 (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
             return false;
         }
         return (AbstractDungeon.getCurrRoom()).event == null || !((AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain);
     }
     public AbstractPotion makeCopy() {
         return new JinDan();
     }
     public int getPotency(int potency) {
         return 0;
     }

 }

