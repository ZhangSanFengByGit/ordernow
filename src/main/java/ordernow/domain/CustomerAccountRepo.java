package ordernow.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerAccountRepo extends MongoRepository<CustomerAccount,String> {
    public Optional<CustomerAccount> findByPhoneNumber(String phoneNumber);
}
