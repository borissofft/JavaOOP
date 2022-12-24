package L12DesignPatterns.Lab.template;

public class Main {
    public static void main(String[] args) {

        TemplatePurchase purchase1 = new OnlinePurchase();
        TemplatePurchase purchase2 = new LocalStorePurchase();

        purchase1.purchaseOrder();
        System.out.println();

        purchase2.purchaseOrder();

    }
}
