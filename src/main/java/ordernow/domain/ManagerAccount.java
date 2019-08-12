package ordernow.domain;

import javax.persistence.*;

@Entity
public class ManagerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;
    public String phoneNumber;
    public String badgeNumber;
    public String password;

    public ManagerAccount(){super();}

    public ManagerAccount(String phoneNumber, String password){
        super();
        this.name = "";
        this.phoneNumber = phoneNumber;
        this.badgeNumber = "";
        this.password = password;
    }

    public ManagerAccount(String name, String phoneNumber, String badgeNumber, String password){
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.badgeNumber = badgeNumber;
        this.password = password;
    }

    @Override
    public String toString(){
        String ret = "name:"+name+", phoneNumbe:"+phoneNumber+
                ", badgeNumber:"+badgeNumber+", password:"+password;
        return ret;
    }
}
