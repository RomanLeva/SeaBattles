package seabattles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seabattles.entity.BattleMember;

@Repository
public interface BattleMemberJpaRepo extends JpaRepository<BattleMember, Long> {
}
