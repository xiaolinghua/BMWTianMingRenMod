package modcore.Patches;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;

 public abstract class AbstractB1Card extends CustomCard
 {
       public AbstractB1Card(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target)
       {
             super(id, name, img, cost, rawDescription, type, color, rarity, target);
             FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.WHITE);
             FlavorText.AbstractCardFlavorFields.boxColor.set(this, Color.BROWN);
             FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.WHITE);
       }
 }