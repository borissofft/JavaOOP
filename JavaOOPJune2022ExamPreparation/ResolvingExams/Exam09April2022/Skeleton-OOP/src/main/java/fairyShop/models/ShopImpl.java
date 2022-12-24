package fairyShop.models;

import java.util.Collection;
import java.util.List;

public class ShopImpl implements Shop {

    @Override
    public void craft(Present present, Helper helper) {

        Collection<Instrument> instruments = helper.getInstruments();

        while (helper.canWork() && !instruments.isEmpty() && !present.isDone()) {
            for (Instrument instrument : instruments) {
                while (!instrument.isBroken() && helper.canWork() && !present.isDone()) {
                    present.getCrafted();
                    helper.work();
                    instrument.use();
                }
            }

            if (instruments.stream().allMatch(Instrument::isBroken)) {
                break;
            }

        }

    }

//    @Override
//    public void craft(Present present, Helper helper) {
//
//        List<Instrument> instruments = (List<Instrument>) helper.getInstruments();
//        Instrument instrument = null;
//        int index = 0;
//        if (!instruments.isEmpty()) {
//            instrument = instruments.get(index);
//        }
//
//        while (instrument != null) {
//
//            if (helper.canWork()) {
//                if (!instrument.isBroken()) {
//                    instrument.use();
//                    present.getCrafted();
//                    helper.work();
////                } else if (instruments.iterator().hasNext()) { // Не работи!!!! Имам грешка
//                } else if (index < instruments.size() - 1) {
//                    index++;
//                    instrument = instruments.get(index);
//                } else {
//                    break;
//                }
//                if (present.isDone()) {
//                    break;
//                }
//            } else {
//                break;
//            }
//
//        }
//
//    }

}
