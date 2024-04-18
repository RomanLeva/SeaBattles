package seabattles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seabattles.entity.Battle;
import seabattles.entity.BattleMember;
import seabattles.entity.Warship;

@Repository
public interface BattleMemberJpaRepo extends JpaRepository<BattleMember, Long> {
    BattleMember findByWarshipAndBattle(Warship warship, Battle battle);
}
