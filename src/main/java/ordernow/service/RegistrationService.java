package ordernow.service;

import ordernow.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private CuisineRepo cuisineRepo;
    @Autowired
    private ManagerAccountRepo managerAccountRepo;
    @Autowired
    private CustomerAccountRepo customerAccountRepo;

    public boolean registerCuisine(Cuisine cuisine){
        Optional<Cuisine> pre = cuisineRepo.findByName(cuisine.name);
        if (pre.isPresent()){
            return false;
        }
        cuisineRepo.save(new Cuisine(cuisine));
        return true;
    }

    public boolean registerCustomer(String phoneNumber, String password){
        Optional<CustomerAccount> pre = customerAccountRepo.findByPhoneNumber(phoneNumber);
        if (pre.isPresent()){
            return false;
        }
        customerAccountRepo.save(new CustomerAccount(phoneNumber,password));
        return true;
    }

    public boolean registerManager(String name, String phoneNumber, String badgeNumber, String password){
        Optional<ManagerAccount> pre = managerAccountRepo.findByPhoneNumber(phoneNumber);
        if (pre.isPresent()){
            return false;
        }// to delete the previous one if found duplicates.
        managerAccountRepo.save(new ManagerAccount(name,phoneNumber,badgeNumber,password));
        return true;
    }

}
