package ordernow.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuisineRepo extends JpaRepository<Cuisine,Long> {
    public Optional<Cuisine> findByName(String name);
    public Optional<List<Cuisine>> findByPrice(String price);
}
