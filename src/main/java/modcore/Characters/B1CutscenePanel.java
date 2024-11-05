package modcore.Characters;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;

public class B1CutscenePanel extends CutscenePanel
{
    public B1CutscenePanel(String imgUrl, String sfx) {
        super(imgUrl);
        this.sfx = sfx;
    }
    private String sfx;
    public B1CutscenePanel(String imgUrl) {
        super(imgUrl);
    }
    public void activate() {
        if (this.sfx != null) {
            CardCrawlGame.sound.play(this.sfx);
        }
        this.activated = true;
    }
}