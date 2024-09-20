package behavioural_design_pattern.src.com.example.strategy;

import java.util.logging.Logger;

public class RazorPayPayment implements PaymentStrategy {
    private Logger logger = Logger.getLogger(RazorPayPayment.class.getName());
    private String email;
    
    public RazorPayPayment(String email){
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        try{
            logger.info("Paid " + amount + " $ Using Razor Pay account " + email);
        }
        catch(Exception e){
            logger.severe("RazorPay Payment failed: " + e.getMessage());
            throw new RuntimeException("RazorPay payment failed", e);
        }
    }
}
