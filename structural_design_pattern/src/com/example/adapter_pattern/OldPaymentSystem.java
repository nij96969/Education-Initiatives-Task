package structural_design_pattern.src.com.example.adapter_pattern;

class OldPaymentSystem {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Old Payment Processor.");
    }
}