package L02Encapsulation.Exercise.P05FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {


    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        boolean isRemoved = this.players.removeIf(player -> playerName.equals(player.getName()));
        if (!isRemoved) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.", playerName, this.name));
        }
    }

    public double getRating() {
        return (this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0));
    }

}
