package dikov;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.5, 10);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.49, 36);
        stockList.addStock(temp);

        temp = new StockItem("chips", 2.79, 50);
        stockList.addStock(temp);

        System.out.println(stockList.toString());

        Basket emosBasket = new Basket("Emo");
        //sellItem(emosBasket, "cup", 1);
       // sellItem(emosBasket, "chips", 2);

        Basket basket = new Basket("customer");
        sellItem(basket,"cup",100);
        sellItem(basket, "juice",5 );
        removeItem(basket,"cup",1);
        System.out.println(basket);

        removeItem(emosBasket,"cup",1);


        if (sellItem(emosBasket, "juice", 7) != 1) {
            System.out.println("There are no more juices in stock.");
        }

        sellItem(emosBasket, "car", 2);
        sellItem(emosBasket, "cake", 3);
        sellItem(emosBasket, "bread", 3);
        sellItem(emosBasket, "cake", 2);
        System.out.println(emosBasket);
        System.out.println(stockList);

        for (Map.Entry<String, Double> price : stockList.priceList().entrySet()) {
            System.out.println(price.getKey() + " cost " + price.getValue() + "lv.");
        }
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
