import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class ProductStockTest {

    private ProductStock productStock;

    @Before
    public void init() {
        this.productStock = new Instock();
    }


    private List<Product> createProducts(int count) {
        List<Product> products = new ArrayList<>();

        while (count > 0) {
            Product product = new Product(UUID.randomUUID().toString(), 12.5 * count, 10 * count);
            products.add(product);
            count--;
        }
        return products;
    }

    @Test
    public void addOneProductShouldIncreaseSize() {
        Product product = this.createProducts(1).get(0);
        productStock.add(product);
        Assert.assertEquals(1, productStock.getCount());
    }

    @Test
    public void addTenProductsShouldIncreaseSize() {
        List<Product> products = this.createProducts(10);
        for (Product product : products) {
            productStock.add(product);
        }

        Assert.assertEquals(products.size(), productStock.getCount());
    }

    @Test
    public void addShouldContainsCorrectReference() {
        Product product = this.createProducts(1).get(0);
        productStock.add(product);
        Assert.assertTrue(productStock.contains(product));
    }

    @Test
    public void containsShouldReturnTrueIfProductExist() {
        List<Product> products = this.createProducts(10);
        products.forEach(product -> this.productStock.add(product));

        Product product = products.get(products.size() - 1);
        Assert.assertTrue(this.productStock.contains(product));
    }

    @Test
    public void containsShouldReturnFalseIfProductNotExist() {
        Product product = this.createProducts(1).get(0);
        Assert.assertFalse(this.productStock.contains(product));
    }

    @Test
    public void countShouldBeZeroIfEmptyCollection() { // Щом работят тестовете за add() метода, е напълно достатъчно да проверим дали count() метода връща 0 при подадена празна колекция
        Assert.assertEquals(0, this.productStock.getCount());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findShouldThrowException() {
        this.productStock.find(this.productStock.getCount());
    }

    @Test
    public void findShouldReturnCorrectProductByIndex() {
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertEquals(product, productStock.find(0));
    }

    @Test
    public void changeQuantityAddingShouldWorkingCorrect() {
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        this.productStock.changeQuantity(product.getLabel(), 0);
        Assert.assertEquals(0, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityShouldThrowExceptionIfNotExist() {
        Product product = this.createProducts(1).get(0);
        this.productStock.changeQuantity(product.getLabel(), 0); // Продукта не е добавен и няма как да го намери
        Assert.assertEquals(0, product.getQuantity());
    }

    @Test
    public void findByLabelShouldReturnCorrectReference() {
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        Product found = this.productStock.findByLabel(product.getLabel());
        Assert.assertEquals(product, found);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLabelShouldThrowExceptionIfNotExist() {
        Product product = this.createProducts(1).get(0);
        Product found = this.productStock.findByLabel(product.getLabel());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnCorrectOrder() {
        List<Product> products = this.createProducts(3);
        products.get(0).setLabel("A");
        products.get(1).setLabel("B");
        products.get(2).setLabel("C");

        products.forEach(product -> this.productStock.add(product));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(3);
//        Product[] expected = products.toArray(new Product[0]);
        Product[] expected = products.toArray(Product[]::new);
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionWithIllegalArgument() {
        List<Product> products = this.createProducts(3);
        products.get(0).setLabel("A");
        products.get(1).setLabel("B");
        products.get(2).setLabel("C");

        products.forEach(product -> this.productStock.add(product));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(6); // Добавили сме 3, а искаме да ни върне 6
        Product[] expected = new Product[0];
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollection() {
        Product[] expected = new Product[0];
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(3);
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getIterableShouldReturnAllProducts() {
        List<Product> products = this.createProducts(20);
        products.forEach(product -> this.productStock.add(product));
        Product[] expected = products.toArray(new Product[0]);

        final Iterator<Product> productIterator = this.productStock.iterator();
        Iterable<Product> productIterable = () -> productIterator;

        Product[] actual = StreamSupport.stream(productIterable.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllByQuantityShouldReturnCorrectValues() {
        List<Product> products = this.createProducts(3);
        products.get(0).setQuantity(10);
        products.get(1).setQuantity(10);
        products.get(2).setQuantity(10);

        products.forEach(product -> this.productStock.add(product));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findAllByQuantity(10);
        Product[] expected = products.toArray(Product[]::new);
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    /**
     * spliterator() - Взима една колекция от обекти и ги разделя на отделни части (сплитва ги на парчета) и започва да ги обработва на парчета
     * параметъра който сме подали като false в StreamSupport - а, му оказваме дали да използва паралелен stream.
     */

    @Test
    public void findAllByQuantityShouldReturnEmptyCollection() {
        List<Product> products = this.createProducts(3);
        products.get(0).setQuantity(10);
        products.get(1).setQuantity(10);
        products.get(2).setQuantity(10);

        products.forEach(product -> this.productStock.add(product));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findAllByQuantity(20);
        Product[] expected = new Product[0];
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }



}