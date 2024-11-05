package modcore.Patches.potion;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import modcore.potion.JinDan;

@SpirePatch(cls = "com.megacrit.cardcrawl.potions.AbstractPotion", method = "canDiscard")
     public  class CannotThrowJinDanPatch
    {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn Insert(AbstractPotion __instance)
        {
            if (__instance instanceof JinDan)
            {
                return SpireReturn.Return(Boolean.valueOf(false));
            }
            return SpireReturn.Return(Boolean.valueOf(((AbstractDungeon.getCurrRoom()).event == null || !((AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain))));
        }
    }
