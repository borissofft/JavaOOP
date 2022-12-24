package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Deque<Player> players = new ArrayDeque<>(civilPlayers);
        Deque<Gun> guns = new ArrayDeque<>(mainPlayer.getGunRepository().getModels());

        Player player = players.poll();
        Gun gun = guns.poll();

        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                player.takeLifePoints(gun.fire());
            }

            if (gun.canFire()) {
                player = players.poll();
            } else if (player.isAlive()) {
                gun = guns.poll();
            }

        }


        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Deque<Gun> gunsCivilPlayer = new ArrayDeque<>(civilPlayer.getGunRepository().getModels());
                Gun gunCivilPlayer = gunsCivilPlayer.poll();

                while (gunCivilPlayer != null) {
                    while (gunCivilPlayer.canFire() && mainPlayer.isAlive()) {
                        mainPlayer.takeLifePoints(gunCivilPlayer.fire());
                    }

                    if (!mainPlayer.isAlive()) {
                        return;
                    }

                    gunCivilPlayer = gunsCivilPlayer.poll();

                }
            }
        }


    }

}
