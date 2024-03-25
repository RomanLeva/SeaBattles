package seabattles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seabattles.entity.Battle;

@Repository
public interface BattleJpaRepo extends JpaRepository<Battle, Long> {
}
