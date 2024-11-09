package modcore.shows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;


public class MyEmptyOrbSlot extends showsOrb
 {
       private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("MyEmptyOrbSlot");
       public static final String NAME = orbString.NAME;
       public static final String[] DESCRIPTION = orbString.DESCRIPTION;
       public static int excharged = 0;
     private final Texture img0 = ImageMaster.loadImage("B1ModResources/images/orb/gunshidian0.png");
     private final Texture img1 = ImageMaster.loadImage("B1ModResources/images/orb/gunshidian1.png");
     private final Texture img2 = ImageMaster.loadImage("B1ModResources/images/orb/gunshidian2.png");
       private final Texture img3 = ImageMaster.loadImage("B1ModResources/images/orb/gunshidian3.png");
        private float vfxTimer = 1.0F;

     public MyEmptyOrbSlot()
     {
             super(excharged);
             this.angle = MathUtils.random(360.0F);
             this.name = orbString.NAME;
             this.evokeAmount = 0;
             this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
             this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
             updateDescription();
     }


       public void updateDescription()
       {
           if (AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
           {
               this.description = DESCRIPTION[0] + AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount + DESCRIPTION[1]+DESCRIPTION[3]+AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount/3+DESCRIPTION[4];
           }
           else
           {
               this.description = DESCRIPTION[2];
           }
       }


       public void onEvoke() {}

       public void updateAnimation()
       {
           super.updateAnimation();
           this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
           this.vfxTimer -= Gdx.graphics.getDeltaTime();
           if (this.vfxTimer < 0.0F) {
               AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
               if (MathUtils.randomBoolean()) {
                   AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
               }

               this.vfxTimer = MathUtils.random(0.15F, 0.8F);
          }
       }


       public void render(SpriteBatch sb) {

             if (this.exchange==0)
             {
                   sb.setColor(this.c);
                   sb.draw(this.img0, this.cX - 48.0F - this.bobEffect.y / 8.0F, this.cY - 48.0F + this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
                   sb.draw(this.img0, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0f, 0, 0, 96, 96, false, false);
                   renderText(sb);
                   this.hb.render(sb);
             }
             else if (this.exchange==1)
             {
                 sb.setColor(this.c);
                 sb.draw(this.img1, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 //sb.setBlendFunction(770, 1);
                 sb.draw(this.img1, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 renderText(sb);
                 this.hb.render(sb);
             }
             else if (this.exchange==2)
             {
                 sb.setColor(this.c);
                 sb.draw(this.img2, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 //sb.setBlendFunction(770, 1);
                 sb.draw(this.img2, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 renderText(sb);
                 this.hb.render(sb);
             }
             else if (this.exchange==3)
             {
                 sb.setColor(this.c);
                 sb.draw(this.img3, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 //sb.setBlendFunction(770, 1);
                 sb.draw(this.img3, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0F, 0, 0, 96, 96, false, false);
                 renderText(sb);
                 this.hb.render(sb);
             }
             else
             {
                 return;
             }
           }
     public void triggerEvokeAnimation()
     {
         CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
         AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
     }

       public AbstractOrb makeCopy() {
             return new MyEmptyOrbSlot();
           }

       public void playChannelSFX() {
           CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
       }
     }


