package models;

import military.enums.MissionState;

public class MissionImpl implements Mission {

    private String name;
    private MissionState state;

    public MissionImpl(String name, MissionState state) {
        this.name = name;
        this.state = state;
    }

    @Override
    public void completeMission() {
        this.state = MissionState.FINISHED;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", this.name, this.state.getState());
    }

}
