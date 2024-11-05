package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import modcore.B1Mod;

// 假设B1Mod.MyOrblist是一个ArrayList<CustomOrb>，并且CustomOrb类有exchange属性和updateDescription方法
public class OrbAddAmountAction extends AbstractGameAction {
    private final int amountToAdd;

    public OrbAddAmountAction(int amountToAdd) {
        this.amountToAdd = amountToAdd;
    }

    @Override
    public void update() {
        int effectiveAmountToAdd = amountToAdd; // 使用实际的amountToAdd值
        // 处理负数情况，从最后一个orb开始减少
        if (amountToAdd < 0) {
            for (int i = B1Mod.MyOrblist.size() - 1; i >= 0 && effectiveAmountToAdd < 0; i--) {
                int maxReduce = B1Mod.MyOrblist.get(i).exchange; // 最多可以减少到0
                int reduceAmount = Math.max(effectiveAmountToAdd, -maxReduce); // 计算当前orb能减少的最大值

                // 更新orb的exchange值
                B1Mod.MyOrblist.get(i).exchange += reduceAmount; // 注意：这里reduceAmount是负数，所以实际上是减少


                // 更新剩余要减少的值
                effectiveAmountToAdd -= reduceAmount;
            }
        } else {
            // 处理正数情况，从第一个orb开始增加
            for (int i = 0; i < B1Mod.MyOrblist.size() && effectiveAmountToAdd > 0; i++) {
                int currentExchange = B1Mod.MyOrblist.get(i).exchange;
                int maxCapacity = 3; // 每个orb的最大容量
                int allocatableAmount = Math.min(effectiveAmountToAdd, maxCapacity - currentExchange); // 计算当前orb能接收的最大值

                // 更新orb的exchange值
                B1Mod.MyOrblist.get(i).exchange += allocatableAmount;


                // 更新剩余要添加的值
                effectiveAmountToAdd -= allocatableAmount;
            }
        }
        for (int i = 0; i < B1Mod.MyOrblist.size(); i++)
        {
            B1Mod.MyOrblist.get(i).updateDescription(); // 更新描述
        }
        // 标记动作为已完成
        this.isDone = true;
    }
}