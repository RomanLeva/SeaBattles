package seabattles.service.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seabattles.entity.Warship;
import seabattles.service.dto.WarshipDto;
import seabattles.service.dto.WarshipRegisterDto;

@AllArgsConstructor
@Component
public class MapperServiceToRepositoryWarshipDto {
    @Autowired
    private final ModelMapper modelMapper;

    public WarshipDto mapWarshipToDto(Warship warship) {
        WarshipDto warshipDto = new WarshipDto();
        String decommissionDate;

        if (! warship.getDecommissionDate().toString().isEmpty()){
            decommissionDate = warship.getDecommissionDate().toString();
        } else {
            decommissionDate = "In operation";
        }
        modelMapper.map(warship, warshipDto);
        warshipDto.setWarshipDecommissionDate(decommissionDate);
        return warshipDto;
    }

    public Warship mapRegisterToWarship(WarshipRegisterDto warshipRegisterDto) {
        Warship warship = new Warship();
        modelMapper.map(warshipRegisterDto, warship);
        return warship;
    }
}
