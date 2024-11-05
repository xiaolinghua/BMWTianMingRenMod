package modcore.shows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;


public class GunShiUI extends AbstractPanel
{
    // 定义一些静态常量，用于设置UI元素的尺寸和位置
    private static final float ICON_W = 64.0F * Settings.scale; // 图标宽度
    private static final float PAD = 10.0F * Settings.scale; // 内边距
    private static final float PANEL_X = 100.0F * Settings.scale; // 面板X坐标
    private static final float PANEL_Y = Settings.HEIGHT / 2.0F - ICON_W * 2.0F - PAD; // 面板Y坐标
    //private static final float X =Settings.scale*572;
    private static final float X =AbstractDungeon.player.drawX + 110.0F;
    //private static final float Y = (float) Settings.HEIGHT /((float) 1200 /439);
    private static final float Y = AbstractDungeon.player.drawY +100F;
    // 定义一些私有成员变量，用于存储UI元素的状态和属性
    private final Color color; // 颜色
    private final Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);
    private final Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);

    // 构造函数，初始化面板和图标等UI元素
    public GunShiUI() {
        super(PANEL_X, PANEL_Y, -480.0F * Settings.scale, 200.0F * Settings.scale, 200.0F * Settings.scale, 12.0F * Settings.scale, null, true);
        this.color = Color.WHITE.cpy(); // 复制一个白色颜色对象
        this.color.a = 1.0F; // 设置颜色透明度为不透明
    }

    // 更新方法，用于处理面板的动画和交互逻辑
    public void update(float hbAlpha) {
        // 更新面板位置（如果目标位置与当前位置不同）
        if (this.target_x != this.current_x) this.current_x = this.target_x;
        if (this.target_y != this.current_y) this.current_y = this.target_y;
        // 更新颜色透明度
        this.color.a = hbAlpha;
        // 更新点击区域位置
    }

    // 渲染方法，用于绘制面板和图标等UI元素
    public void render(SpriteBatch sb) {

        if (CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&"tianmingren".equals(AbstractDungeon.player.name))
        {
            // 设置绘制颜色
            //sb.setColor(this.color);
            if (!AbstractDungeon.player.hasPower("blackmythwukong:GunShi"))
            {
                Color c;
                c = this.redColor;
                sb.setColor(c);
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, "0", X, Y, Settings.RED_TEXT_COLOR);
            }
            else
            {
                Color c;
                c = this.greenColor;
                sb.setColor(c);
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, String.valueOf(AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount), X, Y, Settings.GOLD_COLOR);
            }
            /* 计算图标绘制参数
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont,
                    String.format(!AbstractDungeon.player.hasPower("blackmythwukong:GunShi") ? "0" : String.valueOf(AbstractDungeon.player.getPower("blackmythwukong:GunShi").amount)),
                    X, Y, Settings.LIGHT_YELLOW_COLOR);

             */
        }
    }

}