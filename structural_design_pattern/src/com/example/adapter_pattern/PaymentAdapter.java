package structural_design_pattern.src.com.example.adapter_pattern;

public class PaymentAdapter implements NewPaymentSystem{
    private OldPaymentSystem oldProcessor;

    public PaymentAdapter(OldPaymentSystem oldProcessor) {
        this.oldProcessor = oldProcessor;
    }
    
    @Override
    public void executePayment(double amount) {
        oldProcessor.processPayment(amount);  // Adapting the new interface to the old system
    }
}
