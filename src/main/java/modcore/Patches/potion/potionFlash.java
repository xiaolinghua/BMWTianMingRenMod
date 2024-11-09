package modcore.Patches.potion;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.FlashPotionEffect;

public class potionFlash
{
    @SpirePatch(clz = FlashPotionEffect.class, method = "<ctor>")
    public static class ObtainPotionEffectPatch
    {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(FlashPotionEffect _instance, AbstractPotion potion)
        {
            if (potion.ID.contains("blackmythwukong"))
            {
                ReflectionHacks.setPrivate(_instance, FlashPotionEffect.class, "containerImg", ImageMaster.loadImage("B1ModResources/images/potion/"+potion.ID.substring("blackmythwukong:".length())+".png"));
                ReflectionHacks.setPrivate(_instance, FlashPotionEffect.class, "liquidImg", ImageMaster.loadImage("B1ModResources/images/potion/"+potion.ID.substring("blackmythwukong:".length())+".png"));
                ReflectionHacks.setPrivate(_instance, FlashPotionEffect.class, "hybridImg",ImageMaster.loadImage("B1ModResources/images/potion/"+potion.ID.substring("blackmythwukong:".length())+".png"));
                ReflectionHacks.setPrivate(_instance, FlashPotionEffect.class, "spotsImg", ImageMaster.loadImage("B1ModResources/images/potion/"+potion.ID.substring("blackmythwukong:".length())+".png"));
            }
            return SpireReturn.Continue();
        }
    }
}
