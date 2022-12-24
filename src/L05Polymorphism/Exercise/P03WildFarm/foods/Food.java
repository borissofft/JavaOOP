package L05Polymorphism.Exercise.P03WildFarm.foods;

//package foods;

public abstract class Food {

   private Integer quantity;

   protected Food(Integer quantity) {
      this.quantity = quantity;
   }

   public Integer getQuantity() {
      return this.quantity;
   }

}
