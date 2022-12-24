package L12DesignPatterns.Lab.builder;

public class LunchOrder {

    public static class Builder {

        private LunchOrder order;

        private Builder() { // Така никой не може да създаде обект и да сетне полетата
            this.order = new LunchOrder();
        }

        public static Builder get() {
            return new Builder();
        }

        public Builder withBread(String bread) {
            this.order.setBread(bread);
            return this; // Инстанционен метод. Винаги ще се извикват върху Builder-а върху който са get-нати.
        }               // Връща Builder(същият обект), което ни позволява да можем да chain-ваме следващото извикване(другите методи)

        public Builder withMeat(String meat) {
            this.order.setMeat(meat);
            return this; // Връща Builder
        }

        public Builder withSalad(String salad) {
            this.order.setSalad(salad);
            return this; // Връща Builder
        }

        public Builder withDrink(String drink) {
            this.order.setDrink(drink);
            return this; // Връща Builder
        }

        public LunchOrder build() {
            return this.order;
        }

    }

    private String bread;
    private String meat;
    private String salad;
    private String drink;

    // Не можем да създадем конструктори с по един параметър, защото всички полета са от тип String !!!
    // Решението е да си създадем builder pattern

    private LunchOrder() { // Така никой не може да създаде обект и да сетне полетата
    }

    private void setBread(String bread) {
        this.bread = bread;
    }

    private void setMeat(String meat) {
        this.meat = meat;
    }

    private void setSalad(String salad) {
        this.salad = salad;
    }

    private void setDrink(String drink) {
        this.drink = drink;
    }

    public String getBread() {
        return bread;
    }

    public String getMeat() {
        return this.meat;
    }

    public String getSalad() {
        return this.salad;
    }

    public String getDrink() {
        return this.drink;
    }

    @Override
    public String toString() {
        return "LunchOrder{" +
                "bread='" + bread + '\'' +
                ", meat='" + meat + '\'' +
                ", salad='" + salad + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}
