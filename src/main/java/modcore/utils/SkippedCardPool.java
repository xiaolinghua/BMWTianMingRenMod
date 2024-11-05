package modcore.utils;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class SkippedCardPool implements CustomSavable<SkippedCardPool.ModSaveData>
{
    public static LinkedList<AbstractCard> skippedCards= new LinkedList<>();
    public static void AddSkippedCard(ArrayList<AbstractCard> cards)
    {
        // 先添加所有新卡片到一个临时列表中
        LinkedList<AbstractCard> tempCards = new LinkedList<>(cards);

        // 计算需要删除的老卡片的数量，以保持总数不超过20
        int cardsToRemove = Math.max(0, skippedCards.size() + tempCards.size() - 20);

        // 如果需要删除卡片，则从列表头部删除最老的卡片
        for (int i = 0; i < cardsToRemove; i++)
        {
            skippedCards.removeFirst();
        }
        // 添加新卡片到列表尾部
        skippedCards.addAll(tempCards);
    }
    public static void AddSkippedCard(AbstractCard cards)
    {
        skippedCards.add(cards);
    }
    public static void RemoveSkippedCard(AbstractCard cards)
    {
        skippedCards.remove(cards);
    }
    public static void ToString()
    {
        for (AbstractCard c:skippedCards)
        {
            System.out.println(c.cardID);
        }
    }
    public static AbstractCard GetRandomCardFromPool()
    {
        if (skippedCards.isEmpty())
        {
            return new Madness();
        }
        else
        {
            return skippedCards.get(cardRandomRng.random(skippedCards.size() - 1));
        }
    }
    @Override
    public ModSaveData onSave()
    {
        return new ModSaveData(skippedCards);
    }

    @Override
    public void onLoad(ModSaveData modSaveData)
    {
        skippedCards=modSaveData.skippedCards;
    }

    public static class ModSaveData {

        public LinkedList<AbstractCard> skippedCards;

        public ModSaveData(LinkedList<AbstractCard> skippedCards) {
            this.skippedCards=skippedCards;
        }
    }
}
