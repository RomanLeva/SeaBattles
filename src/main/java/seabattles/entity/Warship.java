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
    @Setter(AccessLevel.NONE)
    private Long id;

    @NaturalId
    @Setter(AccessLevel.NONE)
    private String warshipName;

    @Enumerated(EnumType.STRING)
    private WARSHIP_CLASS warshipClass;

    private Date commissionDate;

    private Date decommissionDate;

}
