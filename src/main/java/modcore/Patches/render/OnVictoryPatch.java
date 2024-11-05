 package modcore.Patches.render;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import modcore.B1Mod;




 public class OnVictoryPatch
        {
       @SpirePatch(clz = AbstractPlayer.class, method = "onVictory")
       public static class VictoryPatch
               {
             @SpirePrefixPatch
             public static void Patch()
             {
                   B1Mod.MyOrblist.clear();
             }
           }
     }


