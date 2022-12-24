package L06SOLID.Lab.p02_OpenClosedPrinciple.p03_ShoppingCart;

public class Main {
    public static void main(String[] args) {

        Cart cart = new Cart();
        cart.add(new OrderItem("123-abc-123", 20));
        Order order = new OnlineOrder(cart, "myEmail@dot.com");
        order.getTotalAmount();

    }
}
