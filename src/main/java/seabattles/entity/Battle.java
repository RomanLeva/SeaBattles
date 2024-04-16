package seabattles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;

@Entity
@Table(name = "battles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NaturalId
    @Column(name = "battle_name", unique = true)
    private String battleName;

    @Column(name = "battle_date")
    private Date battleDate;
}
