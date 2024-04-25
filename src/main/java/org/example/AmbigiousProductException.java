package org.example;

import java.util.List;

public class AmbigiousProductException extends RuntimeException{
    public AmbigiousProductException(List<String> info) {
        super("AmbitiousProduct: "+ info);
    }
}
