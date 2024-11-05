package modcore.utils;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;
import java.util.Set;

public class ScreenPartition {
    public static final Set<Integer> assignedPositions = new HashSet<>();
    public static int currentRow = 3;
    public static int currentCol = 5;
    public static void assignSequentialPosition(AbstractMonster am1, AbstractMonster am2) {
        int gridSizeX = Settings.WIDTH / 2 / 5;
        int gridSizeY = (int)(Settings.HEIGHT * 0.9D) / 5;
        if (assignedPositions.size() >= 25) {
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

    private static int getNextPosition() {
        int position;
        if (currentCol == 3)
            currentCol--;
        if (currentRow == 3 && currentCol % 2 == 0) {
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
}