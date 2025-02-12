package modcore.utils;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;
import java.util.Set;

public class ScreenPartition {
    public static final Set<Integer> assignedPositions = new HashSet<>();
    public static int currentRow = 1;
    public static int currentCol = 3;
    private static int numMonsters = 0;
    /*public static void assignSequentialPosition(AbstractMonster am1, AbstractMonster am2)
    {
        int gridSizeX = Settings.WIDTH / 2 / 5;
        int gridSizeY = (int)(Settings.HEIGHT * 0.9D) / 5;
        if (assignedPositions.size() >= 25)
        {
            assignedPositions.clear();
            currentRow = 0;
            currentCol = 4;
        }
        int position = getNextPosition();
        assignedPositions.add(position);
        int x = currentCol * gridSizeX + gridSizeX / 2;
        int y = currentRow * gridSizeY + gridSizeY / 2;
        if (am1 != null) {
            am1.drawX = x;
            am1.drawY = y;
        }
        if (am2 != null) {
            am2.drawX = (Settings.WIDTH - x);
            am2.drawY = y;
        }
    }

     */
    public static void assignSequentialPosition(AbstractMonster am1, AbstractMonster am2)
    {
        int gridSizeX = Settings.WIDTH/ 6;
        //int gridSizeY = (int)(Settings.HEIGHT * 0.4D) / 2;
        if (assignedPositions.size() >= 10)
        {
            assignedPositions.clear();
            currentRow = 1;
            currentCol = 3;
            numMonsters = 0;
        }
        int position = getNextPosition();
        assignedPositions.add(position);
        //int x = currentCol * gridSizeX + gridSizeX / 2 + (int)(Math.random() * gridSizeX / 4 - (double) gridSizeX / 8);
        int x = (currentCol-1) * gridSizeX + gridSizeX / 2+ (int)(Math.random() * 80F -40F);
        float y;
        if (currentRow == 1)
        {
            //y = 0.35F * Settings.HEIGHT + (float)(Math.random() * 0.24F * Settings.HEIGHT - 0.12F * Settings.HEIGHT);
            y = 0.35F * Settings.HEIGHT+ (int)(Math.random() * 40F -20F);
        }else
        {
           // y = 0.227F * Settings.HEIGHT + (float)(Math.random() * 0.24F * Settings.HEIGHT - 0.12F * Settings.HEIGHT);
            y = 0.227F * Settings.HEIGHT+ (int)(Math.random() * 40F -20F);
        }
        //int y = currentRow * gridSizeY + gridSizeY / 2 + (int)(Math.random() * gridSizeY / 4 - (double) gridSizeY / 8);
        if (am1 != null) {
            am1.drawX = x;
            am1.drawY = y;
        }
        if (am2 != null) {
            am2.drawX = (Settings.WIDTH - x);
            am2.drawY = y;
        }
    }
    /*private static int getNextPosition()
    {
        int position;
        if (currentCol == 3)
            currentCol--;
        if (currentRow == 3 && currentCol % 2 == 0)
        {
            position = currentRow * 5 + currentCol;
        } else if (currentRow == 1 && currentCol % 2 == 0) {
            position = currentRow * 5 + currentCol;
        } else if (currentRow == 3 && currentCol % 2 != 0) {
            position = currentRow * 5 + currentCol + 5;
        } else {
            position = currentRow * 5 + currentCol + 5;
        }
        if (currentCol == 0) {
            if (currentRow == 3) {
                currentRow = 1;
            } else {
                currentRow = 3;
            }
            currentCol = 4;
        } else {
            currentCol--;
        }
        return position;
    }

     */
    private static int getNextPosition()
    {
        switch (numMonsters) {
            case 0:
                currentRow = 1;
                currentCol = 3;
                break;
            case 1:
                currentRow = 1;
                currentCol = 4;
                break;
            case 2:
                currentRow = 1;
                currentCol = 6;
                break;
            case 3:
                currentRow = 2;
                currentCol = 3;
                break;
            case 4:
                currentRow = 2;
                currentCol = 4;
                break;
            case 5:
                currentRow = 2;
                currentCol = 5;
                break;
            case 6:
                currentRow = 2;
                currentCol = 6;
                break;
            case 7:
                currentRow = 1;
                currentCol = 1;
                break;
            case 8:
                currentRow = 2;
                currentCol = 1;
                break;
            default:
                numMonsters = 0;
                currentRow = 1;
                currentCol = 3;
                break;
        }
        return numMonsters++;
    }
}