
package mage.cards.a;

import java.util.HashSet;
import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.SetPowerToughnessSourceEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author LevelX2
 */
public final class AwakenedAmalgam extends CardImpl {

    public AwakenedAmalgam(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{4}");

        this.subtype.add(SubType.GOLEM);
        this.power = new MageInt(0);
        this.toughness = new MageInt(0);

        // Awakened Amalgam's power and toughness are each equal to the number of differently named lands you control.
        DynamicValue value = (new AwakenedAmalgamLandNamesCount());
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new SetPowerToughnessSourceEffect(value, Duration.EndOfGame)));
    }

    public AwakenedAmalgam(final AwakenedAmalgam card) {
        super(card);
    }

    @Override
    public AwakenedAmalgam copy() {
        return new AwakenedAmalgam(this);
    }
}

class AwakenedAmalgamLandNamesCount implements DynamicValue {

    public AwakenedAmalgamLandNamesCount() {
    }

    public AwakenedAmalgamLandNamesCount(AwakenedAmalgamLandNamesCount dynamicValue) {
    }

    @Override
    public int calculate(Game game, Ability sourceAbility, Effect effect) {
        HashSet<String> landNames = new HashSet<>();
        for (Permanent permanent : game.getBattlefield().getAllActivePermanents(sourceAbility.getControllerId())) {
            if (permanent.isLand()) {
                landNames.add(permanent.getName());
            }
        }
        return landNames.size();
    }

    @Override
    public AwakenedAmalgamLandNamesCount copy() {
        return new AwakenedAmalgamLandNamesCount(this);
    }

    @Override
    public String toString() {
        return "1";
    }

    @Override
    public String getMessage() {
        return "differently named lands you control";
    }
}
