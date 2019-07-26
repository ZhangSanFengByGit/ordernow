package ordernow.service;

import ordernow.domain.ManagerAccount;
import ordernow.domain.ManagerAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterManager {
    @Autowired
    private ManagerAccountRepo managerAccountRepo;

    public void register(String name, String phoneNumber, String badgeNumber, String password){
        Optional<ManagerAccount> pre = managerAccountRepo.findByPhoneNumber(phoneNumber);
        if (pre.isPresent()){
            managerAccountRepo.deleteById(pre.get().id);
        }// to delete the previous one if found duplicates.
        managerAccountRepo.save(new ManagerAccount(name,phoneNumber,badgeNumber,password));
    }
}
