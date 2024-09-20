package behavioural_design_pattern.src.com.example.strategy;

import java.util.logging.Logger;

public class CreditCardPayment implements PaymentStrategy{
    private Logger logger = Logger.getLogger(CreditCardPayment.class.getName());
    private String cardNumber;

    public CreditCardPayment(String cardNumber){
        if(cardNumber == null || cardNumber.length() != 16){
            logger.warning("cardNumber is in Invalid format");
            throw new IllegalArgumentException("cardNumber is in Invalid format");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount){
        try{
            logger.info("Paid" + amount + " $ Using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
        }
        catch(Exception e){
            logger.severe("Payment failed: " + e.getMessage());
            throw new RuntimeException("Credit card payment failed", e);
        }
    }
}
