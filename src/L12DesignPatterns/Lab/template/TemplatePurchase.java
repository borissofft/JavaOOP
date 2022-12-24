package L12DesignPatterns.Lab.template;

public abstract class TemplatePurchase { // Определя шаблонният алгоритъм за това как правим дадена покупка, в различни видове магазини например

    public final void purchaseOrder() {
        getItemsFromCart();
        calculatePricesWithDiscount();
        createReceipt();
        payOrder();
        notifyClient();
    }

    protected abstract void getItemsFromCart();

    protected abstract void calculatePricesWithDiscount();

    protected abstract void createReceipt();

    protected abstract void payOrder();

    protected abstract void notifyClient();

}
