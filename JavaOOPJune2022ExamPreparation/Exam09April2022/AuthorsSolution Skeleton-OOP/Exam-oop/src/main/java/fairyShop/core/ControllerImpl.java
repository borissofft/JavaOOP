package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final HelperRepository helpers;
    private final PresentRepository presents;

    public ControllerImpl() {
        this.helpers = new HelperRepository();
        this.presents = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        } else {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        this.helpers.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);
        if (this.helpers.findByName(helperName) == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        for (Helper helper : this.helpers.getModels()) {
            if (helper.getName().equals(helperName)) {
                helper.addInstrument(instrument);
            }
        }
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presents.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Shop shop = new ShopImpl();
        List<Helper> suitableHelpers = new ArrayList<>();
        Present present1 = null;
        for (Helper model : this.helpers.getModels()) {
            if (model.getEnergy() > 50) {
                suitableHelpers.add(model);
            }
        }
        int instrumentsCount = 0;
        if (suitableHelpers.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        for (Present present : this.presents.getModels()) {
            if (present.getName().equals(presentName)) {
                present1 = present;
                for (Helper suitableHelper : suitableHelpers) {
                    shop.craft(present, suitableHelper);
                }

                for (Helper suitableHelper : suitableHelpers) {
                    for (Instrument instrument : suitableHelper.getInstruments()) {
                        if (instrument.isBroken()) {
                            instrumentsCount++;
                        }
                    }
                }
            }
        }
        assert present1 != null;
        if (present1.isDone()) {
            return String.format(PRESENT_DONE, presentName, "done") + String.format(COUNT_BROKEN_INSTRUMENTS, instrumentsCount);
        }
        return String.format(PRESENT_DONE, presentName, "not done") + String.format(COUNT_BROKEN_INSTRUMENTS, instrumentsCount);
    }
    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        int countPresents = 0;
        int instrumentCount = 0;
        for (Present model : this.presents.getModels()) {
            if(model.isDone()){
                countPresents++;
            }
        }
        sb.append(String.format("%d presents are done!", countPresents)).append(System.lineSeparator())
                .append("Helpers info:").append(System.lineSeparator());

        for (Helper model : this.helpers.getModels()) {
            sb.append(String.format("Name: %s", model.getName())).append(System.lineSeparator())
                    .append(String.format("Energy: %d", model.getEnergy())).append(System.lineSeparator());
            for (Instrument instrument : model.getInstruments()) {
                if(!instrument.isBroken()){
                    instrumentCount++;
                }
            }
            sb.append(String.format("Instruments: %d not broken left", instrumentCount))
                    .append(System.lineSeparator());
            instrumentCount = 0;
        }
        return sb.toString().trim();
    }
}
