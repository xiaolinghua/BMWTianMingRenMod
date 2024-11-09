package modcore;

import basemod.interfaces.StartGameSubscriber;
import com.megacrit.cardcrawl.core.CardCrawlGame;

 public interface SaveLoadSubscriber
           extends StartGameSubscriber
         {
       default void receiveStartGame() {
             if (CardCrawlGame.loadingSave)
                   onLoadGame();
           }

       void onLoadGame();
     }