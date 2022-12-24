package military.enums;

public enum MissionState {

    IN_PROGRES("inProgress"),
    FINISHED("finished");

    private final String state;

    MissionState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
