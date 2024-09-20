package creational_design_pattern.src.com.example.factory_method;

import java.util.logging.Logger;

public class PushNotification implements Notifications{
        private Logger logger = Logger.getLogger(PushNotification.class.getName());

    @Override
    public void notifyUser(){
        logger.info("Push Notification sent ");        
    }
}
