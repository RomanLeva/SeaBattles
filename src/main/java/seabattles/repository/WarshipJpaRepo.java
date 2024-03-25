package seabattles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seabattles.entity.Warship;

@Repository
public interface WarshipJpaRepo extends JpaRepository<Warship, Long> {
    Warship findByWarshipName(String warshipName);
}
