package ordernow.domain;

import org.springframework.data.annotation.Id;

public class ManagerAccount {
    @Id
    public String id;

    public String name;
    public String phoneNumber;
    public String badgeNumber;
    public String password;

    public ManagerAccount(){}

    public ManagerAccount(String name, String phoneNumber, String badgeNumber, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.badgeNumber = badgeNumber;
        this.password = password;
    }
}
