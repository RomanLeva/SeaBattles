package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Country {
    public enum SIDE {ALLIES, NAZI}

    @Id
    private Long id;

    @NaturalId
    private String countryName;

    @Enumerated(EnumType.STRING)
    private SIDE side;
}
