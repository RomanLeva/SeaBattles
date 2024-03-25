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
public class Battle {

    @Id
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String battleName;

    private Date battleDate;
}
