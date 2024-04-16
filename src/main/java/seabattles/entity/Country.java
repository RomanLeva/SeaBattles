package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country {
    public enum SIDE {ALLIES, NAZI}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NaturalId
    @Column(name = "country_name")
    private String countryName;

    @Column(name = "side")
    @Enumerated(EnumType.STRING)
    private SIDE side;
}
