package L04InterfacesAndAbstraction.Lab.P02CarShopExtended;

public interface Rentable { // Не е добра идея да extends Car, за да може в Main да се създаде кола по следният начин: Rentable audi = new Audi()
                           // Rentable няма нищо общо с Car!!!
    Integer getMinRentDay();

    Double getPricePerDay();

}
