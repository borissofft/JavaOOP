package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.*;
import java.util.stream.Collectors;


public class ControllerImpl implements Controller {

    private MainPlayer mainPlayer;
    //    private List<Player> civilPlayers;
    private Map<String, Player> civilPlayers;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
//        this.civilPlayers = new ArrayList<>();
        this.civilPlayers = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        CivilPlayer civilPlayer = new CivilPlayer(name);
        this.civilPlayers.put(name, civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }

        this.guns.offer(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = this.guns.poll(); // null if this deque is empty
        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        if (!"Vercetti".equals(name)) {
//            Player civilPlayer = findCivilPlayerByName(name, this.civilPlayers);
            Player civilPlayer = this.civilPlayers.get(name);
            if (civilPlayer == null) {
                return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            }
            civilPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
        } else {
            this.mainPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), this.mainPlayer.getName());
        }

    }

    @Override
    public String fight() {

        this.neighbourhood.action(this.mainPlayer, this.civilPlayers.values());

        boolean everythingIsOK = (this.mainPlayer.getLifePoints() == 100
                && this.civilPlayers.values().stream().allMatch(player -> player.getLifePoints() == 50));

        if (everythingIsOK) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }

        List<Player> deadPlayers = this.civilPlayers.values().stream().filter(player -> !player.isAlive()).collect(Collectors.toList());
        int alivePlayers = this.civilPlayers.size() - deadPlayers.size();

        StringBuilder sb = new StringBuilder(ConstantMessages.FIGHT_HAPPENED);
        sb.append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints())).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size())).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, alivePlayers));

        for (Player deadPlayer : deadPlayers) {
            this.civilPlayers.remove(deadPlayer.getName());
        }

        return sb.toString().trim();
    }

    public Player findCivilPlayerByName(String name, List<Player> civilPlayers) { // Use it only if you decide to use List<Player> civilPlayers instead Map<String, Player> civilPlayers;
        return civilPlayers
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
