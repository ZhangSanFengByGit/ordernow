package ordernow.domain;

import org.springframework.data.annotation.Id;

public class CustomerAccount {
    @Id
    public String id;

    public String phoneNumber;
    public String password;

    public CustomerAccount(){}

    public CustomerAccount(String phoneNumber, String password){
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
