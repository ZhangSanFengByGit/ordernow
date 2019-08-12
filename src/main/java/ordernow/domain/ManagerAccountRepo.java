package ordernow.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerAccountRepo extends JpaRepository<ManagerAccount, Long> {
    public Optional<ManagerAccount> findByPhoneNumber(String phoneNumber);
    public Optional<ManagerAccount> findByBadgeNumber(String BadgeNumber);
    public Optional<List<ManagerAccount>> findByName(String Name);
}
