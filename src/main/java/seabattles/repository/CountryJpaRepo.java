package seabattles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seabattles.entity.Country;

@Repository
public interface CountryJpaRepo extends JpaRepository<Country, Long> {
}
