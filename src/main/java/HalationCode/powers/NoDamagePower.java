package HalationCode.powers;

import HalationCode.tools.TextureLoader;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NoDamagePower extends AbstractPower {
    public static final String POWER_ID = "halation:NoSkillsPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public NoDamagePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = TextureLoader.getTexture("HalationImages/powers/NoDamagePower.png");
        this.canGoNegative = false;
    }

    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public int onLoseHp (int damageAmount) {
        return 0;
    }
}
