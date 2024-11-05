//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modcore.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

public class DingShenPower extends AbstractPower {
    public static final String POWER_ID = "blackmythwukong:DingShenPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private EnemyMoveInfo move;

    public DingShenPower(AbstractMonster owner) {
        this(owner, 1);
    }

    public DingShenPower(AbstractMonster owner, int amount) {
        this.name = NAME;
        this.ID = "blackmythwukong:DingShenPower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.updateDescription();
        String path128 = "B1ModResources/images/powers/Ding84.png";
        String path48 = "B1ModResources/images/powers/Ding32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount;
        if (this.amount == 1) {
            this.description = this.description + DESCRIPTIONS[1];
        } else {
            this.description = this.description + DESCRIPTIONS[2];
        }

    }

    public void atEndOfRound() {
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
        }

    }

    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (DingShenPower.this.owner instanceof AbstractMonster) {
                    DingShenPower.this.moveByte = ((AbstractMonster)DingShenPower.this.owner).nextMove;
                    DingShenPower.this.moveIntent = ((AbstractMonster)DingShenPower.this.owner).intent;

                    try {
                        Field f = AbstractMonster.class.getDeclaredField("move");
                        f.setAccessible(true);
                        DingShenPower.this.move = (EnemyMoveInfo)f.get(DingShenPower.this.owner);
                        EnemyMoveInfo stunMove = new EnemyMoveInfo(DingShenPower.this.moveByte,AbstractMonster.Intent.STUN, -1, 0, false);
                        f.set(DingShenPower.this.owner, stunMove);
                        System.out.println("-----------设置怪物意图为定身 ");
                        ((AbstractMonster)DingShenPower.this.owner).createIntent();
                    } catch (NoSuchFieldException | IllegalAccessException var3) {
                        var3.printStackTrace();
                    }
                }

                this.isDone = true;
            }
        });
    }

    public void onRemove() {
        if (this.owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)this.owner;
            if (this.move != null) {
                m.setMove(this.moveByte, this.moveIntent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
            } else {
                m.setMove(this.moveByte, this.moveIntent);
            }

            m.createIntent();
            m.applyPowers();
        }

    }
}
