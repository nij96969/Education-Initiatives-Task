package behavioural_design_pattern.src.com.example.strategy;

import java.util.Scanner;
import java.util.logging.Logger;

public class MainDemoPaymentSystem {
    private static Logger logger = Logger.getLogger(MainDemoPaymentSystem.class.getName());

    public static void main(String[] args) {
        logger.info("System Started");
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        
        while(!exit){
            try{
                System.out.println("Select Payment Method :: ");
                System.out.println("1.Credit Card\n2.RazorPay Payment\n3.BankTranser Payment\n4.exit\n");
                int choice = Integer.parseInt(scanner.nextLine());
    
                if(choice == 4){
                    exit = true;
                    continue;
                }
    
                System.out.println("Enter amount:");
                int amount = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter Credit Card Number :: ");
                        String cardNumber = scanner.nextLine();
                        cart.setPaymentStrategy(new CreditCardPayment(cardNumber));
                        break;
                    case 2:
                        System.out.println("Enter Email :: ");
                        String email = scanner.nextLine();
                        cart.setPaymentStrategy(new RazorPayPayment(email));
                        break;
    
                    case 3:
                        System.out.println("Enter Account Number :: ");
                        String accountNumber = scanner.nextLine();
                        cart.setPaymentStrategy(new BankTransferPayment(accountNumber));
                        break;
    
                    default:
                        System.out.println("Enter Valid Choice\n");
                        break;
                }
                
                cart.checkout(amount);
            }
            catch(Exception e){
                logger.severe("Error during payment: " + e.getMessage());
                System.out.println("Payment failed. Please try again.");
            }
        }

        scanner.close();
    }
}
