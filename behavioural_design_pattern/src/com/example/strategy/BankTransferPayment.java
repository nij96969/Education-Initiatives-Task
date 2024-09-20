package behavioural_design_pattern.src.com.example.strategy;

import java.util.logging.Logger;

public class BankTransferPayment implements PaymentStrategy{
    private Logger logger = Logger.getLogger(BankTransferPayment.class.getName());
    private String bankAccount;

    public BankTransferPayment(String bankAccount){
        if (bankAccount == null || bankAccount.length() != 9) {
            throw new IllegalArgumentException("Invalid bank account number");
        }
        this.bankAccount = bankAccount;
    }

    @Override
    public void pay(int amount) {
        try{
            logger.info("Amount " + amount + " $ using Bank Transfer to account " + bankAccount);
        }
        catch (Exception e) {
            logger.severe("Payment failed: " + e.getMessage());
            throw new RuntimeException("Bank transfer payment failed", e);
        }
    }
}
