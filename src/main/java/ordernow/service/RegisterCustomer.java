package ordernow.service;

import ordernow.domain.CustomerAccount;
import ordernow.domain.CustomerAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterCustomer {
    @Autowired
    private CustomerAccountRepo customerAccountRepo;

    public void register(String phoneNumber, String password){
        Optional<CustomerAccount> pre = customerAccountRepo.findByPhoneNumber(phoneNumber);
        if (pre.isPresent()){
            customerAccountRepo.deleteById(pre.get().id);
        }
        customerAccountRepo.save(new CustomerAccount(phoneNumber,password));
    }
}
