package modcore.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import modcore.B1Mod;
import modcore.shows.showsOrb;

// 假设CustomOrb是B1Mod.MyOrblist中元素的类型，并且具有exchange属性和updateDescription()方法
// public class CustomOrb {
//     public int exchange;
//     public void updateDescription() {
//         // 更新描述的代码
//     }
// }

public class OrbCleanAndAddAction extends AbstractGameAction {
    // 假设B1Mod.MyOrblist是一个ArrayList<CustomOrb>
    private final int amountToAdd;
    private int temporaryOverflow = 0; // 用于临时存储每次迭代中的溢出值

    public OrbCleanAndAddAction(int amountToAdd) {
        this.amountToAdd = amountToAdd;
    }

    @Override
    public void update() {
        System.out.println("这次接收到的棍shi是 "+this.amountToAdd);
        // 清零所有orbs的exchange值
        for (showsOrb orb : B1Mod.MyOrblist) {
            orb.exchange = 0;
        }

        int effectiveAmountToAdd = amountToAdd + temporaryOverflow; // 加上之前迭代的剩余溢出值（但在这个动作开始时应该是0）

        // 重新计算每个orb应该接收的值
        for (int i = 0; i < B1Mod.MyOrblist.size(); i++) {
            int maxCapacity = 3; // 每个orb的最大容量
            int allocatableAmount = Math.min(effectiveAmountToAdd, maxCapacity); // 因为已经清零，所以直接计算最大可分配值

            // 更新orb的exchange值
            B1Mod.MyOrblist.get(i).exchange += allocatableAmount;
            B1Mod.MyOrblist.get(i).updateDescription(); // 更新描述

            // 更新剩余要添加的值和临时溢出值
            effectiveAmountToAdd -= allocatableAmount;
            if (i < B1Mod.MyOrblist.size() - 1 && effectiveAmountToAdd > 0) {
                // 如果不是最后一个orb且还有剩余值要添加，但由于已经清零，这里实际上不会进入
                // 因为每个orb都会尽可能多地接收值，直到effectiveAmountToAdd被完全分配或orbs被遍历完
                // 但为了保持逻辑的一致性（尽管在这个特定情况下是多余的），我们仍然保留这个判断
                temporaryOverflow = effectiveAmountToAdd;
            } else {
                // 如果是最后一个orb或没有剩余值要添加，则重置溢出值
                // 注意：在这个动作开始时，temporaryOverflow应该是0，所以这里实际上不会改变它的值
                // 除非amountToAdd非常大，以至于即使所有orbs都清零并接收最大值后仍然有剩余
                temporaryOverflow = 0;
            }

            // 由于我们已经清零了所有orbs，并且每个orb都尽可能多地接收了值
            // 所以effectiveAmountToAdd在遍历完所有orbs后应该为0（或者如果amountToAdd非常大则为负数，但这种情况我们忽略）
            // 因此，下面的else分支实际上在这个特定的动作中是不会执行的
            // 但为了代码的完整性和可读性，我们仍然保留它
        }

        // 在这个动作的上下文中，temporaryOverflow最终应该是0
        // 因为我们已经假设了每个orb都能接收尽可能多的值，并且忽略了无法分配的值

        this.isDone = true; // 标记动作为已完成
    }
}