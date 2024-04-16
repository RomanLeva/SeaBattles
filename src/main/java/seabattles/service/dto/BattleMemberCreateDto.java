package seabattles.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BattleMemberCreateDto {
    public enum STATUS {SURVIVED, DESTROYED, DAMAGED}

    private WarshipDto warship;
    private BattleDto battle;
//    private String warshipName;
//    private String battleName;
    private STATUS status;
}
