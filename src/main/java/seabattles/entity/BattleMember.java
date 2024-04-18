package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "battle_members", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"warship_id", "battle_id"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BattleMember {
    public enum STATUS {SURVIVED, DESTROYED, DAMAGED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "warship_id")
    private Warship warship;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS status;
}
