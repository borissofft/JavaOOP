package core;

import military.*;
import military.enums.Corps;


import military.enums.MissionState;
import military.interfaces.LieutenantGeneral;
import military.interfaces.Soldier;
import models.Mission;
import models.MissionImpl;
import models.Repair;
import models.RepairImpl;
import utils.Validator;
import utils.readers.InputReader;

import java.util.ArrayList;
import java.util.List;


public class EngineImpl implements Engine {

    public static final String END_LINE = "End";
    private InputReader reader; // По този начин можем да подаваме какъвто си поискаме инпут, без да е нужно енджина да знае какъв точно е.

    public EngineImpl(InputReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {

        List<Soldier> privates = new ArrayList<>();

        String line = this.reader.readLine();
        while (!END_LINE.equals(line)) {

            String[] tokens = line.split("\\s+");
            String type = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];

            Soldier soldier = null;

            switch (type) {
                case "Private":
                    soldier = new PrivateImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));
                    break;
                case "LieutenantGeneral":
                    LieutenantGeneral lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));

                    for (int i = 5; i < tokens.length; i++) {
                        int wantedId = Integer.parseInt(tokens[i]);
                        Soldier first = privates.stream().filter(p -> p.getId() == wantedId).findFirst().orElse(null);

                        lieutenantGeneral.addPrivate(first);
                    }
                    soldier = (Soldier) lieutenantGeneral;

                    break;
                case "Engineer":
                    try {
                        Validator.validateCorps(tokens[5]);

                        Repair[] repairs = new Repair[(tokens.length - 6) / 2];

                        int index = 0;
                        for (int i = 6; i < tokens.length; i += 2) {
                            repairs[index++] = new RepairImpl(tokens[i], Integer.parseInt(tokens[i + 1]));
                        }

                        Corps corps = tokens[5].equals(Corps.AIRFORCE.getName()) ? Corps.AIRFORCE : Corps.MARINE;
                        soldier = new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), corps, repairs);
                    } catch (IllegalArgumentException ex) {
                        line = reader.readLine();
                        continue;
                    }
                    break;
                case "Commando":
                    try {
                        Validator.validateCorps(tokens[5]);

                        Mission[] missions = new Mission[(tokens.length - 6) / 2];

                        int index = 0;
                        for (int i = 6; i < tokens.length; i += 2) {

                            boolean isValid = Validator.validateMissionState(tokens[i + 1]);
                            if (isValid) {
                                MissionState state = tokens[i + 1].equals(MissionState.FINISHED.getState()) ? MissionState.FINISHED : MissionState.IN_PROGRES;
                                missions[index++] = new MissionImpl(tokens[i], state);
                            }

                        }

                        Corps corps = tokens[5].equals(Corps.AIRFORCE.getName()) ? Corps.AIRFORCE : Corps.MARINE;
                        soldier = new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), corps, missions);

                    } catch (IllegalArgumentException ex) {
                        line = reader.readLine();
                        continue;
                    }
                    break;
                case "Spy":
                    soldier = new SpyImpl(id, firstName, lastName, tokens[4]);
                    break;
            }

            if (soldier != null) {
                privates.add(soldier);
            }

            line = this.reader.readLine();
        }

        for (Soldier aPrivate : privates) {
            System.out.println(aPrivate);
        }

    }

}
