package creational_design_pattern.src.com.example.builder;

import java.util.List;

public class Pizza{
    // Required parameters
    private final String size;

    // Optional parameters
    private final List<String> toppings;

    // public constructor, accessible by the Builder
    public Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.toppings = builder.toppings;
    }
    
    // Getters for pizza attributes
    public String getSize() { return size; }
    public List<String> getToppings() { return toppings; }
}


