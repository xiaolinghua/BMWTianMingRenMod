 package modcore.shows;

 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.math.Interpolation;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.helpers.MathHelper;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.orbs.AbstractOrb;
 import com.megacrit.cardcrawl.powers.AbstractPower;








 public class showsOrb extends AbstractOrb
         {
       public int exchange;

       public showsOrb(int exchange) {
             this.exchange = exchange;
           }


       public void updateDescription() {}

       public void onEvoke() {}

       public void setSlot(int maxOrbs)
       {
           if (maxOrbs==1)
           {
               this.tX = AbstractDungeon.player.drawX + 140.0F;
           }
           else if (maxOrbs==2)
           {

               this.tX = AbstractDungeon.player.drawX + 180.0F;
           }else if (maxOrbs==3)
           {
               this.tX = AbstractDungeon.player.drawX + 200.0F;
           }
           else if (maxOrbs==4)
           {
               this.tX = AbstractDungeon.player.drawX + 185.0F;
           }
           this.tY = AbstractDungeon.player.drawY - 40.0F + 70.0F * maxOrbs;
           this.hb.move(this.tX, this.tY);
       }


       public void applyFocus() {
             AbstractPower power = AbstractDungeon.player.getPower("Focus");
             if (power != null && !this.ID.equals("Plasma")) {

                   this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
                   this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount);
                 }
             else {

                   this.passiveAmount = this.basePassiveAmount;
                   this.evokeAmount = this.baseEvokeAmount;
                 }
           }


       public void update() {
             this.hb.update();
             updateAnimation();
             if (this.hb.hovered)
             {
                   TipHelper.renderGenericTip(this.tX + 96.0F * Settings.scale, this.tY + 64.0F * Settings.scale, this.name, this.description);
             }
             this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
           }


       public void updateAnimation()
       {
             this.bobEffect.update();
             this.cX = MathHelper.orbLerpSnap(this.cX, AbstractDungeon.player.animX + this.tX);
             this.cY = MathHelper.orbLerpSnap(this.cY, AbstractDungeon.player.animY + this.tY);
             if (this.channelAnimTimer != 0.0F) {

                   this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
                   if (this.channelAnimTimer < 0.0F) {
                         this.channelAnimTimer = 0.0F;
                       }
                 }
             this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
             this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
       }


       public void render(SpriteBatch var1) {}

       protected void renderText(SpriteBatch sb) {
             if (!(this instanceof MyEmptyOrbSlot)) {
                   if (this.showEvokeValue) {
                         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
                       } else {
                         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
                       }
                 }
           }


       public void showEvokeValue() {
             this.showEvokeValue = true;
             this.fontScale = 1.5F;
           }


       public void hideEvokeValues() {
             this.showEvokeValue = false;
           }


       public void playChannelSFX() {

       }

       public AbstractOrb makeCopy() {
             return this;
           }
     }


