package structural_design_pattern.src.com.example.adapter_pattern;

public class PaymentClient {
    public static void main(String[] args) {
        OldPaymentSystem oldProcessor = new OldPaymentSystem();
        NewPaymentSystem paymentAdapter = new PaymentAdapter(oldProcessor);

        // Using the new payment gateway in place of the old one
        paymentAdapter.executePayment(100.0);
    }
}
