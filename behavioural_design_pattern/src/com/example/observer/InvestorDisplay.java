package behavioural_design_pattern.src.com.example.observer;

import java.util.logging.Logger;

public class InvestorDisplay implements StockObserver{
    private Logger logger = Logger.getLogger(StockObserver.class.getName());
    private String stockSymbol;
    private float stockPrice;    

    @Override
    public void update(String stockSymbol , float stockPrice){
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        display();
    }

    public void display(){
        logger.info("Stock Symbol :: " + stockSymbol + " Current Stock Price :: " + stockPrice + "\n");
    }
}
