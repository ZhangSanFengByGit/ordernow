package ordernow.service;

import ordernow.domain.Cuisine;
import ordernow.domain.CuisineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterCuisine {
    @Autowired
    private CuisineRepo cuisineRepo;

    public void register(String name, String price, String info){
        Optional<Cuisine> pre = cuisineRepo.findByName(name);
        if (pre.isPresent()){
            cuisineRepo.deleteById(pre.get().id);
        }
        cuisineRepo.save(new Cuisine(name,price,info));
    }
}
