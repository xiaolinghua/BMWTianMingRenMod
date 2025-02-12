package modcore.Patches.render;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import modcore.B1Mod;
import modcore.gameEffect.DingEffect;

import java.util.ArrayList;

public class DingPatch
{
    @SpirePatch(clz = AbstractMonster.class, method = "updateIntentVFX")
    public static class updateDingVFXPatch
    {
        @SpireInsertPatch(rloc = 33)
        public static void Insert(AbstractMonster __instance, @ByRef float[] ___intentParticleTimer)
        {

            if (__instance.intent == B1Mod.DING)
            {
                ___intentParticleTimer[0] -= Gdx.graphics.getDeltaTime();
                //System.out.println("-----------------intentParticleTimer:"+___intentParticleTimer[0]);
                if (___intentParticleTimer[0] < 0.0F)
                {
                    ___intentParticleTimer[0]=3F;
                    System.out.println("-----------------已执行 updateIntentVFX Ding");
                    ArrayList<AbstractGameEffect> intentVfx = ReflectionHacks.getPrivate(__instance, AbstractMonster.class, "intentVfx");
                    intentVfx.add(new DingEffect(__instance.intentHb.cX, __instance.intentHb.cY));
                }
            }
        }
    }
    @SpirePatch(clz = AbstractMonster.class, method = "updateIntentTip")
    public static class updateDingTipPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractMonster __instance)
        {
            if (__instance.intent == B1Mod.DING)
            {
                PowerTip powerTip=new PowerTip("Ding!","Ding!",ImageMaster.loadImage("B1ModResources/images/powers/Ding84.png"));
                ReflectionHacks.setPrivate(__instance, AbstractMonster.class, "intentTip", powerTip);
                return SpireReturn.Return();
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }
    @SpirePatch(clz = AbstractMonster.class, method = "getIntentImg")
    public static class getDingImgPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Texture> Prefix(AbstractMonster __instance)
        {
            if (__instance.intent == B1Mod.DING)
            {
                return SpireReturn.Return(null);
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }
}
