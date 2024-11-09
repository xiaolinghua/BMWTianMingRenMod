package modcore.Patches.render;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.DeathScreen;

import java.util.ArrayList;

@SpirePatch(clz = DeathScreen.class, method = "getDeathText")
public class DeathPatch {
    @SpireInsertPatch(rloc = 17,localvars={"list"})
    public static void Insert(DeathScreen __instance, ArrayList<String> list)
    {
        System.out.println("死亡嘲讽Patch启动");
        list.add("六丁六甲，从不吃素");
        list.add("佛法无边，不度无缘 ");
        list.add("是我多虑了，只是长得像了点 ");
        list.add("朽木不可雕也 ");
        list.add("天命人，不过如此 ");
        list.add("你的血，一定很新鲜罢 ");
        list.add("年轻，还是太年轻喽 ");
        list.add("你啊，死的不冤 ");
        list.add("为了天，舍了命，值吗？ ");
        list.add("志大才疏,孽根祸胎，该死 ");
        list.add("又想赌，那你已经输了 ");
        list.add("信天命，却不信自己，这便是下场 ");
        list.add("守旧不变，如何走远 ");
        list.add("现在，好好歇一下罢 ");
        list.add("你，终究不是他 ");
        list.add("你的身段像个丑角 ");
        list.add("下一世，再相会......  ");
        list.add("来世，莫在欺凌弱小 ");
        list.add("以杀止杀，以暴制暴 ");
        list.add("活着，真无趣啊 ");
        list.add("打不过，就得跪下 ");
        list.add("打不过，也不能跪下 ");
        list.add("可怜，怎的又死了 ");
        list.add("一样的石头，竟这般不经打 ");
        list.add("切，原来就是只普通猴子 ");
        list.add("心随意，果自圆 ");
        list.add("小妖打的费劲，大怪更是仓惶。如此笨拙痴蠢，如何见得真章？  ");
        list.add("天命人，不过如此  ");
        list.add("急之易不暇，缓之或自明  ");
        list.add("输赢太快,可就没趣了 ");
        list.add("你既不服管，那就伏诛吧 ");
        list.add("天地不仁，天命不公。你一路走来，看明白了吗？ ");
        list.add("躲不过这刀，还不如 再来过 ");
        list.add("这般身慢，那天命 也该休了 ");
        list.add("前程前程，多少冤魂 ");
        list.add("欲学前人 当坚苦志 ");
    }
}
