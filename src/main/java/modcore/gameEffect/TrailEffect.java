package modcore.gameEffect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Pool;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TrailEffect extends AbstractGameEffect implements Pool.Poolable {
    private static TextureAtlas.AtlasRegion img = null;
    private static final float SCALE_MULTI = Settings.scale * 50.0F;
    private static final int W_DIV_2 = 25;
    private final float startX;
    private final float startY;
    private final float endX;
    private final float endY;
    private float currentX;
    private float currentY;
    private final float controlX;
    private final float controlY;
    public TrailEffect(float startX, float startY, float endX, float endY) {
        img = ImageMaster.vfxAtlas.findRegion("combat/blurDot2");
        this.duration = 0.6F;
        this.startingDuration = 0.6F;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.currentX = startX;
        this.currentY = startY;
        this.controlX = (startX + endX) / 2 + 600; // 控制点X坐标，可以根据需要调整
        this.controlY = (startY + endY) / 2; // 控制点Y坐标，可以根据需要调整
        this.color = new Color(16F, 13F, 231F, 0.85F); // 蓝色，完全不透明
        this.renderBehind = false;
    }
    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime(); // 减少持续时间
        float progress = 1.0F - (this.duration / this.startingDuration); // 计算进度
        //this.currentX = Interpolation.pow2Out.apply(this.startX, this.endX, progress); // 先慢后快插值计算当前X坐标
        //this.currentY = Interpolation.pow2Out.apply(this.startY, this.endY, progress); // 先慢后快插值计算当前Y坐标
        this.currentX = (1 - progress) * (1 - progress) * this.startX + 2 * (1 - progress) * progress * this.controlX + progress * progress * this.endX;
        this.currentY = (1 - progress) * (1 - progress) * this.startY + 2 * (1 - progress) * progress * this.controlY + progress * progress * this.endY;
        if (this.duration < 0.2F)
        {
            this.scale = 0.2F * SCALE_MULTI;
        }
        else
        {
            this.scale = this.duration* SCALE_MULTI;
        }
        if (this.duration < 0.0F)
        {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.currentX - W_DIV_2, this.currentY - W_DIV_2, 6.0F, 6.0F, 12.0F, 12.0F, this.scale, this.scale, 0.0F);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}

    public void reset() {}
}