package modcore.Patches.render;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import modcore.powers.GunShiPower;

public class GunShiPatch
{
    @SpirePatch(clz = AbstractCreature.class, method = "renderPowerIcons")
    public static class GunShiPatchRenderPowerIcons
    {
        public static ExprEditor Instrument() {
            return new ExprEditor()
            {
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getMethodName().equals("renderIcons") || m.getMethodName().equals("renderAmount")) {
                        m.replace("if (p instanceof " + GunShiPower.class.getName() + ") {offset -= POWER_ICON_PADDING_X;} else {$proceed($$);}");
                    }
                }
            };
        }
    }
}
