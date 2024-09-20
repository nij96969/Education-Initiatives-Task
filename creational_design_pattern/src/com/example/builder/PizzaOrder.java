package creational_design_pattern.src.com.example.builder;

public class PizzaOrder {
    public static void main(String[] args) {
        // Use the Builder to create a customized Pizza object
        Pizza pizza = new PizzaBuilder("Large")
                .addTopping("Cheese")
                .addTopping("Pepperoni")
                .addTopping("Mushrooms")
                .addTopping("Olives")
                .build();

        // Display the ordered pizza details
        System.out.println("Pizza Size: " + pizza.getSize());
        System.out.println("Toppings: " + String.join(", ", pizza.getToppings()));
    }
}

