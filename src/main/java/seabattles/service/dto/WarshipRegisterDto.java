package seabattles.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class WarshipRegisterDto {
    public enum WARSHIP_CLASS {AIRCRAFT_CARRIER, BATTLECRUISER, BATTLESHIP, DESTROYER, SUBMARINE, TRANSPORT, MERCHANT}

    private String warshipName;

    private WARSHIP_CLASS warshipClass;

    private Date commissionDate;
}
