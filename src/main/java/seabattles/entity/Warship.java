package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;

@Entity
@Table(name = "warships")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Warship {
    public enum WARSHIP_CLASS {AIRCRAFT_CARRIER, BATTLECRUISER, BATTLESHIP, DESTROYER, SUBMARINE, TRANSPORT, MERCHANT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NaturalId
    @Column(name = "warship_name", unique = true)
    private String warshipName;

    @Enumerated(EnumType.STRING)
    @Column(name = "warship_class")
    private WARSHIP_CLASS warshipClass;

    @Column(name = "commission_date")
    private Date commissionDate;

    @Column(name = "decommission_date")
    private Date decommissionDate;

}
