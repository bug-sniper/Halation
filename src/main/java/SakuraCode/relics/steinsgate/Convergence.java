package SakuraCode.relics.steinsgate;

import SakuraCode.SakuraModInitializer;
import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;

import java.util.ArrayList;

public class Convergence extends CustomRelic implements ClickableRelic {
    public static final String ID = "sakura:Convergence";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/Convergence.png");

    public Convergence() {
        super(ID, IMG, AbstractRelic.RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Convergence();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            ArrayList<String> emptyList = new ArrayList<String>();
            switch (AbstractDungeon.id) {
                case Exordium.ID:
                    new Exordium(AbstractDungeon.player, emptyList);
                    AbstractDungeon.scene.fadeOutAmbiance();
                    AbstractDungeon.dungeonMapScreen.open(true);
                    AbstractDungeon.player.maxHealth -= 20;
                    break;
                case TheCity.ID:
                    AbstractDungeon.scene.fadeOutAmbiance();
                    new TheCity(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                    AbstractDungeon.dungeonMapScreen.open(true);
                    AbstractDungeon.player.maxHealth -= 20;
                    break;
                case TheBeyond.ID:
                    AbstractDungeon.scene.fadeOutAmbiance();
                    new TheBeyond(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                    AbstractDungeon.dungeonMapScreen.open(true);
                    AbstractDungeon.player.maxHealth -= 20;
                    break;
                default:
                    break;
            }
        }
    }
}