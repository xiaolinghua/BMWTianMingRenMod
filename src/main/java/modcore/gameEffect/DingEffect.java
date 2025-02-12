package modcore.gameEffect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DingEffect extends AbstractGameEffect
{
    private float yOffset;
    private static final float FLOAT_AMPLITUDE = 9f;
    private static final float FLOAT_SPEED = 2.0f;
    private final Texture img;
    private final float x;
    private final float y;
    public DingEffect(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.yOffset = 0.0f;
        this.duration = 3f;
        img=ImageMaster.loadImage("B1ModResources/images/effect/Ding128.png");
    }
    @Override
    public void update()
    {
        this.yOffset = FLOAT_AMPLITUDE * (float)Math.sin(FLOAT_SPEED * this.duration);
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }
    @Override
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(img, this.x-64* Settings.scale, this.y + yOffset);
    }

    @Override
    public void dispose()
    {

    }
}
