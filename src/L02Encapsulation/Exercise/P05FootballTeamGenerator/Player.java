package L02Encapsulation.Exercise.P05FootballTeamGenerator;

public class Player {

    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
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

    private void setEndurance(int endurance) {
        validateStatsFields("Endurance", endurance);
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        validateStatsFields("Sprint", sprint);
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateStatsFields("Dribble", dribble);
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateStatsFields("Passing", passing);
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateStatsFields("Shooting", shooting);
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }

    private void validateStatsFields(String statName, int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(statName + " should be between 0 and 100.");
        }
    }

}
