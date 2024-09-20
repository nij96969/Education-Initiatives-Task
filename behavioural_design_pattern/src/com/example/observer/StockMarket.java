package behavioural_design_pattern.src.com.example.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StockMarket {
    private Logger logger = Logger.getLogger(StockMarket.class.getName());
    private List<StockObserver> observers; //List<StockObserver> to store any objects that implement the StockObserver interface.
    private String stockSymbol;
    private float stockPrice;
    
    public StockMarket(){
        //Create observers List
        observers = new ArrayList<>();
    }

    public boolean isObserverRegistered(StockObserver observer){
        if(observers.contains(observer)) return true;
        return false;
    }

    public void addObserver(StockObserver observer) {
        if (observer == null) {
            logger.severe("Failed to add observer: Observer cannot be null" + "\n");
            return;
        }
        if (isObserverRegistered(observer)) {
            logger.warning("Failed to add observer: Observer is already registered" + "\n");
            return;
        }
    
        observers.add(observer);
        logger.info("Observer successfully added to the system");
    }
    
    public void removeObserver(StockObserver observer) {
        if (observer == null) {
            logger.severe("Failed to remove observer: Observer cannot be null" + "\n");
            return;
        }
        if (!isObserverRegistered(observer)) {
            logger.warning("Failed to remove observer: Observer not found" + "\n");
            return;
        }
    
        observers.remove(observer);
        logger.info("Observer successfully removed from the system" + "\n");
    }    

    public void notifyObservers(){
        for(StockObserver observer : observers){
            //Update stockPrice for every Observer
            try{
                observer.update(stockSymbol , stockPrice);
            }
            catch(Exception e){
                logger.warning("Exception in notify observer" + e.getMessage() + "\n");
            }
        }
    }

    public void setStockPrice(String stockSymbol , float stockPrice){
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            logger.severe("Stock symbol cannot be null or empty");
            return;
        }
        if (stockPrice < 0) {
            logger.warning("Stock price cannot be negative");
            return;
        }
        
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        notifyObservers();
    }   
}
