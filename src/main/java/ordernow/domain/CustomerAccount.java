package ordernow.domain;

import javax.persistence.*;

@Entity
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String phoneNumber;
    public String password;

    public CustomerAccount(){super();}

    public CustomerAccount(String phoneNumber, String password){
        super();
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString(){
        String ret = "phoneNumbe:"+phoneNumber+", password:"+password;
        return ret;
    }
}
