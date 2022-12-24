package L04InterfacesAndAbstraction.Lab.P01CarShop;

import java.io.Serializable;

public interface Car extends Serializable {

    int TIRES = 4;

    String getModel();

    String getColor();

    Integer getHorsePower();

    String countryProduced();

}
