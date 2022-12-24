public interface ProductStock extends Iterable<Product> {
    //getter
    int getCount(); // O(1)

    //Validations
    boolean contains(Product product); // O(1)

    //Modifications
    void add(Product product); // O(1)

    void changeQuantity(String product, int quantity); // O(1)

    //Retrievals
    Product find(int index); // O(n) with Map

    Product findByLabel(String label);

    Iterable<Product> findFirstByAlphabeticalOrder(int count); // TreeMap

    //Querying
    Iterable<Product> findAllInRange(double lo, double hi); // O(n)

    Iterable<Product> findAllByPrice(double price); // O(n)

    Iterable<Product> findFirstMostExpensiveProducts(int count); // O(n)

    Iterable<Product> findAllByQuantity(int quantity); // O(n)

}
