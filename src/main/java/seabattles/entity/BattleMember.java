package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "battle_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BattleMember {
    public enum STATUS {SURVIVED, DESTROYED, WOUNDED}

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    private Warship warship;

    @OneToOne
    private Battle battle;

    @Enumerated(EnumType.STRING)
    private STATUS result;
}
