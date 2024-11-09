 package modcore.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.TalkAction;
 import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.potions.AbstractPotion;

 import java.util.Objects;

 public class ExhaustAndObtainPotionAction extends AbstractGameAction
 {
     public static int numExhausted;
     public ExhaustAndObtainPotionAction(int amount)
     {
         this.p = AbstractDungeon.player;
         this.amount = amount;
         this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
         this.actionType = AbstractGameAction.ActionType.EXHAUST;
     }
     private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
     public static final String[] TEXT = uiStrings.TEXT;
     private final AbstractPlayer p;
     public void update()
     {
         if (this.duration == this.startDuration)
         {
             if (this.p.hand.isEmpty())
             {
                 this.isDone = true;
                 return;
             }
             numExhausted = this.amount;
             AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true);
             tickDuration();
             return;
         }
         if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
         {
             for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
             {
                 if(Objects.equals(c.name, "大圣重棍")|| (Objects.equals(c.name, "小呱呱")&&!c.canUpgrade()))
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.RARE, true)));
                     addToBot(new TalkAction(AbstractDungeon.player, "天地万物皆炉鼎，唯有上品得我心。", 1.2F, 3F));
                 }
                 else if (c.rarity == AbstractCard.CardRarity.RARE)
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.RARE, true)));
                     addToBot(new TalkAction(AbstractDungeon.player, "天地万物皆炉鼎，唯有上品得我心。", 1.2F, 3F));
                 }
                 else if (c.rarity == AbstractCard.CardRarity.BASIC)
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true)));
                 }
                 else if (c.rarity == AbstractCard.CardRarity.COMMON)
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true)));
                 }
                 else if (c.rarity == AbstractCard.CardRarity.UNCOMMON)
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.UNCOMMON, true)));
                 }
                 else
                 {
                     addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true)));
                 }
                 this.p.hand.moveToExhaustPile(c);
             }
             CardCrawlGame.dungeon.checkForPactAchievement();
             AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
         }
         tickDuration();
     }
 }

