package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FoodProduct extends Product{

    private Map<String, Double[]> pricesByProvince;

    private FoodProduct(String name, Map<String, Double[]> pricesByProvince) {
        super(name);
        this.pricesByProvince = pricesByProvince;
    }

    @Override
    public double getPrice(int year, int month) {
        if( year < 2010 || year > 2022 || month < 1 || month > 12 || (year == 2022 && month > 3)){
            throw new IndexOutOfBoundsException("Nie zmiesciles sie w przedziale czasu");
        }

        double totalPrice = 0;
        int count = 0;

        for(Double[] prices: pricesByProvince.values()){
            totalPrice+= prices[(year - 2010) * 12 + month - 1];
            count++;
        }


        return Math.round((totalPrice / count) * 100) / 100.0;
    }

    public double getPrice(int year, int month, String province){

        if(!pricesByProvince.containsKey(province) || year < 2010 || year > 2022 || month < 1 || month > 12 || (year == 2022 && month > 3)){
            throw new IndexOutOfBoundsException("Nie zmiesciles sie w przedziale czasu");
        }

        Double[] prices = pricesByProvince.get(province);

        return prices[(year - 2010) * 12 + month - 1];

    }
    public static FoodProduct fromCsv(Path path){
        String name;
        Map<String, Double[]> pricesByProvince = new HashMap<>();

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine(); // odczytuję pierwszą linię i zapisuję ją jako nazwa
            scanner.nextLine();  // pomijam drugą linię z nagłówkiem tabeli
            while(scanner.hasNextLine()){
                String[] parts = scanner.nextLine().split(";");
                String province = parts[0].trim().toUpperCase();
                Double[] prices = new Double[parts.length - 1];
                for(int i = 1; i < parts.length; i++){
                    prices[i-1] = Double.parseDouble(parts[i].replace(",","."));
                }
                pricesByProvince.put(province, prices);
            }

            scanner.close();

            return new FoodProduct(name, pricesByProvince);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
