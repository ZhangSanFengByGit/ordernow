package ordernow.domain;

import org.springframework.data.annotation.Id;

public class Cuisine {
    @Id
    public String id;

    public String name;
    public String price;
    public String info;

    public Cuisine(){}

    public Cuisine(String name, String price, String info){
        this.name = name;
        this.price = price;
        this.info = info;
    }
}
