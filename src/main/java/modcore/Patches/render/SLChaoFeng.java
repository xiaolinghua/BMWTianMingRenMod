
package modcore.Patches.render;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import modcore.B1Mod;

import java.util.Random;

@SpirePatch(clz = MonsterGroup.class, method = "usePreBattleAction")
public class SLChaoFeng {

    @SpireInsertPatch(rloc = 6,localvars={"m"})
    public static void Insert(MonsterGroup __instance, AbstractMonster m)
    {
        Random rand = new Random();
        if (B1Mod.SL&&rand.nextBoolean())
        {
            final String[] dialog={"还敢回来?何苦再来 ",
                    "现下投降犹时未晚，做条忠犬 总好过做只死猴子",
                    "好 好 好 屡战屡败,还敢再来",
                    "你这猴子,命还挺硬 ",
                    "活着不好吗?还来找死 ",
                    "无冤无仇,既然败了,何苦回来 ",
                    "逃命去吧 ",
                    "还强撑什么？",
                    "哈哈哈，又来只猴子 ",
                    "丑态百出！",
                    "区区猴子，不在话下 ",
                    "不知死活 ！",
                    "不逃,还回来送死?成全你吧 ",
                    "朽木,不可雕 ",
                    "刚杀了一个,怎么又来一个?",
                    "好好歇歇吧",
                    "......... ",
                    "什么动静?有只猴子闯进来了! ",
                    "不好,有人来了 ",
                    "不好,这猴子又来了 ",
                    "哈弥陀...... 你敢打扰爷爷念经 ",
                    "哇呀呀呀",
                    "咦",
                    "好一个回马枪 ",
                    "不好啦，有个毛脸和尚闯进来啦！",
                    "不好啦，外面有个毛脸雷公嘴的和尚闯进来了！"
            };
            B1Mod.SL=false;
            System.out.println("------------------玩家进战斗了");
            System.out.println("------------------开启嘲讽 ");
            AbstractDungeon.actionManager.addToBottom(new TalkAction(m,  dialog[rand.nextInt(dialog.length)], 1.2F, 2F));
        }
    }
}
