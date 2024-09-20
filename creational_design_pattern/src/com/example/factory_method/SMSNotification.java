package creational_design_pattern.src.com.example.factory_method;

import java.util.logging.Logger;

public class SMSNotification implements Notifications{
    
    private Logger logger = Logger.getLogger(SMSNotification.class.getName());

    @Override
    public void notifyUser(){
        logger.info("SMS Notification sent ");        
    }
}
