package org.example;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int amount){
        products.put(product, products.getOrDefault(product,0) + amount);
    }

    public double getPrice(int year, int month){
        double priceOfCart = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet()){
            Product p = entry.getKey();
            int amount = entry.getValue();
            priceOfCart += p.getPrice(year,month) * amount;
        }
        return priceOfCart;
    }

    public double getInflation(int year1, int month1, int year2, int month2){
        double price1 = getPrice(year1, month1);
        double price2 = getPrice(year2,month2);
        int months = (year2 - year1)*12 + month2 - month1;

        return Math.round(((price2 - price1)/price1 *100/ months * 12)*100)/100.0;
    }
}
