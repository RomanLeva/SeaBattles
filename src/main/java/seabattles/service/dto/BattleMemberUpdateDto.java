package seabattles.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BattleMemberUpdateDto {
    public enum STATUS {SURVIVED, DESTROYED, DAMAGED}

    private WarshipDto oldWarship;
    private BattleDto oldBattle;

    private WarshipDto newWarship;
    private BattleDto newBattle;
    private STATUS newStatus;
}
