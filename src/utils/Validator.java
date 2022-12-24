package utils;

import military.enums.Corps;
import military.enums.MissionState;

public class Validator {

    private Validator() { // Празен конструктор, за да не може да се създава инстанция от този обект

    }

    public static void validateCorps(String corps) {
        if (!corps.equals(Corps.AIRFORCE.getName()) && !corps.equals(Corps.MARINE.getName())) {
            throw new IllegalArgumentException("Invalid Corps!");
        }
    }

    public static boolean validateMissionState(String state) {
        if (!state.equals(MissionState.FINISHED.getState()) && !state.equals(MissionState.IN_PROGRES.getState())) {
            return false;
        }
        return true;
    }

}
