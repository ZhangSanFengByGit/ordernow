package ordernow.domain;

import javax.persistence.*;

@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;
    public String price;
    public String info;

    public Cuisine(){super();}

    public Cuisine(Cuisine cuisine){
        super();
        this.name = cuisine.name;
        this.price = cuisine.price;
        this.info = cuisine.info;
    }

    public Cuisine(String name, String price){
        super();
        this.name = name;
        this.price = price;
        this.info = "";
    }

    public Cuisine(String name, String price, String info){
        super();
        this.name = name;
        this.price = price;
        this.info = info;
    }

    @Override
    public String toString(){
        String ret = "name:"+name+", price:"+price
                        +", info:"+info;
        return ret;
    }
}
