package ordernow.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ManagerAccountRepo extends MongoRepository<ManagerAccount, String> {
    public Optional<ManagerAccount> findByPhoneNumber(String phoneNumber);
    public Optional<ManagerAccount> findByBadgeNumber(String BadgeNumber);
    public Optional<List<ManagerAccount>> findByName(String Name);
}
