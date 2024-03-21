package seabattles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "battles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Battle {
    @Id
    @Column(name = "battle_name")
    private String battleName;

    @Column(name = "battle_date")
    private Date battleDate;
}
