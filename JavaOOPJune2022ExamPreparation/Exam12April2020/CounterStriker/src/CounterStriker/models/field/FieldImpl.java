package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {

        List<Player> terrorists = players.stream().filter(player -> player.getClass().getSimpleName().equals("Terrorist")).collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream().filter(player -> player.getClass().getSimpleName().equals("CounterTerrorist")).collect(Collectors.toList());

//        boolean haveAliveCounterTerrorists = counterTerrorists.stream().anyMatch(Player::isAlive);
//        boolean haveAliveTerrorists = terrorists.stream().anyMatch(Player::isAlive);

        while (true) {

            for (Player terrorist : terrorists) {
                Gun gun = terrorist.getGun();
                if (terrorist.isAlive() && gun.getBulletsCount() != 0) {

                    for (Player counterTerrorist : counterTerrorists) {
                        if (counterTerrorist.isAlive()) {
                            counterTerrorist.takeDamage(gun.fire());
                        }
                    }

                }
            }

            if (counterTerrorists.stream().noneMatch(Player::isAlive)) {
                break;
            }

            for (Player counterTerrorist : counterTerrorists) {
                Gun gun = counterTerrorist.getGun();
                if (counterTerrorist.isAlive() && gun.getBulletsCount() != 0) {

                    for (Player terrorist : terrorists) {
                        if (terrorist.isAlive()) {
                            terrorist.takeDamage(gun.fire());
                        }
                    }

                }
            }

            if (terrorists.stream().noneMatch(Player::isAlive)) {
                break;
            }

        }

        if (counterTerrorists.stream().noneMatch(Player::isAlive)) {
            return OutputMessages.TERRORIST_WINS;
        } else {
            return OutputMessages.COUNTER_TERRORIST_WINS;
        }

    }

}
