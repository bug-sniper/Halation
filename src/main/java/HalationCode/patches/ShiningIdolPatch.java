package HalationCode.patches;

import HalationCode.events.buttons.RelicDialogOptionButton;
import HalationCode.relics.lovelive.ShiningIdol;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.ForgottenAltar;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.GoldenIdol;

public class ShiningIdolPatch {
    public static final String ID = "halation:ShiningIdol";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;


    @SpirePatch(
            clz = ForgottenAltar.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class AltarCtor {
        public static void Postfix(ForgottenAltar __instance) {
            if (AbstractDungeon.player.hasRelic(GoldenIdol.ID)) {
                __instance.imageEventText.optionList.add(new RelicDialogOptionButton(3, OPTIONS[0], new ShiningIdol(), false));
            }
        }
    }

    @SpirePatch(
            clz = ForgottenAltar.class,
            method = "buttonEffect"
    )
    public static class ButtonEffectPatch {
        public static SpireReturn Prefix(ForgottenAltar __instance, @ByRef int[] buttonPressed) {
            if ((int) ReflectionHacks.getPrivate(__instance, ForgottenAltar.class, "screenNum") == 0) {
                if (buttonPressed[0] == 3) {
                    AbstractDungeon.player.loseRelic(GoldenIdol.ID);
                    UPDATEBODYTEXT(__instance);
                    ReflectionHacks.setPrivate(__instance, ForgottenAltar.class, "screenNum", 1);
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }

        private static void UPDATEBODYTEXT(ForgottenAltar __instance) {
            __instance.imageEventText.updateBodyText(DESCRIPTIONS[0]);
            __instance.imageEventText.updateDialogOption(0, OPTIONS[1]);
            __instance.imageEventText.clearRemainingOptions();
        }
    }
}
