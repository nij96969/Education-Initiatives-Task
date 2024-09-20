package behavioural_design_pattern.src.com.example.observer;

public class MainDemoStockMarket {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        InvestorDisplay investor1 = new InvestorDisplay();
        InvestorDisplay investor2 = new InvestorDisplay();

        stockMarket.addObserver(investor1);
        stockMarket.addObserver(investor2);
        stockMarket.addObserver(investor2);

        stockMarket.setStockPrice("Apple", 145.30f);
        stockMarket.setStockPrice("Apple", 2750.00f);

        stockMarket.removeObserver(investor2);
        stockMarket.removeObserver(investor2);

        stockMarket.setStockPrice("Apple", 2000.00f);
    }
}
