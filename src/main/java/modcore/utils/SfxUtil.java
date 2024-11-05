package modcore.utils;

// 导入所需的库和类

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import modcore.B1Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * SfxUtil类：用于管理和播放游戏中的音效。
 */
public class SfxUtil {
    // 常量
    private static final int SFX_NONE = 999; // 用于标识没有播放的音效的常量

    // 静态成员变量
    private static List<SfxUtil> sfxList = new ArrayList<>(); // 存储所有SfxUtil实例的列表

    // 实例成员变量
    private String[] sfxIds; // 存储音效ID的数组
    private boolean consecutive; // 标识音效是否可以连续播放
    private float initProb; // 音效的初始播放概率
    private float deltaProb; // 每次播放后概率的减少量
    private float minProb; // 音效播放概率的最小值
    private float prob; // 当前音效的播放概率
    private int playedVoice; // 最近播放的音效索引，SFX_NONE表示没有播放
    private int numOfSfx; // 音效的总数

    /**
     * 创建一个SfxUtil实例，并将其添加到sfxList中。
     *
     * @param sfxIds     音效ID数组
     * @param consecutive 是否可以连续播放音效
     * @param sfxProb     音效的初始播放概率
     * @param deltaProb   每次播放后概率的减少量
     * @param minProb     音效播放概率的最小值
     * @return 创建的SfxUtil实例
     */
    public static SfxUtil createInstance(String[] sfxIds, boolean consecutive, float sfxProb, float deltaProb, float minProb) {
        SfxUtil sfx = new SfxUtil(sfxIds, consecutive, sfxProb, deltaProb, minProb);
        sfxList.add(sfx); // 将新创建的实例添加到列表中
        return sfx; // 返回创建的实例
    }

    /**
     * 重置所有SfxUtil实例的播放状态和概率。
     */
    public static void resetAll() {
        sfxList.forEach(SfxUtil::reset); // 对列表中的每个实例调用reset方法
    }

    /**
     * 私有构造函数，用于创建SfxUtil实例。
     *
     * @param sfxIds     音效ID数组
     * @param consecutive 是否可以连续播放音效
     * @param initProb    音效的初始播放概率
     * @param deltaProb   每次播放后概率的减少量
     * @param minProb     音效播放概率的最小值
     */
    private SfxUtil(String[] sfxIds, boolean consecutive, float initProb, float deltaProb, float minProb) {
        this.sfxIds = sfxIds;
        this.consecutive = consecutive;
        this.initProb = initProb;
        this.deltaProb = deltaProb;
        this.minProb = minProb;
        this.prob = initProb;
        this.playedVoice = SFX_NONE; // 初始化为没有播放的状态
        this.numOfSfx = sfxIds.length; // 设置音效的总数
    }

    /**
     * 播放音效，使用默认的音量。
     */
    public void playSFX() {
        playSFX(1.0F); // 调用重载的方法，使用1.0F作为音量修饰符
    }

    /**
     * 播放音效，并允许修改音量。
     *
     * @param volumeMod 音量修饰符
     */
    public void playSFX(float volumeMod) {
        if (B1Mod.getVoiceDisabled()) // 如果游戏设置中禁用了音效
            return; // 则不播放音效

        boolean canPlay = false; // 标识是否可以播放音效

        // 如果允许连续播放，或者之前没有播放过音效，则根据概率决定是否播放
        if (this.consecutive || this.playedVoice == SFX_NONE) {
            canPlay = (MathUtils.random(1.0F) < this.prob); // 根据概率决定是否播放音效
        }

        // 如果决定播放音效，则更新playedVoice的值；否则，将其重置为SFX_NONE
        this.playedVoice = canPlay ? this.playedVoice : SFX_NONE;

        // 如果可以播放音效
        if (canPlay) {
            // 生成一个随机索引，用于选择音效
            int random = MathUtils.random(0, this.numOfSfx - 1);

            // 如果随机索引与上次播放的音效相同，则选择下一个音效（循环到数组开头）
            this.playedVoice = (random == this.playedVoice) ? ((random + 1) % this.numOfSfx) : random;

            // 播放音效，并应用音量修饰符
            CardCrawlGame.sound.playV(this.sfxIds[this.playedVoice], volumeMod);

            // 更新音效的播放概率
            this.prob = Math.max(this.minProb, this.prob - this.deltaProb);
        }
    }
    /**
     * 重置当前实例的播放状态和概率。
     */
    public void reset() {
        this.playedVoice = 0; // 重置为第一个音效（或视为未播放状态的一种表示）
        this.prob = 1.0F; // 重置播放概率为初始值
    }
}