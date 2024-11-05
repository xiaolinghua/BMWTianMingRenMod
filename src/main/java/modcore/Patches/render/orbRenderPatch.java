 package modcore.Patches.render;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import modcore.B1Mod;
 import modcore.shows.GunShiUI;
 import modcore.shows.showsOrb;

 public class orbRenderPatch
 {
       public static class OfferSlotPatches
               {
             @SpirePatch(clz = EnergyPanel.class, method = "render", paramtypez = {SpriteBatch.class})
             public static class RenderPatch
             {
                   @SpirePrefixPatch
                   public static void Prefix(EnergyPanel inst, SpriteBatch sb)
                   {
                         for (showsOrb s : B1Mod.MyOrblist)
                         {
                               s.render(sb);
                         }
                       GunShiUI gunShiUI=new GunShiUI();
                       gunShiUI.render(sb);
                   }
             }
             @SpirePatch(clz = EnergyPanel.class, method = "update")
             public static class UpdatePatch
                     {
                   @SpirePrefixPatch
                   public static void Prefix(EnergyPanel inst) {
                         for (showsOrb s : B1Mod.MyOrblist)
                         {
                               s.update();
                         }
                       GunShiUI gunShiUI=new GunShiUI();
                       gunShiUI.update(1);
                       }
                 }
           }
 }



