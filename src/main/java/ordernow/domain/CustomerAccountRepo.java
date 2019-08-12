package ordernow.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAccountRepo extends JpaRepository<CustomerAccount,Long> {
    public Optional<CustomerAccount> findByPhoneNumber(String phoneNumber);
}
