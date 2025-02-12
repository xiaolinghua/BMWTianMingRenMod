package modcore.gameEffect;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HanDongEffect extends AbstractGameEffect {
    private final AbstractCreature owner;
    private final float x;
    private final float y;
    private final Texture iceTexture;

    public HanDongEffect(AbstractCreature owner) {
        this.owner = owner;
        this.x = owner.hb.cX;
        this.y = owner.hb.cY;
        this.iceTexture = ImageMaster.loadImage("B1ModResources/images/effect/bingdong256.png");
        this.duration = 1.5F; // Duration of the effect
    }

    @Override
    public void update() {
        if (this.duration < 0.0F)
        {
            this.isDone = true;
        }
        this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1.0F, 1.0F, 1.0F, 1);
        sb.draw(this.iceTexture, x - (owner.hb_w / 1.8F), y - (owner.hb_h / 2.0F), owner.hb_w*1.3f, owner.hb_h*1.3f);
    }

    @Override
    public void dispose() {
        // Dispose resources if needed
    }
}