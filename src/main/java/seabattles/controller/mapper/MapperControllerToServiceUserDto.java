package seabattles.controller.mapper;

import seabattles.controller.dto.AuthUserDto;
import seabattles.controller.dto.NewUserDto;
import seabattles.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seabattles.service.dto.UserAuthDto;

@Component
@RequiredArgsConstructor
public class MapperControllerToServiceUserDto {
    @Autowired
    private final ModelMapper modelMapper;

    public User mapNewUserDtoToUser(NewUserDto newUserDto){
        User user = new User();
        modelMapper.map(newUserDto, user);
        return user;
    }

    public UserAuthDto mapControllerAuthToServiceAuthDto(AuthUserDto authUserDto){
        UserAuthDto userAuthDto = new UserAuthDto();
        modelMapper.map(authUserDto, userAuthDto);
        return userAuthDto;
    }
}
