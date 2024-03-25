package seabattles.controller.mapper;

import seabattles.controller.dto.UserAuthDto;
import seabattles.controller.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperControllerToServiceUserDto {
    @Autowired
    private final ModelMapper modelMapper;

    public seabattles.service.dto.UserRegisterDto mapUserRegControllerDtoToUserServiceDto(UserRegisterDto controllerDto){
        seabattles.service.dto.UserRegisterDto serviceDto = new seabattles.service.dto.UserRegisterDto();
        modelMapper.map(controllerDto, serviceDto);
        return serviceDto;
    }

    public seabattles.service.dto.UserAuthDto mapUserControllerDtoToAuthServiceDto(UserAuthDto controllerDto){
        seabattles.service.dto.UserAuthDto serviceDto = new seabattles.service.dto.UserAuthDto();
        modelMapper.map(controllerDto, serviceDto);
        return serviceDto;
    }
}
