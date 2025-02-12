package modcore.Patches;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import modcore.cardmods.FrozenCardMod;

public abstract class AbstractB1Card extends CustomCard
{
    public static final Color LightgoldColor = new Color(1.0f, 252F / 255F, 60F / 255F, 1.0f);
    public int attackCount = 1;
    public AbstractB1Card(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.WHITE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, Color.BROWN);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.WHITE);
        ReflectionHacks.setPrivate(this, AbstractCard.class, "goldColor", LightgoldColor.cpy());
    }

    static
    {
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo()
        {
            @Override
            public boolean test(AbstractCard card)
            {
                // 判断是否为卡
                return CardModifierManager.hasModifier(card, FrozenCardMod.ID);
            }

            @Override
            public Color getColor(AbstractCard card)
            {
                // 返回白色
                return Color.WHITE.cpy();
            }

            @Override
            public String glowID()
            {
                // 返回唯一的 ID
                return "blackmythwukong:WhiteGlow";
            }
        });
    }
}