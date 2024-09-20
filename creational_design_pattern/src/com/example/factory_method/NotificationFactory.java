package creational_design_pattern.src.com.example.factory_method;

public class NotificationFactory {
    public static Notifications createNotification(String type) {
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type.");
        }
    }
}

