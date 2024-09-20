package creational_design_pattern.src.com.example.builder;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    // Required parameters
    public final String size;

    // Optional parameters
    public final List<String> toppings = new ArrayList<>();

    // Constructor for required parameters
    public PizzaBuilder(String size) {
        this.size = size;
    }

    // Method to add a topping
    public PizzaBuilder addTopping(String topping) {
        toppings.add(topping);
        return this;
    }

    // Build method to create the Pizza object
    public Pizza build() {
        return new Pizza(this);
    }
}


