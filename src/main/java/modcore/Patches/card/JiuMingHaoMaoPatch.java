package modcore.Patches.card;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import modcore.cards.power.JiuMingHaoMao;

@SpirePatch(clz = AbstractPlayer.class, method = "damage")
public class JiuMingHaoMaoPatch {
    @SpireInsertPatch(rloc = 147)
    public static SpireReturn<Void> Insert(AbstractPlayer __instance)
    {
        System.out.println("救命毫毛Patch启动");
        for (AbstractCard c:AbstractDungeon.player.hand.group)
        {
            if ("blackmythwukong:JiuMingHaoMao".equals(c.cardID))
            {
                AbstractDungeon.player.currentHealth = 0;
                int healAmt = AbstractDungeon.player.maxHealth / 2;
                if (healAmt < 1)
                {
                    healAmt = 1;
                }
                AbstractDungeon.player.heal(healAmt, true);
                CardCrawlGame.sound.playV("B1:jiuMingHaoMao",1);
                JiuMingHaoMao.seedialog=false;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, null, c.energyOnUse, true, true), true);
                return SpireReturn.Return();
            }
        }
        return SpireReturn.Continue();
    }
}
