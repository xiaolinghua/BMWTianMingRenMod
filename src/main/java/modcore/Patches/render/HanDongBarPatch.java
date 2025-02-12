package modcore.Patches.render;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modcore.powers.HanDongPower;

import static com.megacrit.cardcrawl.helpers.ImageMaster.loadImage;

@SpirePatch(clz = AbstractCreature.class, method = "renderHealth", paramtypez = {SpriteBatch.class})
public class HanDongBarPatch
{
    static float HEALTH_BAR_OFFSET_Y = -28F * Settings.scale;//-28.0F * Settings.scale;
    static float HEALTH_BAR_HEIGHT = 20.0F * Settings.scale;
    static float HEALTH_BG_OFFSET_X = 31.0F * Settings.scale;
    static Color blockOutlineColor = new Color(0.8F, 0.8F, 0.8F, 0.8F);
    static float HEALTH_TEXT_OFFSET_Y = 6.0F * Settings.scale;
    static float HB_Y_OFFSET_DIST = 12.0F * Settings.scale;
    static float hbYOffset = HB_Y_OFFSET_DIST * 2.0F;
    @SpirePostfixPatch
    public static void Postfix(AbstractCreature _inst, SpriteBatch sb)
    {
        if (_inst instanceof AbstractMonster && _inst.hasPower(HanDongPower.POWER_ID))
        {
            HanDongPower hanDongPower = (HanDongPower) _inst.getPower(HanDongPower.POWER_ID);
            float x = _inst.hb.cX - _inst.hb.width / 2.0F;
            float y = _inst.hb.cY - _inst.hb.height / 2.0F+hbYOffset;
            renderToughnessBar(sb, (AbstractMonster) _inst, x, y, hanDongPower.amount / 5.0F > 1 ? 1.0F : hanDongPower.amount / 5.0F);
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont,
                    hanDongPower.amount + "/" + 10,
                    _inst.hb.cX, y + HEALTH_BAR_OFFSET_Y + HEALTH_TEXT_OFFSET_Y + 5.0F * Settings.scale,
                    new Color(0.8F, 0.8F, 0.8F, 1.0F));

        }
    }

   private static void renderToughnessBar(SpriteBatch sb, AbstractMonster m, float x, float y,float ratio) {
    try
    {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
        // 绘制健康条的阴影部分
        sb.draw(loadImage("B1ModResources/images/ui/leftBG.png"), x - HEALTH_BAR_HEIGHT, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.draw(loadImage("B1ModResources/images/ui/bodyBG.png"), x, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, m.hb.width, HEALTH_BAR_HEIGHT);
        sb.draw(loadImage("B1ModResources/images/ui/rightBG.png"), x + m.hb.width, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.setColor(blockOutlineColor);
        sb.draw(loadImage("B1ModResources/images/ui/Frozen.png"), x + m.hb.width + 24, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale-24*Settings.scale, 64*Settings.scale, 64*Settings.scale);
        // 绘制健康条的实际部分
        sb.draw(loadImage("B1ModResources/images/ui/left7.png"), x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.draw(loadImage("B1ModResources/images/ui/body7.png"), x, y + HEALTH_BAR_OFFSET_Y, ratio*m.hb.width, HEALTH_BAR_HEIGHT);
        if (ratio == 1.0F)
        {
            sb.draw(loadImage("B1ModResources/images/ui/right7.png"), x + m.hb.width, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        }
    } catch (Exception exception) {
        System.out.println("HanDongBarPatch.renderToughnessBar: " + exception.getMessage());
    }
}
}
