package creational_design_pattern.src.com.example.factory_method;

public class DemoMainNotification {
    
    public static void main(String[] args) {
        Notifications notification1 = NotificationFactory.createNotification("email");
        notification1.notifyUser(); // Output: Email notification sent.
        
        Notifications notification2 = NotificationFactory.createNotification("sms");
        notification2.notifyUser(); // Output: SMS notification sent.

        Notifications notification3 = NotificationFactory.createNotification("push");
        notification3.notifyUser(); // Output: Push notification sent.
    }
}

