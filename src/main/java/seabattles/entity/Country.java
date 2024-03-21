package seabattles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country {
    enum SIDE {ALLIES, NAZI}

    @Id
    @Column(name = "country_name")
    private String countryName;

    @Column(name = "side")
    @Enumerated(EnumType.STRING)
    private SIDE side;
}
