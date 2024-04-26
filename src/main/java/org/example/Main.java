package org.example;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        NonFoodProduct product = NonFoodProduct.fromCsv(Paths.get("src", "main", "resources", "data", "nonfood", "benzyna.csv"));
        System.out.println("Produkt: " + product.getName());
        System.out.println("Cena 06.2015: " + product.getPrice(2015, 6));


        FoodProduct foodProduct = FoodProduct.fromCsv(Paths.get("src", "main", "resources", "data", "food", "buraki.csv"));
        System.out.println("produkt: " + foodProduct.getName());
        System.out.println("Srednia Cena 06.2015: " + foodProduct.getPrice(2015, 6));
        System.out.println("wojewodztwo: " + foodProduct.getPrice(2015, 6, "LUBELSKIE"));


        Product.addProducts(NonFoodProduct::fromCsv, Paths.get("src","main","resources","data","nonfood"));
        Product.addProducts(FoodProduct::fromCsv, Paths.get("src","main","resources","data","food"));

        System.out.println(Product.countProducts());
        try {
            Product.getProducts("Abc");
        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        Product p = Product.getProducts("Bu");
        System.out.println(p.getName());

        try {
            Product.getProducts("Ja");
        }catch(AmbigiousProductException e){
            System.out.println(e.getMessage());
        }


        Cart cart = new Cart();
        cart.addProduct(Product.getProducts("Bu"),3);
        cart.addProduct((Product.getProducts("Jaja")),5);

        System.out.println(cart.getPrice(2020,4));
        System.out.println(cart.getInflation(2020,1,2022,1));
    }



    /*https://github.com/Kacperr4/Kolos2022
    https://github.com/kdmitruk
    https://github.com/mdpiekarz/java_lab_2024
    https://github.com/michaldziuba03/java
    https://github.com/Innocenttt3/Semestr2-OOP
    https://mvnrepository.com/artifact/junit/junit/4.13.1
    * */
}