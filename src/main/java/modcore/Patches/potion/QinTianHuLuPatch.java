package modcore.Patches.potion;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import modcore.potion.QingTianHuLu;

import java.util.Objects;

@SpirePatch(clz = PotionPopUp.class, method = "updateInput")
public  class QinTianHuLuPatch
{
    @SpireInsertPatch(rloc = 24)
    public static SpireReturn<Void> Insert(PotionPopUp __instance,AbstractPotion ___potion)
    {
        if (Objects.equals(___potion.ID, QingTianHuLu.ID))
        {
            System.out.println("喝到青田葫芦了 ");
            __instance.close();
            return SpireReturn.Return();
        }
        else
        {
            return SpireReturn.Continue();
        }
    }
}