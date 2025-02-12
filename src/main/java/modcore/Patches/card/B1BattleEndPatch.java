 package modcore.Patches.card;

 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.potions.AbstractPotion;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import modcore.potion.QingTianHuLu;


 @SpirePatch(clz = AbstractRoom.class, method = "endBattle")
 public class B1BattleEndPatch
 {
     //private static SfxUtil sfxUtil = SfxUtil.createInstance(new String[] { "B1:TongGuan"}, true, 1.0F, 0F, 1F);

     public static void Prefix(AbstractRoom __instance)
       {
            //sfxUtil.playSFX();
           AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "blackmythwukong:FengYunZhuanPower"));
            if (AbstractDungeon.player.hasPotion(QingTianHuLu.ID))
            {
                for (AbstractPotion p : AbstractDungeon.player.potions)
                {
                    if (p.ID.equals(QingTianHuLu.ID))
                    {
                        p.flash();
                        p.use(AbstractDungeon.player);
                    }
                }
            }
       }
 }


