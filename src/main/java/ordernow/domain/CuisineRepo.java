package ordernow.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CuisineRepo extends MongoRepository<Cuisine,String> {
    public Optional<Cuisine> findByName(String name);
    public Optional<List<Cuisine>> findByPrice(String price);
}
