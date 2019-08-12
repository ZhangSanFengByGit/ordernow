package ordernow.service;

import ordernow.domain.CustomerAccount;
import ordernow.domain.CustomerAccountRepo;
import ordernow.domain.ManagerAccount;
import ordernow.domain.ManagerAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {
    @Autowired
    private CustomerAccountRepo customerAccountRepo;
    @Autowired
    private ManagerAccountRepo managerAccountRepo;

    public boolean validateCustomer(String phoneNumber, String password){
        Optional<CustomerAccount> c = customerAccountRepo.findByPhoneNumber(phoneNumber);
        if (c.isPresent()){
            if (c.get().password.equals(password)) return true;
        }
        return false;
    }

    public boolean validateManager(String phoneNumber, String password){
        Optional<ManagerAccount> m = managerAccountRepo.findByPhoneNumber(phoneNumber);
        if (m.isPresent()){
            if (m.get().password.equals(password)) return true;
        }
        return false;
    }
}
