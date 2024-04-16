package seabattles.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarshipCreateDto {
    public enum WARSHIP_CLASS {AIRCRAFT_CARRIER, BATTLECRUISER, BATTLESHIP, DESTROYER, SUBMARINE, TRANSPORT, MERCHANT}

    private String warshipName;

    private WARSHIP_CLASS warshipClass;

    private Date commissionDate;
}
