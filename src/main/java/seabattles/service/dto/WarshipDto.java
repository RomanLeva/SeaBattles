package seabattles.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarshipDto {
   private String warshipName;
   private String warshipClass;
   private String warshipCommissionDate;
   private String warshipDecommissionDate;
}
