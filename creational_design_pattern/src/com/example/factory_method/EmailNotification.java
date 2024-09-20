package creational_design_pattern.src.com.example.factory_method;

import java.util.logging.Logger;
public class EmailNotification implements Notifications{
    
    private Logger logger = Logger.getLogger(EmailNotification.class.getName());

    @Override
    public void notifyUser(){
        logger.info("Email Notification sent ");        
    }
}
