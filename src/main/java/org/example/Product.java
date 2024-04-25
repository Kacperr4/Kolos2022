package org.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Product {
    private String name;

    public String getName() {
        return name;
    }

    public Product(String name) {
        this.name = name;
    }

    public abstract double getPrice(int year,int month);

    private static ArrayList<Product> productsList = new ArrayList<>();

    public static String countProducts(){
        return "Products: " + String.valueOf(productsList.size());
    }
    public static void clearProduct(){
        productsList.clear();
    }
    //do 3

    public static void addProducts(Function<Path, ? extends Product> fromCsvFunction, Path directoryPath) {
        try {
            Files.list(directoryPath)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(path -> productsList.add(fromCsvFunction.apply(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Product getProducts(String prefix){
        List<String> products = new ArrayList<>();
        for(Product x: productsList ){
            if(x.getName().startsWith(prefix)){
                products.add(x.getName());
            }
        }
        if(products.isEmpty()){
            throw new IndexOutOfBoundsException("Prefiks nie wskazuje na zaden obiekt: " + prefix);

        }else if(products.size() == 1){
            return productsList.stream()
                    .filter(x -> x.getName().equals(products.getFirst())).findFirst().orElse(null);

        }else{
            throw new AmbigiousProductException(products);
        }


    }
}

//
//