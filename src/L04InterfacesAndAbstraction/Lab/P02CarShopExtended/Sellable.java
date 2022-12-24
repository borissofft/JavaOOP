package L04InterfacesAndAbstraction.Lab.P02CarShopExtended;

public interface Sellable { // Не е добра идея да extends Car, за да може в Main да се създаде кола по следният начин: Sellable seat = new Seat()
                            // Sellable няма нищо общо с Car!!!
    Double getPrice();

}
