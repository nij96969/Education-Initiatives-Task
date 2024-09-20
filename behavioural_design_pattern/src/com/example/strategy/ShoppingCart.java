package behavioural_design_pattern.src.com.example.strategy;

import java.util.logging.Logger;

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    private Logger logger = Logger.getLogger(ShoppingCart.class.getName());
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        if(paymentStrategy == null){
            logger.severe("Payment Strategy not selected");
            throw new IllegalArgumentException("Payment cannot be null");
        }
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount){
        if(paymentStrategy == null){
            logger.severe("Payment Strategy not set");
            throw new IllegalArgumentException("Payment Strategy not set");
        }
        if(amount < 0){
            logger.warning("Amount cannot be negative");
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        try {
            paymentStrategy.pay(amount);
        } catch (Exception e) {
            logger.severe("Checkout failed: " + e.getMessage());
            throw new RuntimeException("Checkout failed", e);
        }

    }
}