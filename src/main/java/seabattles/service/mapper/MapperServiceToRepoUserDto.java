package seabattles.service.mapper;

import seabattles.entity.User;
import org.springframework.stereotype.Component;
import seabattles.service.dto.UserAuthDto;
import seabattles.service.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperServiceToRepoUserDto {

    public List<UserDto> mapUserToDtoList(List<User> users){
        return users.stream().map(user -> new UserDto(user.getLogin())).collect(Collectors.toList());
    }

    public UserDto mapUserToDto(User user){
        return new UserDto(user.getLogin());
    }

    public User mapDtoToUser(UserDto userDto){
        User user = new User();
        user.setLogin(userDto.getLogin());
        return user;
    }

    public User mapUserAuthDtoToUser(UserAuthDto userAuthDto){
        User user = new User();
        user.setLogin(userAuthDto.getLogin());
        user.setPassword(userAuthDto.getPassword());
        return user;
    }
}
