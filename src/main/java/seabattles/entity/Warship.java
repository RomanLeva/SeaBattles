package seabattles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "warships")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Warship {
    enum WARSHIP_CLASS {AIRCRAFT_CARRIER, BATTLECRUISER, BATTLESHIP, DESTROYER, SUBMARINE, TRANSPORT, MERCHANT}

    @Id
    @Column(name = "warship_name")
    private String warshipName;

    @Enumerated(EnumType.STRING)
    @Column(name = "warship_class")
    private WARSHIP_CLASS warshipClass;

    @Column(name = "commission")
    private Date commissionDate;

    @Column(name = "decommission")
    private Date decommissionDate;
}
